package com.xor.main;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.xor.handler.DraftHandler;

/**
 * @author negi_s
 * 
 */
public final class Test {

	private static final String BASE_URI = "/Documents and Settings/negi_s/Desktop/";
	private static final String XML_SOURCE = "/Documents and Settings/negi_s/Desktop/XML Location/";

	private Test() {
	}

	public static void main(String[] args) {

		try {

			XMLReader reader = XMLReaderFactory
					.createXMLReader("com.sun.org.apache.xerces.internal.parsers.SAXParser");
			int i;
			int numberOfFiles=new File(XML_SOURCE).listFiles().length;
			for (i = 1; i <=numberOfFiles; i++) {
				reader.setContentHandler(new DraftHandler());
				reader.parse(XML_SOURCE+i + ".xml");
				DraftHandler currentHandler = (DraftHandler) reader
						.getContentHandler();

				System.out.println("\n**********************************\n"
						+ currentHandler.isValid()
						+ "\n**********************************\n");
				/*
				 * Move file according to validity
				 */

				moveXmlToLocation(currentHandler.isValid(), i);

				/*
				 * Get the violations !
				 */

				List<String> violations = currentHandler.getViolations();
				Iterator<String> it = violations.iterator();
				while (it.hasNext()) {
					System.out.println(it.next());
				}
			}

		} catch (SAXException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private static void moveXmlToLocation(boolean valid, int i) {

		File xml = new File(XML_SOURCE + i + ".xml");
		String folder = "";
		if (valid) {
			folder = "Pass/";
		} else {
			folder = "Fail/";
		}

		if (xml.renameTo(new File(BASE_URI + folder + xml.getName()))) {
			System.out.println("File is moved successful!");
		} else {
			System.out.println("File is failed to move!");
		}

	}
}
