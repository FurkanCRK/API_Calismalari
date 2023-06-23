package test;

import baseUrl.HerokuAppBaseURL;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import testData.TestDataHerokuapp;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C24_Post_Deserialization extends HerokuAppBaseURL {
    /*
        https://restfulbooker.herokuapp.com/booking url’ine asagidaki body'ye sahip bir POST
        request gonderdigimizde donen response’un id haric asagidaki gibi oldugunu test edin.

        Request body
        {
            "firstname" : "Ahmet",
            "lastname" : "Bulut",
            "totalprice" : 500,
            "depositpaid" : false,
            "bookingdates" : {
                                "checkin" : "2021-06-01",
                                "checkout" : "2021-06-10"

                             }
             "additionalneeds" : "wi-fi"
        }

        Response Body // Expected Data

        {
            "bookingid" : 24,
            "booking" : {
                "firstname" : "Ahmet",
                "lastname" : "Bulut",
                "totalprice" : 500,
                "depositpaid" : false,
                "bookingdates" : {
                                    "checkin" : "2021-06-01",
                                    "checkout" : "2021-06-10"

                                 }
                 "additionalneeds" : "wi-fi"
                        },
        }
     */

    @Test
    public void post01(){

        // 1- Url ve Request Body hazirla

        specHerokuApp.pathParam("pp1","booking");

        TestDataHerokuapp testDataHerokuapp = new TestDataHerokuapp();

        HashMap <String ,Object> reqBody = testDataHerokuapp.reqBodyOlusturMap();

        // 2- Expected Data hazirla

        HashMap <String,Object> expData = testDataHerokuapp.expDataOlusturMap();

        // 3- Response'i kaydet

        Response response = given()
                                .spec(specHerokuApp)
                                .contentType(ContentType.JSON)
                            .when()
                                .body(reqBody)
                                .post("/{pp1}");

        response.prettyPrint();


        // 4- Assertion

        HashMap<String,Object> respMap = response.as(HashMap.class);

        assertEquals(((Map)(expData.get("booking"))).get("firstname"),
                     ((Map)(respMap.get("booking"))).get("firstname"));
        assertEquals(((Map)(expData.get("booking"))).get("lastname"),
                     ((Map)(respMap.get("booking"))).get("lastname"));
        assertEquals(((Map)(expData.get("booking"))).get("totalprice"),
                     ((Map)(respMap.get("booking"))).get("totalprice"));
        assertEquals(((Map)(expData.get("booking"))).get("depositpaid"),
                     ((Map)(respMap.get("booking"))).get("depositpaid"));
        assertEquals(((Map)(expData.get("booking"))).get("additionalneeds"),
                     ((Map)(respMap.get("booking"))).get("additionalneeds"));
        assertEquals(((Map)(((Map)(expData.get("booking"))).get("bookingdates"))).get("checkin"),
                     ((Map)(((Map)(respMap.get("booking"))).get("bookingdates"))).get("checkin"));
        assertEquals(((Map)(((Map)(expData.get("booking"))).get("bookingdates"))).get("checkout"),
                     ((Map)(((Map)(respMap.get("booking"))).get("bookingdates"))).get("checkout"));

    }



}
