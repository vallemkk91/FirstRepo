package mypack;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import java.util.Scanner;

public class Gql6 
{
    public static void main(String[] args) 
    {
        // Use Scanner to take input for the variables in the GraphQL mutation
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the product ID:");
        int productId = scanner.nextInt();
        System.out.println("Enter the new price:");
        float newPrice = scanner.nextFloat();

        // GraphQL mutation for updating product price
        String mutation = "mutation($productId: Int!, $newPrice: Float!) {\r\n" +
                "  updateProduct(id: $productId, price: $newPrice) {\r\n" +
                "    id\r\n" +
                "    name\r\n" +
                "    price\r\n" +
                "  }\r\n" +
                "}";

        // Create a JSON object with the mutation and variables
        JSONObject json = new JSONObject();
        json.put("query", mutation);
        JSONObject variables = new JSONObject();
        variables.put("productId", productId);
        variables.put("newPrice", newPrice);
        json.put("variables", variables);

        // Convert JSON object to a string
        String jsonString = json.toString();

        // Send the GraphQL request using RestAssured
        RestAssured.baseURI = "https://www.predic8.de/fruit-shop-graphql";
        Response response = RestAssured.given()
                .log().all()
                .header("Content-Type", "application/json")
                .body(jsonString)
                .when()
                .post();

        // Print the response details
        response.then()
                .log().all()
                .assertThat().statusLine("HTTP/1.1 200 OK");

        // Close the scanner
        scanner.close();
    }
}

