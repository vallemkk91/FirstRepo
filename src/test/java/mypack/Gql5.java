package mypack;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import java.util.Scanner;

public class Gql5 {

    public static void main(String[] args) {
        // Use Scanner to take input for the variable in the GraphQL query
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the product ID:");
        String x = scanner.nextLine();
        System.out.println("Enter true or false for vendor info");
        boolean y = scanner.nextBoolean();
        // GraphQL query with the variable
        String payload = "query($productId: String!, $includeVendor: Boolean!) {\r\n" +
                "  products(id: $productId) {\r\n" +
                "    name\r\n" +
                "    price\r\n" +
                "    category {\r\n" +
                "      name\r\n" +
                "    }\r\n" +
                "    vendor @include(if: $includeVendor) {\r\n" +
                "      name\r\n" +
                "      id \r\n" +
                "    }\r\n" +
                "  }\r\n" +
                "}";
        // Create a JSON object with the query and variables
        JSONObject variables = new JSONObject();
        variables.put("productId", x);
        variables.put("includeVendor", y);
        JSONObject json = new JSONObject();
        json.put("query", payload);
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

