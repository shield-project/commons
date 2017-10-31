package org.shoper.commons.http;


import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by ShawnShoper on 16/8/7.
 * This util is for Apache HttpCLient...
 * It's different from org.shoper.http.httpClient.HttpClient
 */
public class HttpClientBuilder {
    private String charset;
    private int timeout = 20;
    private int retry = 3;
    private TimeUnit timeoutUnit = TimeUnit.SECONDS;
    private String url;
    private List <Map <String, String>> formDatas;
    private boolean proxy;

    public HttpClientBuilder setProxy(boolean proxy) {
        this.proxy = proxy;
        return this;
    }

    public HttpClientBuilder setCharset(String charset) {
        this.charset = charset;
        return this;
    }

    public HttpClientBuilder setTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }

    public HttpClientBuilder setRetry(int retry) {
        this.retry = retry;
        return this;
    }

    public HttpClientBuilder setTimeoutUnit(TimeUnit timeoutUnit) {
        this.timeoutUnit = timeoutUnit;
        return this;
    }

    public HttpClientBuilder setUrl(String url) {
        this.url = url;
        return this;
    }

    public HttpClientBuilder setFormDatas(List <Map <String, String>> formDatas) {
        this.formDatas = formDatas;
        return this;
    }

    public HttpClientBuilder setFormDatas(Map <String, String> formDatas) {
        List <Map <String, String>> formData = new ArrayList <>();
        formData.add(formDatas);
        setFormDatas(formData);
        return this;
    }

    public static HttpClientBuilder custom() {
        return new HttpClientBuilder();
    }

    public HttpClient build() throws MalformedURLException {
        AccessBean accessBean = new AccessBean(charset, timeout, retry, timeoutUnit, url, proxy, formDatas);
        return new HttpClient(accessBean);
    }

//	public static void main(String[] args) throws MalformedURLException {
//        Map<String, String> postDatas = new HashMap<>();
//        String str = "广东省旅游局222012年度政府信息公开工作报告";
//        System.out.println(str);
//        postDatas.put("title",str);
//        postDatas.put("channel","4028808e56e3d9eb0156e88b14b80020");
//        postDatas.put("seccode","E2DA8B08502F206D01891EB5F079B488E4C82FA9F381E967");
//        try {
//            String url ="http://120.76.103.213:8080/zxwcms/app/rest?method=outnewsSave";
//           HttpClient build = HttpClientBuilder.custom().setUrl(url).setTimeout(200000).setTimeoutUnit(TimeUnit.DAYS).setRetry(3).setCharset("utf-8").setFormDatas(postDatas).build();
//            System.out.println(build.post());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (TimeoutException e) {
//            e.printStackTrace();
//		}
//	}
}
