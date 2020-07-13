package resouces;

import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Utils {
	RequestSpecification req;
	
	public RequestSpecification requestSpecification() throws IOException
	
	{
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		
PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
		.setContentType(ContentType.JSON).build();
		
		return req;

		}
	public static String getGlobalValue(String key) throws IOException
	{
		Properties prop= new Properties();
		FileInputStream fis = new FileInputStream("/Users/praseeda/eclipse-workspace/APIFramework/src/test/java/resouces/global.properties");//where the file is 
		prop.load(fis); //load the properties from the file 
		return prop.getProperty("baseUrl"); // get a particular property from the file 
		
	}
	

}
