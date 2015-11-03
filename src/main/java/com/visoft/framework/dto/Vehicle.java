package com.visoft.framework.dto;

import java.util.List;

public class Vehicle {
	private long id;
	private String platNo;
	private List<String> vehicleLisencePic;
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the platNo
	 */
	public String getPlatNo() {
		return platNo;
	}
	/**
	 * @param platNo the platNo to set
	 */
	public void setPlatNo(String platNo) {
		this.platNo = platNo;
	}
	/**
	 * @return the vehicleLisencePic
	 */
	public List<String> getVehicleLisencePic() {
		return vehicleLisencePic;
	}
	/**
	 * @param vehicleLisencePic the vehicleLisencePic to set
	 */
	public void setVehicleLisencePic(List<String> vehicleLisencePic) {
		this.vehicleLisencePic = vehicleLisencePic;
	}
	
}
