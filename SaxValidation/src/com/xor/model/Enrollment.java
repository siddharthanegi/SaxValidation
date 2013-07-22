package com.xor.model;

import java.util.ArrayList;
import java.util.List;

public class Enrollment {

	private String sponsorEIN;
	private String insurerCMSPlanID;
	private List<Enrollee> enrollee;
	
	public Enrollment(){
		enrollee=new ArrayList<Enrollee>();
	}
	public String getSponsorEIN() {
		return sponsorEIN;
	}
	public void setSponsorEIN(String sponsorEIN) {
		this.sponsorEIN = sponsorEIN;
	}
	public String getInsurerCMSPlanID() {
		return insurerCMSPlanID;
	}
	public void setInsurerCMSPlanID(String insurerCMSPlanID) {
		this.insurerCMSPlanID = insurerCMSPlanID;
	}
	public List<Enrollee> getEnrollee() {
		return enrollee;
	}
	public void setEnrollee(List<Enrollee> enrollee) {
		this.enrollee = enrollee;
	}
}
