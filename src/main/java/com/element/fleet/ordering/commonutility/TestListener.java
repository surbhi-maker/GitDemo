package com.element.fleet.ordering.commonutility;

import java.util.List;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestResult;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlTest;

import com.ge.capital.rainbow.testng.PDFReporter;

public class TestListener extends PDFReporter implements ISuiteListener {

	public TestListener() throws Throwable {
		super();
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Into Failure Listener");
		super.onTestFailure(result);
	}

	@Override
	public void onStart(ISuite suite) {
		String foUrl = suite.getParameter("applicationURL");
		String boUrl = suite.getParameter("applicationBOURL");
		String username = suite.getParameter("username");
		String waitTime = suite.getParameter("waitTime");
		System.out.println("FO URL: " + foUrl);
		System.out.println("BO URL: " + boUrl);
		System.out.println("Username: " + username);
		System.out.println("Wait time: " + waitTime);
		System.out.println("Suite name: " + suite.getName());
		System.out.println("Tests Name: ");
		List<XmlTest> xmlTestsList = suite.getXmlSuite().getTests();
		for(XmlTest xmlTest: xmlTestsList) {
			System.out.println("Test: " + xmlTest.getName());
			for(XmlClass xmlClasses: xmlTest.getClasses()) {
				System.out.println("Class: " + xmlClasses.getName());
			}
		}
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Suite finished");
	}
	
}
