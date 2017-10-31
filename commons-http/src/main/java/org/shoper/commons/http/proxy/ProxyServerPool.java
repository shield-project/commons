package org.shoper.commons.http.proxy;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by ShawnShoper on 16/8/9.
 */
public class ProxyServerPool {
    //	//代理服务器队列
//	private static final PriorityBlockingQueue<ProxyServerStatus> proxyServers = new PriorityBlockingQueue<>();
    //代理服务器状态监测
    private static final Map<String, ProxyServerStatus> proxyServers = new ConcurrentHashMap<>();

    /**
     * Get proxyServer list and sort it
     *
     * @return
     */
    public static List<String> getProxyServerHosts() {
        if (proxyServers.isEmpty())
            return null;
        List<String> list = new ArrayList<>(proxyServers.size());
        proxyServers.values().stream().forEach(pss -> list.add(pss.getProxyServer().getHost()));
        sortIP(list);
        return list;
    }

    /**
     * Put proxy server into pool
     *
     * @param proxyServer
     */
    public static void putProxyServer(ProxyServer proxyServer) {
        ProxyServerStatus pss = new ProxyServerStatus(proxyServer);
        if (!proxyServers.containsKey(proxyServer.getHost()))
            proxyServers.put(pss.getId(), pss);
    }


    public static void putProxyServer(List<ProxyServer> proxyServers) {
        proxyServers.forEach(ps -> putProxyServer(ps));
    }

    /**
     * support format host  port  username    password proxyType
     *
     * @param file
     */
    public synchronized static void importProxyServer(File file, Charset charset) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(file);
        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        inputStream,
                        charset
                ));
        br.lines().parallel().distinct().map(line ->
                JSONObject.parseObject(line)
        ).forEach(jsonObj ->
                putProxyServer(jsonObj.toJavaObject(jsonObj, ProxyServer.class))
        );
    }

    /**
     * 移除一个代理服务器
     *
     * @param id
     */
    public static void removeProxyServer(String id) {
        if (proxyServers.containsKey(id))
            proxyServers.remove(id);
    }


    /**
     * 返还 proxy server并获取一个新的
     *
     * @return
     * @throws InterruptedException
     */
    public static ProxyServer getProxyServer() {
        if (proxyServers.isEmpty()) return null;
        int random = new Random().nextInt(proxyServers.size());
        ProxyServerStatus proxyServerStatus = proxyServers.values().stream().collect(Collectors.toList()).get(random);
        proxyServerStatus.getUsage().incrementAndGet();
        return proxyServerStatus.getProxyServer();

    }

    /**
     * 返还 proxy server
     *
     * @param proxyServer proxy server info
     * @param success     是否成功
     */
    public static void returnProxyServer(ProxyServer proxyServer, boolean success) {
        //如果是 one in one 模式的那么修改代理状态, all in one 需要修改
        if (Objects.nonNull(proxyServer)) {
            if (proxyServers.containsKey(proxyServer.getHost())) {
                ProxyServerStatus proxyServerStatus = proxyServers.get(proxyServer.getHost());
                if (!success)
                    proxyServerStatus.getFail().incrementAndGet();
            }
        }
    }

    /**
     * sort proxy list.
     *
     * @param list
     */
    private static void sortIP(List<String> list) {
        Collections.sort(
                list,
                (o1, o2) -> {
                    String[] a1 = o1.split("\\.");
                    String[] a2 = o2.split("\\.");
                    for (int i = 0; i < 4; i++) {
                        char[] a11 = a1[i].toCharArray();
                        char[] a22 = a2[i].toCharArray();
                        int maxLoop = 3;
                        if (a11.length > a22.length)
                            maxLoop = a22.length;
                        else if (a11.length < a22.length)
                            maxLoop = a11.length;
                        if (a11.length == a22.length) maxLoop = a11.length;
                        for (int j = 0; j < maxLoop; j++)
                            if (a11.length < a22.length)
                                return -1;
                            else if (a11.length == a22.length) {
                                if (a11[j] < a22[j])
                                    return -1;
                                else if (a11[j] > a22[j])
                                    return 1;
                            }
                    }
                    return 0;
                }
        );
    }


}
