package com.moneycontrol.listeners;

import static com.moneycontrol.utils.TestUtil.*;

import java.util.Arrays;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.moneycontrol.base.TestBase;


public class TestListeners extends TestBase implements ITestListener {
	
	private String logMessage;
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName()+ " - Test Case: " + result.getName());
	}

	/**
	 * Invoked each time a test succeeds.
	 *
	 * @param result <code>ITestResult</code> containing information about the run
	 *               test
	 * @see ITestResult#SUCCESS
	 */
	public void onTestSuccess(ITestResult result) {
		
		logMessage = "<b> Test Passed: " + result.getName().toUpperCase() + "</b>";
		Markup m = MarkupHelper.createLabel(logMessage, ExtentColor.GREEN);
		test.pass(m);
	}

	/**
	 * Invoked each time a test fails.
	 *
	 * @param result <code>ITestResult</code> containing information about the run
	 *               test
	 * @see ITestResult#FAILURE
	 */
	public void onTestFailure(ITestResult result) {
		
		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		
		String logMessage = "<details><summary><b><font color=red>"
				+ "Exception Occurred, Click here to see details:"
				+ "</font></b></summary>" + exceptionMessage.replaceAll(",", "<br>") 
				+ "</details>";
//		test.log(Status.FAIL, );
		test.fail(logMessage + "<b><font color=blue>Attached Screenshot:", 
				MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot()).build());
		
//		test.failfail("Test Failed: " + result.getName().toUpperCase(), media);

	}

	/**
	 * Invoked each time a test is skipped.
	 *
	 * @param result <code>ITestResult</code> containing information about the run
	 *               test
	 * @see ITestResult#SKIP
	 */
	public void onTestSkipped(ITestResult result) {
		// not implemented
	}

	/**
	 * Invoked before running all the test methods belonging to the classes inside
	 * the &lt;test&gt; tag and calling all their Configuration methods.
	 *
	 * @param context The test context
	 */
	public void onStart(ITestContext context) {
		// not implemented
	}

	/**
	 * Invoked after all the test methods belonging to the classes inside the
	 * &lt;test&gt; tag have run and all their Configuration methods have been
	 * called.
	 *
	 * @param context The test context
	 */
	public void onFinish(ITestContext context) {
		// not implemented
	}

}
