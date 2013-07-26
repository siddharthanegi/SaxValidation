package com.xor.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.xor.model.MandatoryFields;

/**
 * @author negi_s
 * 
 */
public class DraftHandler extends DefaultHandler {
	/**
	 * elementValue : stores the text between an element tag. violations :
	 * stores a list of all violations encountered during parsing. valid :
	 * boolean to check the validity of the XML doc. nodes : stack to store the
	 * elements encountered while parsing. mandatoryFields : object which stores
	 * the values of the required elements from the XML doc.
	 * currentEnrollmentStatus : status of the current enrollment block being
	 * parsed.
	 */
	private String elementValue;
	private List<String> violations;
	private boolean valid;
	private Stack<String> nodes;
	private MandatoryFields mandatoryFields;
	private String currentEnrollmentStatus;
	private int enrollmentId;

	/**
	 * Patterns to be used to check for validity.
	 */
	private static final String ISSUER_IDENTIFIER_PATTERN = "[0-9]{10}";
	private static final String DATE_PATTERN = "^(19|20)[0-9]{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])$";
	private static final String HEALTH_COVERAGE_POLICY_NUMBER_PATTERN = "[0-9]{6}";

	/**
	 * Default Constructor
	 */
	public DraftHandler() {
		elementValue = "";
		violations = new ArrayList<String>();
		valid = true;
		nodes = new Stack<String>();
		mandatoryFields = new MandatoryFields();
		currentEnrollmentStatus = "";
	}

	/**
	 * Receive notification of character data inside an element. By default, do
	 * nothing. Application writers may override this method to take specific
	 * actions for each chunk of character data (such as adding the data to a
	 * node or buffer, or printing it to a file).
	 * 
	 * @param ch
	 *            The characters.
	 * @param start
	 *            The start position in the character array.
	 * @param length
	 *            The number of characters to use from the character array.
	 */
	public void characters(char ch[], int start, int length)
			throws SAXException {

		char[] cloneElementValue = Arrays.copyOf(ch, ch.length);
		elementValue = new String(cloneElementValue, start, length);
	}

