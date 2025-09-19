package com.test.practiceframework.tests.restfulbooker.crud;

import com.test.practiceframework.base.BaseTest;
import com.test.practiceframework.endpoints.APIConstants;
import com.test.practiceframework.pojos.responsePOJO.restfulbooker.BookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class TestHealthCheck extends BaseTest {
    @Test(groups = "reg", priority = 1)
    @Owner("Anbu")
    @Description("TC#1 - Verify that the Booking can be Created")
    public void testHealthCheck_Positive() {

        // Setup will first and making the request - Part - 1
        requestSpecification.basePath(APIConstants.PING_URL);
        response = RestAssured.given(requestSpecification)
                .when()
                .log().all().get();
        validatableResponse = response.then().log().all();


        // Validation and verification via the AssertJ, TestNG Part - 3
        assertActions.verifyStatusCode(response, 201);


    }
}
