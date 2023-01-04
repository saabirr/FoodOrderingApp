package model;

import org.json.JSONObject;
import persistence.Writable;


public class Meal implements Writable {

    private String mealName;
    private String mealCategory;
    private int mealPrice;

    //REQUIRES: mealName has a non-zero length.
    //EFFECTS:name of Meal is set to mealName; mealPrice is a
    //               positive integer in CAD.

    public Meal(String mealCategory, String mealName, int mealPrice) {
        this.mealCategory = mealCategory;
        this.mealName = mealName;
        this.mealPrice = mealPrice;

    }

    public String getMealCategory() {
        return mealCategory;
    }

    public String getMealName() {
        return mealName;
    }

    public int getMealPrice() {
        return mealPrice;
    }

    // EFFECTS: returns string representation of this Meal.

    @Override
    public String toString() {
        return "Meal{"
                + "mealCategory='"
                + mealCategory + '\''
                + ", mealName"
                + mealName + '\''
                + "," + " mealPrice='"
                + mealPrice + '\'' + '}';
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("mealCategory", mealCategory);
        json.put("mealName", mealName);
        json.put("mealPrice", mealPrice);
        return json;

    }


}
