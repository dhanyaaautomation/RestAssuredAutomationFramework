package com.api.reports;

public final class ExtentLogger {

	private ExtentLogger() {

	}

	public static void pass(String message) {
		ExtentManager.getExtentTest().pass(message);

	}

	public static void fail(String message) {
		ExtentManager.getExtentTest().fail(message);
		// Get Screenshot for failed test cases
		/*
		 * ExtentManager.getExtentTest().fail(message, MediaEntityBuilder
		 * .createScreenCaptureFromBase64String(ScreenShotUtils.getBase64Image()).build(
		 * ));
		 */

	}

	public static void skip(String message) {
		ExtentManager.getExtentTest().skip(message);

	}

	public static void info(String message) {
		ExtentManager.getExtentTest().info(message);

	}
}
