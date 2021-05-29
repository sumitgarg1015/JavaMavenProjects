package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.TestBase;

public class WHReviewConfirmationPage extends TestBase{
	
	By reviewConfirmBy, profileButtonBy, profileItemBy;
	
	public WHReviewConfirmationPage(){
		
		reviewConfirmBy = By.tagName(OR.getProperty("reviewConfirmation"));
		profileButtonBy = By.xpath(OR.getProperty("profileLink"));
		profileItemBy = By.xpath(OR.getProperty("itemProfileLink"));
	}
	
	public String getConfirmationMessage() {
		
		return driver.findElement(reviewConfirmBy).getText();
		
	}
	
	public void hoverMouseOverUserID() {
		act.moveToElement(driver.findElement(profileButtonBy)).perform();
	}
	
	public void clickProfileLink() {
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(profileItemBy)));
		act.moveToElement(driver.findElement(profileItemBy)).click().build().perform();
	}
	
}