	/**
	 * Receive notification of the start of an element.
	 * 
	 * @param uri
	 *            The Namespace URI, or the empty string if the element has no
	 *            Namespace URI or if Namespace processing is not being
	 *            performed.
	 * @param localName
	 *            The local name (without prefix), or the empty string if
	 *            Namespace processing is not being performed.
	 * @param qName
	 *            The qualified name (with prefix), or the empty string if
	 *            qualified names are not available.
	 * @param attributes
	 *            The attributes attached to the element. If there are no
	 *            attributes, it shall be an empty Attributes object.
	 */
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		elementValue = "";
		nodes.push(qName);
		if (qName.equals("enrollment")) {
			currentEnrollmentStatus = "BEGIN"; // Flag variable to get data of
												// the subscriber.
		}

	}

	/**
	 * Receive notification of the end of an element.
	 * 
	 * @param uri
	 *            The Namespace URI, or the empty string if the element has no
	 *            Namespace URI or if Namespace processing is not being
	 *            performed.
	 * @param localName
	 *            The local name (without prefix), or the empty string if
	 *            Namespace processing is not being performed.
	 * @param qName
	 *            The qualified name (with prefix), or the empty string if
	 *            qualified names are not available.
	 */
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		if (currentEnrollmentStatus.equals("BEGIN")
				|| currentEnrollmentStatus.equals("CONFIRM")) {

			getMandatoryElements(qName); // Get element values for validations
											// at the end of enrollee tag.

		}
		if (qName.equals("lookupValueCode")) {

			String temp = nodes.pop();
			String parentElement = nodes.peek();

			nodes.push(temp);
			if (parentElement.equals("additionalMaintReason")) {
				System.out.println(qName + ":" + elementValue);
				currentEnrollmentStatus = elementValue;
				statusCheckandValidation(); // Check for status of enrollment
											// and perform validations at the
											// subscriber level.

			}

		}
		if (qName.equals("enrollee")
				&& currentEnrollmentStatus.equals("CONFIRM")
				&& mandatoryFields.getSubscriberFlag().equals("N")) {
			statusConfirmValidations(); // Validations at member level (only for
										// status="CONFIRM").
		}
		if (qName.equals("id")) {
			enrollmentId = Integer.parseInt(elementValue); // Get the current
															// enrollment ID.
		}

	}

	/**
	 * Checks the current status of the enrollment and performs subscriber level
	 * validations.
	 */
	private void statusCheckandValidation() {

		if (elementValue.equals("CONFIRM")) {

			statusConfirmValidations();
		} else if (elementValue.equals("CANCEL")) {
			statusCancelValidations();
		} else if (elementValue.equals("TERM")) {
			statusTerminateValidations();
		} else {
			setValid(false);
			violations.add("Bad Status Code!");
		}

	}

	/**
	 * Store the mandatory field data sequentially into the mandatoryField
	 * object for validations at the member level.Only for enrollments with
	 * status : "CONFIRM".
	 * 
	 * @param qName
	 *            current element name.
	 */
	private void getMandatoryElements(String qName) {

		if (qName.equals("subscriberFlag")) {
			System.out.println(qName + ":" + elementValue);
			mandatoryFields.setSubscriberFlag(elementValue);
		} else if (qName.equals("issuerIndivIdentifier")) {
			System.out.println(qName + ":" + elementValue);
			mandatoryFields.setIssuerIndivIdentifier(elementValue);
		}

		else if (qName.equals("issuerSubscriberIdentifier")) {
			System.out.println(qName + ":" + elementValue);
			mandatoryFields.setIssuerSubscriberIdentifier(elementValue);

		} else if (qName.equals("benefitEffectiveBeginDate")) {
			System.out.println(qName + ":" + elementValue);
			mandatoryFields.setBenefitEffectiveBeginDate(elementValue);

		} else if (qName.equals("lastPremiumPaidDate")) {
			System.out.println(qName + ":" + elementValue);
			mandatoryFields.setLastPremiumPaidDate(elementValue);
		} else if (qName.equals("benefitEffectiveEndDate")) {
			System.out.println(qName + ":" + elementValue);
			mandatoryFields.setBenefitEffectiveEndDate(elementValue);
		} else if (qName.equals("healthCoveragePolicyNo")) {
			System.out.println(qName + ":" + elementValue);
			mandatoryFields.setHealthCoveragePolicyNo(elementValue);

		}

	}

	/**
	 * Subscriber level validations for enrollments with status : "CANCEL".
	 */
	private void statusCancelValidations() {

		System.out.println("\n*Cancel Validations*");
		checkCommonIdViolations();
		checkViolation(DATE_PATTERN,
				mandatoryFields.getBenefitEffectiveEndDate(),
				"benefitEffectiveEndDate");
	}

	/**
	 * Subscriber level validations for enrollments with status : "TERM".
	 */
	private void statusTerminateValidations() {

		System.out.println("\n*Terminate Validations*");
		checkViolation(DATE_PATTERN,
				mandatoryFields.getBenefitEffectiveEndDate(),
				"benefitEffectiveEndDate");
		checkViolation(DATE_PATTERN, mandatoryFields.getLastPremiumPaidDate(),
				"lastPremiumPaidDate");
	}

	/**
	 * Subscriber level validations for enrollments with status : "CONFIRM".
	 */
	private void statusConfirmValidations() {

		System.out.println("\n*Confirm Validations*");
		checkCommonIdViolations();
		checkViolation(DATE_PATTERN,
				mandatoryFields.getBenefitEffectiveBeginDate(),
				"benefitEffectiveBeginDate");
		checkViolation(DATE_PATTERN, mandatoryFields.getLastPremiumPaidDate(),
				"lastPremiumPaidDate");
		checkViolation(HEALTH_COVERAGE_POLICY_NUMBER_PATTERN,
				mandatoryFields.getHealthCoveragePolicyNo(),
				"healthCoveragePolicyNo");

	}

	/**
	 * Validation for elements common between various statuses.
	 */
	private void checkCommonIdViolations() {

		checkViolation(ISSUER_IDENTIFIER_PATTERN,
				mandatoryFields.getIssuerIndivIdentifier(),
				"issuerIndivIdentifier");
		checkViolation(ISSUER_IDENTIFIER_PATTERN,
				mandatoryFields.getIssuerSubscriberIdentifier(),
				"issuerSubscriberIdentifier");
	}

	/**
	 * Getter for violations present in the XML doc.
	 * 
	 * @return violations.
	 */
	public List<String> getViolations() {
		return violations;
	}

	/**
	 * Getter for the boolean valid which holds the validity of the XMl doc.
	 * 
	 * @return valid.
	 */
	public boolean isValid() {
		return valid;
	}

	/**
	 * Setter for boolean valid.
	 * 
	 * @param valid
	 *            .
	 */
	public void setValid(boolean valid) {
		this.valid = valid;
	}

	/**
	 * Checks for violations in the passed elementValue according to the regular
	 * expression provided. If a violation is found, the boolean valid is set to
	 * false and the violation is added to the violations list.
	 * 
	 * @param pattern
	 *            Regular Expression.
	 * @param elementValue
	 *            Element value to be checked.
	 * @param elementName
	 *            Element name.
	 */
	private void checkViolation(String pattern, String elementValue,
			String elementName) {

		if (!elementValue.matches(pattern)) {
			setValid(false);
			violations.add("Enrollment ID:" + enrollmentId + " Field:"
					+ elementName + "\nViolation:Empty or invalid data!"
					+ " Data:" + elementValue + " Expected:" + pattern + "\n");
		}

	}

}
