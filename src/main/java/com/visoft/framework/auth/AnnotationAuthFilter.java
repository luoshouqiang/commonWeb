package com.visoft.framework.auth;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

public class AnnotationAuthFilter extends AuthFilter{

	AnnotationMethodRequest annotationMethodRequest;

	private AuthManager authManager;
	

	/**
	 * @param annotationMethodRequest the annotationMethodRequest to set
	 */
	public void setAnnotationMethodRequest(AnnotationMethodRequest annotationMethodRequest) {
		this.annotationMethodRequest = annotationMethodRequest;
	}


	/**
	 * @param authManager the authManager to set
	 */
	public void setAuthManager(AuthManager authManager) {
		this.authManager = authManager;
	}


	@Override
	public boolean match(HttpServletRequest request, HttpServletResponse response) {
		String requestUrl = request.getRequestURI();
		String enc=request.getCharacterEncoding();
		String contextPath=request.getContextPath();
		requestUrl=normalize(requestUrl,enc);
		requestUrl=	requestUrl.substring(contextPath.length());
		boolean  isNeedAuthMethod = annotationMethodRequest.isNeedAuthMethod(requestUrl);
		if (!isNeedAuthMethod) {
			return true;
		} else {
			return authManager.auth(request, response);
		}
		
	}

	private String normalize(String requestUrl,String enc){
		if(StringUtils.isEmpty(requestUrl)){
			return "";
		}
		int semicolon=requestUrl.indexOf(";");
		if(semicolon>0){
			requestUrl=requestUrl.substring(0, semicolon);
		}
		try {
			requestUrl=URLDecoder.decode(requestUrl, enc);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			requestUrl=URLDecoder.decode(requestUrl);
		}
		return requestUrl;
	}


	
}
