package pages;

import org.openqa.selenium.By;

import base.TestBase;

public class WHLoginPage extends TestBase{
	
	By emailBy, passBy, loginBy;
	
	public WHLoginPage(){
		
		emailBy = By.xpath(OR.getProperty("walUserId"));
		passBy = By.xpath(OR.getProperty("walpass"));
		loginBy = By.xpath(OR.getProperty("loginButton"));
	}
	
	public void typeUserEmail() {
		driver.findElement(emailBy).sendKeys(config.getProperty("wallEmailId"));
	}
	
	public void typeUserPass() {
		driver.findElement(passBy).sendKeys(config.getProperty("wallPassword"));
	}
	
	public void clickLoginButton() {
		driver.findElement(loginBy).click();
	}

}
