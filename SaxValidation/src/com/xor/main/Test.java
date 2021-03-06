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

public class Test {

	/**
	 * @param args
	 */
	private static final String XML_READER_IMPL= "org.apache.crimson.parser.XMLReaderImpl";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			
			XMLReader reader= XMLReaderFactory.createXMLReader(XML_READER_IMPL);
			reader.setContentHandler(new CustomHandler());
			reader.parse("sample.xml");
			CustomHandler currentHandler=(CustomHandler) reader.getContentHandler();
			System.out.println(currentHandler.isValid());
			/* Printing unmarshalled object
			 * 
			 */
			Enrollments enrollments=currentHandler.getEnrollments();
			System.out.println("<enrollments>");
			
			int i;
			List<Enrollment>enrollment=enrollments.getEnrollment();
			for(i=0;i<enrollment.size();i++){
				System.out.println("<enrollment>");
				System.out.println(enrollment.get(i).getSponsorEIN());
				System.out.println(enrollment.get(i).getInsurerCMSPlanID());
//				List<Enrollee>enrollee=enrollment.get(i).getEnrollee();
//				int j=0;
				Iterator<Enrollee> it=enrollment.get(i).getEnrollee().iterator();
				while(it.hasNext()){
				
					System.out.println("<enrollee>");
					System.out.println(it.next().getExchgSubscribertIdentifier());
					
				}
				
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
