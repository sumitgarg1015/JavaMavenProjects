package com.moneycontrol.testcases;

import static com.moneycontrol.utils.TestUtil.*;

import java.util.HashMap;

import org.testng.annotations.Test;

import com.moneycontrol.utils.ExcelOps;
import static com.moneycontrol.utils.ExcelOps.*;

public class HomePageTestCases {

	@Test(enabled=false)
	public void login_Account() {
		click("moneyControlLink_link");
		hoverMouse("login_xpath");

		click("loginButton_css");

		waitForElement("frame_id");
		switchToFrame("frame_id");

		waitForElement("email_css");

		//TestUtil class inherits the members of TestBase class 
		//Therefore all static members of Testbase can be utilized here
		type("email_css", config.getProperty("userid")); 

		switchToDefault();
		
		click("close_css");

	}
	
	@Test
	public void openIncomeTaxCalculator() {
		
		click("moneyControlLink_link");
		hoverMouse("personalFinance_css");
		waitForElement("incomeTax_css");
		click("incomeTax_css");
		
		verifyContent("headingCalculator_xpath","incomeTax");
		softAssert.assertAll();
		
	}
	
	@Test(dependsOnMethods = {"openIncomeTaxCalculator"}, dataProviderClass=ExcelOps.class, dataProvider="dp")
	public void calculateTax(HashMap<String, String> data) {
		
		type("taxableIncome_css", data.get("Taxable_Amount"));
		
		selectItemUsingSelect("taxProfile_css", data.get("Tax_Profile"));
		
		click("calculateTax_xpath");
		
		String taxAmount = getTextData("taxAmount_css");
		String taxRate = getTextData("taxRate_css");
		
		System.out.println(taxAmount + ":" + taxRate);
		
	}

}
