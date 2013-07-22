package com.xor.model;

public class HealthCoverage {
	private MaintenanceTypeCode maintenanceTypeCode;
	private InsuranceTypeLkp insuranceTypeLkp;
	private String benefitEffectiveBeginDate;
	private String lastPremiumPaidDate;
	private String classOfContractCode;
	private String healthCOveragePolicyNo;

	public HealthCoverage(){
		maintenanceTypeCode=new MaintenanceTypeCode();
		insuranceTypeLkp=new InsuranceTypeLkp();
	}
	public MaintenanceTypeCode getMaintanenceTypeCode() {
		return maintenanceTypeCode;
	}

	public void setMaintanenceTypeCode(MaintenanceTypeCode maintenanceTypeCode) {
		this.maintenanceTypeCode = maintenanceTypeCode;
	}

	public InsuranceTypeLkp getInsuranceTypeLkp() {
		return insuranceTypeLkp;
	}

	public void setInsuranceTypeLkp(InsuranceTypeLkp insuranceTypeLkp) {
		this.insuranceTypeLkp = insuranceTypeLkp;
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

	public String getClassOfContractCode() {
		return classOfContractCode;
	}

	public void setClassOfContractCode(String classOfContractCode) {
		this.classOfContractCode = classOfContractCode;
	}

	public String getHealthCOveragePolicyNo() {
		return healthCOveragePolicyNo;
	}

	public void setHealthCOveragePolicyNo(String healthCOveragePolicyNo) {
		this.healthCOveragePolicyNo = healthCOveragePolicyNo;
	}

}
