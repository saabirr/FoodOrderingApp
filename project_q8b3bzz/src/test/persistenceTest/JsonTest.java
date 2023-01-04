package persistenceTest;

import model.Meal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkMeal( String mealCategory, String mealName,int mealPrice,Meal meal) {
        assertEquals(mealCategory, meal.getMealCategory());
        assertEquals(mealName, meal.getMealName());
        assertEquals(mealPrice, meal.getMealPrice());


    }
}

