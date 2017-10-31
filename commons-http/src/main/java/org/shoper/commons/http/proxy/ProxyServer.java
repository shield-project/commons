package org.shoper.commons.http.proxy;

/**
 * Created by ShawnShoper on 16/8/9.
 * Proxy server bean
 */
public class ProxyServer {
	private String host;
	private int port;
	private String username;
	private String password;

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

	public String getUsername () {
		return username;
	}

	public void setUsername (String username) {
		this.username = username;
	}

	public String getPassword () {
		return password;
	}

	public void setPassword (String password) {
		this.password = password;
	}

	public ProxyServer () {
	}

	public ProxyServer (String host, int port, String username, String password) {
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
	}

	@Override
	public String toString () {
		return "ProxyServer{" +
				"host='" + host + '\'' +
				", port=" + port +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				'}';
	}


}
