package com.api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	/*
	 * Data Provider - Get the data from excel sheet and store it in 2D array
	 * It will then provide the data to the test method.
	 * Test Method will repeate multiple times based on the number of inputs provided by the data provider
	 */

	//Gets all the data from excel
	@DataProvider(name = "Data")
	public String[][] getAllData() throws IOException {

		// Path of excel file
		String path = System.getProperty("user.dir") + "/src/test/resources/UserAPI_Test_Data.xlsx";
		XLUtility xl = new XLUtility(path);

		int rowcount = xl.getRowCount("Sheet1");
		int colcount = xl.getCellCount("Sheet1", 1);

		String apidata[][] = new String[rowcount][colcount];

		for (int i = 1; i <= rowcount; i++) {
			for (int j = 0; j < colcount; j++) {

				apidata[i - 1][j] = xl.getCellData("Sheet1", i, j);
			}
		}
		return apidata;
	}

	//Gets only usernames or first row data from excel
	@DataProvider(name = "Usernames")
	public String[] getUsernames() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/resources/UserAPI_Test_Data.xlsx";
		XLUtility xl = new XLUtility(path);

		int rowcount = xl.getRowCount("Sheet1");
		
		String apidata[] = new String[rowcount];
		
		for(int i = 1 ; i <= rowcount; i++) {
			apidata[i-1] = xl.getCellData("Sheet1", i, 1);
		}
		return apidata;
	}

}
