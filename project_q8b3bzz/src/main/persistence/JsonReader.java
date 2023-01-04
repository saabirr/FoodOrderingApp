package persistence;

import model.Menu;
import model.Meal;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// All code over here is inspired by JsonSerialization demo.

// Represents a reader that reads Menu from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Menu from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Menu read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMenu(jsonObject);
    }


    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses Menu from JSON object and returns it
    private Menu parseMenu(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Menu mn = new Menu(name);
        addMeals(mn, jsonObject);
        return mn;
    }

    // MODIFIES: menu
    // EFFECTS: parses meals from JSON object and adds them to menu.
    private void addMeals(Menu mn, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("menu");
        for (Object json : jsonArray) {
            JSONObject nextMeal = (JSONObject) json;
            addMeal(mn, nextMeal);
        }
    }

    // MODIFIES: Meal
    // EFFECTS: parses a meal from JSON object and adds it to Menu.
    private void addMeal(Menu mn, JSONObject jsonObject) {

        int mealPrice = jsonObject.getInt("mealPrice");
        String mealCategory = jsonObject.getString("mealCategory");
        String meanName = jsonObject.getString("mealName");


        Meal meal = new Meal(mealCategory, meanName,
                mealPrice);
        mn.addMeal(meal);
    }
}

