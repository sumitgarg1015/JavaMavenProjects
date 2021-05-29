package pages;

import org.openqa.selenium.By;

import base.TestBase;

public class FacebookHomePage extends TestBase{
	
	By userIdBy, passBy, loginBy;
	
	public FacebookHomePage() {
		
		if(driver==null) {
			setUp();
		}
		
		userIdBy = By.xpath(OR.getProperty("userid"));
		passBy = By.xpath(OR.getProperty("pass"));
		loginBy = By.xpath(OR.getProperty("login"));
	}
	
	public void typeUserId() {
		driver.findElement(userIdBy).sendKeys(config.getProperty("faceLoginId"));
	}
	
	public void typePass() {
		driver.findElement(passBy).sendKeys(config.getProperty("facePassword"));
	}
	
	public void clickLoginButton() {
		driver.findElement(loginBy).click();
	}
	
}
