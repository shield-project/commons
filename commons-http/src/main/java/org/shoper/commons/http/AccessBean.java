package org.shoper.commons.http;


import org.shoper.commons.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by ShawnShoper on 16/8/10.
 */
public class AccessBean {
	private String charset;
	private int timeout = 20;
	private int retry = 3;
	private TimeUnit timeoutUnit = TimeUnit.SECONDS;
	private String url;
	private List<BasicNameValuePair> formDatas = new ArrayList<>();
	public void setFormDatas (List<BasicNameValuePair> formDatas) {
		this.formDatas = formDatas;
	}

	public AccessBean (String url) {
		this(null, 0, 0, null, url, false, null);
	}

	public AccessBean (String charset, int timeout, int retry, TimeUnit timeoutUnit, String url, boolean isProxy) {
		this(charset, timeout, retry, timeoutUnit, url, isProxy, null);
	}

	public AccessBean (String charset, int timeout, int retry, TimeUnit timeoutUnit, String url, boolean isProxy, List<Map<String, String>> formDatas) {
		this.charset = charset;
		this.timeout = timeout;
		this.retry = retry;
		this.timeoutUnit = timeoutUnit;
		this.url = url;
		this.isProxy = isProxy;
		if (Objects.nonNull(formDatas)) {
			formDatas.forEach(map -> {
				List<BasicNameValuePair> list = map.keySet().stream().map(key -> new BasicNameValuePair(key, map.get(key))).collect(Collectors.toList());
				this.formDatas.addAll(list);
			});

		}
	}

	public List<BasicNameValuePair> getFormDatas () {
		return formDatas;
	}

	public void setFormDatas (Map<String, String> formDatas) {
		if (Objects.nonNull(formDatas)) {
			this.formDatas = new ArrayList<BasicNameValuePair>(formDatas.size());
			this.formDatas.addAll(formDatas.keySet().stream().map(key -> new BasicNameValuePair(key, formDatas.get(key))).collect(Collectors.toList()));
		}
	}

	public int getTimeout () {
		return timeout;
	}

	public void setTimeout (int timeout) {
		this.timeout = timeout;
	}

	public int getRetry () {
		return retry;
	}

	public void setRetry (int retry) {
		this.retry = retry;
	}

	public TimeUnit getTimeoutUnit () {
		return timeoutUnit;
	}

	public void setTimeoutUnit (TimeUnit timeoutUnit) {
		this.timeoutUnit = timeoutUnit;
	}

	public String getCharset () {
		return charset;
	}

	public void setCharset (String charset) {
		this.charset = charset;
	}

	private boolean isProxy;

	public String getUrl () {
		return url;
	}

	public void setUrl (String url) {
		this.url = url;
	}

	public boolean isProxy () {
		return isProxy;
	}

	public void setProxy (boolean proxy) {
		isProxy = proxy;
	}

}
