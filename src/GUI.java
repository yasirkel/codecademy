import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application {
    private CursistController cursistController;
    private DatabaseManager db = new DatabaseManager();

    @Override
    public void start(Stage stage) {
        cursistController = new CursistController();

        TextField naamField = new TextField("Naam");
        TextField emailField = new TextField("Email");
        TextField birthDateField = new TextField("Birthdate");

        ChoiceBox<String> genderChoiceBox = new ChoiceBox<>();
        genderChoiceBox.getItems().addAll("Man", "Vrouw"); // Voeg hier andere geslachtskeuzes toe indien nodig

        TextField addressField = new TextField("Address");
        TextField residenceField = new TextField("Residence");
        TextField countryField = new TextField("Country");

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
            String residence = residenceField.getText();
            String country = countryField.getText();

            Cursist nieuweCursist = new Cursist();
            nieuweCursist.setName(naam);
            nieuweCursist.setEmailAddress(email);
            nieuweCursist.setBirthDate(birthDate);
            nieuweCursist.setSex(gender);
            nieuweCursist.setAddress(address);
            nieuweCursist.setResidence(residence);
            nieuweCursist.setCountry(country);

            cursistController.toevoegenCursist(nieuweCursist);

            // Voeg hier eventueel code toe om feedback aan de gebruiker te tonen
            System.out.println("Cursist toegevoegd: " + nieuweCursist.getName() + ", " + nieuweCursist.getEmailAddress()
                    + ", " + nieuweCursist.getBirthDate() + ", " + nieuweCursist.getAddress() + ", "
                    + nieuweCursist.isSex() + ", " + nieuweCursist.getAddress() + ", " + nieuweCursist.getResidence()
                    + ", " + nieuweCursist.getCountry());
        });

        VBox createFields = new VBox(naamField, emailField, birthDateField, genderChoiceBox, addressField,
                residenceField,
                countryField, addButton);

        // CRUD Buttons worden aangemaakt
        Button createButton = new Button("Create Cursist");
        Button readButton = new Button("All Cursists");
        Button deleteButton = new Button("Delete");
        Button updateButton = new Button("Update Cursist");

        readButton.setOnAction(e -> {
            ArrayList<String> cursistNames = db.getAllCursist();

            ListView<String> list = new ListView<>();
            ObservableList<String> items = FXCollections.observableArrayList(cursistNames);

            list.setItems(items);

            Scene cursistsScene = new Scene(list);
            stage.setTitle("Cursist overwiew");
            stage.setScene(cursistsScene);
            stage.show();
        });

        // zet de buttons in een horizontale box
        HBox buttonsMenu = new HBox(createButton, readButton, deleteButton, updateButton);

        // Maak borderpane aan en voeg beide vbox & hbox samen.
        BorderPane mainPane = new BorderPane();
        mainPane.setTop(createFields);
        mainPane.setBottom(buttonsMenu);

        // laad de mainpane in de scene
        Scene scene = new Scene(mainPane);
        stage.setTitle("Cursist Beheer");
        stage.setScene(scene);
        stage.show();
    }

}
