package org.ibs;

import io.restassured.response.Response;
import org.ibs.pojo.Item;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestSteps {

    public void itemTest(String name, String type, Boolean exotic) {

        Item requestItem = new Item();

        System.out.println("Задаем значения полей для POJO объекта: Наименование = " + name
                + ", Тип = " + type + ", Экзотичность = " + exotic);
        requestItem.setName(name);
        requestItem.setType(type);
        requestItem.setExotic(exotic);

        System.out.println("Делаем POST запрос на добавление товара");
        Response response = given()
                .body(requestItem)
                .when()
                .post("/api/food");

        System.out.println("Считываем список товаров GET запросом");
        List<Item> itemList = given()
                .get("/api/food")
                .then()
                .extract()
                .jsonPath().getList("", Item.class);

        System.out.println("Находим индекс товара с названием " + name);
        int index = (int) itemList.stream().takeWhile(item -> !item.getName().equals(name)).count();

        System.out.println("Проверяем соответствие найденного товара с созданным");
        assertAll(
                () -> assertEquals(200, response.statusCode(), "Данные не отправлены"),
                () -> assertEquals(name, itemList.get(index).getName(), "Нет товара с заданным именем"),
                () -> assertEquals(type, itemList.get(index).getType(), "Нет товара с заданным типом"),
                () -> assertEquals(exotic, itemList.get(index).getExotic(), "Нет товара с заданной экзотичностью"));

    }
}
