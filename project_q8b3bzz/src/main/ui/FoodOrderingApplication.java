package ui;

import model.Meal;
import model.Menu;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.List;
import java.util.Scanner;


//Meal ordering application
public class FoodOrderingApplication {
    private static final String JSON_STORE = "./data/menu.json";
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    // fields
    private final Scanner scanner;
    String input = ""    /*stores user input*/;
    String mealName;
    String mealCategory;
    int mealPrice;
    // private Scanner input;
    private Menu menu;

    private List<String> list;


    //EFFECTS: runs the FoodOrdering application
    public FoodOrderingApplication() {


        this.menu = new Menu("My Menu");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        this.scanner = new Scanner(System.in);

        runFoodOrderingApplication(); //invoke method runFoodOrderingApp
    }


    // MODIFIES: this
    // EFFECTS: processes user input

    private void runFoodOrderingApplication() {

        //loop
        while (true) {
            printMenu();


            input = scanner.nextLine();
            input.toLowerCase();

            performMenu(input);
        }
    }
    // MODIFIES: this
    // EFFECTS: processes user command

    private void performMenu(String input) {
        switch (input) {
            case "1":
                doAddMeal();
                break;
            case "2":
                doGetSameCategory();
                break;
            case "3":
                doDeleteMeal();
                break;
            case "4":
                doCheckMealAvailability();
                break;
            case "5": doSaveMenu();
                break;
            case "6": doLoadMenu();
                break;
            case "7":doPrintMenu();
                break;
            case "8":
                System.exit(1);
                break;
        }
    }


    // EFFECTS: displays menu of options to user

    private void printMenu() {
        System.out.print("Choose from the menu below: \n");
        System.out.print("\n1. Add a meal (press 1)");
        System.out.print("\n2. List meals with same category (press 2)");
        System.out.print("\n3. Delete a meal from the list (press 3)");
        System.out.print("\n4. Available meal?? (press 4)");
        System.out.print("\n5. save menu to file (press 5)");
        System.out.print("\n6. load menu from file (press 6)");
        System.out.print("\n7. print meals (press 7)");

    }

    // MODIFIES: this
    // EFFECTS: adds meals to list of meals by passing
    // the  category name,name of the meal and mealPrice.

    private void doAddMeal() {
        System.out.print("\n Enter name of the meal: \n");
        mealName = scanner.next();
        System.out.println("mealName = " + mealName);

        System.out.print("Enter Category of the meal: \n");
        mealCategory = scanner.next();
        System.out.println("Category = " + mealCategory);

        System.out.print("Enter Price of the meal: \n");
        mealPrice = scanner.nextInt();
        System.out.println("Meal Price = " + mealPrice);


        Meal meal = new Meal(mealCategory, mealName, mealPrice);


        menu.addMeal(meal);  //add the meal
    }


    // MODIFIES: this
    //EFFECTS: finds and returns a list of meal names with the given category.

    private void doGetSameCategory() {
        System.out.print("Enter Category of the meal: \n");
        mealCategory = scanner.nextLine();
        list = menu.sameCategory(mealCategory);
        //list ---> meal names
        System.out.println(list);
    }

    // MODIFIES: this
    //EFFECTS: finds and deletes a meal of the given name from a list.

    private void doDeleteMeal() {
        System.out.println("Enter meal name to delete from the list of meals: \n");
        mealName = scanner.nextLine();
        menu.deleteMeal(mealName);

    }

    //EFFECTS: check if a given meal is available in the library.

    private void doCheckMealAvailability() {
        System.out.println("Enter meal name to search from the list of meals in the menu: \n");
        mealName = scanner.nextLine();

        if (menu.mealAvailable(mealName)) {
            System.out.println("The meal is available in the Restaurant");
        } else {
            System.out.println("The meal is NOT available in the Restaurant: \n");

        }
    }
    // EFFECTS: saves menu to File

    private void doSaveMenu() {
        try {
            jsonWriter.open();
            jsonWriter.write(menu);
            jsonWriter.close();
            System.out.println("Saved " + menu.getMenuName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }
    /// MODIFIES: this
    /// EFFECTS: loads menu from  file.

    private void doLoadMenu() {
        try {
            menu = jsonReader.read();
            System.out.println("Loaded " + menu.getMenuName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: prints all the meals in menu to the console
    private void doPrintMenu() {
        List<Meal> meals = menu.getMeals();

        for (Meal ml : meals) {
            System.out.println(ml);
        }
    }



}




