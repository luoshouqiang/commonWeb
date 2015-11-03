package com.visoft.framework.dto;



import java.math.BigDecimal;

public class InsurOrderDetail {

	private Long id;

	private BigDecimal amount;

	private String buyOrNot;

	private Long insurItemId;

	private Long insurOrderId;
	
	private InsurOrderItem item;
	
	private BigDecimal realPrice;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getBuyOrNot() {
		return this.buyOrNot;
	}

	public void setBuyOrNot(String buyOrNot) {
		this.buyOrNot = buyOrNot;
	}

	public Long getInsurItemId() {
		return this.insurItemId;
	}

	public void setInsurItemId(Long insurItemId) {
		this.insurItemId = insurItemId;
	}

	public Long getInsurOrderId() {
		return this.insurOrderId;
	}

	public void setInsurOrderId(Long insurOrderId) {
		this.insurOrderId = insurOrderId;
	}

	/**
	 * @return the item
	 */
	public InsurOrderItem getItem() {
		return item;
	}

	/**
	 * @param item the item to set
	 */
	public void setItem(InsurOrderItem item) {
		this.item = item;
	}

	/**
	 * @return the realPrice
	 */
	public BigDecimal getRealPrice() {
		return realPrice;
	}

	/**
	 * @param realPrice the realPrice to set
	 */
	public void setRealPrice(BigDecimal realPrice) {
		this.realPrice = realPrice;
	}
	
	

}