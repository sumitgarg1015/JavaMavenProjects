package pages;


import org.openqa.selenium.By;

import base.TestBase;

public class WHUserProfilePage extends TestBase{
	
	By userRatingsBy;

	public WHUserProfilePage() {
		
		userRatingsBy = By.xpath(OR.getProperty("ratings"));
	}
	
	
	public int getStarRatingCount() {
		return driver.findElements(userRatingsBy).size();
	}

}
