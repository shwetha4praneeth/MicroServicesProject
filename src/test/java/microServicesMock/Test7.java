package microServicesMock;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Test7 
{
	public static void main(String[] args) 
	{
		//Create SOAP request
		RestAssured.baseURI="http://localhost:9999/webservicesserver/numberconversion";
		Response res=RestAssured.given().post();
		int sc=res.getStatusCode();
		System.out.println("The status code received: "+sc);
		//System.out.println("Response body: "+res.body().asString());
		String x=res.body().xmlPath().getString("**.find{it.name()=='NumberToDollarsResult'}");
		System.out.println(x);
	}
}
