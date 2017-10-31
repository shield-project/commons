package org.shoper.commons.http.exception;

/**
 * Created by ShawnShoper on 16/9/21.
 */
public class TimeoutException extends Exception {
	public TimeoutException (Throwable e) {
		super(e);
	}

	public TimeoutException (String e) {
		super(e);
	}
}
