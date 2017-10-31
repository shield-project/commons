package org.shoper.commons.core;

import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class StringUtilTests {
    @Test
    public void reverse() {
        String world = "saasdsadsadsadanjnj12bjbasldnLKNSdlknasjkbe1kl2b312k3b 12nk4 13b21l312saasdsadsadsadanjnj12bjbasldnLKNSdlknasjkbe1kl2b312k3b 12nk4 13b21l312saasdsadsadsadanjnj12bjbasldnLKNSdlknasjkbe1kl2b312k3b 12nk4 13b21l312saasdsadsadsadanjnj12bjbasldnLKNSdlknasjkbe1kl2b312k3b 12nk4 13b21l312saasdsadsadsadanjnj12bjbasldnLKNSdlknasjkbe1kl2b312k3b 12nk4 13b21l312saasdsadsadsadanjnj12bjbasldnLKNSdlknasjkbe1kl2b312k3b 12nk4 13b21l312saasdsadsadsadanjnj12bjbasldnLKNSdlknasjkbe1kl2b312k3b 12nk4 13b21l312saasdsadsadsadanjnj12bjbasldnLKNSdlknasjkbe1kl2b312k3b 12nk4 13b21l312saasdsadsadsadanjnj12bjbasldnLKNSdlknasjkbe1kl2b312k3b 12nk4 13b21l312saasdsadsadsadanjnj12bjbasldnLKNSdlknasjkbe1kl2b312k3b 12nk4 13b21l312";
        for (int i = 0; i < 10; i++) {
            world += world;
        }
        System.out.println(world.length());
        long d1 = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            System.out.println(StringUtil.reverse(world));
        }
        long diff = System.currentTimeMillis() - d1;
        System.out.println("总耗时" + DateUtil.timeToStr(diff) + "平均" + 100 + "次耗时" + diff / 100);
        StringBuilder stringBuilder = new StringBuilder(world);
        long d2 = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            stringBuilder.reverse();
        }
        diff = System.currentTimeMillis() - d2;
        System.out.println("总耗时" + DateUtil.timeToStr(diff) + "平均" + 100 + "次耗时" + diff / 100);
    }

    @Test
    public void urlEncode() {
        String str = "http://blog.sina.com.cn/s/blog_9c7605530101gg9d/新闻.html";
        System.out.println(StringUtil.urlEncode(str));
    }

    @Test
    public void urlDecode() {
        String str = "http%3A%2F%2Fblog.sina.com.cn%2Fs%2Fblog_9c7605530101gg9d%2F%E6%96%B0%E9%97%BB.html";
        try {
            System.out.println(StringUtil.urlDecode(str));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Test
    public void urlDecode1(){
        String str = "%e2%91%a0%e4%b8%ba%e4%bf%9d%e8%af%81%e5%8f%96%e7%a5%a8%e3%80%81%e5%85%a5%e5%9b%ad%e9%a1%ba%e5%88%a9%ef%bc%8c%e9%a2%84%e8%ae%a2%e6%97%b6%e8%af%b7%e5%8a%a1%e5%bf%85%e5%a1%ab%e5%86%99%e7%9c%9f%e5%ae%9e%e5%a7%93%e5%90%8d%e3%80%81%e6%89%8b%e6%9c%ba%e5%8f%b7%e7%a0%81%e7%ad%89%e4%bf%a1%e6%81%af%ef%bc%9b%3cbr%3e%e2%91%a1%e5%a5%97%e5%8d%a1%e5%bd%93%e5%a4%a9%e4%bd%bf%e7%94%a8%ef%bc%8c%e8%bf%87%e6%9c%9f%e4%bd%9c%e5%ba%9f%ef%bc%9b%3cbr%3e%e2%91%a2%e6%b8%b8%e4%b9%90%e9%a1%b9%e7%9b%ae%e6%9c%89%e8%ba%ab%e9%ab%98%e3%80%81%e5%b9%b4%e9%be%84%e7%ad%89%e5%ae%89%e5%85%a8%e5%9b%a0%e7%b4%a0%e9%99%90%e5%88%b6%ef%bc%8c%e4%b8%ba%e4%ba%86%e6%82%a8%e7%9a%84%e4%ba%ba%e8%ba%ab%e5%ae%89%e5%85%a8%ef%bc%8c%e8%af%b7%e9%81%b5%e5%ae%88%e7%8e%b0%e5%9c%ba%e8%a7%84%e5%ae%9a%ef%bc%8c%e6%84%9f%e8%b0%a2%e6%82%a8%e7%9a%84%e9%85%8d%e5%90%88%ef%bc%9b%3cbr%3e%e2%91%a3%e5%8f%af%e6%b8%b8%e7%8e%a9%e9%a1%b9%e7%9b%ae%ef%bc%88%e4%bb%85%e4%be%9b%e5%8f%82%e8%80%83%ef%bc%8c%e5%85%b7%e4%bd%93%e4%bb%a5%e7%8e%b0%e5%9c%ba%e8%a7%84%e5%ae%9a%e4%b8%ba%e5%87%86%ef%bc%89%ef%bc%9a%e9%9f%b3%e4%b9%90%e5%96%b7%e6%b3%89%e3%80%81%e6%bf%80%e6%b5%81%e5%8b%87%e8%bf%9b%e3%80%81%e6%91%87%e6%91%86%e6%97%8b%e8%bd%ac%e4%bc%9e%e3%80%81%e6%91%a9%e5%a4%a9%e8%bd%ae%e3%80%81%e7%96%af%e7%8b%82%e8%bf%aa%e6%96%af%e7%a7%91%e3%80%81%e6%97%8b%e8%bd%ac%e6%9c%a8%e9%a9%ac%e3%80%81%e5%84%bf%e7%ab%a5%e8%bf%87%e5%b1%b1%e8%bd%a6%e3%80%81%e7%b3%96%e6%9e%9c%e4%b9%90%e5%9b%ad%e3%80%81%e8%87%aa%e6%8e%a7%e9%a3%9e%e6%9c%ba%e3%80%81%e6%9e%81%e9%80%9f%e6%bc%82%e7%a7%bb%e3%80%81%e6%bf%80%e6%83%85%e5%86%b2%e6%b5%aa%e3%80%81%e6%98%9f%e9%99%85%e9%a3%9e%e8%89%87%e3%80%81%e8%bd%ac%e8%bd%ac%e6%9d%af%e3%80%81%e7%ab%a5%e8%af%9d%e6%a3%ae%e6%9e%97%e3%80%81%e5%8a%a8%e7%89%a9%e7%8e%8b%e5%9b%bd%e3%80%81%e6%a2%a6%e5%b9%bb%e5%b0%8f%e8%bd%ac%e9%a9%ac%e3%80%81%e6%a2%a6%e5%b9%bb%e5%b0%8f%e6%b5%b7%e7%9b%97%e8%88%b9%e3%80%81%e7%8b%82%e9%87%8e%e5%8a%a8%e7%89%a9%e5%9b%ad%e3%80%81%e6%b0%b4%e6%9e%9c%e6%97%8b%e9%a3%8e%e3%80%81%e6%97%8b%e8%bd%ac%e8%bd%a6%e3%80%81%e8%9a%82%e8%9a%81%e4%b9%90%e5%9b%ad%e3%80%81%e6%b5%b7%e6%b4%8b%e4%b9%8b%e6%81%8b%e3%80%81%e6%a2%a6%e5%b9%bb%e7%93%a2%e8%99%ab%e3%80%81%e5%be%ae%e7%ac%91%e4%b9%90%e5%9b%ad%e3%80%81%e7%bf%bc%e5%a4%a9%e3%80%81%e6%b5%b7%e7%9b%97%e8%88%b9%e3%80%81%e8%b7%b3%e8%b7%83%e4%ba%91%e9%9c%84%e3%80%81%e6%b3%a2%e6%b5%aa%e7%bf%bb%e6%bb%9a%e3%80%81%e7%96%af%e7%8b%82%e6%bb%91%e6%9d%bf%e3%80%81%e6%9e%81%e9%80%9f%e9%a3%8e%e8%bd%a6%e3%80%81%e6%8c%91%e6%88%98%e8%80%85%e4%b9%8b%e6%97%85%e3%80%81%e7%81%ab%e5%87%a4%e5%87%b0%e3%80%81%e6%97%8b%e9%a3%8e%e9%aa%91%e5%a3%ab%e3%80%82";
        try {
            System.out.println(StringUtil.urlDecode(str));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @Test
    public void unicodeToStr() {
        String str = "世界你好hello world";
        System.out.println("------" + StringEscapeUtils.escapeHtml4(str));
        String result = StringUtil.toUnicode(str);
        System.out.println(result);
    }

    @Test
    public void strToUni() {
        String result = "\u4E16\u754C\u4F60\u597Dhello world";
        System.out.println(StringUtil.unicodeToStr(result));
    }

    @Test
    public void fixURL() {
        String img_url = "xxfb/jdxwnew2/201506/t20150627_719621.shtml";
        String query = "http://www.cnta.gov.cn/";
        query = query.substring(0, query.lastIndexOf("/")) + "/" + img_url;
        System.out.println(StringUtil.fixURL(query));
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(new String("a十大".getBytes(), "unicode"));
    }
    @Test
    public void md5() throws NoSuchAlgorithmException {
        System.out.println(MD5Util.getMD5Code("蚂蜂窝"));
    }
}
