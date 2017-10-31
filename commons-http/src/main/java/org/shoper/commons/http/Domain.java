package org.shoper.commons.http;

/**
 * Created by ShawnShoper on 16/8/10.
 */
public class Domain {
	private String host;
	private int port;
	private String protcol;

	public String getHost () {
		return host;
	}

	public void setHost (String host) {
		this.host = host;
	}

	public int getPort () {
		return port;
	}

	public void setPort (int port) {
		this.port = port;
	}

	public String getProtcol () {
		return protcol;
	}

	public void setProtcol (String protcol) {
		this.protcol = protcol;
	}

	public Domain (String host, int port, String protcol) {
		this.host = host;
		this.port = port;
		this.protcol = protcol;
	}
}
