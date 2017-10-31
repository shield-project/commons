package org.shoper.commons.core;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * URL parser util
 */
public class URLParser {
    private Map<String, List<Object>> parameters = new HashMap<>();
    private URL url;
    Pattern pattern = Pattern.compile("(\\w+)=([^&]*)(&|$)");

    private URLParser(URL url) {
        this.url = url;
    }

    public static URLParser newInstance(String url) throws MalformedURLException {
        return new URLParser(new URL(url));
    }

    public Map<String, List<Object>> getParameters() {
        return parameters;
    }

    public URL getUrl() {
        return url;
    }

    /**
     * Get single value
     *
     * @param key
     * @return
     */
    public Object getValue(String key) {
        List<Object> values = this.parameters.get(key);
        if (!values.isEmpty())
            return values.get(0);
        else
            return null;
    }

    /**
     * Get multi value
     *
     * @param key
     * @return
     */
    public List<Object> getValues(String key) {
        return this.parameters.get(key);
    }

    /**
     * Get url hash value,
     * Eg.http://www.example.com/#url=www.baidu.com
     *
     * @return
     */
    public String getHash() {
        return this.url.getRef();
    }

    /**
     * Get url hash value,
     * Eg.http://www.example.com?url=www.baidu.com
     *
     * @return
     */
    public String getQuery() {
        return this.url.getQuery();
    }

    URLDecoder urlDecoder = new URLDecoder();

    /**
     * parse url's parameter
     *
     * @return
     * @throws MalformedURLException
     */
    public URLParser parseParameter(boolean isHash, boolean urlDecode) throws MalformedURLException {
        String parameterStr = isHash ? getHash() : getQuery();
        if (StringUtil.isEmpty(parameterStr)) {
            String msg = "This URL [" + this.url + "] has not container ";
            if (isHash)
                msg += "#";
            else
                msg += "?";
            throw new RuntimeException(msg);
        }
        Matcher matcher = pattern.matcher(parameterStr);
        while (matcher.find()) {
            String key = matcher.group(1);
            String value = null;
            try {
                value = urlDecode ? URLDecoder.decode(matcher.group(2), Charset.forName("UTF-8").name()) : matcher.group(2);
            } catch (UnsupportedEncodingException e) {
                //impossibility
            }
            if (parameters.containsKey(key)) {
                parameters.get(key).add(value);
            } else {
                int parametersCount = StringUtil.wordCount(parameterStr, "&");
                //仅支持JDK8的ArrayList算法.如果是JDK8以下可能会多发生一次扩容
                List<Object> values = new ArrayList<>(parametersCount);
                values.add(value);
                parameters.put(key, values);
            }
        }
        return this;
    }

    public URLParser parseParameter(boolean isHash) throws MalformedURLException {
        return parseParameter(isHash, false);
    }

}
