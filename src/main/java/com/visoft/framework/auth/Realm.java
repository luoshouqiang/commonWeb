package com.visoft.framework.auth;

import java.util.Set;

import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMethod;

public abstract class Realm {

	/**
	 * 授权
	 */
	protected abstract void authorization(AuthUser authUser);

	/**
	 * 认证
	 */
	public boolean authentication(AuthUser authUser, Permission needCheckPermission) {

		for (Permission existPer : authUser.getAllPermissions()) {
			String existUrl = existPer.getUrl();
			String checUrl = needCheckPermission.getUrl();
			if (existUrl != null && existUrl.endsWith(checUrl)) {
				Set<RequestMethod> needCheckOp = needCheckPermission.getOperationLists();
				if (CollectionUtils.isEmpty(needCheckOp)) {
					return false;
				}
				if (needCheckOp.size() > 1) {
					throw new AuthException("验证的时候只能添加一个权限");
				}
				RequestMethod requestMethod = (RequestMethod) needCheckOp.toArray()[0];
				if (existPer.getOperationLists().contains(requestMethod)) {
					return true;
				}
			}
		}
		return false;

	};

	protected abstract int login(String userName, String password);
}
