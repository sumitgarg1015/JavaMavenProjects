package com.moneycontrol.testcases;

import static com.moneycontrol.base.TestBase.softAssert;
import static com.moneycontrol.utils.TestUtil.*;

import java.util.HashMap;

import org.testng.annotations.Test;

import com.moneycontrol.utils.ExcelOps;

public class IncomeTaxCalculator {
	
	@Test
	public void openIncomeTaxCalculator() {
		
		hoverMouse("personalFinance_css");
		waitForElement("incomeTax_css");
		click("incomeTax_css");
		
		verifyContent("headingCalculator_xpath","incomeTax");
		softAssert.assertAll();
		
	}
	
	@Test(dependsOnMethods = {"openIncomeTaxCalculator"}, dataProviderClass = ExcelOps.class, dataProvider = "dp")
	public void calculateTax(HashMap<String, String> data) {
		
		type("taxableIncome_css", data.get("Taxable_Amount"));
		
		selectItemUsingSelect("taxProfile_css", data.get("Tax_Profile"));
		
		click("calculateTax_xpath");
		
		String taxAmount = getTextData("taxAmount_css");
		String taxRate = getTextData("taxRate_css");
		
		System.out.println(taxAmount + ":" + taxRate);
		
	}

}
