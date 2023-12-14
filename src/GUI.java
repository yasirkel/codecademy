import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class GUI extends Application {
    private CursistController cursistController;
    private ListView<String> list;
    private ObservableList<String> items;
    private Scene mainScene;
    private Scene homeScene;
    private Button deleteButton;
    private Button backHome;
    private Scene cursistPage;

    @Override
    public void start(Stage stage) {
        cursistController = new CursistController();

        // Create a welcome message for the homepage
        Label welcomeLabel = new Label("Welkom bij cursist beheer");

        // CRUD Buttons for the homepage
        Button createButton = new Button("Create Cursist");
        Button readButton = new Button("All Cursists");

        // Set the actions for the buttons on the homepage
        createButton.setOnAction(e -> {
            stage.setScene(cursistPage);
            stage.show();
        });

         readButton.setOnAction(e -> {
            // arraylist met alle cursist namen
            ArrayList<String> cursistNames = cursistController.getAllCursists();

            items.setAll(cursistNames);
            list.setItems(items);

            BorderPane cursistPage = new BorderPane();

            cursistPage.setTop(list);
            cursistPage.setBottom(backHome);

            // Add delete button to the right side of the page
            cursistPage.setLeft(deleteButton);

            mainScene = new Scene(cursistPage);  // Assign mainScene here

            stage.setTitle("Cursist overzicht");
            stage.setScene(mainScene);
            stage.show();
        });

        // Create layout for the homepage
        VBox homeLayout = new VBox(welcomeLabel, createButton, readButton);
        homeScene = new Scene(homeLayout, 300, 200);


        TextField naamField = new TextField();
        naamField.setPromptText("Naam");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        TextField birthDateField = new TextField();
        birthDateField.setPromptText("Birthdate");

        ChoiceBox<String> genderChoiceBox = new ChoiceBox<>();
        genderChoiceBox.getItems().addAll("Selecteer geslacht", "Man", "Vrouw");
        genderChoiceBox.getSelectionModel().selectFirst();

        TextField addressField = new TextField();
        addressField.setPromptText("Address");

        TextField cityField = new TextField();
        cityField.setPromptText("City");

        TextField countryField = new TextField();
        countryField.setPromptText("Country");

        Button addButton = new Button("Voeg Cursist Toe");

        addButton.setOnAction(e -> {
            String naam = naamField.getText();
            String email = emailField.getText();
            String birthDateText = birthDateField.getText();

            // Voeg controle toe om lege invoer te voorkomen
            if (birthDateText.trim().isEmpty()) {
                // Voeg hier eventueel code toe om feedback aan de gebruiker te tonen
                System.out.println("Ongeldige invoer voor geboortedatum.");
                return;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate birthDate = LocalDate.parse(birthDateText, formatter);

            String gender = genderChoiceBox.getValue(); // Hier krijg je de geselecteerde waarde van de ChoiceBox
            String address = addressField.getText();
            String city = cityField.getText();
            String country = countryField.getText();

            Cursist nieuweCursist = new Cursist();
            nieuweCursist.setName(naam);
            nieuweCursist.setEmailAddress(email);
            nieuweCursist.setBirthDate(birthDate);
            nieuweCursist.setSex(gender);
            nieuweCursist.setAddress(address);
            nieuweCursist.setCity(city);
            nieuweCursist.setCountry(country);

            cursistController.toevoegenCursist(nieuweCursist);

            // Voeg hier eventueel code toe om feedback aan de gebruiker te tonen
            System.out.println("Cursist toegevoegd: " + nieuweCursist.getName() + ", " + nieuweCursist.getEmailAddress()
                    + ", " + nieuweCursist.getBirthDate() + ", " + nieuweCursist.getAddress() + ", "
                    + nieuweCursist.isSex() + ", " + nieuweCursist.getCity()
                    + ", " + nieuweCursist.getCountry());

            // Clear the input fields after adding a cursist
            naamField.clear();
            emailField.clear();
            birthDateField.clear();
            genderChoiceBox.getSelectionModel().selectFirst();
            addressField.clear();
            cityField.clear();
            countryField.clear();

            // Go back to the homepage
            stage.setScene(homeScene);
            stage.show();
        });

        VBox createFields = new VBox(naamField, emailField, birthDateField, genderChoiceBox, addressField,
                cityField, countryField, addButton);

        // CRUD Buttons are created
        Button deleteButton = new Button("Delete");
        Button updateButton = new Button("Update Cursist");
        Button backHome = new Button("< Home");

        // zet de buttons in een horizontale box
        HBox buttonsMenu = new HBox(deleteButton, updateButton);

        // Maak borderpane aan en voeg beide vbox & hbox samen.
        BorderPane mainPane = new BorderPane();
        mainPane.setTop(createFields);
        mainPane.setBottom(buttonsMenu);

        // Initialize list and items
        list = new ListView<>();
        items = FXCollections.observableArrayList();

        // CRUD (read) functionality...
        readButton.setOnAction(e -> {
            // arraylist met alle cursist namen
            ArrayList<String> cursistNames = cursistController.getAllCursists();

            items.setAll(cursistNames);
            list.setItems(items);

            BorderPane cursistPage = new BorderPane();

            cursistPage.setTop(list);
            cursistPage.setBottom(backHome);

            // Add delete button to the right side of the page
            cursistPage.setLeft(deleteButton);

            mainScene = new Scene(cursistPage);  // Assign mainScene here

            stage.setTitle("Cursist overzicht");
            stage.setScene(mainScene);
            stage.show();
        });

        // Handle delete button action
        deleteButton.setOnAction(e -> {
            String selectedCursist = list.getSelectionModel().getSelectedItem();

            if (selectedCursist != null) {
                cursistController.deleteCursist(selectedCursist);
                items.remove(selectedCursist);
            }
        });

        // Create main scene
        Scene mainScene = new Scene(mainPane);
        stage.setTitle("Cursist Beheer");
        stage.setScene(mainScene);
        stage.show();

        // Terug naar home knop
        backHome.setOnAction(e -> {
            stage.setScene(homeScene);
            stage.show();
        });

        // Set the initial scene to the homepage
        stage.setScene(homeScene);
        stage.setTitle("Cursist Beheer");
        stage.show();
    }

    
}
