package com.moneycontrol.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.Status;
import com.moneycontrol.base.TestBase;

public class TestUtil extends TestBase{
	
public static WebElement getElement(String locatorProperty) {
		
		WebElement element=null;
		By locator = null;
		
		String prefix = locatorProperty.substring(locatorProperty.indexOf("_"));
		
		if(prefix.equalsIgnoreCase("_xpath")) {
			locator = By.xpath(OR.getProperty(locatorProperty));
		}else if(prefix.equalsIgnoreCase("_css")) {
			locator = By.cssSelector(OR.getProperty(locatorProperty));
		}else if(prefix.equalsIgnoreCase("_id")) {
			locator = By.id(OR.getProperty(locatorProperty));
		}else if(prefix.equalsIgnoreCase("_tagName")) {
			locator = By.tagName(OR.getProperty(locatorProperty));
		}else if(prefix.equalsIgnoreCase("_name")) {
			locator = By.name(OR.getProperty(locatorProperty));
		}else if(prefix.equalsIgnoreCase("_link")){
			locator = By.partialLinkText(OR.getProperty(locatorProperty));
		}
		
		element = driver.findElement(locator);
		return element;
		
	}

	public static void click(String locatorProperty) {
		getElement(locatorProperty).click();
		test.log(Status.PASS, "Clicked On " + locatorProperty);
	}
	
	public static void type(String locatorProperty, String valueProperty) {
		getElement(locatorProperty).sendKeys(config.getProperty(valueProperty));
		test.log(Status.PASS, valueProperty + " typed in " + locatorProperty);
	}
	
	public static void hoverMouse(String locatorProperty) {
		act.moveToElement(getElement(locatorProperty)).build().perform();
	}
	
	public static void waitForElement(String locatorProperty) {
		wait.until(ExpectedConditions.visibilityOf(getElement(locatorProperty)));
	}
	
	public static void switchToFrame(String locatorProperty) {
		waitForElement(locatorProperty);
		driver.switchTo().frame(getElement(locatorProperty));
	}
	
	public static void switchToDefault() {
		driver.switchTo().defaultContent();
	}

}
