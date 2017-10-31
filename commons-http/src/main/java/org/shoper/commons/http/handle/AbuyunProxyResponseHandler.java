package org.shoper.commons.http.handle;


import org.shoper.commons.http.HttpClient;
import org.shoper.commons.http.exception.UnHandleException;

/**
 * Created by ShawnShoper on 16/9/12.
 */
public class AbuyunProxyResponseHandler extends ResponseHandler {
	@Override
	public void errHandle (int status, HttpClient httpClient) throws UnHandleException {

		//407
		//402
		//403
		//429
		//503
		String msg = null;
		if (status == 407)
			msg = "所发出的HTTP请求中，未正确包含 Proxy-Authorization 头，可能是隧道通行证、通行密钥错误导致的，也可能是 Proxy-Authorization 头压根就没成功加上。可使用 WireShark 抓包查看发出的请求是否正确包含 Proxy-Authorization 头";
		else if (status == 402)
			msg = "代理隧道过期，是时候续费了。";
		else if (status == 403)
			msg = "当前代理隧道已被禁止使用。";
		else if (status == 429)
			msg = "1 秒内请求数过多，超出了代理隧道默认每秒请求数与该隧道购买的每秒请求数之和。";
		else if (status == 503)
			msg = "抱歉，当前代理请求失败（超时）。可以尝试切换代理IP后重新发起请求。";
		if (status == 407 || status == 503 || status == 429) {
			throw new UnHandleException("Unexpected response status: " + status + "\t access url:" + httpClient.getAccessBean().getUrl() + "\tmsg:" + msg + "\thost:" + httpClient.getProxyServer().getHost() + "\tport:" + httpClient.getProxyServer().getPort() + "\tuser:" + httpClient.getProxyServer().getUsername() + "\tpwd:" + httpClient.getProxyServer().getPassword());
//			if(HTTPMethod.POST.equals(httpClient.getLastMethod()){
//				httpClient.post();
//			}
		}
		String errmsg = "Unexpected response status: " + status + "\tmsg:" + msg + "\thost:" + httpClient.getProxyServer().getHost() + "\tport:" + httpClient.getProxyServer().getPort() + "\tuser:" + httpClient.getProxyServer().getUsername() + "\tpwd:" + httpClient.getProxyServer().getPassword();
		throw new UnHandleException(errmsg);
	}

}
