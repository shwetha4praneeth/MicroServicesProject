package microServicesMock;

import io.restassured.RestAssured;

public class Test2 
{
	public static void main(String[] args) 
	{
		//Connect to a mock service resource-1 instead of real service under construction
		RestAssured.baseURI = "http://localhost:9999/user/one";
		String mockres1 = RestAssured.given().get().body().jsonPath().getString("id");
		System.out.println(mockres1);
		//Connect to a mock service resource-2 instead of real service under construction
		RestAssured.baseURI = "http://localhost:9999/user/two";
		String mockres2 = RestAssured.given().get().body().jsonPath().getString("id");
		System.out.println(mockres2);
	}
}
