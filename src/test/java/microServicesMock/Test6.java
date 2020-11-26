package microServicesMock;

import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Test6 
{
	public static void main(String[] args) 
	{
		// Create HTTP Request
		RestAssured.baseURI = "http://localhost:9999/myform";
		RequestSpecification req = RestAssured.given();
		// Pack parameters with values & then attach to request body
		JSONObject jo = new JSONObject();
		jo.put("rollNumber", "11");
		jo.put("name", "Gaurav");
		req.body(jo.toString());
		// Send request with HTTP method
		Response res = req.request(Method.POST, "");
		int sc = res.getStatusCode();
		System.out.println("The status code received: "+sc);
		System.out.println("Response body: "+res.body().asString());
	}
}
