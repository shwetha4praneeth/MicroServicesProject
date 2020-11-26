package microServicesMock;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Test4 
{
	public static void main(String[] args) 
	{
		//Connect to a mock service instead of real service under construction
		RestAssured.baseURI="http://localhost:9999/person";
		Response res=RestAssured.given().queryParam("id","46").queryParam("search_item","address").get();
		String mockres=res.getBody().asString();
		System.out.println(mockres);
		String x=res.getBody().jsonPath().getString("address.city");
		System.out.println(x);
	}
}
