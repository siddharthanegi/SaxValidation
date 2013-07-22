package com.xor.main;

import java.io.IOException;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.xor.handler.CustomHandler;

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
			
			} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
