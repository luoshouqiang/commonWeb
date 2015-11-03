package com.visoft.framework.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class InsuranceOrder {
	
	private long vehicleId;
	
	private String vehiclePlatNo;
	
	private String engineNo;
	
	private long insurOrderId;

	private BigDecimal insurAmount;

	private String insurNumber;

	private Integer insurYear;

	private String orderStatus;

	private String orderText;

	private String payNumber;

	private String payType;

	private Long userId;
	
	private Long insurId;
	
	private String insurName;
	
	private String  insureTitle ;
	
	private String  insureBeginDate ;
	
	private String  insureEndDate ;
	
	private long inquriyId;
	
	private BigDecimal compulsoryInsurAmount;
	
	private List<InsurOrderDetail> itemList;

	/**
	 * @return the vehicleId
	 */
	public long getVehicleId() {
		return vehicleId;
	}

	/**
	 * @param vehicleId
	 *            the vehicleId to set
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
	 * @param vehiclePlatNo
	 *            the vehiclePlatNo to set
	 */
	public void setVehiclePlatNo(String vehiclePlatNo) {
		this.vehiclePlatNo = vehiclePlatNo;
	}

	/**
	 * @return the itemList
	 */
	public List<InsurOrderDetail> getItemList() {
		return itemList;
	}

	/**
	 * @param itemList
	 *            the itemList to set
	 */
	public void setItemList(List<InsurOrderDetail> itemList) {
		this.itemList = itemList;
	}

	
	/**
	 * @return the insurOrderId
	 */
	public long getInsurOrderId() {
		return insurOrderId;
	}

	/**
	 * @param insurOrderId the insurOrderId to set
	 */
	public void setInsurOrderId(long insurOrderId) {
		this.insurOrderId = insurOrderId;
	}

	/**
	 * @return the insurAmount
	 */
	public BigDecimal getInsurAmount() {
		return insurAmount;
	}

	/**
	 * @param insurAmount
	 *            the insurAmount to set
	 */
	public void setInsurAmount(BigDecimal insurAmount) {
		this.insurAmount = insurAmount;
	}

	/**
	 * @return the insurNumber
	 */
	public String getInsurNumber() {
		return insurNumber;
	}

	/**
	 * @param insurNumber
	 *            the insurNumber to set
	 */
	public void setInsurNumber(String insurNumber) {
		this.insurNumber = insurNumber;
	}

	/**
	 * @return the insurYear
	 */
	public Integer getInsurYear() {
		return insurYear;
	}

	/**
	 * @param insurYear
	 *            the insurYear to set
	 */
	public void setInsurYear(Integer insurYear) {
		this.insurYear = insurYear;
	}

	/**
	 * @return the orderStatus
	 */
	public String getOrderStatus() {
		return orderStatus;
	}

	/**
	 * @param orderStatus
	 *            the orderStatus to set
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	/**
	 * @return the orderText
	 */
	public String getOrderText() {
		return orderText;
	}

	/**
	 * @param orderText
	 *            the orderText to set
	 */
	public void setOrderText(String orderText) {
		this.orderText = orderText;
	}

	/**
	 * @return the payNumber
	 */
	public String getPayNumber() {
		return payNumber;
	}

	/**
	 * @param payNumber
	 *            the payNumber to set
	 */
	public void setPayNumber(String payNumber) {
		this.payNumber = payNumber;
	}

	/**
	 * @return the payType
	 */
	public String getPayType() {
		return payType;
	}

	/**
	 * @param payType
	 *            the payType to set
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the insurId
	 */
	public Long getInsurId() {
		return insurId;
	}

	/**
	 * @param insurId the insurId to set
	 */
	public void setInsurId(Long insurId) {
		this.insurId = insurId;
	}

	/**
	 * @return the insurName
	 */
	public String getInsurName() {
		return insurName;
	}

	/**
	 * @param insurName the insurName to set
	 */
	public void setInsurName(String insurName) {
		this.insurName = insurName;
	}

	/**
	 * @return the engineNo
	 */
	public String getEngineNo() {
		return engineNo;
	}

	/**
	 * @param engineNo the engineNo to set
	 */
	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}

	/**
	 * @return the insureTitle
	 */
	public String getInsureTitle() {
		return insureTitle;
	}

	/**
	 * @param insureTitle the insureTitle to set
	 */
	public void setInsureTitle(String insureTitle) {
		this.insureTitle = insureTitle;
	}	

	/**
	 * @return the insureBeginDate
	 */
	public String getInsureBeginDate() {
		return insureBeginDate;
	}

	/**
	 * @param insureBeginDate the insureBeginDate to set
	 */
	public void setInsureBeginDate(String insureBeginDate) {
		this.insureBeginDate = insureBeginDate;
	}

	/**
	 * @return the insureEndDate
	 */
	public String getInsureEndDate() {
		return insureEndDate;
	}

	/**
	 * @param insureEndDate the insureEndDate to set
	 */
	public void setInsureEndDate(String insureEndDate) {
		this.insureEndDate = insureEndDate;
	}

	/**
	 * @return the compulsoryInsurAmount
	 */
	public BigDecimal getCompulsoryInsurAmount() {
		return compulsoryInsurAmount;
	}

	/**
	 * @param compulsoryInsurAmount the compulsoryInsurAmount to set
	 */
	public void setCompulsoryInsurAmount(BigDecimal compulsoryInsurAmount) {
		this.compulsoryInsurAmount = compulsoryInsurAmount;
	}

	/**
	 * @return the inquriyId
	 */
	public long getInquriyId() {
		return inquriyId;
	}

	/**
	 * @param inquriyId the inquriyId to set
	 */
	public void setInquriyId(long inquriyId) {
		this.inquriyId = inquriyId;
	}
	

	


	
}
