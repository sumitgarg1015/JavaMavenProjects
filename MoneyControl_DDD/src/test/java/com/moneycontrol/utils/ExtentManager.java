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
		report.attachReporter(spark);
		return report;
		
	}

}
