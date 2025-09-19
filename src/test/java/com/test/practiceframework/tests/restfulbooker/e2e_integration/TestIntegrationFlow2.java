package com.test.practiceframework.tests.restfulbooker.e2e_integration;

import com.test.practiceframework.base.BaseTest;
import com.test.practiceframework.endpoints.APIConstants;
import com.test.practiceframework.pojos.responsePOJO.restfulbooker.BookingResponse;
import com.test.practiceframework.pojos.responsePOJO.restfulbooker.GetBookingResponse;
import com.test.practiceframework.pojos.responsePOJO.restfulbooker.TokenResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.ITestContext;
import org.testng.annotations.Test;

//using Token
public class TestIntegrationFlow2 extends BaseTest {
    // TestE2EFlow_02

    //  Test E2E Scenario 2

    //  1. Create a Booking -> bookingID

    // 2. Create Token -> token

    // 3. Verify that the Create Booking is working - GET Request to bookingID

    // 4. Partial Update the booking ( bookingID, Token) - Need to get the token, bookingID from above request

    // 5. Delete the Booking - Need to get the token, bookingID from above request
    @Test(groups = "qa", priority = 1)
    @Owner("Anbu")
    @Description("TC#INT2 - Step 1. Verify that the Booking can be Created")
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
    @Description("TC#INT2 - Step 2. Verify that the Booking By ID")
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

    @Test(groups = "reg", priority = 3)
    @Owner("Anbu")
    @Description("TC#INT2 - Step 3. Create Token")
    public void testCreateToken(ITestContext iTestContext) {

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
        //setting Booking ID as attribute
        iTestContext.setAttribute("token", tokenResponse.getToken());
        System.out.println(tokenResponse.getToken());

    }

    @Test(groups = "qa", priority = 3)
    @Owner("Anbu")
    @Description("TC#INT1 - Step 4. Verify Updated Booking by ID")
    public void testPartialUpdateBookingByID(ITestContext iTestContext) {

        Integer BookingID = (Integer) iTestContext.getAttribute("bookingid");
        String Token = iTestContext.getAttribute("token").toString();
        System.out.println(BookingID);
        // Setup will first and making the request - Part - 1
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + BookingID)
                .header("Accept", "application/json")
                .cookie("token", Token);
        response = RestAssured.given(requestSpecification)
                .when().body(payloadManager.createPayloadPartialUpdateBookingAsString())
                .log().all().patch();

        validatableResponse = response.then().log().all();

        //Extraction Part - 2
        GetBookingResponse getBookingResponse = payloadManager.getBookingResponseJava(response.asString());

        // Validation and verification via the AssertJ, TestNG Part - 3
        assertActions.verifyStatusCode(response, 200);
        assertActions.verifyStringKey(getBookingResponse.getFirstname(), "Josh_Partial");
        assertActions.verifyStringKey(getBookingResponse.getLastname(), "Allen_Partial");
        assertActions.verifyJsonSchema(response, "schemas/getBookingSchema.json");

    }

    @Test(groups = "qa", priority = 4)
    @Owner("Anbu")
    @Description("TC#INT1 - Step 5. Delete the Booking by ID")
    public void testDeleteBookingById(ITestContext iTestContext) {
        Integer BookingID = (Integer) iTestContext.getAttribute("bookingid");
        String Token = iTestContext.getAttribute("token").toString();
        System.out.println(BookingID);
        // Setup will first and making the request - Part - 1
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + BookingID)
                .header("Accept", "application/json")
                .cookie("token", Token);
        response = RestAssured.given(requestSpecification)
                .when().log().all().delete();

        validatableResponse = response.then().log().all();


        // Validation and verification via the AssertJ, TestNG Part - 3
        assertActions.verifyStatusCode(response, 201);
    }
}
