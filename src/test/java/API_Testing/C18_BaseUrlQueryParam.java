package API_Testing;

import baseURL.baseURLHerOkuapp;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C18_BaseUrlQueryParam extends baseURLHerOkuapp {
    /*
    https://restful-booker.herokuapp.com/booking endpointine
    asagidaki body’ye sahip bir POST request gonderdigimizde
    donen response’un status code’unun 200 oldugunu ve
    “firstname” degerinin “Ahmet” oldugunu test edin
 {
    "firstname" : "Ahmet",
    "lastname" : “Bulut", "
    totalprice" : 500,
    "depositpaid" : false,
    "bookingdates" : {
                        "checkin" : "2021-06-01",
                        "checkout" : "2021-06-10"
                    },
    "additionalneeds" : "wi-fi"
}
     */

    @Test
    public void post01(){
        //1- url ve Request Body hazırlama
        specHerOkuApp.pathParam("pp1","booking");

        JSONObject inner =new JSONObject();
        JSONObject reqbody=new JSONObject();

        inner.put("checkin" , "2021-06-01");
        inner.put("checkout","2021-06-10");

        reqbody.put("firstname" , "Ahmet");
        reqbody.put("lastname" , "Bulut");
        reqbody.put("totalprice" , 500);
        reqbody.put("depositpaid" , false);
        reqbody.put("bookingdates" , inner);
        reqbody.put("additionalneeds" , "wi-fi");

        //2-Expected Hazırlama /Ama bu soruda istenmemis

        //3-Response Kaydet
        Response response=given()
                .spec(specHerOkuApp)
                .contentType(ContentType.JSON)
                .when()
                .body(reqbody.toString()).post("/{pp1}");

        //4-Assertion

        response.then().assertThat()
                .statusCode(200)
                .body("booking.firstname", equalTo("Ahmet"));

    }

}