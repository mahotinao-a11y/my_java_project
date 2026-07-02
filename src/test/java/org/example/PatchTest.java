package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class PatchTest {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://postman-echo.com";
    }

    @Test
    @DisplayName("PATCH отправка текста, проверка статуса и тела")
    public void whenSendPatchRequest_thenStatus200() {
        // Тело запроса
        String requestBody = "This is expected to be sent back as part of response body.";

        //Отправляем PATCH запрос
        Response response = given().log().all()
                .contentType("text/plain")
                .body(requestBody)
                .when()
                .patch("/patch")
                .then()
                .log().all()
                .statusCode(200)    // Проверяем статус 200 OK
                .extract().response();              // Сохраняем ответ

        // 3. Проверяем поле data (сервер возвращает отправленный текст)
        String data = response.jsonPath().getString("data");
        assertEquals(requestBody, data);
    }
}