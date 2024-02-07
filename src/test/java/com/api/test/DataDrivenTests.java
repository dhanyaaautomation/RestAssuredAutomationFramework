package com.api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.endpoints.UserEndPoints;
import com.api.payload.User;
import com.api.utilities.DataProviders;

import io.restassured.response.Response;

public class DataDrivenTests {

	//If the dataprovider is defined in the same class we need on specify dataProviderClass or else it should be defined
	@Test(priority=1,dataProvider = "Data", dataProviderClass = DataProviders.class)
	
	//Receive excel data values in the form of parameters
	public void testPostUser(String userid,String username,String fname,String lname,String email,String pwd,String phone) {
		
		User userpayload = new User();
		
		userpayload.setId(Integer.parseInt(userid));
		userpayload.setUsername(username);
		userpayload.setFirstname(fname);
		userpayload.setLastname(lname);
		userpayload.setEmail(email);
		userpayload.setPassword(pwd);
		userpayload.setPhone(phone);
		
		Response response = UserEndPoints.createUser(userpayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
	@Test(priority=2, dataProvider = "Usernames", dataProviderClass = DataProviders.class)
	public void testDeleteUserByUserName(String userName) {
		
		Response response = UserEndPoints.deleteUser(userName);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
		
	}
}
