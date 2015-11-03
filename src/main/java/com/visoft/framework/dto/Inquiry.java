package com.visoft.framework.dto;

/**
 * 
 * @author luosq
 *
 */
public class Inquiry {
	private String startDate;
	private String endDate;
	private String vehiclePlatNo;
	private String vin;
	private String inquiryStatus;
	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
	 * @return the vin
	 */
	public String getVin() {
		return vin;
	}
	/**
	 * @param vin the vin to set
	 */
	public void setVin(String vin) {
		this.vin = vin;
	}
	/**
	 * @return the inquiryStatus
	 */
	public String getInquiryStatus() {
		return inquiryStatus;
	}
	/**
	 * @param inquiryStatus the inquiryStatus to set
	 */
	public void setInquiryStatus(String inquiryStatus) {
		this.inquiryStatus = inquiryStatus;
	}
	
}
