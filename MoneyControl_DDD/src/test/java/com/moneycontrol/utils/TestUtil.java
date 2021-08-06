package com.moneycontrol.utils;

import java.io.File;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.moneycontrol.base.TestBase;

public class TestUtil extends TestBase {

	private static String screenShotPath;

	public static WebElement getElement(String locatorProperty) {

		WebElement element = null;
		By locator = null;

		String prefix = locatorProperty.substring(locatorProperty.indexOf("_") + 1);

		if (prefix.equalsIgnoreCase("xpath")) {
			locator = By.xpath(OR.getProperty(locatorProperty));
		} else if (prefix.equalsIgnoreCase("css")) {
			locator = By.cssSelector(OR.getProperty(locatorProperty));
		} else if (prefix.equalsIgnoreCase("id")) {
			locator = By.id(OR.getProperty(locatorProperty));
		} else if (prefix.equalsIgnoreCase("tagName")) {
			locator = By.tagName(OR.getProperty(locatorProperty));
		} else if (prefix.equalsIgnoreCase("name")) {
			locator = By.name(OR.getProperty(locatorProperty));
		} else if (prefix.equalsIgnoreCase("link")) {
			locator = By.partialLinkText(OR.getProperty(locatorProperty));
		}

		element = driver.findElement(locator);
		return element;

	}

	// Click on the WebElement
	public static void click(String locatorProperty) {
		getElement(locatorProperty).click();
		test.log(Status.PASS, "Clicked On " + locatorProperty);
		log.info("Clicked on " + locatorProperty);
	}

	// Sending any text to WebElement
	public static void type(String locatorProperty, String value) {
		getElement(locatorProperty).clear();
		getElement(locatorProperty).sendKeys(value);
		test.log(Status.PASS, value + " typed in " + locatorProperty);
		log.info("Entered " + value + " in " + locatorProperty);
	}

	// Mouse hovering on WebElement
	public static void hoverMouse(String locatorProperty) {
		act.moveToElement(getElement(locatorProperty)).build().perform();
		test.log(Status.PASS, "Hovered Mouse over " + locatorProperty);
		log.info("Mouse Hovered over " + locatorProperty);
	}

	// Select the text from the dropdown using Select class
	public static void selectItemUsingSelect(String locatorProperty, String value) {
		select = new Select(getElement(locatorProperty));
		select.selectByVisibleText(value);
		test.log(Status.PASS, value + " selected in " + locatorProperty + " dropdown");
	}

	// Getting any Text from WebElement
	public static String getTextData(String locatorProperty) {
		String textData = getElement(locatorProperty).getText();
		test.log(Status.PASS, textData + " was fetched from " + locatorProperty);
		return textData;
	}

	// Wait for WebElement to be visible
	public static void waitForElement(String locatorProperty) {
		wait.until(ExpectedConditions.visibilityOf(getElement(locatorProperty)));
		log.info("Waiting for " + locatorProperty + " to be visible");
	}

	// Switch to frame
	public static void switchToFrame(String locatorProperty) {
		waitForElement(locatorProperty);
		driver.switchTo().frame(getElement(locatorProperty));
		log.info("Switched to Frame: " + locatorProperty);
	}

	// Switch to main Window
	public static void switchToDefault() {
		driver.switchTo().defaultContent();
		log.info("Switched to Main Window");
	}

	// Verify Text on the Webpage
	public static void verifyContent(String locatorProperty, String textToCompareProperty) {
		String textToVerify = getElement(locatorProperty).getText();
		softAssert.assertTrue(textToVerify.contains(config.getProperty(textToCompareProperty)));
	}

	// Capture Screenshot
	public static String captureScreenshot() {

		d = new Date();
		String filename = d.toString().replace(" ", "_").replace(":", "_"); 
		screenShotPath = ".//target//extent-reports//" + filename + ".PNG";
		TakesScreenshot screen = (TakesScreenshot)driver;
		File src = screen.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File(screenShotPath));
		}catch (Exception e) {
			System.out.println(e);
		}

		return screenShotPath;
	}

	public static boolean isElementPresent(String locatorProperty) {

		boolean flag;
		try {
			flag = getElement(locatorProperty).isDisplayed();
		} catch (Exception e) {
			flag = false;
		}
		
		return flag;
	}

}
