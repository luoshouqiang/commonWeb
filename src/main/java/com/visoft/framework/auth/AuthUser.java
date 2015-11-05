package com.visoft.framework.auth;

import java.util.HashSet;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import com.sun.tools.javac.util.Assert;
import com.visoft.framework.auth.Permission.RequestMethod;

public class AuthUser {
	private int id;
	private Set<Role> roles = new HashSet<Role>();
	private Set<Permission> allPermissions = new HashSet<Permission>();;

	public AuthUser(int id){
		this.id=id;
		getAuthInfo();
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
	
	public void getAuthInfo(){
		Permission permission=new Permission();
		permission.setUrl("/user/test");
		permission.addOperation(RequestMethod.GET);
		
		Role role=new Role();
		role.setRoleName("tester");
		role.addPermission(permission);
		addRole(role);
		
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

	 boolean hasPermission(Permission permission) {
		for(Permission existPer:allPermissions){
				String existUrl=existPer.getUrl();
				String checUrl=permission.getUrl();
				if(existUrl!=null&&existUrl.endsWith(checUrl)){
					Set<RequestMethod> needCheckOp=permission.getOperationLists();
					if(CollectionUtils.isEmpty(needCheckOp)){
						return false;
					}
					if(needCheckOp.size()>1){
						throw new AuthException("验证的时候只能添加一个权限");
					}
					RequestMethod requestMethod=(RequestMethod)needCheckOp.toArray()[0];
					if(existPer.getOperationLists().contains(requestMethod)){
						return true;
					}
				}
			}
		return false;
	}

}
