package org.example;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class EchoApiPostTest {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://postman-echo.com";
    }

    @Test
    @DisplayName("POST /post — отправка JSON, статус 200")
    public void whenSendPostRequest_thenStatus200() {
        // Тело запроса
        String requestBody = "{\n    \"test\": \"value150\"\n}";

        given().log().body()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/post")
                .then()
                .log().body()
                .statusCode(200);  // 200 OK
    }
}