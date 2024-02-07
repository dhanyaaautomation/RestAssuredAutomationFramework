package com.api.listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.api.reports.ExtentLogger;
import com.api.reports.ExtentReport;


public class ListenerClass implements ITestListener, ISuiteListener {
	
	public void onStart(ISuite suite) { //Similar to Before Suite
		ExtentReport.initReports();
	}

	public void onFinish(ISuite suite) { //Similar to After Suite
		ExtentReport.tearDownReports();
	}

	public void onTestStart(ITestResult result) { //Similar to Before Method
		ExtentReport.createTest(result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		ExtentLogger.pass(result.getName() + " is passed");
	}

	public void onTestFailure(ITestResult result) {
		ExtentLogger.fail(result.getName() + " is failed");
	}

	public void onTestSkipped(ITestResult result) {
		ExtentLogger.skip(result.getName() + " is skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		//ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		//ITestListener.super.onTestFailedWithTimeout(result);
	}

	public void onStart(ITestContext context) {
		//ITestListener.super.onStart(context);
	}

	public void onFinish(ITestContext context) {
		//ITestListener.super.onFinish(context);
	}


}
