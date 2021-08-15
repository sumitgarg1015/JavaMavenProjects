package com.moneycontrol.testcases;

import static com.moneycontrol.utils.TestUtil.*;

import org.testng.annotations.Test;

public class HomePageTestCases {

	@Test
	public void login_Account() {
		
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


}
