package com.visoft.framework.auth;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

public class PathAuthFilter extends AuthFilter {

	private static final Logger LOG = LoggerFactory.getLogger(PathAuthFilter.class);
	private List<String> whilteUrl;
	private AuthManager authManager;

	/**
	 * @param whilteUrl
	 *            the whilteUrl to set
	 */
	public void setWhilteUrl(List<String> whilteUrl) {
		this.whilteUrl = whilteUrl;
	}


	/**
	 * @param authManager
	 *            the authManager to set
	 */
	public void setAuthManager(AuthManager authManager) {
		this.authManager = authManager;
	}


	private boolean matchListUrl(String url) {
		if(CollectionUtils.isEmpty(whilteUrl)){
			return true;
		}
		boolean find = false;
		for (String patternPath : whilteUrl) {
			find = matchUrl(patternPath, url);
			if (find) {
				return find;
			}
		}
		return find;
	}

	private boolean matchUrl(String patternPath, String url) {
		boolean finded = false;
		try {
			Pattern pattern = Pattern.compile(patternPath);
			Matcher matcher = pattern.matcher(url);
			finded = matcher.find();
		} catch (Exception ex) {
			LOG.error("白名单路径的URL不符合正则规范", ex);

		}
		return finded;
	}


	@Override
	protected boolean match(HttpServletRequest request, HttpServletResponse response) {
		String requestUrl=WebUtils.getRequestURI(request);
		
		if ((!CollectionUtils.isEmpty(whilteUrl)&&whilteUrl.contains(requestUrl)) || matchListUrl(requestUrl)) {
			return true;
		}
			return authManager.auth(request, response);
		
	
	}

}
