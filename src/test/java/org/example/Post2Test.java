package org.example;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Post2Test {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://postman-echo.com";
    }

    @Test
    @DisplayName("POST /post — отправка JSON, проверка статуса и тела")
    public void whenSendPostRequest_thenStatus200() {
        String requestBody = "{\"foo1\": \"bar1\", \"foo2\": \"bar2\"}";

        given().log().all()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/post")
                .then()
                .log().all()
                .statusCode(200)
                // Проверяем data как объект (Map)
                .body("data.foo1", equalTo("bar1"))   // ✅ data.foo1 = "bar1"
                .body("data.foo2", equalTo("bar2"))   // ✅ data.foo2 = "bar2"
                .body("url", containsString("postman-echo.com/post"));
    }
}