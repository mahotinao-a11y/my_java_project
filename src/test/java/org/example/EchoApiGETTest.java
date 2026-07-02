package org.example;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.given;

public class EchoApiGETTest {

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
                .when().get("/get")
                .then()
                .statusCode(200)                                    // проверка статуса
                .body("args.foo1", equalTo("bar1"))                 // проверка поля foo1
                .body("args.foo2", equalTo("bar2"))  ;               // проверка поля foo2

    }
}


