package com.xor.model;

public class MandatoryFields {
	
	private String subscriberFlag;
	private String issuerIndivIdentifier;
	private String issuerSubscriberIdentifier;
	private String benefitEffectiveBeginDate;
	private String benefitEffectiveEndDate;
	private String lastPremiumPaidDate;
	private String healthCoveragePolicyNo;
	private String status;
	
	public String getBenefitEffectiveEndDate() {
		return benefitEffectiveEndDate;
	}
	public void setBenefitEffectiveEndDate(String benefitEffectiveEndDate) {
		this.benefitEffectiveEndDate = benefitEffectiveEndDate;
	}
	public String getSubscriberFlag() {
		return subscriberFlag;
	}
	public void setSubscriberFlag(String subscriberFlag) {
		this.subscriberFlag = subscriberFlag;
	}
	public String getIssuerIndivIdentifier() {
		return issuerIndivIdentifier;
	}
	public void setIssuerIndivIdentifier(String issuerIndivIdentifier) {
		this.issuerIndivIdentifier = issuerIndivIdentifier;
	}
	public String getIssuerSubscriberIdentifier() {
		return issuerSubscriberIdentifier;
	}
	public void setIssuerSubscriberIdentifier(String issuerSubscriberIdentifier) {
		this.issuerSubscriberIdentifier = issuerSubscriberIdentifier;
	}
	public String getBenefitEffectiveBeginDate() {
		return benefitEffectiveBeginDate;
	}
	public void setBenefitEffectiveBeginDate(String benefitEffectiveBeginDate) {
		this.benefitEffectiveBeginDate = benefitEffectiveBeginDate;
	}
	public String getLastPremiumPaidDate() {
		return lastPremiumPaidDate;
	}
	public void setLastPremiumPaidDate(String lastPremiumPaidDate) {
		this.lastPremiumPaidDate = lastPremiumPaidDate;
	}
	public String getHealthCoveragePolicyNo() {
		return healthCoveragePolicyNo;
	}
	public void setHealthCoveragePolicyNo(String healthCoveragePolicyNo) {
		this.healthCoveragePolicyNo = healthCoveragePolicyNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
