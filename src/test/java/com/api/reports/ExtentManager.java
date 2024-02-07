package com.api.reports;

import com.aventstack.extentreports.ExtentTest;

public final class ExtentManager {
	
	private ExtentManager() {
		
	}
	
	private static ThreadLocal<ExtentTest> threadLocal = new ThreadLocal<ExtentTest>();

	public static ExtentTest getExtentTest() {
		return threadLocal.get();
	}

	public static void setExtentTest(ExtentTest extentTest) {
		threadLocal.set(extentTest);
	}
	
	public static void unLoad() {
		threadLocal.remove();
	}

}
