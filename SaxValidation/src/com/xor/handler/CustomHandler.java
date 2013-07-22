package com.xor.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.xor.model.Enrollee;
import com.xor.model.Enrollment;
import com.xor.model.Enrollments;
import com.xor.model.HealthCoverage;



public class CustomHandler extends DefaultHandler {

	private String elementValue;
	private List<String> violations;
	private boolean valid;
	private Enrollments enrollments;
	private Enrollment enrollment;
	private Enrollee enrollee;
	private HealthCoverage healthCoverage;
	private Stack<String> nodes;
	
	public CustomHandler() {
		elementValue = "";
		violations=new ArrayList<String>();
		setValid(true);
		enrollments=new Enrollments();
		nodes=new Stack<String>();
	}

	public void characters(char ch[], int start, int length)
			throws SAXException {

		elementValue = new String(ch, start, length);
	}

	public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException {
		
		elementValue = "";
		nodes.push(qName);
		if (qName.equals("enrollment")) {
			//System.out.println("Yo");
			enrollment=new Enrollment();
		}
		if(qName.equals("enrollee")){
			enrollee=new Enrollee();
		}
		if(qName.equals("healthCoverage")){
			healthCoverage=new HealthCoverage();
		}
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {
	
		if(qName.equals("issuerIndivIdentifier")){
			System.out.println(qName+":"+elementValue);
			enrollee.setIssuerIndivIdentifier(elementValue);
			if(!elementValue.matches("[0-9]{10}")){
				setValid(false);
				violations.add(qName+" is empty or invalid data!");
			}
			System.out.println("<"+nodes.pop()+">");
		}
		
		else if(qName.equals("issuerSubscriberIdentifier")){
			System.out.println(qName+":"+elementValue);
			enrollee.setIssuerSubscriberIdentifier(elementValue);
			if(!elementValue.matches("[0-9]{10}")){
				setValid(false);
				violations.add(qName+" is empty or invalid data!");
			}
			System.out.println("<"+nodes.pop()+">");
				
		}
		else if(qName.equals("benefitEffectiveBeginDate") || qName.equals("lastPremiumPaidDate")){
			
			System.out.println(qName+":"+elementValue);
			if(!elementValue.matches("^(19|20)[0-9]{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])$")){
				setValid(false);
				violations.add(qName+" is empty or invalid data!");
			}
		
			if(qName.equals("benefitEffectiveBeginDate")){
				healthCoverage.setBenefitEffectiveBeginDate(elementValue);
			}
			else{
				healthCoverage.setLastPremiumPaidDate(elementValue);
			}
			System.out.println("<"+nodes.pop()+">");
		
		}
		
		else if(qName.equals("classOfContractCode")){
			System.out.println(qName+":"+elementValue);
			healthCoverage.setClassOfContractCode(elementValue);
			if(!elementValue.matches("[0-9A-Z]{16}")){
				setValid(false);
				violations.add(qName+" is empty or invalid data!");
			}
			System.out.println("<"+nodes.pop()+">");
				
		}
		else if(qName.equals("healthCoveragePolicyNo")){
			System.out.println(qName+":"+elementValue);
			healthCoverage.setClassOfContractCode(elementValue);
			if(!elementValue.matches("[0-9]{6}")){
				setValid(false);
				violations.add(qName+" is empty or invalid data!");
			}
			System.out.println("<"+nodes.pop()+">");
				
		}
		else if(qName.equals("lookupValueCode")){
			
			System.out.println("<"+nodes.pop()+">");
			String parentElement=nodes.peek();
			if(parentElement.equals("maintenanceTypeCode")){
				System.out.println(qName+":"+elementValue);
				healthCoverage.getMaintanenceTypeCode().setLookupValueCode(elementValue);
				if(elementValue.matches("[0-9]{3}")){
					setValid(false);
					violations.add(parentElement+"->"+qName+" is empty or invalid data!");
				}
					
			}
			else if(parentElement.equals("insuranceTypeLkp")){
				System.out.println(qName+":"+elementValue);
				healthCoverage.getInsuranceTypeLkp().setLookupValueCode(elementValue);
				if(elementValue.matches("[A-Z]{3}")){
					setValid(false);
					violations.add(parentElement+"->"+qName+" is empty or invalid data!");
				}
					
			}
			else if(parentElement.equals("additionalMaintReason")){
				System.out.println(qName+":"+elementValue);
				enrollee.getMemberReportingCategory().getAdditionalMaintReason().setLookupValueCode(elementValue);
				if(elementValue.matches("[A-Z]+")){
					setValid(false);
					violations.add(parentElement+"->"+qName+" is empty or invalid data!");
				}
					
			}
		}
				
		else if(qName.equals("healthCoverage")){
			enrollee.setHealthCoverage(healthCoverage);
			System.out.println("<"+nodes.pop()+">");
		}
		else if(qName.equals("enrollee")){
			enrollment.getEnrollee().add(enrollee);
			System.out.println("<"+nodes.pop()+">");
		}
		else if(qName.equals("enrollment")){
			enrollments.getEnrollment().add(enrollment);
			System.out.println("<"+nodes.pop()+">");
		}
		else{
			System.out.println("<"+nodes.pop()+">");
		}
	}

	public List<String> getViolations() {
		return violations;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public Enrollments getEnrollments() {
		return enrollments;
	}

}
