package com.api.reports;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReport {
	
	private ExtentReport() {}
	
	public static ExtentReports extent;
	
	public static void initReports() {
		if(Objects.isNull(ExtentManager.getExtentTest())) {
			
		String timeStamp = new SimpleDateFormat("yyy.MM.dd.HH.mm.ss").format(new Date());
		String reportName = "Extent-Report-" + timeStamp + ".html";
		extent = new ExtentReports();
		ExtentSparkReporter sprak = new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/"+reportName);
		
		sprak.config().setTheme(Theme.DARK);
		sprak.config().setReportName("API AutomationReport");
		sprak.config().setDocumentTitle("API AutomationReport");
		extent.attachReporter(sprak);
		}
	}
	
	public static void tearDownReports() {
		if(Objects.nonNull(ExtentManager.getExtentTest())) {
		//Flush method will write all the logs to the report
		extent.flush();
		ExtentManager.unLoad();
		}
	}
	
	public static void createTest(String testcasename){
		ExtentTest test = extent.createTest(testcasename);
		ExtentManager.setExtentTest(test);
		
	}
}
