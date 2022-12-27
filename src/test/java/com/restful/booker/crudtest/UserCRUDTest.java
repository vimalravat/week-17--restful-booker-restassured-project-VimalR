package com.restful.booker.crudtest;

import com.restful.booker.model.UserPojo;
import com.restful.booker.testbase.TestBase;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;


public class UserCRUDTest extends TestBase {
    static ValidatableResponse response;

    public static String token() {
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBody("{ \"username\" : \"admin\", \"password\" : \"password123\"}")
                .build();

        return given(requestSpec).post("https://restful-booker.herokuapp.com/auth").path("token");
    }

    @Test
    public void getAllBookingInfo() {

        Response response = given()
                .when()
                .get("/");
        response.then().statusCode(200);
        response.prettyPrint();

    }

    @Test
    public void getSingleBookingInfo() {
        Response response = given()
                .pathParam("bookingid", 1436)
                .when()
                .get("/{bookingid}");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void verifyUserCreatedBookingSuccessfully() {
        List<String> additionalNeedsList = new ArrayList<>();
        additionalNeedsList.add("Breakfast");
        additionalNeedsList.add("Wi-Fi");

        UserPojo userPojo = new UserPojo();
        userPojo.setFirstName("Prime");
        userPojo.setLastName("Testing");
        userPojo.setTotalPrice(1000);
        userPojo.setDepositPaidOrNot("True");
        userPojo.setBookingDates("checkin: 2022-12-25, checkout: 2022-12-31");
        userPojo.setAdditionalNeeds(additionalNeedsList);

        Response response = given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", token())
                .when()
                .body(userPojo)
                .post();
        response.prettyPrint();
        response.then().log().all().statusCode(201);
    }

    @Test
    public void verifyUserUpdateSuccessfully() {
        UserPojo userPojo = new UserPojo();
        userPojo.setFirstName("Prime");
        userPojo.setLastName("Testing");
        userPojo.setTotalPrice(1000);
        userPojo.setDepositPaidOrNot("True");



        Response response = given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "8bbdf67089527d9")
                .when()
                .body(userPojo)
                .post();
        response.prettyPrint();
        response.then().log().all().statusCode(201);
    }



    @Test
    public void VerifyUserDeleteSuccessfully() {


       /* Booking testBooking = BookingHelper.testBookingEntry();
        Integer bookingID = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(BookingHelper.toJson(testBooking, Booking.class))
                .post()
                .then()
                .extract().response().path("bookingid");

        RestAssured.given()
                .contentType(ContentType.JSON)
                .cookie("token", token())
                .delete("/" + bookingID)
                .then()
                .statusCode(HttpStatus.SC_OK);

        RestAssured.given()
                .contentType(ContentType.TEXT)
                .when()
                .get("/" + bookingID)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);*/
    }


}


