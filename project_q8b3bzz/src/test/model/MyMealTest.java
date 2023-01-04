package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyMealTest {
    private Meal meal1;
    private Meal meal2;
    private Meal meal3;
    private Meal meal4;

    @BeforeEach
    public void runBefore() {
        meal1 = new Meal("Lunch", "Pasta",
                20);
        meal2 = new Meal("Breakfast", "Pancakes",
                10);
        meal3 = new Meal("Vegetarian", "Smashed cucumber salad",
                35);
        meal4 = new Meal("Seafood", "Shrimp",
                40);
    }

    @Test
    public void ConstructorTest() {
        assertEquals("Pasta", meal1.getMealName());
        assertEquals("Smashed cucumber salad", meal3.getMealName());
        assertEquals("Breakfast", meal2.getMealCategory());
        assertEquals(40, meal4.getMealPrice());


    }

    @Test

    public void toStringTest() {
        assertEquals("Meal{"
                + "mealCategory='"
                + "Lunch" + '\''
                + ", mealName"
                + "Pasta" + '\''
                + "," + " mealPrice='"
                + 20 + '\'' + '}', meal1.toString());
    }


}













