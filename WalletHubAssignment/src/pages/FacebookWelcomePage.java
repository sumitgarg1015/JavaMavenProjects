package pages;

import org.openqa.selenium.By;

import base.TestBase;

public class FacebookWelcomePage extends TestBase{
	
	By logoBy;
	
	public FacebookWelcomePage() {
		
		if(driver==null) {
			setUp();
		}
		
		logoBy = By.xpath(OR.getProperty("logo"));
	}
	
		
	public boolean verifyLogo() {
		boolean result =  driver.findElement(logoBy).isDisplayed();
		return result;
	}
	
	

}
