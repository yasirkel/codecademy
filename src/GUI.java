import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
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
    private DatabaseManager db = new DatabaseManager();

    @Override
    public void start(Stage stage) {
        cursistController = new CursistController();

        // Create a welcome message for the homepage
        Button createButton = new Button("Create Cursist");
        Button readButton = new Button("All Cursists");
        Label welcomeLabel = new Label("Welkom bij cursist beheer");
        Insets welcomeLabelPadding = new Insets(25);
        welcomeLabel.setPadding(null);
        welcomeLabel.setStyle("-fx-font-size: 30;");
        Button editButton = new Button("Edit Cursist");

        // Create layout for the homepage
        BorderPane homePane = new BorderPane();
        BorderPane.setAlignment(welcomeLabel, Pos.CENTER);
        homePane.setTop(welcomeLabel);
        VBox homeLayout = new VBox(10, createButton, readButton, editButton);

        createButton.setPrefSize(150, 50);
        createButton.setStyle("-fx-font-size: 20");
        readButton.setPrefSize(150, 50);
        readButton.setStyle("-fx-font-size: 20");
        editButton.setPrefSize(150, 50);
        editButton.setStyle("-fx-font-size: 20");

        homeLayout.setAlignment(Pos.CENTER);
        homePane.setCenter(homeLayout);
        Insets padding = new Insets(100);
        homePane.setPadding(padding);
        homeScene = new Scene(homePane, 600, 400);

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

        });

        VBox createFields = new VBox(naamField, emailField, birthDateField, genderChoiceBox, addressField,
                cityField, countryField);
        createFields.setSpacing(7);

        // CRUD Buttons are created
        Button deleteButton = new Button("Delete");
        Button updateButton = new Button("Update Cursist");
        Button backHome = new Button("< Home");

        // zet de buttons in een horizontale box
        HBox buttonsMenu = new HBox(addButton, backHome);

        Insets buttonsMenuPadding = new Insets(10);
        addButton.setPadding(buttonsMenuPadding);
        deleteButton.setPadding(buttonsMenuPadding);
        updateButton.setPadding(buttonsMenuPadding);
        backHome.setPadding(buttonsMenuPadding);

        buttonsMenu.setAlignment(Pos.CENTER);
        buttonsMenu.setSpacing(5);

        VBox vboxesCombined = new VBox(createFields, buttonsMenu);
        vboxesCombined.setSpacing(10);

        // Maak borderpane aan en voeg beide vbox & hbox samen.
        BorderPane mainPane = new BorderPane();
        mainPane.setCenter(vboxesCombined);

        // Initialize list and items
        list = new ListView<>();
        items = FXCollections.observableArrayList();

        // CRUD (read) functionality...
        readButton.setOnAction(e -> {
            // arraylist met alle cursist namen
            ArrayList<String> cursistNames = cursistController.getAllCursists();

            items.setAll(cursistNames);
            list.setItems(items);
            list.setStyle("-fx-font-size: 24; -fx-alignment: center;");

            BorderPane cursistPage = new BorderPane();

            cursistPage.setCenter(list);

            Label cursistPageTitle = new Label("Overzicht alle cursisten");
            cursistPageTitle.setStyle("-fx-font-size: 30;");
            BorderPane.setAlignment(cursistPageTitle, Pos.CENTER);
            cursistPage.setTop(cursistPageTitle);

            VBox cursistPageButtons = new VBox(deleteButton, backHome);
            cursistPageButtons.setSpacing(10);
            Insets cursistPageButtonsPadding = new Insets(0, 15, 0, 15);
            cursistPageButtons.setPadding(cursistPageButtonsPadding);
            cursistPage.setLeft(cursistPageButtons);

            mainScene = new Scene(cursistPage, 600, 400); // Assign mainScene here

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
        Label mainSceneTitle = new Label("Create a new cursist");
        mainSceneTitle.setStyle("-fx-font-size: 30;");
        Insets mainSceneTitlePadding = new Insets(0, 0, 25, 0);
        mainSceneTitle.setPadding(mainSceneTitlePadding);
        mainPane.setTop(mainSceneTitle);
        BorderPane.setAlignment(mainSceneTitle, Pos.CENTER);
        Insets mainPanePadding = new Insets(25);
        mainPane.setPadding(mainPanePadding);

        Scene mainScene = new Scene(mainPane, 600, 400);
        stage.setTitle("Cursist Beheer");
        stage.setScene(mainScene);
        stage.show();

        // Set the initial scene to the homepage
        stage.setScene(homeScene);
        stage.setTitle("Cursist Beheer");
        stage.show();

        // // Create button op homepage
        createButton.setOnAction(e -> {
            stage.setScene(mainScene);
            stage.show();

        });

        editButton.setOnAction(e -> {
            BorderPane editPane = new BorderPane();
            Label title = new Label("Choose cursist to edit");
            BorderPane.setAlignment(title, Pos.TOP_CENTER);
            title.setStyle("-fx-font-size: 30;");
            Button chooseButton = new Button("Edit");
            VBox buttonsEdit = new VBox(chooseButton, backHome);
            buttonsEdit.setSpacing(15);
            Insets buttonsEditPadding = new Insets(0, 15, 0, 15);
            buttonsEdit.setPadding(buttonsEditPadding);
            ArrayList<String> cursistNames = cursistController.getAllCursists();

            items.setAll(cursistNames);
            list.setItems(items);

            editPane.setLeft(buttonsEdit);
            editPane.setTop(title);
            editPane.setCenter(list);
            list.setStyle("-fx-font-size: 24; -fx-alignment: center;");

            Scene updateScene = new Scene(editPane, 600, 400);
            stage.setScene(updateScene);
            stage.show();

            chooseButton.setOnAction(f -> {
                BorderPane editWindow = new BorderPane();
                Label editWindowTitle = new Label("Edit window");
                Button confirmButton = new Button("Confirm");
                confirmButton.setPadding(buttonsMenuPadding);
                VBox editButtons = new VBox(backHome, confirmButton);

                String selectedCursistName = list.getSelectionModel().getSelectedItem();
                Cursist selectedCursist = db.getCursistByName(selectedCursistName);

                naamField.setText(selectedCursist.getName());
                emailField.setText(selectedCursist.getEmailAddress());
                birthDateField.setText(selectedCursist.getBirthDate().toString());
                genderChoiceBox.getSelectionModel().selectFirst();
                addressField.setText(selectedCursist.getAddress());
                cityField.setText(selectedCursist.getCity());
                countryField.setText(selectedCursist.getCountry());

                editWindow.setTop(editWindowTitle);
                editWindow.setCenter(createFields);
                editWindow.setLeft(editButtons);

                Scene confirmEdit = new Scene(editWindow, 600, 400);
                stage.setScene(confirmEdit);
                stage.show();

                confirmButton.setOnAction(g -> {
                    selectedCursist.setEmailAddress(emailField.getText());
                    selectedCursist.setName(naamField.getText());
                    selectedCursist.setCity(cityField.getText());
                    selectedCursist.setCountry(countryField.getText());
                    selectedCursist.setAddress(addressField.getText());

                    db.updateCursistFields(selectedCursist);

                    System.out.println("Edits completed succesfully");
                });

            });

        });
        backHome.setOnAction(j -> {
            stage.setScene(homeScene);
            stage.show();
        });

    }

}
