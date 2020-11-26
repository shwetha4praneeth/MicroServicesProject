package microServicesMock;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import io.restassured.RestAssured;

public class Test10 
{
	public static void main(String[] args) throws Exception
	{
		//Step:1
		//Start WireMock server via batch file
		String path="cmd /c start C:\\Users\\Gaurav\\Desktop\\WireMock.bat";
		Runtime.getRuntime().exec(path);
		RestAssured.baseURI="http://localhost:9999/person";
		String res1=RestAssured.given().get("/year").getBody().asString();
		System.out.println(res1); //2020
		RestAssured.baseURI="http://localhost:9999/person";
		String res2=RestAssured.given().get("/canada").getBody().asString();
		System.out.println(res2); //CA
		
		//Step:2
		//Specify the base URI of the Restful micro service
		String mockres=res1+"/"+res2;
		RestAssured.baseURI="https://date.nager.at/Api/v2/PublicHolidays";
		List<String> restres=RestAssured.given().get(mockres).jsonPath().getList("date");
		System.out.println(restres);
		
		//Step:3
		//Connect to DB of micro service
		MongoClient mc=new MongoClient("localhost",27017);
		MongoDatabase db=mc.getDatabase("kalamdb");
		MongoCollection<Document> col=db.getCollection("holidays");
		Document doc=col.find(Filters.and(Filters.eq("year",res1),Filters.regex("cc",res2))).first();
		List<String> dbres=(ArrayList<String>) doc.get("date");
		System.out.println(dbres);
		
		//Step:4 - Validation
		if(restres.equals(dbres))
		{
			System.out.println("Component test passed");
		}
		else
		{
			System.out.println("Component test failed");
		}
		
		//Stop WireMock server batch
		Runtime.getRuntime().exec("Taskkill /F /IM conhost.exe");
	}
}
