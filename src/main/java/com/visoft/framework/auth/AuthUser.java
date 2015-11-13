package com.visoft.framework.auth;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.bind.annotation.RequestMethod;

import com.sun.tools.javac.util.Assert;

public class AuthUser {
	private int id;
	private Set<Role> roles = new HashSet<Role>();
	private Set<Permission> allPermissions = new HashSet<Permission>();;

	public AuthUser(int id){
		this.id=id;
	}
	/**
	 * @return the roles
	 */
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles
	 *            the roles to set
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
		addPermission(roles);
	}

	public void addRole(Role role) {
		roles.add(role);
		addPermission(role);
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	

	private void addPermission(Role role) {
		Assert.checkNonNull(role, "role is null");
		Set<Permission> permissions = role.getPermission();
		for(Permission permission:permissions){
			mergePermission(permission);
		}
	}
	
	private void mergePermission(Permission permission){
		Assert.checkNonNull(roles, "permission is null");
		if(allPermissions.isEmpty()){
			allPermissions.add(permission);
			return;
		}
		for(Permission existPer:allPermissions){
			String existUrl=existPer.getUrl();
			String findUrl=permission.getUrl();
			if(existUrl!=null&&existUrl.equals(findUrl)){
				Set<RequestMethod> opList=permission.getOperationLists();
				existPer.getOperationLists().addAll(opList);			
			}else{
				allPermissions.add(permission);
			}
		}
		
	}

	private void addPermission(Set<Role> roles) {
		Assert.checkNonNull(roles, "role is null");
		for (Role role : roles) {
			addPermission(role);
		}
	}
	
	 Set<Permission> getAllPermissions() {
		return allPermissions;
	}

	 

}
