package com.test.practiceframework.tests.restfulbooker.crud;

import com.test.practiceframework.base.BaseTest;
import com.test.practiceframework.endpoints.APIConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class TestDeleteBooking extends BaseTest {

    @Test(groups = "reg", priority = 1)
    @Owner("Anbu")
    @Description("TC#1 - Get Booking ")
    public void testPartialUpdateBooking() {

        String BookingID = "1";
        // Setup will first and making the request - Part - 1
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + BookingID)
                .header("Accept", "application/json")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=");
        response = RestAssured.given(requestSpecification)
                .when().log().all().delete();

        validatableResponse = response.then().log().all();


        // Validation and verification via the AssertJ, TestNG Part - 3
        assertActions.verifyStatusCode(response, 201);

    }

}
