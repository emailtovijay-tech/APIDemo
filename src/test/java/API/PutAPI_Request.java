package API;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PutAPI_Request
{
    @Test
    public void PutAPI()
    {
        // Base URI
        RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";

        // Create Request
        RequestSpecification httpRequest = RestAssured.given();

        // Create JSON request body
        JSONObject requestParam = new JSONObject();
        requestParam.put("employee_name", "TTT");
        requestParam.put("employee_salary", "6000");
        requestParam.put("employee_age", "25");

        // Set Content-Type header
        httpRequest.header("Content-Type", "application/json");

        // Attach JSON body
        httpRequest.body(requestParam.toString());

        // Send PUT request (update employee with ID = 21)
        Response response = httpRequest.request(Method.PUT, "/update/21");

        // Print details
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

        // Validate Response Body (optional simple check)
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("TTT"), "Employee name not updated correctly");
        Assert.assertTrue(responseBody.contains("6000"), "Employee salary not updated correctly");
    }
}