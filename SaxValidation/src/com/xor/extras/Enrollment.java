package com.xor.extras;

import java.util.ArrayList;
import java.util.List;

public class Enrollment {

	
	private List<Enrollee> enrollee;
	
	public Enrollment(){
		enrollee=new ArrayList<Enrollee>();
	}
	
	public List<Enrollee> getEnrollee() {
		return enrollee;
	}
	public void setEnrollee(List<Enrollee> enrollee) {
		this.enrollee = enrollee;
	}
}
