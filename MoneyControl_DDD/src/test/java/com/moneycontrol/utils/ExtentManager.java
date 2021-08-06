package com.moneycontrol.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	
	private static ExtentReports report;
	
	public static ExtentReports getInstance() {
		
		report = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("./target/extent-reports/extent.html");
		spark.config().setDocumentTitle("Extent Report (Data Driven Development)");
		spark.config().setReportName("MoneyControl.com");
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setEncoding("utf-8");
		report.attachReporter(spark);
		report.setSystemInfo("Automation Tester", "Sumit Garg");
		report.setSystemInfo("Organization", "MoneyControl.com");
		report.setSystemInfo("Build No", "M1222V23");
		return report;
		
	}

}
