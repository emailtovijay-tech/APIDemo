package API;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetAPI_Request
{

    @Test
    public void GetAPI()
    {
        RestAssured.baseURI = "https://fakestoreapi.com";

        // Basi Authentication
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName("username");
        authScheme.setPassword("password");
        RestAssured.authentication = authScheme;

        RequestSpecification httprequest = RestAssured.given();

        Response response =   httprequest.request(Method.GET, "/products");


        System.out.println("Status Code: " +response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
        System.out.println("Response Time: " +response.getTime() + " ms");
        System.out.println("Header: " + "\n" +response.headers());
        System.out.println("Cookies: " +response.cookies());
        System.out.println("Content type: " +response.contentType());

        // Validation on Status Code
        int status_code = response.getStatusCode();
        Assert.assertEquals(status_code, 200);

        // Validation on content type
        String contenttype = response.header("Content-type");
        System.out.println("Content type: " +contenttype);
        Assert.assertEquals(contenttype, "application/json; charset=utf-8","Content type not matching");

        JsonPath jsonpath = response.jsonPath();
        System.out.println("Title: " + jsonpath.getString("[0].title"));
        System.out.println("Price: " + jsonpath.getFloat("[0].price"));
        System.out.println("Description: " + jsonpath.getString("[0].description"));
        System.out.println("Category: " + jsonpath.getString("[0].category"));

        String category = jsonpath.getString("[0].category");

        // Validation response body - category
        Assert.assertEquals(category, "men's clothing", "Category not Matching");


    }
}
