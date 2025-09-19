package com.test.practiceframework.modules.restfulbooker;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.test.practiceframework.pojos.requestPOJO.restfulbooker.Auth;
import com.test.practiceframework.pojos.requestPOJO.restfulbooker.Booking;
import com.test.practiceframework.pojos.requestPOJO.restfulbooker.BookingDates;
import com.test.practiceframework.pojos.responsePOJO.restfulbooker.BookingResponse;
import com.test.practiceframework.pojos.responsePOJO.restfulbooker.GetBookingResponse;
import com.test.practiceframework.pojos.responsePOJO.restfulbooker.TokenResponse;

public class PayloadManager {

    // The responsibility of POJO is to serialization and deserialization.

    Gson gson;
    Faker faker;

    // Convert the Java Object into the JSON String to use as Payload.
    // Serialization
    public String createPayloadBookingAsString() {

        Booking booking = new Booking();
        booking.setFirstname("Lucky");
        booking.setLastname("Dutta");
        booking.setTotalprice(112);
        booking.setDepositpaid(true);

        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-05");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

//        System.out.println(booking);

        // Java Object -> JSON
        gson = new Gson();
        return gson.toJson(booking);

//        {
//            "firstname" : "Josh",
//                "lastname" : "Allen",
//                "totalprice" : 3000,
//                "depositpaid" : true,
//                "bookingdates" : {
//            "checkin" : "2025-07-22",
//                    "checkout" : "2025-07-27"
//        },
//            "additionalneeds" : "Breakfast"
//        }
    }

    public String createPayloadBookingAsStringWrongBody() {
        Booking booking = new Booking();
        booking.setFirstname("会意; 會意");
        booking.setLastname("会意; 會意");
        booking.setTotalprice(112);
        booking.setDepositpaid(false);

        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin("5025-02-01");
        bookingdates.setCheckout("5025-02-01");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("会意; 會意");

        System.out.println(booking);

        // Java Object -> JSON
        gson = new Gson();
        return gson.toJson(booking);
    }

    public String createPayloadBookingFakerJS() {
        //  This option is you dynamically generate the first name,
        //  last name and other variables.
        faker = new Faker();
        Booking booking = new Booking();
        booking.setFirstname(faker.name().firstName());
        booking.setLastname(faker.name().lastName());
        booking.setTotalprice(faker.random().nextInt(1, 1000));
        booking.setDepositpaid(faker.random().nextBoolean());

        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-01");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

        System.out.println(booking);

        // Java Object -> JSON
        gson = new Gson();
        return gson.toJson(booking);

        // method with the dynamic data we use,
        // we will fetch the data from Excel file.
        // Apache POI
        // String the value, firstName, lastName, and everything, and then we will verify from the response.


    }

    public String createPayloadCredentialsAsString() {

        Auth credentials = new Auth();
        credentials.setUsername("admin");
        credentials.setPassword("password123");


//        System.out.println(auth);

        // Java Object -> JSON
        gson = new Gson();
        return gson.toJson(credentials);

//        {
//            "username" : "admin",
//                "password" : "password123"
//        }
    }
    // deserialization ( JSON String to Java Objects)
    // Convert the JSON String to Java Object so that we can verify response Body
    // DeSerialization

    public BookingResponse bookingResponseJava(String responseString) {
        gson = new Gson();
        return gson.fromJson(responseString, BookingResponse.class);
    }

    public GetBookingResponse getBookingResponseJava(String responseString) {
        gson = new Gson();
        return gson.fromJson(responseString, GetBookingResponse.class);
    }

    public TokenResponse tokenResponseJava(String responseString) {
        gson = new Gson();
        return gson.fromJson(responseString, TokenResponse.class);
    }

    //createPayloadUpdateBookingAsString
    // Convert the Java Object into the JSON String to use as Payload.
    // Serialization
    public String createPayloadUpdateBookingAsString() {

        Booking booking = new Booking();
        booking.setFirstname("Josh_Update");
        booking.setLastname("Allen_Update");
        booking.setTotalprice(333);
        booking.setDepositpaid(true);

        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin("2025-02-01");
        bookingdates.setCheckout("2025-02-05");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Noodles");

//        System.out.println(booking);

        // Java Object -> JSON
        gson = new Gson();
        return gson.toJson(booking);

//        {
//            "firstname": "Josh",
//                "lastname": "Allen",
//                "totalprice": 111,
//                "depositpaid": true,
//                "bookingdates": {
//            "checkin": "2018-01-01",
//                    "checkout": "2019-01-01"
//        },
//            "additionalneeds": "super bowls"
//        }

    }

    //createPayloadUpdateBookingAsString
    // Convert the Java Object into the JSON String to use as Payload.
    // Serialization
    public String createPayloadPartialUpdateBookingAsString() {

        Booking booking = new Booking();
        booking.setFirstname("Josh_Partial");
        booking.setLastname("Allen_Partial");
//        booking.setTotalprice(333);
//        booking.setDepositpaid(true);
//
//        BookingDates bookingdates = new BookingDates();
//        bookingdates.setCheckin("2025-02-01");
//        bookingdates.setCheckout("2025-02-05");
//        booking.setBookingdates(bookingdates);
//        booking.setAdditionalneeds("Noodles");


        // Java Object -> JSON
        gson = new Gson();
        return gson.toJson(booking);

//        {
//            "firstname": "Josh",
//                "lastname": "Allen",
//                "totalprice": 111,
//                "depositpaid": true,
//                "bookingdates": {
//            "checkin": "2018-01-01",
//                    "checkout": "2019-01-01"
//        },
//            "additionalneeds": "super bowls"
//        }

    }

//    // DeSer ( JSON String -> Java Object
//    public String getInvalidResponse(String invalidTokenResponse){
//        gson = new Gson();
//        InvalidTokenResponse tokenResponse1 = gson.fromJson(invalidTokenResponse, InvalidTokenResponse.class);
//        return  tokenResponse1.getReason();
//    }
}
