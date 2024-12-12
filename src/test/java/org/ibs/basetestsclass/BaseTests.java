package org.ibs.basetestsclass;

import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.ibs.Specifications;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class BaseTests {

    protected static String BASEURL = "http://localhost:8080";
    protected static String cookieCode;
    protected static Cookie sessionCookie;

    @BeforeAll
    public static void beforeAll(){

        cookieCode = get(BASEURL + "/api/food").getCookie("JSESSIONID");
        sessionCookie = new Cookie.Builder("JSESSIONID", cookieCode).build();
        System.out.println("Создаем Cookie сессии");
        Specifications.installSpecification
                (Specifications.requestSpecification(BASEURL, sessionCookie),
                        Specifications.responseSpecification(200));
        System.out.println("Установлена спецификация");
    }

    @AfterEach
    public void afterEach() {
        given()
                .baseUri(BASEURL)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .cookie(sessionCookie)
                .when()
                .post("/api/data/reset");
        System.out.println("Сбрасываем список товаров");
    }


}
