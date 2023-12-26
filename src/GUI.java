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
import javafx.scene.control.Alert.AlertType;
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
    private Button backHome;
    private DatabaseManager db = new DatabaseManager();

    @Override
    public void start(Stage stage) {
        cursistController = new CursistController();

        Button backToHomeButton = new Button("< Home");
        backHome = new Button("< Home");
        backHome.setOnAction(j -> {
            stage.setScene(homeScene);
            stage.show();
        });

        // Create a welcome message for the homepage
        Button createButton = new Button("Create Cursist");
        Button readButton = new Button("All Cursists");
        Label welcomeLabel = new Label("Welkom bij cursist beheer");
        Insets welcomeLabelPadding = new Insets(25);
        welcomeLabel.setPadding(welcomeLabelPadding);
        welcomeLabel.setStyle("-fx-font-size: 24;");
        Button editButton = new Button("Edit Cursist");

        // Create layout for the homepage
        BorderPane homePane = new BorderPane();
        BorderPane.setAlignment(welcomeLabel, Pos.CENTER);
        homePane.setTop(welcomeLabel);
        VBox homeLayout = new VBox(10, createButton, readButton, editButton);

        createButton.setPrefSize(150, 50);
        createButton.setStyle("-fx-font-size: 18");
        readButton.setPrefSize(150, 50);
        readButton.setStyle("-fx-font-size: 18");
        editButton.setPrefSize(150, 50);
        editButton.setStyle("-fx-font-size: 18");

        homeLayout.setAlignment(Pos.CENTER);
        homePane.setCenter(homeLayout);
        Insets padding = new Insets(100);
        homePane.setPadding(padding);
        homeScene = new Scene(homePane, 600, 400);

        TextField createNaamField = new TextField();
        createNaamField.setPromptText("Naam");

        TextField createEmailField = new TextField();
        createEmailField.setPromptText("Email");

        TextField createBirthDateField = new TextField();
        createBirthDateField.setPromptText("Birthdate");

        ChoiceBox<String> genderChoiceBox = new ChoiceBox<>();
        genderChoiceBox.getItems().addAll("Selecteer geslacht", "Man", "Vrouw");
        genderChoiceBox.getSelectionModel().selectFirst();

        TextField createAddressField = new TextField();
        createAddressField.setPromptText("Address");

        TextField createCityField = new TextField();
        createCityField.setPromptText("City");

        TextField createCountryField = new TextField();
        createCountryField.setPromptText("Country");

        Button addButton = new Button("Voeg Cursist Toe");

        addButton.setOnAction(e -> {
            String naam = createNaamField.getText();
            String email = createEmailField.getText();
            String birthDateText = createBirthDateField.getText();

            // Check if all fields are filled
            if (birthDateText.trim().isEmpty()) {
                // Check if all fields are filled
                System.out.println("Ongeldige invoer voor geboortedatum.");
                return;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate birthDate = LocalDate.parse(birthDateText, formatter);

            String gender = genderChoiceBox.getValue(); // Get the selected gender
            String address = createAddressField.getText();
            String city = createCityField.getText();
            String country = createCountryField.getText();

            Cursist nieuweCursist = new Cursist();
            nieuweCursist.setName(naam);
            nieuweCursist.setEmailAddress(email);
            nieuweCursist.setBirthDate(birthDate);
            nieuweCursist.setSex(gender);
            nieuweCursist.setAddress(address);
            nieuweCursist.setCity(city);
            nieuweCursist.setCountry(country);

            cursistController.addCursist(nieuweCursist);

            // Add alert pop-up that cursist has been added
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cursist added");
            alert.setHeaderText(null);
            alert.setContentText("Cursist added successfully.");
            alert.showAndWait();

            // Clear the input fields after adding a cursist
            createNaamField.clear();
            createEmailField.clear();
            createBirthDateField.clear();
            genderChoiceBox.getSelectionModel().selectFirst();
            createAddressField.clear();
            createCityField.clear();
            createCountryField.clear();

        });

        VBox createFields = new VBox(createNaamField, createEmailField, createBirthDateField, genderChoiceBox,
                createAddressField,
                createCityField, createCountryField);
        createFields.setSpacing(7);

        // CRUD Buttons are created
        Button deleteButton = new Button("Delete");
        Button updateButton = new Button("Update Cursist");

        // Create layout for the homepage
        HBox buttonsMenu = new HBox(addButton, backToHomeButton);

        Insets buttonsMenuPadding = new Insets(10);
        addButton.setPadding(buttonsMenuPadding);
        deleteButton.setPadding(buttonsMenuPadding);
        updateButton.setPadding(buttonsMenuPadding);
        backHome.setPadding(buttonsMenuPadding);

        buttonsMenu.setAlignment(Pos.CENTER);
        buttonsMenu.setSpacing(5);

        VBox vboxesCombined = new VBox(createFields, buttonsMenu);
        vboxesCombined.setSpacing(10);

        // Main pane is created
        BorderPane mainPane = new BorderPane();
        mainPane.setCenter(vboxesCombined);

        // Initialize list and items
        list = new ListView<>();
        items = FXCollections.observableArrayList();

        // CRUD (read) functionality...
        readButton.setOnAction(e -> {
            // arraylist of cursist names
            ArrayList<String> cursistNames = cursistController.getAllCursists();

            items.setAll(cursistNames);
            list.setItems(items);
            list.setStyle("-fx-font-size: 24; -fx-alignment: center;");

            BorderPane cursistPage = new BorderPane();

            cursistPage.setCenter(list);
            BorderPane.setMargin(list, new Insets(25));

            Label cursistPageTitle = new Label("Overzicht alle cursisten");
            cursistPageTitle.setStyle("-fx-font-size: 30;");
            BorderPane.setAlignment(cursistPageTitle, Pos.CENTER);
            cursistPage.setTop(cursistPageTitle);

            HBox cursistPageButtons = new HBox(deleteButton, backHome);
            cursistPageButtons.setSpacing(10);
            Insets cursistPageButtonsPadding = new Insets(0, 15, 0, 15);
            cursistPageButtons.setPadding(cursistPageButtonsPadding);
            cursistPage.setBottom(cursistPageButtons);
            cursistPageButtons.setAlignment(Pos.CENTER);
            BorderPane.setMargin(cursistPageButtons, new Insets(0, 0, 25, 0));

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

        Label mainSceneTitle = new Label("Create a new cursist");
        mainSceneTitle.setStyle("-fx-font-size: 30;");
        Insets mainSceneTitlePadding = new Insets(0, 0, 25, 0);
        mainSceneTitle.setPadding(mainSceneTitlePadding);
        mainPane.setTop(mainSceneTitle);
        BorderPane.setAlignment(mainSceneTitle, Pos.CENTER);
        Insets mainPanePadding = new Insets(25);
        mainPane.setPadding(mainPanePadding);
        backToHomeButton.setPadding(buttonsMenuPadding);

        backToHomeButton.setOnAction(e -> {
            stage.setScene(homeScene);
            stage.show();
        });

        Scene mainScene = new Scene(mainPane, 600, 400);
        stage.setTitle("Cursist Management");
        stage.setScene(mainScene);
        stage.show();

        stage.setScene(homeScene);
        stage.setTitle("Cursist Management");
        stage.show();

        // Create button on homepage
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
            chooseButton.setPadding(buttonsMenuPadding);

            // Use the class-level backHome variable
            HBox buttonsEdit = new HBox(chooseButton, backHome);
            buttonsEdit.setSpacing(15);
            Insets buttonsEditPadding = new Insets(0, 15, 0, 15);
            buttonsEdit.setPadding(buttonsEditPadding);
            ArrayList<String> cursistNames = cursistController.getAllCursists();

            items.setAll(cursistNames);
            list.setItems(items);

            editPane.setBottom(buttonsEdit);
            buttonsEdit.setAlignment(Pos.CENTER);
            buttonsEdit.setAlignment(Pos.CENTER);
            BorderPane.setMargin(buttonsEdit, new Insets(0, 0, 25, 0));
            editPane.setTop(title);
            editPane.setCenter(list);
            list.setStyle("-fx-font-size: 24; -fx-alignment: center;");
            BorderPane.setMargin(list, new Insets(25));

            Scene updateScene = new Scene(editPane, 600, 400);
            stage.setScene(updateScene);
            stage.show();
            TextField updateNaamField = new TextField();
            TextField updateEmailField = new TextField();
            TextField updateBirthDateField = new TextField();
            TextField updateAddressField = new TextField();
            TextField updateCityField = new TextField();
            TextField updateCountryField = new TextField();

            chooseButton.setOnAction(f -> {
                BorderPane editWindow = new BorderPane();
                Label editWindowTitle = new Label("Edit window");
                editWindowTitle.setStyle("-fx-font-size: 30;");
                Button confirmButton = new Button("Confirm");
                confirmButton.setPadding(buttonsMenuPadding);

                // Use the class-level buttonsEdit variable
                HBox editButtons = new HBox(backHome, confirmButton);

                String selectedCursistName = list.getSelectionModel().getSelectedItem();
                Cursist selectedCursist = db.getCursistByName(selectedCursistName);

                updateNaamField.setText(selectedCursist.getName());
                updateEmailField.setText(selectedCursist.getEmailAddress());
                updateBirthDateField.setText(selectedCursist.getBirthDate().toString());
                genderChoiceBox.getSelectionModel().selectFirst();
                updateAddressField.setText(selectedCursist.getAddress());
                updateCityField.setText(selectedCursist.getCity());
                updateCountryField.setText(selectedCursist.getCountry());

                VBox updateFields = new VBox(updateNaamField, updateEmailField, updateBirthDateField,
                        updateAddressField, updateCityField, updateCountryField);
                updateFields.setSpacing(7);

                editWindow.setTop(editWindowTitle);
                BorderPane.setAlignment(editWindowTitle, Pos.CENTER);
                editWindow.setCenter(updateFields);
                editWindow.setBottom(editButtons);
                editButtons.setAlignment(Pos.CENTER);
                editButtons.setSpacing(15);
                BorderPane.setMargin(editButtons, new Insets(0, 0, 25, 0));
                BorderPane.setMargin(updateFields, new Insets(25));

                Scene confirmEdit = new Scene(editWindow, 600, 400);
                stage.setScene(confirmEdit);
                stage.show();

                confirmButton.setOnAction(g -> {
                    selectedCursist.setEmailAddress(updateEmailField.getText());
                    selectedCursist.setName(updateNaamField.getText());
                    selectedCursist.setCity(updateCityField.getText());
                    selectedCursist.setCountry(updateCountryField.getText());
                    selectedCursist.setAddress(updateAddressField.getText());

                    db.updateCursistFields(selectedCursist);

                    Alert alert = new Alert(AlertType.INFORMATION);

                    // Set the title and header text
                    alert.setTitle("Confirmed");
                    alert.setHeaderText(null);

                    // Set the content text
                    alert.setContentText("Edits have been confirmed!");

                    // Show the alert
                    alert.showAndWait();
                });
            });
        });

    }

}
