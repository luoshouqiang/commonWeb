package com.visoft.framework.auth;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthFilter implements Filter{
		
	private List<String> whilteUrl;
	private String loginUrl;
	private String loginOutUrl;
	
	/**
	 * @param whilteUrl the whilteUrl to set
	 */
	public void setWhilteUrl(List<String> whilteUrl) {
		this.whilteUrl = whilteUrl;
	}

	/**
	 * @param loginUrl the loginUrl to set
	 */
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	/**
	 * @param loginOutUrl the loginOutUrl to set
	 */
	public void setLoginOutUrl(String loginOutUrl) {
		this.loginOutUrl = loginOutUrl;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest httpRequest=(HttpServletRequest)request;
		HttpServletResponse httpResponse=(HttpServletResponse)response;
		String requestUrl=httpRequest.getServletPath();
		if(whilteUrl.contains(requestUrl)){
			chain.doFilter(httpRequest, response);
			return;
		}
		
		
		if(!AuthManager.auth(httpRequest,httpResponse)){
			System.out.println("无权限");
			return;
		};
		chain.doFilter(httpRequest, response);
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
