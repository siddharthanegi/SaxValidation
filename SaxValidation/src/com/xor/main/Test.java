package com.xor.main;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.xor.handler.DraftHandler;

public final class Test {

	/**
	 * @author Siddhartha Negi
	 */
	
	private Test(){}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			
			XMLReader reader= XMLReaderFactory.createXMLReader("com.sun.org.apache.xerces.internal.parsers.SAXParser");
			reader.setContentHandler(new DraftHandler());
			reader.parse("main-sample.xml");
			DraftHandler currentHandler=(DraftHandler) reader.getContentHandler();
			System.out.println("\n**********************************\n"+currentHandler.isValid()+"\n**********************************\n");
			
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
