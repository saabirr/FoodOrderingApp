package persistenceTest;

import model.Menu;
import model.Meal;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            Menu mn = new Menu("My Menu");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyMenu() {
        try {
            Menu mn = new Menu("My Menu");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyMenu.json");
            writer.open();
            writer.write(mn);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderEmptyMenu.json");
            mn = reader.read();
            assertEquals("My Menu", mn.getMenuName());
            assertEquals(0, mn.numMeals());
        } catch (IOException e) {
            //  fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralMenu() {
        try {
            Menu mn = new Menu("My Menu");
            mn.addMeal(new Meal("Vegetarian", "Salad",
                    10));
            mn.addMeal(new Meal("Lunch", "Pasta",
                    24));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralMenu.json");
            writer.open();
            writer.write(mn);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderGeneralMenu.json");
            mn = reader.read();
            assertEquals("My Menu", mn.getMenuName());
            List<Meal> meals = mn.getMeals();
            assertEquals(2, meals.size());
            checkMeal("Vegetarian", "Salad",
                    10, meals.get(0));
            checkMeal("Lunch", "Pasta",
                    24, meals.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}



