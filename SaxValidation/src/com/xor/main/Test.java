package com.xor.main;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.xor.handler.CustomHandler;
import com.xor.model.Enrollee;
import com.xor.model.Enrollment;
import com.xor.model.Enrollments;

public final class Test {

	/**
	 * @param args
	 */
	
	private Test(){}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			
			XMLReader reader= XMLReaderFactory.createXMLReader();
			reader.setContentHandler(new CustomHandler());
			reader.parse("main-sample.xml");
			CustomHandler currentHandler=(CustomHandler) reader.getContentHandler();
			System.out.println("\n**********************************\n"+currentHandler.isValid()+"\n**********************************\n");
			
			/* Printing unmarshalled object
			 * 
			 */
			Enrollments enrollments=currentHandler.getEnrollments();
			
			int i;
			List<Enrollment>enrollment=enrollments.getEnrollment();
			for(i=0;i<enrollment.size();i++){
						
				List<Enrollee>enrollee=enrollment.get(i).getEnrollee();
				Iterator<Enrollee> it=enrollee.iterator();
				while(it.hasNext()){
					Enrollee en=it.next();
					System.out.println(en.getIssuerIndivIdentifier());
					System.out.println(en.getHealthCoverage().getInsuranceTypeLkp().getLookupValueCode());
					System.out.println(en.getMemberReportingCategory().getAdditionalMaintReason().getLookupValueCode());
									
				}
				
			}

			/*Get the violations !
			 * 
			 */
			
			List<String>violations=currentHandler.getViolations();
			Iterator<String> it=violations.iterator();
			while(it.hasNext()){
				System.out.println(it.next());
			}
					
		
		
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
