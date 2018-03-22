package com.web.sj.domain;

public class DeliveryLocation {
	
	public Integer settlementId;
	public String settlement;
	public DeliveryLocation() {
		super();
	}
	public Integer getSettlementId() {
		return settlementId;
	}
	public void setSettlementId(Integer settlementId) {
		this.settlementId = settlementId;
	}
	public String getSettlement() {
		return settlement;
	}
	public void setSettlement(String settlement) {
		this.settlement = settlement;
	}
}
