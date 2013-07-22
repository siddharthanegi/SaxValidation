package com.xor.handler;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;



public class CustomHandler extends DefaultHandler {

	private String elementValue;
	private List<String> violations;
	private boolean valid;
	public CustomHandler() {
		elementValue = "";
		violations=new ArrayList<String>();
		setValid(true);
	}

	public void characters(char ch[], int start, int length)
			throws SAXException {

		elementValue = new String(ch, start, length);
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		
		elementValue = "";
		if (qName.equals("enrollee")) {
			//System.out.println("Yo");
		}
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
	
		if(qName.equals("sponsorEIN")){
			System.out.println(qName+":"+elementValue);
			if(!elementValue.matches("[0-9]{9}")){
			//System.out.println(qName+" is empty or invalid data!");
			violations.add(qName+" is empty or invalid data!");
			setValid(false);
			}
			}
		if(qName.equals("insurerCMSPlanID")){
			System.out.println(qName+":"+elementValue);
			if(!elementValue.matches("[0-9]{7}")){
				//System.out.println(qName+" is empty or invalid data!");
				violations.add(qName+" is empty or invalid data!");
				setValid(false);
				}
		}
		if(qName.equals("exchgSubscriberIdentifier")){
			System.out.println(qName+":"+elementValue);
			if(!elementValue.matches("[0-9]{10}")){
				//System.out.println(qName+" is empty or invalid data!");
				violations.add(qName+" is empty or invalid data!");
				setValid(false);
			}
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

}
