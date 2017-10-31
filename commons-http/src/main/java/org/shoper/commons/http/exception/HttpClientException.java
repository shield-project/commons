package org.shoper.commons.http.exception;

public class HttpClientException extends Exception
{
	private static final long serialVersionUID = 6957999512115196503L;
	public HttpClientException(Throwable e)
	{
		super(e);
	}
	public HttpClientException(String e)
	{
		super(e);
	}
}
