package com.test.practiceframework.tests.restfulbooker.crud;

import com.test.practiceframework.base.BaseTest;
import com.test.practiceframework.endpoints.APIConstants;
import com.test.practiceframework.pojos.responsePOJO.restfulbooker.TokenResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class TestCreateToken extends BaseTest {
    @Test(groups = "reg", priority = 1)
    @Owner("Anbu")
    @Description("TC#1 - Create Token")
    public void testCreateToken() {

        // Setup will first and making the request - Part - 1
        requestSpecification.basePath(APIConstants.AUTH_URL);
        response = RestAssured.given(requestSpecification)
                .when().body(payloadManager.createPayloadCredentialsAsString())
                .log().all().post();
        validatableResponse = response.then().log().all();

        //Extraction Part - 2
        TokenResponse tokenResponse = payloadManager.tokenResponseJava(response.asString());

        // Validation and verification via the AssertJ, TestNG Part - 3
        assertActions.verifyStatusCode(response, 200);
        assertActions.verifyStringKeyNotNull(tokenResponse.getToken());
        assertActions.verifyJsonSchema(response, "schemas/tokenResponseSchema.json");

    }
}
