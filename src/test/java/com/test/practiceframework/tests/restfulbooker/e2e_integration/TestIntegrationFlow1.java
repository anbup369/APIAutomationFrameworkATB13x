package com.test.practiceframework.tests.restfulbooker.e2e_integration;

import com.test.practiceframework.base.BaseTest;
import com.test.practiceframework.endpoints.APIConstants;
import com.test.practiceframework.pojos.responsePOJO.restfulbooker.BookingResponse;
import com.test.practiceframework.pojos.responsePOJO.restfulbooker.GetBookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.ITestContext;
import org.testng.annotations.Test;

//using Authentication Basic
public class TestIntegrationFlow1 extends BaseTest {

    // TestE2EFlow_01

    //  Test E2E Scenario 1

    //  1. Create a Booking -> bookingID

    // 2. Use Authentication Basic

    // 3. Verify that the  Booking by ID is working - GET Request to bookingID

    // 4. Update the booking ( bookingID, Authentication) - Need to  Pass Authentication Header and  bookingID

    // 5. Delete the Booking - Need to  Pass Authentication Header and  bookingIDt

    @Test(groups = "qa", priority = 1)
    @Owner("Anbu")
    @Description("TC#INT1 - Step 1. Verify that the Booking can be Created")
    public void testCreateBooking(ITestContext iTestContext) {

        // Setup will first and making the request - Part - 1
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given(requestSpecification)
                .when().body(payloadManager.createPayloadBookingAsString())
                .log().all().post();
        validatableResponse = response.then().log().all();

        //Extraction Part - 2
        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());


        // Validation and verification via the AssertJ, TestNG Part - 3
        assertActions.verifyStatusCode(response, 200);
        assertActions.verifyStringKeyNotNull(bookingResponse.getBookingid());
        assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(), "Lucky");

        //setting Booking ID as attribute
        iTestContext.setAttribute("bookingid", bookingResponse.getBookingid());
    }

    @Test(groups = "qa", priority = 2)
    @Owner("Anbu")
    @Description("TC#INT1 - Step 2. Verify that the Booking By ID")
    public void testVerifyBookingId(ITestContext iTestContext) {
        // Setup will first and making the request - Part - 1
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        System.out.println(bookingid);
        String basePathGET = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;
        System.out.println(basePathGET);

        requestSpecification.basePath(basePathGET);
        response = RestAssured.given(requestSpecification)
                .when().log().all().get();
        validatableResponse = response.then().log().all();

        //Extraction Part - 2
        GetBookingResponse getBookingResponse = payloadManager.getBookingResponseJava(response.asString());


        // Validation and verification via the AssertJ, TestNG Part - 3
        assertActions.verifyStatusCode(response, 200);
        assertActions.verifyStringKeyNotNull(getBookingResponse.getFirstname());
        assertActions.verifyStringKey(getBookingResponse.getFirstname(), "Lucky");


    }

    @Test(groups = "qa", priority = 3)
    @Owner("Anbu")
    @Description("TC#INT1 - Step 3. Verify Updated Booking by ID")
    public void testUpdateBookingByID(ITestContext iTestContext) {

        Integer BookingID = (Integer) iTestContext.getAttribute("bookingid");
        System.out.println(BookingID);
        // Setup will first and making the request - Part - 1
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + BookingID)
                .header("Accept", "application/json")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=");
        response = RestAssured.given(requestSpecification)
                .when().body(payloadManager.createPayloadUpdateBookingAsString())
                .log().all().put();

        validatableResponse = response.then().log().all();

        //Extraction Part - 2
        GetBookingResponse getBookingResponse = payloadManager.getBookingResponseJava(response.asString());

        // Validation and verification via the AssertJ, TestNG Part - 3
        assertActions.verifyStatusCode(response, 200);
        assertActions.verifyStringKey(getBookingResponse.getFirstname(), "Josh_Update");
        assertActions.verifyStringKey(getBookingResponse.getLastname(), "Allen_Update");
        assertActions.verifyStringKey(getBookingResponse.getTotalprice().toString(), "333");
        assertActions.verifyJsonSchema(response, "schemas/getBookingSchema.json");

    }

    @Test(groups = "qa", priority = 4)
    @Owner("Anbu")
    @Description("TC#INT1 - Step 4. Delete the Booking by ID")
    public void testDeleteBookingById(ITestContext iTestContext) {
        Integer BookingID = (Integer) iTestContext.getAttribute("bookingid");
        System.out.println(BookingID);
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
