package org.shoper.commons.http.proxy;


import org.shoper.commons.core.MD5Util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ShawnShoper on 16/8/9.
 * Monitor proxyServer usage
 */
public class ProxyServerStatus implements Comparable<ProxyServerStatus> {
	private String id;
	private ProxyServer proxyServer;
	private long takeTime;
	private long returnTime;
	private boolean available = true;
	private AtomicInteger usage = new AtomicInteger(0);
	private AtomicInteger fail = new AtomicInteger(0);

	public String getId () {
		return id;
	}


	public ProxyServer getProxyServer () {
		return proxyServer;
	}

	public void setProxyServer (ProxyServer proxyServer) {
		this.proxyServer = proxyServer;
		this.id = MD5Util.getMD5Code(proxyServer.getHost() + proxyServer.getUsername() + proxyServer.getPassword());
	}

	public long getTakeTime () {
		return takeTime;
	}

	public void setTakeTime (long takeTime) {
		this.takeTime = takeTime;
	}

	public long getReturnTime () {
		return returnTime;
	}

	public void setReturnTime (long returnTime) {
		this.returnTime = returnTime;
	}

	public boolean isAvailable () {
		return available;
	}

	public void setAvailable (boolean available) {
		this.available = available;
	}

	public void setId (String id) {
		this.id = id;
	}

	public AtomicInteger getUsage () {
		return usage;
	}

	public void setUsage (AtomicInteger usage) {
		this.usage = usage;
	}

	public AtomicInteger getFail () {
		return fail;
	}

	public void setFail (AtomicInteger fail) {
		this.fail = fail;
	}

	public ProxyServerStatus () {
	}

	public ProxyServerStatus (ProxyServer proxyServer) {
		this.setProxyServer(proxyServer);
	}

	@Override
	public String toString () {
		return "ProxyServerStatus{" +
				"proxyServer=" + proxyServer +
				", takeTime=" + takeTime +
				", returnTime=" + returnTime +
				", available=" + available +
				", usage=" + usage +
				", fail=" + fail +
				'}';
	}

	@Override
	public int compareTo (ProxyServerStatus o) {
		//提取策略,可用-->usage最少-->失败率低
		if (o.isAvailable() && !this.isAvailable()) return 1;
		else if (!o.isAvailable() && !this.isAvailable()) return -1;
		double ou = o.getUsage().get();
		double tu = this.getUsage().get();
		//失败率
//		double ofp = o.getFail() / o.getUsage();
//		double tfp = this.getFail() / this.getUsage();
		if (ou < tu) return 1;
		else if (ou > tu) return -1;
		else
			return 0;
	}

	@Override
	public boolean equals (Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ProxyServerStatus that = (ProxyServerStatus) o;
		return proxyServer.getHost() != null ? proxyServer.getHost().equals(that.getProxyServer().getHost()) : that.getProxyServer().getHost() == null;
	}

	@Override
	public int hashCode () {
		int result = proxyServer.hashCode();
		result = 31 * result + (int) (takeTime ^ (takeTime >>> 32));
		result = 31 * result + (int) (returnTime ^ (returnTime >>> 32));
		result = 31 * result + (available ? 1 : 0);
		result = 31 * result + usage.get();
		result = 31 * result + fail.get();
		return result;
	}

}
