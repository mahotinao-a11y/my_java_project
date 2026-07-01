package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.equalTo;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class EchoApiTest {

    @BeforeAll  // один раз для всех тестов вывод url
    static void setup() {
        RestAssured.baseURI = "https://postman-echo.com"; // базовый url,далее в тестах сожно писать get
    }

    @Test
    @DisplayName("GET — проверка статуса и тела ответа")
    void testGetRequest() {

        given()
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .when()
                .get("/get")
                .then()
                .statusCode(200)                                    // ← проверка статуса
                .body("args.foo1", equalTo("bar1"))                 // ← проверка поля foo1
                .body("args.foo2", equalTo("bar2"))                 // ← проверка поля foo2
                .body("headers.host", equalTo("postman-echo.com"))  // ← проверка host
                .body("url", equalTo("https://postman-echo.com/get?foo1=bar1&foo2=bar2")); // ← проверка url
    }
}


