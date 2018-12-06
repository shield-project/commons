package org.shoper.commons.http.handle;

import org.shoper.commons.core.StringUtil;
import org.shoper.commons.http.HttpClient;
import org.shoper.commons.http.exception.HttpClientException;
import org.shoper.commons.http.exception.UnHandleException;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Objects;
import java.util.concurrent.TimeoutException;

/**
 * Created by ShawnShoper on 16/9/12.
 */
public abstract class ResponseHandler {
    public HttpResponse response;
    private HttpClient httpClient;

    public HttpResponse handleHttpResponse(HttpResponse response, HttpClient httpClient) throws UnHandleException, TimeoutException, IOException {
        this.httpClient = httpClient;
        this.response = response;
        return response;
    }

    public Charset getContentCharset(Document document) {
        String charset = null;
        Elements metas = document.getElementsByTag("meta");
        if (metas != null && !metas.isEmpty()) {
            search:
            for (Element meta : metas) {
                String content = meta.attr("content");
                if (content.contains("charset")) {
                    String[] contents = content.split(";");
                    for (String val : contents) {
                        val = val.replaceAll(" ", "");
                        if (val.startsWith("charset=")) {
                            charset = val.replace("charset=", "");
                            break search;
                        }
                    }
                }
            }
        }
        return Charset.forName(charset);
    }

    public String getContent() throws HttpClientException {
        String content;
        try {
            content = EntityUtils.toString(this.response.getEntity(), StringUtil.isEmpty(httpClient.getAccessBean().getCharset()) ? HttpClient.DEFAULT_CHARSET : httpClient.getAccessBean().getCharset());
            if (StringUtil.isEmpty(httpClient.getAccessBean().getCharset()))
                if (Objects.nonNull(this.response.getEntity().getContentType()) && "text/html".equals(this.response.getEntity().getContentType().getValue())) {
                    if (!content.isEmpty()) {
                        Document document = Jsoup.parse(content);
                        String actualCharset = getContentCharset(document).name();
                        content = new String(content.getBytes(HttpClient.DEFAULT_CHARSET), actualCharset);
                    }
                }
        } catch (IOException e) {
            throw new HttpClientException(e);
        }
        return content;
    }

    public InputStream getInputSteam() throws IOException {
        return this.response.getEntity().getContent();
    }
}
