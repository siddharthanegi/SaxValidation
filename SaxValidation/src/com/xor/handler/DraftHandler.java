package com.xor.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.xor.model.Enrollee;
import com.xor.model.Enrollment;
import com.xor.model.Enrollments;
import com.xor.model.HealthCoverage;
import com.xor.model.MandatoryFields;

public class DraftHandler extends DefaultHandler {
	
		
	private String elementValue;
	private List<String> violations;
	private boolean valid;
	private Enrollments enrollments;
	private Enrollment enrollment;
	private Enrollee enrollee;
	private HealthCoverage healthCoverage;
	private Stack<String> nodes;
	private MandatoryFields mandatoryFields;
	private String currentEnrollmentStatus;
	
	
	private static final String ISSUER_IDENTIFIER_PATTERN="[0-9]{10}";
	private static final String DATE_PATTERN="^(19|20)[0-9]{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])$";
	private static final String CONTRACT_CODE_PATTERN="[0-9A-Z]{16}";
	private static final String HEALTH_COVERAGE_POLICY_NUMBER_PATTERN="[0-9]{6}";
	private static final String MAINTENANCE_TYPE_CODE_PATTERN="[0-9]{3}";
	private static final String INSURANCE_TYPE_LKP_PATTERN="[A-Z]{3}";
	private static final String ADDITIONAL_MAINT_REASON_PATTERN="[A-Z]+";
	
	
	public DraftHandler() {
		elementValue = "";
		violations=new ArrayList<String>();
		valid=true;
		enrollments=new Enrollments();
		nodes=new Stack<String>();
		mandatoryFields=new MandatoryFields();
		currentEnrollmentStatus="";
	}

	public void characters(char ch[], int start, int length)
			throws SAXException {

		char[] cloneElementValue=Arrays.copyOf(ch, ch.length);
		elementValue = new String(cloneElementValue, start, length);
	}

	public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException {
		
		
		elementValue = "";
		nodes.push(qName);
		if(qName.equals("enrollment")){
			currentEnrollmentStatus="";
		}
			
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {
		
		if(qName.equals("subscriberFlag")){
			System.out.println(qName+":"+elementValue);
			mandatoryFields.setSubscriberFlag(elementValue);
		}
		else if(qName.equals("issuerIndivIdentifier")){
			System.out.println(qName+":"+elementValue);
			mandatoryFields.setIssuerIndivIdentifier(elementValue);
		}
		
		else if(qName.equals("issuerSubscriberIdentifier")){
			System.out.println(qName+":"+elementValue);
			mandatoryFields.setIssuerSubscriberIdentifier(elementValue);
		    
				
		}
		else if(qName.equals("benefitEffectiveBeginDate")){
			System.out.println(qName+":"+elementValue);
			mandatoryFields.setBenefitEffectiveBeginDate(elementValue);
					
		}
		else if(qName.equals("lastPremiumPaidDate")){
			System.out.println(qName+":"+elementValue);
			mandatoryFields.setLastPremiumPaidDate(elementValue);
		}
		else if(qName.equals("benefitEffectiveEndDate")){
			System.out.println(qName+":"+elementValue);
			mandatoryFields.setBenefitEffectiveEndDate(elementValue);
		}
		else if(qName.equals("healthCoveragePolicyNo")){
			System.out.println(qName+":"+elementValue);
			mandatoryFields.setHealthCoveragePolicyNo(elementValue);
			
		}
		 if(qName.equals("lookupValueCode")){
				
				String temp=nodes.pop();
				String parentElement=nodes.peek();
				
				nodes.push(temp);
			    if(parentElement.equals("additionalMaintReason")){
					System.out.println(qName+":"+elementValue);
					if(elementValue.equals("CONFIRM")){
						currentEnrollmentStatus=elementValue;
						statusConfirmValidations();
					}
					else if(elementValue.equals("CANCEL")){
						statusCancelValidations();
					}
					else if(elementValue.equals("TERM")){
						statusTerminateValidations();
					}
					else{
						violations.add("Bad Status Code!");
					}
				
						
				}
				
			}
		
	
	}

	private void statusCancelValidations() {
		// TODO Auto-generated method stub
		
	}

	private void statusTerminateValidations() {
		// TODO Auto-generated method stub
		
	}

	private void statusConfirmValidations() {
		// TODO Auto-generated method stub
		
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
	private void checkViolation(String pattern,String qName){
		
		if(!elementValue.matches(pattern)){
			setValid(false);
			violations.add(qName+" is empty or invalid data!"+" Data:"+elementValue);
			}
		
	}
	private void aggregateData(String qName){
		
		if(qName.equals("healthCoverage")){
			enrollee.setHealthCoverage(healthCoverage);
			
		}
		else if(qName.equals("enrollee")){
			enrollment.getEnrollee().add(enrollee);
			
		}
		else if(qName.equals("enrollment")){
			enrollments.getEnrollment().add(enrollment);
			
		}
	}
	private void lookUpCodeCheck(String qName){
		
     if(qName.equals("lookupValueCode")){
			
			String temp=nodes.pop();
			String parentElement=nodes.peek();
			
			nodes.push(temp);
			if(parentElement.equals("maintenanceTypeCode")){
				System.out.println(qName+":"+elementValue);
				healthCoverage.getMaintanenceTypeCode().setLookupValueCode(elementValue);
				checkViolation(MAINTENANCE_TYPE_CODE_PATTERN, parentElement+"->"+qName);
				
			}
			else if(parentElement.equals("insuranceTypeLkp")){
				System.out.println(qName+":"+elementValue);
				healthCoverage.getInsuranceTypeLkp().setLookupValueCode(elementValue);
				checkViolation(INSURANCE_TYPE_LKP_PATTERN, parentElement+"->"+qName);
					
			}
			else if(parentElement.equals("additionalMaintReason")){
				System.out.println(qName+":"+elementValue);
				enrollee.getMemberReportingCategory().getAdditionalMaintReason().setLookupValueCode(elementValue);
				checkViolation(ADDITIONAL_MAINT_REASON_PATTERN, parentElement+"->"+qName);
			
					
			}
			
		}
	}

}