package com.visoft.framework.auth;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

public class WebUtils {
		
	public static String getRequestURI(HttpServletRequest request){
		String requestUrl = request.getRequestURI();
		String enc=request.getCharacterEncoding();
		String contextPath=request.getContextPath();
		requestUrl=normalize(requestUrl,enc);
		if(requestUrl.startsWith(contextPath)){
			requestUrl=	requestUrl.substring(contextPath.length());
		}
		
		return requestUrl;
	}
	
	private static String normalize(String requestUrl,String enc){
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
