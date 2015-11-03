package com.visoft.framework.dto;

public class InsurOrderItem {

	private Long insurItemId;

	private Long insurId;

	private String insurItemName;

	private String insurItemUnit;

	public InsurOrderItem() {
	}

	public Long getInsurItemId() {
		return this.insurItemId;
	}

	public void setInsurItemId(Long insurItemId) {
		this.insurItemId = insurItemId;
	}

	public Long getInsurId() {
		return this.insurId;
	}

	public void setInsurId(Long insurId) {
		this.insurId = insurId;
	}

	public String getInsurItemName() {
		return this.insurItemName;
	}

	public void setInsurItemName(String insurItemName) {
		this.insurItemName = insurItemName;
	}

	public String getInsurItemUnit() {
		return this.insurItemUnit;
	}

	public void setInsurItemUnit(String insurItemUnit) {
		this.insurItemUnit = insurItemUnit;
	}

}