package microServicesMock;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.response.Response;

public class Test5 
{
	public static void main(String[] args) 
	{
		//Connect to a mock service instead of real service under construction
		RestAssured.baseURI="http://localhost:9999/authperson";
		PreemptiveBasicAuthScheme auth=new PreemptiveBasicAuthScheme();
		auth.setUserName("shwpra@gmail.com");
		auth.setPassword("Swetha@123");
		RestAssured.authentication=auth;
		Response res=RestAssured.given().get();
		System.out.println(res.getBody().asString());
	}
}
