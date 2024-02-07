package com.api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.api.endpoints.UserEndPoints;
import com.api.payload.User;
import com.github.javafaker.Faker;

import io.restassured.response.Response;

public class UserTestCases {

	static Faker faker;
	static User userpayload;
	
	public Logger logger;

	@BeforeTest
	public void setData() {

		faker = new Faker();
		userpayload = new User();

		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstname(faker.name().firstName());
		userpayload.setLastname(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password(5, 10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger = LogManager.getLogger(this.getClass());

	}

	@Test(priority = 1)
	public void postUserTest() {
		
		logger.info("*************User Creation****************");

		Response response = UserEndPoints.createUser(userpayload);
		System.out.println("--------Post User Response--------");
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("*************User Created Successfully**************");
	}

	@Test(priority = 2)
	public static void getUserTest() {

		Response response = UserEndPoints.getUser(userpayload.getUsername());
		System.out.println("--------Get User Response--------");
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 3)
	public static void updateuserTest() {

		// Updating the User details
		userpayload.setFirstname(faker.name().firstName());
		userpayload.setLastname(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());

		Response response = UserEndPoints.updateUser(userpayload.getUsername(), userpayload);
		System.out.println("--------Update User Response--------");
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);

		// Checking the response after updting the user
		Response response_after_updating = UserEndPoints.getUser(userpayload.getUsername());
		System.out.println("--------Get User Response after updating--------");
		response_after_updating.then().log().all();
		Assert.assertEquals(response_after_updating.getStatusCode(), 200);

	}

	@Test(priority = 4)
	public static void deleteUserTest() {

		Response response = UserEndPoints.deleteUser(userpayload.getUsername());
		System.out.println("--------Delete User Response--------");
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
	}
}
