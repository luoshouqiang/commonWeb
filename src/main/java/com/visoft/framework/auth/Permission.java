package com.visoft.framework.auth;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.bind.annotation.RequestMethod;

public class Permission {
	private String url;
	private  Set<RequestMethod> operationLists=new HashSet<RequestMethod>();
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the operationLists
	 */
	public Set<RequestMethod> getOperationLists() {
		return operationLists;
	}
	/**
	 * @param operationLists the operationLists to set
	 */
	public void setOperationLists(Set<RequestMethod> operationLists) {
		this.operationLists = operationLists;
	}
	public void addOperation(RequestMethod method) {
		operationLists.add(method);
	}
	
	
	
	
}
