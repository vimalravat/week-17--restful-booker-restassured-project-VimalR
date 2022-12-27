package com.restful.booker.testsuite;

import com.restful.booker.testbase.TestBase;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class AssertionTest extends TestBase {
    static ValidatableResponse response;
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

}
