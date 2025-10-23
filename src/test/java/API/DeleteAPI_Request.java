package API;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteAPI_Request
{
    @Test
    public void DeleteAPI()
    {
        // Base URI
        RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";

        // Create Request
        RequestSpecification httpRequest = RestAssured.given();

        // Send DELETE request (delete employee with ID = 21)
        Response response = httpRequest.request(Method.DELETE, "/delete/21");

        // Print Response Details
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
        System.out.println("Response Time: " + response.getTime() + " ms");
        System.out.println("Content Type: " + response.contentType());

        // Validate Status Code
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Status code is not matching");

        // Validate Content Type
        String contentType = response.header("Content-Type");
        Assert.assertTrue(contentType.contains("application/json"), "Content type is not matching");

        // Validate Response Body (check for success message)
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("success"), "Delete message not found in response");
        Assert.assertTrue(responseBody.contains("21"), "Employee ID not found in delete confirmation");
    }
}