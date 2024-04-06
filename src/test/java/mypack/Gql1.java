package mypack;

import org.json.JSONObject;
import io.restassured.RestAssured;

public class Gql1
{
	public static void main(String[] args)
	{
		//1. convert graphQL query as JSON string
		String payload="query{\r\n" + 
				"     products{\r\n" + 
				"            name\r\n" + 
				"      }\r\n" + 
				"}";
		JSONObject json = new JSONObject();
	    json.put("query",payload);
	    String jsonString=json.toString();
		//2. connect to graphQL server, submit request, and get required piece of response
		RestAssured.baseURI="https://www.predic8.de/fruit-shop-graphql";           
		RestAssured.given()
			.log().all()
			.header("Content-Type", "application/json")
			.body(jsonString)
		.when()
			.post()
		.then()
			.log().all();
	}
}
