package com.moneycontrol.testcases;

import static com.moneycontrol.base.TestBase.softAssert;
import static com.moneycontrol.utils.TestUtil.*;

import java.util.HashMap;

import org.testng.annotations.Test;

import com.moneycontrol.utils.ExcelOps;

public class CarLoanCalculator {
	
	@Test
	public void openCarLoanCalculator() {
		
		hoverMouse("personalFinance_css");
		waitForElement("carLoanLink_css");
		click("carLoanLink_css");
		verifyContent("carLoanTitle_css","carLoan");
		softAssert.assertAll();
	}
	
	
	@Test(dependsOnMethods = {"openCarLoanCalculator"}, dataProviderClass = ExcelOps.class, dataProvider = "dp")
	public void calculateCarLoan(HashMap<String, String> map) {
		
		type("carLoanAmount_css", map.get("Loan_Amount"));
		type("carLoanPeriod_css", map.get("Loan_Period"));
		selectItemUsingSelect("emiStartsFrom_css", map.get("EMI_Starts_From"));
		type("carLoanInterestRate_css", map.get("Interest_Rate"));
		type("upfrontCharges_css", map.get("Upfront_Charges"));
		click("submitCarLoan_xpath");
		
		System.out.println();
		System.out.println("Car Loan Details as follows:-");
		System.out.print("Car Loan Amount:" + getTextData("totalPayment_css") + ", " 
		+ "Interest and Charges Payment:" + getTextData("interestCharges_css") + ", "
		+ "EMI:" + getTextData("carEMI_css"));
		
	}

}
