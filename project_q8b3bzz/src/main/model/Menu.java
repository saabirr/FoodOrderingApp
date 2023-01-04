package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Represents a Menu having a collection of meals;
public class Menu implements Writable {
    private final String name;
    private List<Meal> menu;

    // EFFECTS: constructs a Menu with a name and an empty list of meals.
    public Menu(String name) {
        this.name = name;
        menu = new ArrayList<>();
    }

    public String getMenuName() {
        return this.name;
    }

    //MODIFIES: this
    //EFFECTS: Add a meal order to my Menu list.
    public void addMeal(Meal meal) {
        menu.add(meal);
        EventLog.getInstance().logEvent(new Event("A meal has been Added to the menu"));

 
    }

    // EFFECTS: returns an unmodifiable list of books in this library
    public List<Meal> getMeals() {
        return Collections.unmodifiableList(menu);
    }


    // REQUIRES: the string mealName to be a non-empty string.
    // MODIFIES: this
    //EFFECTS: finds and deletes a meal from the list of meals given a mealName.


    public List<Meal> deleteMeal(String mealName) {

        Meal temp;
        String tempName;

        for (int i = 0; i < this.menu.size(); i++) {
            temp = this.menu.get(i);
            tempName = temp.getMealName();

            if (tempName.equals(mealName)) {

                this.menu.remove(i);
                EventLog.getInstance().logEvent(new Event("A meal has been deleted from the menu"));
            }


        }
        return this.menu;
    }

    // REQUIRES: the string mealName to be a non-empty string.
    //EFFECTS: finds and checks if a given meal is available in the Menu
    // given a Meal name.

    public boolean mealAvailable(String mealName) {
        Meal temp;
        String tempName;

        for (int i = 0; i < this.menu.size(); i++) {
            temp = this.menu.get(i);
            tempName = temp.getMealName();

            if (tempName.equals(mealName)) {
                return true;
            }
        }
        return false;
    }

    // REQUIRES: the string category to be a non-empty string
    // MODIFIES: this
    //EFFECTS: finds and returns a list of meal names with the given category.
    public List<String> sameCategory(String c) {
        List<String> result = new ArrayList<>();
        Meal temp;

        for (int i = 0; i < this.menu.size(); i++) {
            temp = this.menu.get(i);

            String category = temp.getMealCategory();
            if (category.equals(c)) {

                result.add(temp.getMealName());
            }
        }
        return result;
    }


//EFFECTS: returns the number of meals in the list

    public int numMeals() {
        return this.menu.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("menu", menuToJson());
        return json;
    }
    // EFFECTS: returns meals in this menu as a JSON array

    private JSONArray menuToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Meal ml : menu) {
            jsonArray.put(ml.toJson());
        }
        return jsonArray;
    }


}