package com.moneycontrol.testcases;

import static com.moneycontrol.utils.TestUtil.*;

import org.testng.annotations.Test;

public class HomePageTestCases {

	@Test
	public void login_Account() {

		click("moneyControlLink_link");
		log.info("Click on Login Link");

		hoverMouse("login_xpath");
		log.info("Mouse hovering on login link");

		click("loginButton_css");
		log.info("Click on Login button");

		waitForElement("frame_id");
		switchToFrame("frame_id");
		log.info("Switch to Frame");

		waitForElement("email_css");

		type("email_css", "userid");
		log.info("Enter User ID");

		switchToDefault();

	}

}
