package com.boka.insurance.dto;

import java.util.List;

public class User {
	
	private long userId;
	
	private String name;
	
	private String phone;
	
	private List<Area> areaList;
	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}
	/**
	 * @return the areaList
	 */
	public List<Area> getAreaList() {
		return areaList;
	}
	/**
	 * @param areaList the areaList to set
	 */
	public void setAreaList(List<Area> areaList) {
		this.areaList = areaList;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
