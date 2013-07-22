package com.xor.model;

public class MemberReportingCategory {

	private AdditionalMaintReason additionalMaintReason;
	public MemberReportingCategory(){
		additionalMaintReason=new AdditionalMaintReason();
		
	}

	public AdditionalMaintReason getAdditionalMaintReason() {
		return additionalMaintReason;
	}

	public void setAdditionalMaintReason(AdditionalMaintReason additionalMaintReason) {
		this.additionalMaintReason = additionalMaintReason;
	}
}
