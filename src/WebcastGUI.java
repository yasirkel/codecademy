import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.text.DateFormatter;

import org.xml.sax.Parser;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WebcastGUI extends Application {
    private WebcastController webcastController;
    private ListView<String> list;
    private ObservableList<String> items;
    private Scene mainScene;
    private Scene homeScene;
    private Button backHome;
    private DatabaseManagerWebcast db = new DatabaseManagerWebcast();
    private Button backToCodeCademy;

    public Scene webcastScene(Stage stage) {
        GUI gui = new GUI();
        webcastController = new WebcastController();

        Button backToHomeButton = new Button("< Home");
        backHome = new Button("< Home");
        backHome.setOnAction(j -> {
            stage.setScene(homeScene);
            stage.show();
        });

        // Create a welcome message for the homepage
        Button createButton = new Button("Create webcast");
        Button readButton = new Button("All webcasts");
        Label welcomeLabel = new Label("Welcome to webcast management");

        Insets welcomeLabelPadding = new Insets(25);
        welcomeLabel.setPadding(welcomeLabelPadding);
        welcomeLabel.setStyle("-fx-font-size: 24;");
        Button editButton = new Button("Edit webcast");
        backToCodeCademy = new Button("< CodeCademy");
        backToCodeCademy.setOnAction(l -> {
            stage.setScene(gui.getHomeScene(stage));
            stage.show();
        });
        backToCodeCademy.setPrefSize(150, 50);
        backToCodeCademy.setStyle("-fx-font-size: 18");

        // Create layout for the homepage
        BorderPane homePane = new BorderPane();
        BorderPane.setAlignment(welcomeLabel, Pos.CENTER);
        homePane.setTop(welcomeLabel);
        VBox homeLayout = new VBox(10, createButton, readButton, editButton, backToCodeCademy);

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
        homeScene = new Scene(homePane, 800, 600);

        TextField createTitleField = new TextField();
        createTitleField.setPromptText("Title");

        TextField createLengthField = new TextField();
        createLengthField.setPromptText("Length of webcast (in minutes)");

        TextField createDateField = new TextField();
        createDateField.setPromptText("Date of publication (DD-MM-YYYY)");

        TextField createURLField = new TextField();
        createURLField.setPromptText("URL");

        TextField createNameSpeakerField = new TextField();
        createNameSpeakerField.setPromptText("Name of speaker");

        TextField createOrganisationSpeakerField = new TextField();
        createOrganisationSpeakerField.setPromptText("Organisation of speaker");

        TextField createContentItemIDField = new TextField();
        createContentItemIDField.setPromptText("Content item ID");

        Button addButton = new Button("Add Webcast");

        addButton.setOnAction(f -> {

            if (createTitleField.getText().isBlank()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Title cannot be empty.");
                alert.showAndWait();
                return;
            }

            String title = createTitleField.getText();
            String length = createLengthField.getText();
            int lengthWebcast = Integer.valueOf(length);
            String date = createDateField.getText();
            String url = createURLField.getText();
            String nameSpeaker = createNameSpeakerField.getText();
            String organisationSpeaker = createOrganisationSpeakerField.getText();
            String contentItemIDString = createContentItemIDField.getText();
            int contentItemID = Integer.valueOf(contentItemIDString);

            Webcast newWebcast = new Webcast();
            newWebcast.setTitleWebcast(title);
            newWebcast.setLengthWebcast(lengthWebcast);
            newWebcast.setDatePublication(date);
            newWebcast.setURL(url);
            newWebcast.setNameSpeaker(nameSpeaker);
            newWebcast.setOrganisationSpeaker(organisationSpeaker);
            newWebcast.setContentItemID(contentItemID);

            webcastController.addWebcast(newWebcast);

            // Show alert after adding a course
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Webcast added successfully.");
            alert.showAndWait();

            // Clear the input fields after adding a course
            createTitleField.clear();
            createLengthField.clear();
            createContentItemIDField.clear();
            createURLField.clear();
            createNameSpeakerField.clear();
            createOrganisationSpeakerField.clear();
            createDateField.clear();
        });

        VBox createFields = new VBox(createTitleField, createLengthField, createDateField, createURLField,
                createNameSpeakerField, createOrganisationSpeakerField, createContentItemIDField, addButton);
        createFields.setSpacing(7);

        // CRUD Buttons are created
        Button deleteButton = new Button("Delete");
        Button updateButton = new Button("Update Webcast");

        // Create layout for buttons
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

        // Initialize main pane
        BorderPane mainPane = new BorderPane();
        mainPane.setCenter(vboxesCombined);

        // Initialize list and items
        list = new ListView<>();
        items = FXCollections.observableArrayList();

        // CRUD (read) functionality...
        readButton.setOnAction(g -> {
            // arraylist with all course names
            ArrayList<String> webcastTitles = webcastController.getAllWebcasts();

            items.setAll(webcastTitles);
            list.setItems(items);
            list.setStyle("-fx-font-size: 24; -fx-alignment: center;");
            list.setPadding(buttonsMenuPadding);

            BorderPane webcastPage = new BorderPane();

            webcastPage.setCenter(list);
            BorderPane.setMargin(list, new Insets(25));

            Label webcastPageTitle = new Label("Overview all webcasts");
            webcastPageTitle.setStyle("-fx-font-size: 30;");
            BorderPane.setAlignment(webcastPageTitle, Pos.CENTER);
            webcastPage.setTop(webcastPageTitle);

            HBox cursistPageButtons = new HBox(deleteButton, backHome);
            cursistPageButtons.setSpacing(10);
            Insets cursistPageButtonsPadding = new Insets(0, 15, 0, 15);
            cursistPageButtons.setPadding(cursistPageButtonsPadding);
            cursistPageButtons.setAlignment(Pos.CENTER);
            BorderPane.setMargin(cursistPageButtons, new Insets(0, 0, 25, 0));
            webcastPage.setBottom(cursistPageButtons);

            mainScene = new Scene(webcastPage, 800, 600); // Assign mainScene here

            stage.setTitle("Webcast overview");
            stage.setScene(mainScene);
            stage.show();

        });

        // Handle delete button action
        deleteButton.setOnAction(h -> {
            String selectedWebcast = list.getSelectionModel().getSelectedItem();

            if (selectedWebcast != null) {
                webcastController.deleteWebcast(selectedWebcast);
                items.remove(selectedWebcast);
            }
        });

        Label mainSceneTitle = new Label("Create a new webcast");
        mainSceneTitle.setStyle("-fx-font-size: 30;");
        Insets mainSceneTitlePadding = new Insets(0, 0, 25, 0);
        mainSceneTitle.setPadding(mainSceneTitlePadding);
        mainPane.setTop(mainSceneTitle);
        BorderPane.setAlignment(mainSceneTitle, Pos.CENTER);
        Insets mainPanePadding = new Insets(25);
        mainPane.setPadding(mainPanePadding);
        backToHomeButton.setPadding(buttonsMenuPadding);

        // mainPane.setBottom(backToHomeButton);
        backToHomeButton.setOnAction(i -> {
            stage.setScene(homeScene);
            stage.show();
        });

        Scene mainScene = new Scene(mainPane, 800, 600);
        stage.setTitle("Webcast Management");
        stage.setScene(mainScene);
        stage.show();

        stage.setScene(homeScene);
        stage.setTitle("Webcast Management");
        stage.show();
        // Create button op homepage
        createButton.setOnAction(j -> {
            stage.setScene(mainScene);
            stage.show();
        });

        editButton.setOnAction(k -> {
            BorderPane editPane = new BorderPane();
            Label title = new Label("Choose webcast to edit");
            BorderPane.setAlignment(title, Pos.TOP_CENTER);
            title.setStyle("-fx-font-size: 30;");
            Button chooseButton = new Button("Edit");
            chooseButton.setPadding(buttonsMenuPadding);

            // Use the class-level backHome variable
            HBox buttonsEdit = new HBox(chooseButton, backHome);
            buttonsEdit.setSpacing(15);

            Insets buttonsEditPadding = new Insets(0, 15, 0, 15);
            buttonsEdit.setPadding(buttonsEditPadding);
            ArrayList<String> webcastNames = webcastController.getAllWebcasts();

            items.setAll(webcastNames);
            list.setItems(items);

            editPane.setBottom(buttonsEdit);
            buttonsEdit.setAlignment(Pos.CENTER);
            buttonsEdit.setAlignment(Pos.CENTER);
            BorderPane.setMargin(buttonsEdit, new Insets(0, 0, 25, 0));
            editPane.setTop(title);
            editPane.setCenter(list);
            list.setStyle("-fx-font-size: 24; -fx-alignment: center;");
            BorderPane.setMargin(list, new Insets(25));

            Scene updateScene = new Scene(editPane, 800, 600);
            stage.setScene(updateScene);
            stage.show();
            TextField updateTitleField = new TextField();
            updateTitleField.setDisable(true);
            TextField updateLengthField = new TextField();
            TextField updateDateField = new TextField();
            TextField updateLinkField = new TextField();
            TextField updateNameSpeakerField = new TextField();
            TextField updateOrganisationSpeakerField = new TextField();
            TextField updateContentItemIdField = new TextField();

            chooseButton.setOnAction(f -> {
                BorderPane editWindow = new BorderPane();
                Label editWindowTitle = new Label("Edit window");
                editWindowTitle.setStyle("-fx-font-size: 30;");
                Button confirmButton = new Button("Confirm");
                confirmButton.setPadding(buttonsMenuPadding);

                HBox editButtons = new HBox(backHome, confirmButton);

                String selectedWebcastName = list.getSelectionModel().getSelectedItem();
                Webcast selectedWebcast = db.getWebcastByName(selectedWebcastName);

                updateTitleField.setText(selectedWebcast.getTitleWebcast() + " (Title cannot be changed)");
                updateLengthField.setText(String.valueOf(selectedWebcast.getLengthWebcast()));
                updateDateField.setText(selectedWebcast.getDatePublication().toString());
                updateLinkField.setText(selectedWebcast.getURL());
                updateNameSpeakerField.setText(selectedWebcast.getNameSpeaker());
                updateOrganisationSpeakerField.setText(selectedWebcast.getOrganisationSpeaker());
                updateContentItemIdField.setText(String.valueOf(selectedWebcast.getContentItemID()));

                VBox updateFields = new VBox(updateTitleField, updateLengthField, updateDateField, updateLinkField,
                        updateNameSpeakerField, updateOrganisationSpeakerField, updateContentItemIdField);
                updateFields.setSpacing(7);

                editWindow.setTop(editWindowTitle);
                BorderPane.setAlignment(editWindowTitle, Pos.CENTER);
                editWindow.setCenter(updateFields);
                editWindow.setBottom(editButtons);
                editButtons.setAlignment(Pos.CENTER);
                editButtons.setSpacing(15);
                BorderPane.setMargin(editButtons, new Insets(0, 0, 25, 0));
                BorderPane.setMargin(updateFields, new Insets(25));

                Scene confirmEdit = new Scene(editWindow, 800, 600);
                stage.setScene(confirmEdit);
                stage.show();

                confirmButton.setOnAction(g -> {
                    selectedWebcast.setTitleWebcast(updateTitleField.getText());
                    selectedWebcast.setLengthWebcast(Integer.parseInt(updateLengthField.getText()));
                    selectedWebcast.setDatePublication(updateDateField.getText());
                    selectedWebcast.setURL(updateLinkField.getText());
                    selectedWebcast.setNameSpeaker(updateNameSpeakerField.getText());
                    selectedWebcast.setOrganisationSpeaker(updateOrganisationSpeakerField.getText());
                    selectedWebcast.setContentItemID(Integer.parseInt(updateContentItemIdField.getText()));

                    db.updatewebcastFields(selectedWebcast);

                    // Add alert pop-up that course has been edited
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Webcast edited");
                    alert.setHeaderText(null);
                    alert.setContentText("Webcast has been edited successfully!");
                    alert.showAndWait();

                });
            });
        });
        return homeScene;
    }

    public WebcastGUI(Webcast webcast) {
    }

    @Override
    public void start(Stage arg0) throws Exception {

    }

}
