package com.visoft.framework.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.visoft.framework.auth.Permission.RequestMethod;

public class AuthManager {

	private static final String USERID = "userId";

	public static boolean auth(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		HttpSession session = httpRequest.getSession(false);
		if (session == null) {
			return false;
		}
		Integer userId = (Integer) session.getAttribute(USERID);
		String requestUrl = httpRequest.getServletPath();
		String method = httpRequest.getMethod();
		Permission permission = new Permission();
		permission.setUrl(requestUrl);
		permission.getOperationLists().add(RequestMethod.valueOf(method));

		AuthUser user = new AuthUser(userId);
		return user.hasPermission(permission);

	}

	public static void login(HttpServletRequest httpRequest, String userName, String password) {
		HttpSession session = httpRequest.getSession(true);
		session.setAttribute(USERID, 1);
	}
}
