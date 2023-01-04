package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MyMenuTest {
    private Meal meal1;
    private Meal meal2;
    private Meal meal3;
    private Meal meal4;
    private Meal meal5;

    private Menu meals;
    // private List<Meal> meals;


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
        meal5 = new Meal("Seafood", "shell fish",
                47);

        Menu meals = new Menu("Abdirahman's Menu");


        meals.addMeal(meal1);

        meals.addMeal(meal2);
        meals.addMeal(meal3);
        meals.addMeal(meal5);

    }


    @Test
    public void ConstructorTest() {
        meals = new Menu("My Menu");
        meals.addMeal(meal1);

        meals.addMeal(meal2);
        meals.addMeal(meal3);
        meals.addMeal(meal5);

        assertEquals(4, meals.numMeals());

        assertTrue(meals.mealAvailable(meal1.getMealName()));
        assertTrue(meals.mealAvailable(meal2.getMealName()));
        assertFalse(meals.mealAvailable(meal4.getMealName()));
        assertFalse(meals.mealAvailable("Tuna"));

    }

    @Test
    public void AddMealTest() {
        meals = new Menu("My Menu");
        meals.addMeal(meal1);
        meals.addMeal(meal4);
        assertEquals(2, meals.numMeals());
        assertTrue(meals.mealAvailable(meal4.getMealName()));

    }

    @Test
    public void DeleteMealTest() {
        meals = new Menu("My Menu");
        meals.addMeal(meal1);

        meals.addMeal(meal2);
        meals.addMeal(meal4);
        meals.addMeal(meal5);
        meals.deleteMeal(meal1.getMealName());
        assertEquals(3, meals.numMeals());
        assertFalse(meals.mealAvailable(meal1.getMealName()));
        assertTrue(meals.mealAvailable(meal4.getMealName()));

    }


    @Test
    public void sameCategoryOneTimeTest() {
        meals = new Menu("My Menu");
        meals.addMeal(meal1);

        meals.addMeal(meal2);
        meals.addMeal(meal4);
        meals.addMeal(meal5);

        List<String> lunchCategory = meals.sameCategory("Lunch");
        assertEquals(1, lunchCategory.size());
        assertTrue(lunchCategory.contains("Pasta"));
        assertFalse(lunchCategory.contains("Pancakes"));


    }


    @Test
    public void sameCategoryTwoTimesTest() {
        meals = new Menu("My Menu");
        meals.addMeal(meal1);

        meals.addMeal(meal2);
        meals.addMeal(meal4);
        meals.addMeal(meal5);
        List<String> seafoodCategory = meals.sameCategory("Seafood");
        assertEquals(2, seafoodCategory.size());
        assertTrue(seafoodCategory.contains("Shrimp"));
        assertTrue(seafoodCategory.contains("shell fish"));
        assertFalse(seafoodCategory.contains("Pancakes"));

    }

    @Test
    public void sameCategoryUnavailableTest() {
        meals = new Menu("My Menu");
        meals.addMeal(meal1);

        meals.addMeal(meal2);
        meals.addMeal(meal4);
        meals.addMeal(meal5);
        List<String> unavailableCategory = meals.sameCategory("raw meat");
        assertEquals(0, unavailableCategory.size());
        assertFalse(unavailableCategory.contains("Pasta"));
        assertFalse(unavailableCategory.contains("Shell fish"));


    }
}


