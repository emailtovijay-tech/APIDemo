package API;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PostAPI_UsinDataProvider
{

        @Test(dataProvider = "empdata")
        public void PostAPI(String eName, String eSal, String eAge)
        {
            // Base URI
            RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";

            // Create Request
            RequestSpecification httpRequest = RestAssured.given();

            // Create JSON request body

            // JSONObject requestParam = new JSONObject();
            // requestParam.put("employee_name", eName);
            //requestParam.put("employee_salary", eSal);
            //  requestParam.put("employee_age", eAge);

            JSONObject requestParam = new JSONObject();
            requestParam.put("employee_name", eName);
            requestParam.put("employee_salary", eSal);
            requestParam.put("employee_age", eAge);

            // Set Content-Type header
            httpRequest.header("Content-Type", "application/json");

            // Attach JSON body
            httpRequest.body(requestParam.toString());

            // Send POST request
            Response response = httpRequest.request(Method.POST, "/create");

            // Print details
            System.out.println("Status Code: " +response.getStatusCode());
            System.out.println("Response Body: " +response.getBody().asString());
            System.out.println("Response Time: " +response.getTime() + " ms");

            // Validation
            int Status_code = response.getStatusCode();
            Assert.assertEquals(Status_code, 200, "Status code is not matching");

        }

        @DataProvider(name = "empdata")
        String [][] Getemployeedata()

        {
            String empdata [][]  = {{"DDD", "4544", "65" }, {"FFF", "3434", "32" }, {"GFF", "4344", "65" } };
            return (empdata);
        }

    }

