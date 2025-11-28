package API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Get_NewStyle
{
    @Test
    public void Get_NewStyle()
    {
        RestAssured.baseURI = "https://fakestoreapi.com";

        Response response =
                given()
                        .auth().preemptive().basic("Username", "Password")
                        .log().all()
                        .when()
                        .get("/products")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .contentType("application/json; charset=utf-8")
                        .extract().response();

        // Print details
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Time: " + response.getTime() + " ms");
        System.out.println("Headers: " + response.getHeaders());
        System.out.println("Cookies: " + response.getCookies());

        // Convert response to JSON
        JsonPath jsonpath = response.jsonPath();

        System.out.println("Title: " + jsonpath.getString("[0].title"));
        System.out.println("Price: " + jsonpath.getFloat("[0].price"));
        System.out.println("Description: " + jsonpath.getString("[0].description"));
        System.out.println("Category: " + jsonpath.getString("[0].category"));

        // Validation
        Assert.assertEquals(jsonpath.getString("[0].category"), "men's clothing", "Category mismatch");


    }
}
