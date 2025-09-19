package com.test.practiceframework.base;


import com.test.practiceframework.asserts.AssertActions;
import com.test.practiceframework.endpoints.APIConstants;
import com.test.practiceframework.modules.restfulbooker.PayloadManager;
import com.test.practiceframework.pojos.responsePOJO.restfulbooker.TokenResponse;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    // This is called as common to all test cases.
    // CommonToAll Testcase
    //   // Base URL, Content Type - json - common

    public RequestSpecification requestSpecification;
    public Response response;

    public ValidatableResponse validatableResponse;

    public AssertActions assertActions;
    public PayloadManager payloadManager;

    public JsonPath jsonPath;

    @BeforeMethod
    public void setup() {

        System.out.println("Starting of the Test");
        payloadManager = new PayloadManager();
        assertActions = new AssertActions();

//        requestSpecification = RestAssured.given();
//        requestSpecification.baseUri(APIConstants.BASE_URL);
//        requestSpecification.contentType(ContentType.JSON).log().all();

        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(APIConstants.BASE_URL)
                .addHeader("Content-Type", "application/json")
                .build();


    }

    @AfterTest
    public void tearDown() {
        System.out.println("Finished the Test!");
    }

    public String getToken() {
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri(APIConstants.BASE_URL)
                .basePath(APIConstants.AUTH_URL);
        // Setting the payload
        String payload = payloadManager.createPayloadCredentialsAsString();
        // Get the Token
        response = requestSpecification.contentType(ContentType.JSON).body(payload).when().post();
        //Extraction Part - 2
        TokenResponse tokenResponse = payloadManager.tokenResponseJava(response.asString());

        return tokenResponse.getToken();

    }

}
