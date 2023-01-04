package persistenceTest;

import model.Menu;
import model.Meal;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// All code over here is inspired by JsonSerialization demo.

public class JsonReaderTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Menu mn = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyMenu() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyMenu.json");
        try {
            Menu mn = reader.read();
            assertEquals("My Menu", mn.getMenuName());
            assertEquals(0, mn.numMeals());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralMenu.json");
        try {
            Menu mn = reader.read();
            assertEquals("My Menu", mn.getMenuName());
            List<Meal> meals = mn.getMeals();
            assertEquals(2, meals.size());
            checkMeal("Vegetarian", "Salad",
                    10, meals.get(0));
            checkMeal("Lunch", "Pasta",
                    24, meals.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}


