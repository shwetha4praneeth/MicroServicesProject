package microServicesMock;

import java.util.List;

import io.restassured.RestAssured;

public class Test1 
{
	public static void main(String[] args) 
	{
		//Start mock server by double clicking on .bat file icon
		//Before starting completely, mock server can recheck json files content in mappings folder
		//Connect to a mock service instead of real service under construction
		RestAssured.baseURI = "http://localhost:9999/person/p0001";
		String mockres = RestAssured.given().get().getBody().asString();
		System.out.println(mockres);
		//Specify the base URI of the RESTful microservice(Real service) under testing
		RestAssured.baseURI = "https://date.nager.at/Api/v2/PublicHolidays";
		List<String>restres = RestAssured.given().get(mockres).jsonPath().getList("date");
		for(int i=0; i<restres.size(); i++)
		{
			System.out.println(restres.get(i));
		}
	}
}
