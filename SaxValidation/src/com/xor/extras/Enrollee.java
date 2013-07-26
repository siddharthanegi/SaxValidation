package com.xor.extras;

public class Enrollee {

	private String	issuerIndivIdentifier;
	private String issuerSubscriberIdentifier;
	private HealthCoverage healthCoverage;
	private MemberReportingCategory memberReportingCategory;
	
	public Enrollee()
	{
		healthCoverage=new HealthCoverage();
		memberReportingCategory=new MemberReportingCategory();
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
	public HealthCoverage getHealthCoverage() {
		return healthCoverage;
	}
	public void setHealthCoverage(HealthCoverage healthCoverage) {
		this.healthCoverage = healthCoverage;
	}
	public MemberReportingCategory getMemberReportingCategory() {
		return memberReportingCategory;
	}
	public void setMemberReportingCategory(
			MemberReportingCategory memberReportingCategory) {
		this.memberReportingCategory = memberReportingCategory;
	}


	
}
