package org.shoper.commons.http;

import org.shoper.commons.http.exception.HttpClientException;
import org.shoper.commons.http.proxy.ProxyServerPool;
import org.junit.Test;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2017/1/23 0023.
 */
public class HttpClientTest {
    @Test
    public void httpClientTest() throws MalformedURLException, InterruptedException, HttpClientException, TimeoutException {
        HttpClient httpClient = HttpClientBuilder.custom().setUrl("http://weibo.com/p/aj/v6/mblog/mbloglist?ajwvr=6&domain=100505&profile_ftype=1&is_all=1&pagebar=0&pl_name=Pl_Official_MyProfileFeed__24&id=1005053627755783&script_uri=/u/3627755783&feed_type=0&page=1&pre_page=1&domain_op=100505&__rnd=1470627772452").build();
//		httpClient.setCookies("SINAGLOBAL=4466064745352.929.1475033718231; wvr=6; YF-Ugrow-G0=1eba44dbebf62c27ae66e16d40e02964; SCF=Ah9Z2M30c4B9_6Thfkz--Vh0Lr5sJgOXdV44r8enK_f2lKYdoKOhVPwhLWEx82WTwP83FbhmvFWGfNL-SljN_Yw.; SUB=_2A2566YyeDeTxGedG6VYS9i3LzT6IHXVZnvlWrDV8PUNbmtBeLXPHkW-O44Qf_9hQgtQUtkULL_-K8kpF5A..; SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9WhdPz3lCecbA_hp6RKPxGfu5JpX5KMhUgL.Fo2ReoB0SoeNSoz2dJLoIEqLxK-LBK2L1--LxKML12qLB-B_TCH8SCHFxbHWSEH8Sb-RSF-ReBtt; SUHB=010XX2a6jXFhTf; ALF=1506750542; SSOLoginState=1475214542; YF-V5-G0=8a3c37d39afd53b5f9eb3c8fb1874eec; _s_tentry=login.sina.com.cn; Apache=9010673075615.451.1475214562259; ULV=1475214562281:3:3:3:9010673075615.451.1475214562259:1475159910047; YF-Page-G0=b9004652c3bb1711215bacc0d9b6f2b5; UOR=,,login.sina.com.cn");
        System.out.println(httpClient.doGet());
    }

    @Test
    public void httpClientTest1() throws MalformedURLException, InterruptedException, HttpClientException, TimeoutException, FileNotFoundException {
        ProxyServerPool.importProxyServer(new File("src/test/resources/proxy.json"), Charset.forName("utf-8"));
        String url = "http://product.weather.com.cn/alarm/grepalarm.php?areaid=" + URLEncoder.encode("[0-9]{5,7}") + "&_=1488879475359";
        HttpClient build = HttpClientBuilder.custom().setCharset("utf-8").setUrl(url).build();
        Map<String, String> header = new HashMap<>();
//        header.put("Host","product.weather.com.cn");
        header.put("Referer", "http://www.weather.com.cn/alarm/warninglist.shtml");
//        header.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
//        header.put("Cookies","vjuids=3bc3c179d.15aa7b9996a.0.26114cbbe3c75; __auc=f9b03e6015aa7ba5fff81b000bb; Hm_lvt_080dabacb001ad3dc8b9b9049b36d43b=1488872643,1488879448; Hm_lpvt_080dabacb001ad3dc8b9b9049b36d43b=1488879454; vjlast=1488872643.1488872643.30; f_city=%E5%8C%97%E4%BA%AC%7C101010100%7C%2C%E4%B8%8A%E6%B5%B7%7C101020100%7C%2C%E5%B9%BF%E5%B7%9E%7C101280101%7C");
//        header.put("Accept","*/*");
//        header.put("Accept-Encoding","gzip, deflate, sdch");
//        header.put("Accept-Language","en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4");
        build.setRequestHeader(header);
        System.out.println(build.doGet());
    }

    @Test
    public void httpClientTestt() throws MalformedURLException, HttpClientException, InterruptedException, TimeoutException {
        String data = "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";

        Map<String, String> map = new HashMap<>();
        map.put("pic", data);
        Map<String, String> rh = new HashMap<>();
        rh.put("Content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
        HttpClient build = HttpClientBuilder.custom().setUrl("http://192.168.0.13:8000").setCharset("utf-8").setFormDatas(map).build();
        build.setRequestHeader(rh);
        System.out.println(build.post());
    }

    @Test
    public void httpURL() throws IOException, InterruptedException {
        String data = "pic=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";
//        URL url = new URL("http://192.168.0.13:8000");
//        URLConnection urlConnection = url.openConnection();
//        urlConnection.setDoInput(true);
//        urlConnection.setDoOutput(true);
//        OutputStream outputStream = urlConnection.getOutputStream();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("POST http://192.168.0.13:8000 HTTP/1.1" + "\n")
                .append("Accept: text/html" + "\n")
                .append("Accept-Language: en-us,zh-cn;q=0.5 " + "\n")
                .append("Content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW" + "\n")
                .append("User-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1) " + "\n")
                .append("Content-length: " + data.getBytes().length + "\n")
                .append("\\r\\n\n")
                .append("----WebKitFormBoundary7MA4YWxkTrZu0gW\n")
//                .append("Content-Disposition: form-data;name='pic'\n")
//                .append("Content-Type: application/text \n")
                .append("\n")
                .append(data + "\n")
                .append("----WebKitFormBoundary7MA4YWxkTrZu0gW--\n");
        Socket socket = new Socket("192.168.0.13", 8000);
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter pw = new PrintWriter(outputStream);
        pw.write(stringBuilder.toString());
        pw.flush();
        socket.shutdownOutput();
        TimeUnit.SECONDS.sleep(50);
    }
}

