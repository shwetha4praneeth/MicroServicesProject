package microServicesMock;

public class Test8 
{
	public static void main(String[] args) throws Exception
	{
		//Start WireMock server via a batch file
		String path="cmd /c start C:\\Users\\Gaurav\\Desktop\\WireMock.bat";
		Runtime.getRuntime().exec(path);
		//Corresponding microservice component testing with the help of mock services
		Thread.sleep(5000);
		//Close cmd prompt
		Runtime.getRuntime().exec("Taskkill /F /IM conhost.exe");
	}
}
