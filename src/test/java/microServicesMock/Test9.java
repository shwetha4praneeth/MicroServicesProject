package microServicesMock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;

public class Test9 
{
	public static void main(String[] args) throws Exception
	{
		//Step:1 Start WireMock server via batch file & get required data from mocked service
		String path="cmd /c start C:\\Users\\Gaurav\\Desktop\\WireMock.bat";
		Runtime.getRuntime().exec(path);
		RestAssured.baseURI="http://localhost:9999/person";
		String mockres=RestAssured.given().get("/p0001").getBody().asString();
		System.out.println(mockres);
		//Step:2 Connect to the Restful microservice under component testing & get response
		RestAssured.baseURI="https://date.nager.at/Api/v2/PublicHolidays";
		List<String>restres=RestAssured.given().get(mockres).jsonPath().getList("date");
		System.out.println("No. of holidays is "+restres.size());
		for(int i=0; i<restres.size(); i++)
		{
			System.out.println(restres.get(i));
		}
		//Connect to the Restful microservice under component testing database 
		Class.forName("com.mysql.jdbc.Driver"); //for mysql
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/apibatch2","root","Gaurav@123");
		Statement st=con.createStatement();
		String data[]=mockres.split("/");
		int y=Integer.parseInt(data[0]);
		String c=data[1];
		ResultSet res=st.executeQuery("select * from holidays where year = "+y+" and cc like "+c+" %';");
		List<String>dbres=new ArrayList<String>();
		while(res.next()) //Go to each row in result set
		{
			String z=res.getString(1); //1st column(date) in table(holidays)
			System.out.println(z);
			dbres.add(z);
		}
		con.close();
		//Validation
		if(restres.equals(dbres))
		{
			System.out.println("Component test passed");
		}
		else
		{
			System.out.println("Component test failed");
		}
		//Stop WireMock Server batch
		Runtime.getRuntime().exec("Taskkill /F /IM conhost.exe");
	}
}
