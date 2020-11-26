package microServicesMock;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Test3 
{
	public static void main(String[] args)
	{
		//Connect to a mock service by giving wrong path parameter
		RestAssured.baseURI="http://localhost:9999/shwetha";
		Response res=RestAssured.given().get();
		System.out.println(res.getBody().asString());
		System.out.println(res);
	}
}
