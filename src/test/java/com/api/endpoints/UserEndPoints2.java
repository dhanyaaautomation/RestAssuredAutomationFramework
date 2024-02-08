package com.api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import com.api.payload.User;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints2 {

	static ResourceBundle getURL() {

		// ResourceBundle is a special class in java to read the properties file
		// Specify the name of properties file inside the getBundle method
		//By default it will take the file inside the test/resources folder
		ResourceBundle routes = ResourceBundle.getBundle("routes");
		return routes;
	}

	public static Response createUser(User payload) {

		String post_url = getURL().getString("post_url");

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload)

				.when().post(post_url);

		return response;
	}

	public static Response getUser(String userName) {

		String get_url = getURL().getString("get_url");

		Response response = given().pathParam("username", userName).when().get(get_url);

		return response;

	}

	public static Response updateUser(String userName, User payload) {

		String put_url = getURL().getString("put_url");

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.pathParam("username", userName).body(payload).when().put(put_url);

		return response;
	}

	public static Response deleteUser(String userName) {

		String delete_url = getURL().getString("delete_url");
		
		Response response = given().accept(ContentType.JSON).pathParam("username", userName).when().delete(delete_url);

		return response;

	}
}
