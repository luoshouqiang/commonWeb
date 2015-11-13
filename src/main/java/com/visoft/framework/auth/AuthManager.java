package com.visoft.framework.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMethod;


public class AuthManager {

	private static final String USERID = "visoft_auth_userId";
	
	Realm realm;
	

	/**
	 * @param realm the realm to set
	 */
	public void setRealm(Realm realm) {
		this.realm = realm;
	}

	public  boolean auth(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
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

		AuthUser authUser = new AuthUser(userId);
		realm.authorization(authUser);
		return realm.authentication(authUser, permission);

	}

	public  void login(HttpServletRequest httpRequest, String userName, String password) {
		HttpSession session = httpRequest.getSession(true);	
		int userId=realm.login(userName, password);
		session.setAttribute(USERID, userId);
	}
}
