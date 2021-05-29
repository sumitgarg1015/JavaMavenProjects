package pages;

import org.openqa.selenium.By;

import base.TestBase;

public class WHHomePage extends TestBase{
	
	By loginLinkBy, myWalletBy, reviewTabBy, emptyRating4By, filledRating4By;
	
	public WHHomePage() {
		
		loginLinkBy = By.xpath(OR.getProperty("loginLink"));
		myWalletBy = By.xpath(OR.getProperty("myWalletLink"));
		reviewTabBy = By.xpath(OR.getProperty("reviews"));
		emptyRating4By = By.xpath(OR.getProperty("emptyrating4"));
		filledRating4By = By.xpath(OR.getProperty("filledrating4"));
	}
	
	
	public void clickLoginLink() {
		driver.findElement(loginLinkBy).click();
	}
	
	public boolean verifyWalletIsDisplayed() {
		
		boolean result = driver.findElement(myWalletBy).isDisplayed();
		return result;
	}
	
	public void clickReviewTab() throws Exception {
		driver.findElement(reviewTabBy).click();
		Thread.sleep(3000);
	}
	
	public void hoverMouseOverRating() {
		act.moveToElement(driver.findElement(emptyRating4By)).build().perform();
	}
	
	public boolean verifyRatingIsGlowing() {
		boolean result;
		result = driver.findElement(filledRating4By).isDisplayed();
		return result;
	}
	
	public void performRatingSelection(){
		act.moveToElement(driver.findElement(filledRating4By)).click().build().perform();
	}
	
}
