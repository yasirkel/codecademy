import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application {
    private CursistController cursistController;
    private DatabaseManager db = new DatabaseManager();

    @Override
    public void start(Stage stage) {
        cursistController = new CursistController();

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
        });

        VBox createFields = new VBox(naamField, emailField, birthDateField, genderChoiceBox, addressField,
                cityField,
                countryField, addButton);

        // CRUD Buttons worden aangemaakt
        Button createButton = new Button("Create Cursist");
        Button readButton = new Button("All Cursists");
        Button deleteButton = new Button("Delete");
        Button updateButton = new Button("Update Cursist");
        Button backHome = new Button("< Home");

        // CRUD (read) functionaliteit
        readButton.setOnAction(e -> {
            // arraylist met alle cursist namen
            ArrayList<String> cursistNames = db.getAllCursist();

            ListView<String> list = new ListView<>();
            ObservableList<String> items = FXCollections.observableArrayList(cursistNames);

            list.setItems(items);

            BorderPane cursistPage = new BorderPane();

            cursistPage.setTop(list);
            cursistPage.setBottom(backHome);

            Scene cursistsScene = new Scene(cursistPage);
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

        // Terug naar home knop
        backHome.setOnAction(e -> {
            stage.setScene(scene);
            stage.show();
        });
    }

}
