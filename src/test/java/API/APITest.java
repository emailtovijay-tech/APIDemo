package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


public class APITest {

    @Test
    public void APIDemo()
    {
        Response response = RestAssured.get("https://fakestoreapi.com/products");

        System.out.println("Status Code: " +response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
        System.out.println("Response Time: " +response.getTime() + " ms");

        int status_code = response.getStatusCode();
        Assert.assertEquals(status_code, 200);
    }
}
