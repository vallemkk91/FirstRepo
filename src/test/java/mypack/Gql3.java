package mypack;

import org.json.JSONObject;
import io.restassured.RestAssured;

public class Gql3
{
	public static void main(String[] args)
	{
		//convert graphQL query as JSON string
		String payload="query{\r\n" + 
				"  products(id: \"7\") {\r\n" + 
				"    name\r\n" + 
				"    price\r\n" + 
				"    category {\r\n" + 
				"      name\r\n" + 
				"    }\r\n" + 
				"    vendor {\r\n" + 
				"      name\r\n" + 
				"      id\r\n" + 
				"    }\r\n" + 
				"  }\r\n" + 
				"}";
		JSONObject json = new JSONObject();
	    json.put("query",payload);
	    String jsonString=json.toString();
		//@Test
		RestAssured.baseURI="https://www.predic8.de/fruit-shop-graphql";           
		RestAssured.given()
			.log().all()
			.header("Content-Type", "application/json")
			.body(jsonString)
		.when()
			.post()
		.then()
			.log().all()
			.assertThat().statusLine("HTTP/1.1 200 OK");
	}
}
