package ui;

import model.Event;
import model.EventLog;
import model.Meal;
import model.Menu;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class GUI extends JFrame implements ActionListener, WindowListener {

    private static final String JSON_STORE = "./data/menu.json";
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    private Menu menu;

    private EventLog eventLog;

    // Frame that contains the whole components
    private final JFrame mainFrame = new JFrame("Food Ordering App");

    // label on the top of opening screen
    private final JLabel welcomeLabel = new JLabel("Welcome to Our Restaurant");

    // Menu Buttons
    private final JButton addMeal = new JButton("Add a meal");
    private final JButton listMeal = new JButton("List Meal with same category");
    private final JButton deleteMeal = new JButton("Delete a Meal");
    private final JButton checkMeal = new JButton("Check if a Meal is available");
    private final JButton saveMenu = new JButton("Save Menu to file");
    private final JButton loadMenu = new JButton("Load Menu from file");
    private final JButton printMenu = new JButton("Print the Menu");


    // Add Meal Components which shows by click on the Add Meal Button
    private final JLabel labelAddMealName = new JLabel("Meal Name");
    private JTextField textAddMealName = new JTextField();
    private final JLabel labelAddMealCategory = new JLabel("Meal Category");
    private JTextField textAddMealCategory = new JTextField();
    private final JLabel labelAddMealPrice = new JLabel("Meal Price");
    private JTextField textAddMealPrice = new JTextField();
    private final JButton actualAddMeal = new JButton("Add Meal");
    private final JLabel messageAddMeal = new JLabel();


    // List Meal Components which shows by clicking on the List Meal Button
    private final JLabel labelListMealCategory = new JLabel("Meal Category");
    private JTextField textListMealCategory = new JTextField();
    private final JButton listMealButton = new JButton("Show Meals");
    private JLabel messageMealList = new JLabel();


    // Delete Meal Components which shows by clicking on the Delete Meal Button
    private final JLabel labelDeleteMealName = new JLabel("Meal Name");
    private JTextField textDeleteMealName = new JTextField();
    private final JButton deleteMealButton = new JButton("Delete Meal");
    private JLabel messageDeleteMeal = new JLabel();


    // Check Meal Components which shows by clicking on the Check Meal Button
    private final JLabel labelCheckMealName = new JLabel("Meal Name");
    private JTextField textCheckMealName = new JTextField();
    private final JButton checkMealButton = new JButton("Check Meal");
    private JLabel messageCheckMeal = new JLabel();


    // Save the data Components which shows by clicking on the Save Menu Button
    private JLabel messageSaveMenu = new JLabel();

    private WindowListener windowAdapter;
    // Load the data Components which shows by clicking on the Load Menu Button
    private JLabel messageLoadMenu = new JLabel();

    // Label for printing the entire menu
    private JLabel messagePrintMenu = new JLabel();

    // Default Constructor
    public GUI() {
        mainFrame.setSize(700, 700);
        mainFrame.setVisible(true);
        mainFrame.setLayout(null);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setBackground(Color.WHITE);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.addWindowListener(this);


        addWelcomeLabel();
        addButtons();
        actionListener();


        // calling functions to show the components

        showAddMealComponents();

        showListMealComponents();

        showDeleteMealComponents();

        showCheckMealComponents();

        showSaveMenuComponents();

        showLoadMenuComponents();

        showPrintMenuComponents();

        // Initializing the menu, reader and writer
        this.menu = new Menu("My Menu");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);


    }




    // EFFECTS: Creates the welcome text label and adds it to the main menu panel
    public void addWelcomeLabel() {
        welcomeLabel.setFont(new Font("Adelle Sans Devanagari", Font.PLAIN,
                30));
        welcomeLabel.setBounds(150, 20, 400, 30);
        welcomeLabel.setForeground(new Color(173, 37, 51));
        mainFrame.add(welcomeLabel);
    }

    //MODIFIES: this
    //EFFECTS: Adding Menu Buttons by function, passing x-asix, y-asix and width of the button
    public void addButtons() {
        addButton(addMeal, 30, 70, 150);
        addButton(listMeal, 30, 110, 300);
        addButton(deleteMeal, 30, 150, 200);
        addButton(checkMeal, 30, 190, 300);
        addButton(saveMenu, 30, 230, 230);
        addButton(loadMenu, 30, 270, 230);
        addButton(printMenu, 30, 310, 220);

    }

    //EFFECTS: calling action listener on every button
    public void actionListener() {
        addMeal.addActionListener(this);
        listMeal.addActionListener(this);
        deleteMeal.addActionListener(this);
        checkMeal.addActionListener(this);
        saveMenu.addActionListener(this);
        loadMenu.addActionListener(this);
        printMenu.addActionListener(this);
        actualAddMeal.addActionListener(this);
        listMealButton.addActionListener(this);
        deleteMealButton.addActionListener(this);
        checkMealButton.addActionListener(this);


    }


    public void addButton(JButton button, int x, int y, int width) {
        button.setFont(new Font("Adelle Sans Devanagari", Font.PLAIN, 16));
        button.setBounds(x, y, width, 30);
        button.setBackground(new Color(173, 37, 51));
        button.setForeground(Color.white);
        button.setOpaque(true);
        button.setBorderPainted(false);
        mainFrame.add(button);
    }

    //EFFECTS: shows meal components such as meal label and text.
    public void showAddMealComponents() {
        showAddMealLabels();
        showAddMealTexts();


        actualAddMeal.setBounds(195, 450, 120, 25);
        actualAddMeal.setFont(new Font("Adelle Sans Devanagari", Font.PLAIN, 16));
        actualAddMeal.setBackground(new Color(173, 37, 51));
        actualAddMeal.setForeground(Color.white);
        actualAddMeal.setOpaque(true);
        actualAddMeal.setBorderPainted(false);
        actualAddMeal.setVisible(false);

        messageAddMeal.setBounds(80, 500, 170, 20);
        messageAddMeal.setFont(new Font("Adelle Sans Devanagari", Font.PLAIN, 16));
        messageAddMeal.setForeground(new Color(173, 37, 51));
        messageAddMeal.setVisible(false);


        mainFrame.add(actualAddMeal);

        mainFrame.add(messageAddMeal);
        mainFrame.add(messageAddMeal);
    }

    private void showAddMealLabels() {
        labelAddMealName.setBounds(30, 350, 100, 20);
        labelAddMealName.setFont(new Font("Adelle Sans Devanagari", Font.PLAIN, 16));
        labelAddMealName.setForeground(new Color(173, 37, 51));
        labelAddMealName.setVisible(false);

        labelAddMealCategory.setBounds(30, 380, 130, 20);
        labelAddMealCategory.setFont(new Font("Adelle Sans Devanagari", Font.PLAIN, 16));
        labelAddMealCategory.setForeground(new Color(173, 37, 51));
        labelAddMealCategory.setVisible(false);


        labelAddMealPrice.setBounds(30, 410, 100, 20);
        labelAddMealPrice.setFont(new Font("Adelle Sans Devanagari", Font.PLAIN, 16));
        labelAddMealPrice.setForeground(new Color(173, 37, 51));
        labelAddMealPrice.setVisible(false);

        mainFrame.add(labelAddMealName);
        mainFrame.add(labelAddMealCategory);
        mainFrame.add(labelAddMealPrice);
    }

    private void showAddMealTexts() {
        textAddMealName.setBounds(180, 350, 150, 20);
        textAddMealName.setFont(new Font("Adelle Sans Devanagari", Font.PLAIN, 16));
        textAddMealName.setVisible(false);

        textAddMealCategory.setBounds(180, 380, 150, 20);
        textAddMealCategory.setFont(new Font("Adelle Sans Devanagari", Font.PLAIN, 16));
        textAddMealCategory.setVisible(false);

        textAddMealPrice.setBounds(180, 410, 150, 20);
        textAddMealPrice.setFont(new Font("Adelle Sans Devanagari", Font.PLAIN, 16));
        textAddMealPrice.setVisible(false);


        mainFrame.add(textAddMealName);
        mainFrame.add(textAddMealCategory);
        mainFrame.add(textAddMealPrice);


    }

    public void showListMealComponents() {
        labelListMealCategory.setBounds(30, 350, 130, 20);
        labelListMealCategory.setFont(new Font("Adelle Sans Devanagari", Font.PLAIN, 16));
        labelListMealCategory.setForeground(new Color(173, 37, 51));
        labelListMealCategory.setVisible(false);

        textListMealCategory.setBounds(180, 350, 150, 20);
        textListMealCategory.setFont(new Font("Adelle Sans Devanagari", Font.PLAIN, 16));
        textListMealCategory.setVisible(false);

        listMealButton.setBounds(185, 380, 140, 25);
        listMealButton.setFont(new Font("Adelle Sans Devanagari", Font.PLAIN, 16));
        listMealButton.setBackground(new Color(173, 37, 51));
        listMealButton.setForeground(Color.white);
        listMealButton.setOpaque(true);
        listMealButton.setBorderPainted(false);
        listMealButton.setVisible(false);

        messageMealList.setBounds(30, 420, 600, 20);
        messageMealList.setFont(new Font("Adelle Sans Devanagari", Font.PLAIN, 16));
        messageMealList.setForeground(new Color(173, 37, 51));
        messageMealList.setVisible(false);

        mainFrame.add(labelListMealCategory);
        mainFrame.add(textListMealCategory);
        mainFrame.add(listMealButton);
        mainFrame.add(messageMealList);
    }


    public void showDeleteMealComponents() {
        labelDeleteMealName.setBounds(30, 350, 130, 20);
        labelDeleteMealName.setFont(new Font("Adelle Sans Devanagari", Font.PLAIN, 16));
        labelDeleteMealName.setForeground(new Color(173, 37, 51));
        labelDeleteMealName.setVisible(false);

        textDeleteMealName.setBounds(180, 350, 150, 20);
        textDeleteMealName.setFont(new Font("Adelle Sans Devanagari", Font.PLAIN, 16));
        textDeleteMealName.setVisible(false);

        deleteMealButton.setBounds(185, 380, 140, 25);
        deleteMealButton.setFont(new Font("Adelle Sans Devanagari", Font.PLAIN, 16));
        deleteMealButton.setBackground(new Color(173, 37, 51));
        deleteMealButton.setForeground(Color.white);
        deleteMealButton.setOpaque(true);
        deleteMealButton.setBorderPainted(false);
        deleteMealButton.setVisible(false);

        messageDeleteMeal.setBounds(30, 420, 600, 20);
        messageDeleteMeal.setFont(new Font("Adelle Sans Devanagari", Font.PLAIN, 16));
        messageDeleteMeal.setForeground(new Color(173, 37, 51));
        messageDeleteMeal.setVisible(false);

        mainFrame.add(labelDeleteMealName);
        mainFrame.add(textDeleteMealName);
        mainFrame.add(deleteMealButton);
        mainFrame.add(messageDeleteMeal);
    }


    public void showCheckMealComponents() {
        labelCheckMealName.setBounds(30, 350, 130, 20);
        labelCheckMealName.setFont(new Font("Adelle Sans Devanagari", Font.PLAIN, 16));
        labelCheckMealName.setForeground(new Color(173, 37, 51));
        labelCheckMealName.setVisible(false);

        textCheckMealName.setBounds(180, 350, 150, 20);
        textCheckMealName.setFont(new Font("Adelle Sans Devanagari", Font.PLAIN, 16));
        textCheckMealName.setVisible(false);

        checkMealButton.setBounds(185, 380, 140, 25);
        checkMealButton.setFont(new Font("Adelle Sans Devanagari", Font.PLAIN, 16));
        checkMealButton.setBackground(new Color(173, 37, 51));
        checkMealButton.setForeground(Color.white);
        checkMealButton.setOpaque(true);
        checkMealButton.setBorderPainted(false);
        checkMealButton.setVisible(false);

        messageCheckMeal.setBounds(30, 420, 600, 20);
        messageCheckMeal.setFont(new Font("Adelle Sans Devanagari", Font.PLAIN, 16));
        messageCheckMeal.setForeground(new Color(173, 37, 51));
        messageCheckMeal.setVisible(false);

        mainFrame.add(labelCheckMealName);
        mainFrame.add(textCheckMealName);
        mainFrame.add(checkMealButton);
        mainFrame.add(messageCheckMeal);
    }

    public void showSaveMenuComponents() {
        messageSaveMenu.setBounds(30, 420, 600, 20);
        messageSaveMenu.setFont(new Font("Adelle Sans Devanagari", Font.PLAIN, 16));
        messageSaveMenu.setForeground(new Color(173, 37, 51));
        messageSaveMenu.setVisible(false);

        mainFrame.add(messageSaveMenu);
    }

    public void showLoadMenuComponents() {
        messageLoadMenu.setBounds(30, 420, 600, 20);
        messageLoadMenu.setFont(new Font("Adelle Sans Devanagari", Font.PLAIN, 16));
        messageLoadMenu.setForeground(new Color(173, 37, 51));
        messageLoadMenu.setVisible(false);

        mainFrame.add(messageLoadMenu);
    }

    public void showPrintMenuComponents() {
        messagePrintMenu.setBounds(30, 420, 600, 20);
        messagePrintMenu.setFont(new Font("Adelle Sans Devanagari", Font.PLAIN, 16));
        messagePrintMenu.setForeground(new Color(173, 37, 51));
        messagePrintMenu.setVisible(false);

        mainFrame.add(messagePrintMenu);
    }

    @Override
    // EFFECTS: calls the given methods when a certain button is clicked on
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addMeal) {
            doAddMeal();

        } else if (e.getSource() == listMeal) {
            doListMeal();

        } else if (e.getSource() == deleteMeal) {
            doDeleteMeal();

        } else if (e.getSource() == checkMeal) {
            doCheckMeal();

        } else if (e.getSource() == saveMenu) {
            doSaveMenu();


        } else if (e.getSource() == loadMenu) {
            doLoadMenu();


        } else if (e.getSource() == printMenu) {
            doPrintMenu();

        } else if (e.getSource() == actualAddMeal) {
            doActualAddMeal();

        } else if (e.getSource() == listMealButton) {
            doListMealButton();

        } else if (e.getSource() == deleteMealButton) {
            doDeleteMealButton();

        } else if (e.getSource() == checkMealButton) {
            doCheckMealButton();
        }
    }



    private void doCheckMealButton() {
        System.out.println("Check");
        String mealName = textCheckMealName.getText();
        if (menu.mealAvailable(mealName)) {
            messageCheckMeal.setText("The meal is available in the Restaurant");
        } else {
            messageCheckMeal.setText("The meal is NOT available in the Restaurant");
        }
        messageCheckMeal.setVisible(true);
    }


    private void doActualAddMeal() {
        String name = textAddMealName.getText();
        String category = textAddMealCategory.getText();
        int price = Integer.parseInt(textAddMealPrice.getText());

        Meal meal = new Meal(category, name, price);
        menu.addMeal(meal);
        messageAddMeal.setText("Meal has been added");
        messageAddMeal.setVisible(true);



    }

    private void doDeleteMealButton() {
        String mealName = textDeleteMealName.getText();
        menu.deleteMeal(mealName);
        messageDeleteMeal.setText("Meal has been deleted");
        messageDeleteMeal.setVisible(true);

    }

    private void doListMealButton() {
        String category = textListMealCategory.getText();
        List<String> mealList = menu.sameCategory(category);
        messageMealList.setText(mealList.toString());
        messageMealList.setVisible(true);
    }

    private void doPrintMenu() {
        List<Meal> meals = menu.getMeals();
        messagePrintMenu.setText(meals.toString());
        messagePrintMenu.setVisible(true);

        doPrintMenuComponents();


        messageDeleteMeal.setVisible(false);
        messageCheckMeal.setVisible(false);
        messageAddMeal.setVisible(false);
        messageMealList.setVisible(false);
        messageSaveMenu.setVisible(false);
        messageLoadMenu.setVisible(false);
    }

    private void doPrintMenuComponents() {
        labelAddMealName.setVisible(false);
        textAddMealName.setVisible(false);
        labelAddMealCategory.setVisible(false);
        textAddMealCategory.setVisible(false);
        labelAddMealPrice.setVisible(false);
        textAddMealPrice.setVisible(false);
        actualAddMeal.setVisible(false);

        labelListMealCategory.setVisible(false);
        textListMealCategory.setVisible(false);
        listMealButton.setVisible(false);

        labelDeleteMealName.setVisible(false);
        textDeleteMealName.setVisible(false);
        deleteMealButton.setVisible(false);

        labelCheckMealName.setVisible(false);
        textCheckMealName.setVisible(false);
        checkMealButton.setVisible(false);

    }

    private void doLoadMenu() {
        try {
            menu = jsonReader.read();
            messageLoadMenu.setText("Loaded " + menu.getMenuName() + " from " + JSON_STORE);
        } catch (IOException exp) {
            messageLoadMenu.setText("Unable to read from file: " + JSON_STORE);
        }
        messageLoadMenu.setVisible(true);

        doLoadLabelsAndTexts();


    }

    private void doLoadLabelsAndTexts() {
        labelAddMealName.setVisible(false);
        textAddMealName.setVisible(false);
        labelAddMealCategory.setVisible(false);
        textAddMealCategory.setVisible(false);
        labelAddMealPrice.setVisible(false);
        textAddMealPrice.setVisible(false);
        actualAddMeal.setVisible(false);

        labelListMealCategory.setVisible(false);
        textListMealCategory.setVisible(false);
        listMealButton.setVisible(false);

        labelDeleteMealName.setVisible(false);
        textDeleteMealName.setVisible(false);
        deleteMealButton.setVisible(false);

        labelCheckMealName.setVisible(false);
        textCheckMealName.setVisible(false);
        checkMealButton.setVisible(false);

        messageDeleteMeal.setVisible(false);
        messageCheckMeal.setVisible(false);
        messageAddMeal.setVisible(false);
        messageMealList.setVisible(false);
        messageSaveMenu.setVisible(false);
        messagePrintMenu.setVisible(false);
    }

    private void doSaveMenu() {
        try {
            jsonWriter.open();
            jsonWriter.write(menu);
            jsonWriter.close();
            messageSaveMenu.setText("Saved " + menu.getMenuName() + " to " + JSON_STORE);
        } catch (FileNotFoundException exp) {
            messageSaveMenu.setText("Unable to write to file: " + JSON_STORE);
        }
        messageSaveMenu.setVisible(true);

        doSaveLabelsAndTexts();


    }

    private void doSaveLabelsAndTexts() {
        labelAddMealName.setVisible(false);
        textAddMealName.setVisible(false);
        labelAddMealCategory.setVisible(false);
        textAddMealCategory.setVisible(false);
        labelAddMealPrice.setVisible(false);
        textAddMealPrice.setVisible(false);
        actualAddMeal.setVisible(false);

        labelListMealCategory.setVisible(false);
        textListMealCategory.setVisible(false);
        listMealButton.setVisible(false);

        labelDeleteMealName.setVisible(false);
        textDeleteMealName.setVisible(false);
        deleteMealButton.setVisible(false);

        labelCheckMealName.setVisible(false);
        textCheckMealName.setVisible(false);
        checkMealButton.setVisible(false);

        messageDeleteMeal.setVisible(false);
        messageCheckMeal.setVisible(false);
        messageAddMeal.setVisible(false);
        messageMealList.setVisible(false);
        messageLoadMenu.setVisible(false);
        messagePrintMenu.setVisible(false);
    }

    private void doCheckMeal() {
        doCheckMealComponents();


        messageDeleteMeal.setVisible(false);
        messageCheckMeal.setVisible(false);
        messageAddMeal.setVisible(false);
        messageMealList.setVisible(false);
        messageLoadMenu.setVisible(false);
        messageSaveMenu.setVisible(false);
        messagePrintMenu.setVisible(false);
    }

    private void doCheckMealComponents() {
        labelCheckMealName.setVisible(true);
        textCheckMealName.setVisible(true);
        checkMealButton.setVisible(true);

        textCheckMealName.setText("");

        labelAddMealName.setVisible(false);
        textAddMealName.setVisible(false);
        labelAddMealCategory.setVisible(false);
        textAddMealCategory.setVisible(false);
        labelAddMealPrice.setVisible(false);
        textAddMealPrice.setVisible(false);
        actualAddMeal.setVisible(false);

        labelListMealCategory.setVisible(false);
        textListMealCategory.setVisible(false);
        listMealButton.setVisible(false);

        labelDeleteMealName.setVisible(false);
        textDeleteMealName.setVisible(false);
        deleteMealButton.setVisible(false);
    }

    private void doDeleteMeal() {
        doDeleteMealComponents();

        messageDeleteMeal.setVisible(false);
        messageCheckMeal.setVisible(false);
        messageAddMeal.setVisible(false);
        messageMealList.setVisible(false);
        messageLoadMenu.setVisible(false);
        messageSaveMenu.setVisible(false);
        messagePrintMenu.setVisible(false);
    }

    private void doDeleteMealComponents() {
        labelDeleteMealName.setVisible(true);
        textDeleteMealName.setVisible(true);
        deleteMealButton.setVisible(true);

        textDeleteMealName.setText("");

        labelAddMealName.setVisible(false);
        textAddMealName.setVisible(false);
        labelAddMealCategory.setVisible(false);
        textAddMealCategory.setVisible(false);
        labelAddMealPrice.setVisible(false);
        textAddMealPrice.setVisible(false);
        actualAddMeal.setVisible(false);

        labelListMealCategory.setVisible(false);
        textListMealCategory.setVisible(false);
        listMealButton.setVisible(false);

        labelCheckMealName.setVisible(false);
        textCheckMealName.setVisible(false);
        checkMealButton.setVisible(false);

    }

    private void doListMeal() {
        doListMealLabelsAndTexts();


        messageDeleteMeal.setVisible(false);
        messageCheckMeal.setVisible(false);
        messageAddMeal.setVisible(false);
        messageMealList.setVisible(false);
        messageLoadMenu.setVisible(false);
        messageSaveMenu.setVisible(false);
        messagePrintMenu.setVisible(false);
    }

    private void doListMealLabelsAndTexts() {
        labelListMealCategory.setVisible(true);
        textListMealCategory.setVisible(true);
        listMealButton.setVisible(true);

        textListMealCategory.setText("");

        labelAddMealName.setVisible(false);
        textAddMealName.setVisible(false);
        labelAddMealCategory.setVisible(false);
        textAddMealCategory.setVisible(false);
        labelAddMealPrice.setVisible(false);
        textAddMealPrice.setVisible(false);
        actualAddMeal.setVisible(false);

        labelDeleteMealName.setVisible(false);
        textDeleteMealName.setVisible(false);
        deleteMealButton.setVisible(false);

        labelCheckMealName.setVisible(false);
        textCheckMealName.setVisible(false);
        checkMealButton.setVisible(false);

    }

    private void doAddMeal() {
        doAddMealLabelsAndTexts();


        messageDeleteMeal.setVisible(false);
        messageCheckMeal.setVisible(false);
        messageAddMeal.setVisible(false);
        messageMealList.setVisible(false);
        messageLoadMenu.setVisible(false);
        messageSaveMenu.setVisible(false);
        messagePrintMenu.setVisible(false);
    }

    private void doAddMealLabelsAndTexts() {
        labelAddMealName.setVisible(true);
        textAddMealName.setVisible(true);
        labelAddMealCategory.setVisible(true);
        textAddMealCategory.setVisible(true);
        labelAddMealPrice.setVisible(true);
        textAddMealPrice.setVisible(true);
        actualAddMeal.setVisible(true);

        textAddMealName.setText("");
        textAddMealCategory.setText("");
        textAddMealPrice.setText("");

        labelListMealCategory.setVisible(false);
        textListMealCategory.setVisible(false);
        listMealButton.setVisible(false);

        labelDeleteMealName.setVisible(false);
        textDeleteMealName.setVisible(false);
        deleteMealButton.setVisible(false);

        labelCheckMealName.setVisible(false);
        textCheckMealName.setVisible(false);
        checkMealButton.setVisible(false);
    }





    public static void printEvents() {
        EventLog el = EventLog.getInstance();
        for (Event event : el) {
            System.out.println(event);
        }
    }

    public static void main(String[] args) {
        //  new FoodOrderingApplication();

        new GUI();



    }


    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

        printEvents();
    }

    @Override
    public void windowClosed(WindowEvent e) {



    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}







