package org.ibs;

import org.ibs.basetestsclass.BaseTests;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTests extends BaseTests {

    RestSteps restSteps = new RestSteps();
    @Test
    void testFruit() {
        restSteps.itemTest("Груша","FRUIT",false);
    }

    @Test
    void testExoFruit() {
        restSteps.itemTest("Манго","FRUIT",true);
    }

    @Test
    void testVegetable() {
        restSteps.itemTest("Кабачок","VEGETABLE",false);
    }

    @Test
    void testExoVegetable() {
        restSteps.itemTest("Артишок","VEGETABLE",true);

    }

}
