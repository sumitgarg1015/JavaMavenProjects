package com.moneycontrol.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.moneycontrol.utils.ExtentManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Actions act;
	private FileInputStream file;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static Logger log = Logger.getLogger(TestBase.class);
	public static ExtentReports extent = ExtentManager.getInstance();
	public static ExtentTest test;
	public static SoftAssert softAssert = new SoftAssert();
	public static Select select;
	public static String fileName="";
	public static Date d;
	@BeforeSuite
	public void setUp() throws IOException {

		if (driver == null) {

//			System.setProperty("log4j.configurationFile", "./resources/properties/log4j2.properties");
			
			d = new Date();

			fileName = d.toString().replace(" ", "_").replace(":", "_");
			System.setProperty("current.date", fileName);

			PropertyConfigurator.configure("./src/test/resources/properties/log4j.properties");
			
			log.info("Initialize Set Up");
			
			file = new FileInputStream("./src/test/resources/properties/config.properties");
			config.load(file);
			log.info("Config File loaded!");
			
			file = new FileInputStream("./src/test/resources/properties/OR.properties");
			OR.load(file);
			log.info("Object Repository Loaded");
			
			WebDriverManager.chromedriver().setup();
			log.info("Driver is Set up");
			
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicitWait")),
					TimeUnit.SECONDS);
			log.info("Driver initialized");
			
			driver.manage().deleteAllCookies();
			
			wait = new WebDriverWait(driver, Integer.parseInt(config.getProperty("explicitWait")));
			act = new Actions(driver);
			log.info("Initialized ExplicitWait and Actions class");
			
			driver.get(config.getProperty("appUrl"));
			log.info("Application Launched!");

		}

	}

	@AfterSuite
	public void tearDown() {

		if (driver != null) {
			driver.close();
			driver.quit();
			
			log.info("Driver quit");
			extent.flush();
		}

	}

}
