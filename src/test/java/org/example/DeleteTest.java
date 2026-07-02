package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteTest {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://postman-echo.com";
    }

    @Test
    @DisplayName("Delete проверка статуса и тела")
    public void whenSendDeleteRequest_thenStatus200() {
        // Тело запроса (текст тот что в теле запроса)
        String requestBody = "This is expected to be sent back as part of response body.";

        //  Отправляем DELETE запрос
        Response response = given().log().all()
                .contentType("text/plain")          // Указываем тип данных
                .body(requestBody)                  // тело запроса
                .when()
                .delete("/delete")
                .then()
                .log().all()
                .statusCode(200)    // Проверяем статус 200 OK
                .extract().response();              // Сохраняем ответ

        // Проверяем поле data (сервер возвращает отправленный текст)
        String data = response.jsonPath().getString("data");
        assertEquals(requestBody, data);

    }
}

