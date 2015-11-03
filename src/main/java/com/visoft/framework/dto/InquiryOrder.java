package com.visoft.framework.dto;

import java.util.List;


public class InquiryOrder {
	
	private long userId;
	
	private long vehicleId;
	
	private String vehiclePlatNo;
	
	private List<InsurInquiryItem> inquiryItemList;
	
	private List<Long> insuranceCompanyId;

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
	 * @return the vehicleId
	 */
	public long getVehicleId() {
		return vehicleId;
	}

	/**
	 * @param vehicleId the vehicleId to set
	 */
	public void setVehicleId(long vehicleId) {
		this.vehicleId = vehicleId;
	}

	/**
	 * @return the vehiclePlatNo
	 */
	public String getVehiclePlatNo() {
		return vehiclePlatNo;
	}

	/**
	 * @param vehiclePlatNo the vehiclePlatNo to set
	 */
	public void setVehiclePlatNo(String vehiclePlatNo) {
		this.vehiclePlatNo = vehiclePlatNo;
	}

	/**
	 * @return the inquiryItemList
	 */
	public List<InsurInquiryItem> getInquiryItemList() {
		return inquiryItemList;
	}

	/**
	 * @param inquiryItemList the inquiryItemList to set
	 */
	public void setInquiryItemList(List<InsurInquiryItem> inquiryItemList) {
		this.inquiryItemList = inquiryItemList;
	}

	/**
	 * @return the insuranceCompanyId
	 */
	public List<Long> getInsuranceCompanyId() {
		return insuranceCompanyId;
	}

	/**
	 * @param insuranceCompanyId the insuranceCompanyId to set
	 */
	public void setInsuranceCompanyId(List<Long> insuranceCompanyId) {
		this.insuranceCompanyId = insuranceCompanyId;
	}
	
}
