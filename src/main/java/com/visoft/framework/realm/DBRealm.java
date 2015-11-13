package com.visoft.framework.realm;

import org.springframework.web.bind.annotation.RequestMethod;

import com.visoft.framework.auth.AuthUser;
import com.visoft.framework.auth.Permission;
import com.visoft.framework.auth.Realm;
import com.visoft.framework.auth.Role;

public class DBRealm extends Realm{
	
	@Override
	public void authorization(AuthUser authUser) {
		Permission permission=new Permission();
		permission.setUrl("/user/test");
		permission.addOperation(RequestMethod.GET);
		
		Role role=new Role();
		role.setRoleName("tester");
		role.addPermission(permission);
		authUser.addRole(role);
		
	}

	@Override
	protected int login(String userName, String password) {	
		return 1;
	}

	

}
