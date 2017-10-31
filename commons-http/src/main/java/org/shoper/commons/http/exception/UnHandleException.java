package org.shoper.commons.http.exception;

/**
 * Created by ShawnShoper on 16/8/10.
 */
public class UnHandleException extends HttpClientException {
	public UnHandleException (Throwable e) {
		super(e);
	}
	public UnHandleException (String msg) {
		super(msg);
	}

}
