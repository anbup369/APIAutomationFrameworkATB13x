package com.test.practiceframework.tests.restfulbooker.crud;

import com.test.practiceframework.base.BaseTest;
import com.test.practiceframework.endpoints.APIConstants;
import com.test.practiceframework.pojos.responsePOJO.restfulbooker.BookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class TestGetBookingIds extends BaseTest {
    @Test(groups = "reg", priority = 1)
    @Owner("Anbu")
    @Description("TC#1 - Get Booking ")
    public void testGetBookingIds() {

        // Setup will first and making the request - Part - 1
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);


        response = RestAssured.given(requestSpecification)
                .when().log().all().get();
        validatableResponse = response.then().log().all();


        // Validation and verification via the AssertJ, TestNG Part - 3
        assertActions.verifyStatusCode(response, 200);

        assertActions.verifyJsonSchema(response, "schemas/getBookingIdSchema.json");

    }

    @Test(groups = "reg", priority = 1)
    @Owner("Anbu")
    @Description("TC#1 - Get Booking ")
    public void testGetBookingIds_With_Params_Name() {

        // Setup will first and making the request - Part - 1
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL)
                .queryParams("firstname", "Sally", "lastname", "Smith");


        response = RestAssured.given(requestSpecification)
                .when().log().all().get();
        validatableResponse = response.then().log().all();


        // Validation and verification via the AssertJ, TestNG Part - 3
        assertActions.verifyStatusCode(response, 200);

        assertActions.verifyJsonSchema(response, "schemas/getBookingIdSchema.json");

    }

    @Test(groups = "reg", priority = 1)
    @Owner("Anbu")
    @Description("TC#1 - Get Booking ")
    public void testGetBookingIds_With_Params_Date() {

        // Setup will first and making the request - Part - 1
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL)
                .queryParams("checkin", "2015-09-21", "checkout", "2018-03-06");


        response = RestAssured.given(requestSpecification)
                .when().log().all().get();
        validatableResponse = response.then().log().all();


        // Validation and verification via the AssertJ, TestNG Part - 3
        assertActions.verifyStatusCode(response, 200);

        assertActions.verifyJsonSchema(response, "schemas/getBookingIdSchema.json");

    }
}
