import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class GUI extends Application {
    private courseController courseController;
    private CursistController cursistController;
    private ListView<String> list;
    private ObservableList<String> items;
    private Scene mainScene;
    private Scene homeScene;
    private Button backHome;
    private DatabaseManager db = new DatabaseManager();
    private Button backToCodeCademy;

    @Override
    public void start(Stage stage) {

        // Create the homepage
        BorderPane codeCademyPane = new BorderPane();
        Label codeCademyTitle = new Label("CodeCademy");
        Label welcomeMessage = new Label("Welcome to CodeCademy!" + "\n" + "What would you like to do?" + "\n" +
                "Please select an option from the menu.");

        // All buttons
        Button cursistManagementButton = new Button("Cursist manager");
        Button courseManagementButton = new Button("Course manager");
        Button webcastManagementButton = new Button("Webcast manager");
        Button contentItemManagementButton = new Button("Content item manager");
        Button watchedContentManagementButton = new Button("Watched content manager");
        Button moduleManagementButton = new Button("Module manager");
        Button enrollmentManagementButton = new Button("Enrollment manager");
        Button certificateManagementButton = new Button("Certificate manager");

        cursistManagementButton.setOnMouseEntered(event -> cursistManagementButton.setCursor(Cursor.HAND));
        cursistManagementButton.setOnMouseExited(event -> cursistManagementButton.setCursor(Cursor.DEFAULT));
        courseManagementButton.setOnMouseEntered(event -> courseManagementButton.setCursor(Cursor.HAND));
        courseManagementButton.setOnMouseExited(event -> courseManagementButton.setCursor(Cursor.DEFAULT));
        webcastManagementButton.setOnMouseEntered(event -> webcastManagementButton.setCursor(Cursor.HAND));
        webcastManagementButton.setOnMouseExited(event -> webcastManagementButton.setCursor(Cursor.DEFAULT));
        contentItemManagementButton.setOnMouseEntered(event -> contentItemManagementButton.setCursor(Cursor.HAND));
        contentItemManagementButton.setOnMouseExited(event -> contentItemManagementButton.setCursor(Cursor.DEFAULT));
        watchedContentManagementButton
                .setOnMouseEntered(event -> watchedContentManagementButton.setCursor(Cursor.HAND));
        watchedContentManagementButton
                .setOnMouseExited(event -> watchedContentManagementButton.setCursor(Cursor.DEFAULT));
        moduleManagementButton.setOnMouseEntered(event -> moduleManagementButton.setCursor(Cursor.HAND));
        moduleManagementButton.setOnMouseExited(event -> moduleManagementButton.setCursor(Cursor.DEFAULT));
        enrollmentManagementButton.setOnMouseEntered(event -> enrollmentManagementButton.setCursor(Cursor.HAND));
        enrollmentManagementButton.setOnMouseExited(event -> enrollmentManagementButton.setCursor(Cursor.DEFAULT));
        certificateManagementButton.setOnMouseEntered(event -> certificateManagementButton.setCursor(Cursor.HAND));
        certificateManagementButton.setOnMouseExited(event -> certificateManagementButton.setCursor(Cursor.DEFAULT));

        cursistManagementButton.setPrefSize(150, 50);
        cursistManagementButton.setStyle(
                "-fx-font-size: 10;  -fx-background-radius: 5;");
        courseManagementButton.setPrefSize(150, 50);
        courseManagementButton.setStyle(
                "-fx-font-size: 10 ; -fx-background-radius: 5;");
        webcastManagementButton.setPrefSize(150, 50);
        webcastManagementButton.setStyle(
                "-fx-font-size: 10; -fx-background-radius: 5;");
        contentItemManagementButton.setPrefSize(150, 50);
        contentItemManagementButton.setStyle(
                "-fx-font-size: 10; -fx-background-radius: 5;");
        watchedContentManagementButton.setPrefSize(150, 50);
        watchedContentManagementButton.setStyle(
                "-fx-font-size: 10; -fx-background-radius: 5;");
        moduleManagementButton.setPrefSize(150, 50);
        moduleManagementButton.setStyle(
                "-fx-font-size: 10; -fx-background-radius: 5;");
        enrollmentManagementButton.setPrefSize(150, 50);
        enrollmentManagementButton.setStyle(
                "-fx-font-size: 10; -fx-background-radius: 5;");
        certificateManagementButton.setPrefSize(150, 50);
        certificateManagementButton.setStyle(
                "-fx-font-size: 10; -fx-background-radius: 5;");

        // All buttons in box
        VBox leftButtonBox = new VBox(cursistManagementButton, courseManagementButton, webcastManagementButton,
                contentItemManagementButton);
        VBox rightButtonBox = new VBox(watchedContentManagementButton, moduleManagementButton,
                enrollmentManagementButton, certificateManagementButton);
        HBox buttonBox = new HBox(leftButtonBox, rightButtonBox);

        leftButtonBox.setSpacing(15);
        rightButtonBox.setSpacing(15);
        buttonBox.setSpacing(70);

        // Set styles
        codeCademyTitle.setStyle("-fx-font-size: 30; -fx-padding: 10; -fx-text-fill: #333; -fx-font-weight: bold;");
        welcomeMessage.setStyle("-fx-font-size: 20; -fx-padding: 10;  -fx-text-fill: #333;");

        VBox vbox = new VBox(codeCademyTitle, welcomeMessage);
        vbox.setAlignment(Pos.CENTER);

        codeCademyPane.setStyle(
                "-fx-background-color: #f2f2f2; -fx-padding: 10; -fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #ccc; -fx-border-width: 1;");
        codeCademyPane.setTop(vbox);
        codeCademyPane.setCenter(buttonBox);
        buttonBox.setAlignment(Pos.CENTER);

        BorderPane.setAlignment(codeCademyTitle, Pos.CENTER);
        BorderPane.setMargin(codeCademyTitle, new Insets(10));

        // Create the homepage scene
        Scene codeCademyHomeScene = new Scene(codeCademyPane, 800, 600);
        stage.setScene(codeCademyHomeScene);
        stage.setTitle("CodeCademy");
        stage.show();

        // Cursist Manager Scene
        cursistManagementButton.setOnAction(j -> {

            cursistController = new CursistController();

            Button backToHomeButton = new Button("< Home");
            backHome = new Button("< Home");
            backHome.setOnAction(k -> {
                stage.setScene(homeScene);
                stage.show();
            });

            // Create a welcome message for the homepage
            Button createButton = new Button("Create Cursist");
            Button readButton = new Button("All Cursists");
            Label welcomeLabel = new Label("Welcome to Cursist Manager");
            Insets welcomeLabelPadding = new Insets(25);
            welcomeLabel.setPadding(welcomeLabelPadding);
            welcomeLabel.setStyle("-fx-font-size: 24;");
            Button editButton = new Button("Edit Cursist");
            backToCodeCademy = new Button("< CodeCademy");

            backToCodeCademy.setOnAction(l -> {
                stage.setScene(codeCademyHomeScene);
                stage.show();
            });

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
            backToCodeCademy.setPrefSize(150, 50);
            backToCodeCademy.setStyle("-fx-font-size: 18");

            homeLayout.setAlignment(Pos.CENTER);
            homePane.setCenter(homeLayout);
            Insets padding = new Insets(100);
            homePane.setPadding(padding);
            homeScene = new Scene(homePane, 800, 600);

            TextField createNaamField = new TextField();
            createNaamField.setPromptText("Name");

            TextField createEmailField = new TextField();
            createEmailField.setPromptText("Email");

            TextField createBirthDateField = new TextField();
            createBirthDateField.setPromptText("Birthdate");

            ChoiceBox<String> genderChoiceBox = new ChoiceBox<>();
            genderChoiceBox.getItems().addAll("Select gender", "Male", "Female", "Other");
            genderChoiceBox.getSelectionModel().selectFirst();

            TextField createAddressField = new TextField();
            createAddressField.setPromptText("Address");

            TextField createCityField = new TextField();
            createCityField.setPromptText("City");

            TextField createCountryField = new TextField();
            createCountryField.setPromptText("Country");

            Button addButton = new Button("Add Cursist ");

            addButton.setOnAction(e -> {
                String naam = createNaamField.getText();
                String email = createEmailField.getText();
                String birthDateText = createBirthDateField.getText();

                // Check if all fields are filled
                if (birthDateText.trim().isEmpty()) {
                    // Check if all fields are filled
                    System.out.println("Please fill in all fields.");
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

                Label cursistPageTitle = new Label("Overview all cursists");
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

                mainScene = new Scene(cursistPage, 800, 600); // Assign mainScene here

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

            Scene mainScene = new Scene(mainPane, 800, 600);
            stage.setScene(mainScene);
            stage.show();

            stage.setScene(homeScene);
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

                Scene updateScene = new Scene(editPane, 800, 600);
                stage.setScene(updateScene);
                stage.show();
                TextField updateNaamField = new TextField();
                TextField updateEmailField = new TextField();
                updateEmailField.setDisable(true);

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

                    HBox editButtons = new HBox(backHome, confirmButton);

                    String selectedCursistName = list.getSelectionModel().getSelectedItem();
                    Cursist selectedCursist = db.getCursistByName(selectedCursistName);

                    updateNaamField.setText(selectedCursist.getName());
                    updateEmailField.setText(selectedCursist.getEmailAddress() + " (EmailAddress cannot be changed)");
                    updateBirthDateField.setText(selectedCursist.getBirthDate().toString());
                    genderChoiceBox.getSelectionModel().selectFirst();
                    updateAddressField.setText(selectedCursist.getAddress());
                    updateCityField.setText(selectedCursist.getCity());
                    updateCountryField.setText(selectedCursist.getCountry());

                    VBox updateFields = new VBox(updateNaamField, updateEmailField,
                            updateBirthDateField,
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

                    Scene confirmEdit = new Scene(editWindow, 800, 600);
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

        });

        // --------------------------------------------------------------------------------------------------------------------------------------------------------------
        // --------------------------------------------------------------------------------------------------------------------------------------------------------------
        // --------------------------------------------------------------------------------------------------------------------------------------------------------------
        // --------------------------------------------------------------------------------------------------------------------------------------------------------------
        // --------------------------------------------------------------------------------------------------------------------------------------------------------------
        // --------------------------------------------------------------------------------------------------------------------------------------------------------------
        // --------------------------------------------------------------------------------------------------------------------------------------------------------------
        // --------------------------------------------------------------------------------------------------------------------------------------------------------------
        // COURSE MANAGEMENT

        courseManagementButton.setOnAction(e -> {
            courseController = new courseController();

            Button backToHomeButton = new Button("< Home");
            backHome = new Button("< Home");
            backHome.setOnAction(j -> {
                stage.setScene(homeScene);
                stage.show();
            });

            // Create a welcome message for the homepage
            Button createButton = new Button("Create Course");
            Button readButton = new Button("All Courses");
            Label welcomeLabel = new Label("Welcome to course management");

            Insets welcomeLabelPadding = new Insets(25);
            welcomeLabel.setPadding(welcomeLabelPadding);
            welcomeLabel.setStyle("-fx-font-size: 24;");
            Button editButton = new Button("Edit Course");
            backToCodeCademy = new Button("< CodeCademy");
            backToCodeCademy.setOnAction(l -> {
                stage.setScene(codeCademyHomeScene);
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

            TextField createNaamField = new TextField();
            createNaamField.setPromptText("Naam");

            TextField createSubjectField = new TextField();
            createSubjectField.setPromptText("Subject");

            TextField createIntroductionText = new TextField();
            createIntroductionText.setPromptText("Introduction Text");

            TextField createDifficultyLevel = new TextField();
            createDifficultyLevel.setPromptText("Difficulty Level");

            TextField createCourseId = new TextField();
            createCourseId.setPromptText("CourseId");

            TextField createModuleId = new TextField();
            createModuleId.setPromptText("ModuleId");

            Button addButton = new Button("Add Course");

            addButton.setOnAction(f -> {
                String naam = createNaamField.getText();
                String subject = createSubjectField.getText();
                String introductionText = createIntroductionText.getText();
                int difficultyLevel = Integer.valueOf(createDifficultyLevel.getText());
                int courseId = Integer.valueOf(createCourseId.getText());
                int moduleId = Integer.valueOf(createModuleId.getText());

                Course newCourse = new Course();
                newCourse.setName(naam);
                newCourse.setSubject(subject);
                newCourse.setIntroductionText(introductionText);
                newCourse.setDifficultyLevel(difficultyLevel);
                newCourse.setCourseId(courseId);
                newCourse.setModuleId(moduleId);

                courseController.addCourse(newCourse);

                // Print the new course to the console
                System.out.println("Course added: " + newCourse.getName() + " - " + newCourse.getSubject() + " - "
                        + newCourse.getIntroductionText() + " - " + newCourse.getDifficultyLevel() + " - "
                        + newCourse.getCourseId() + " - " + newCourse.getModuleId());

                // Clear the input fields after adding a course
                createNaamField.clear();
                createSubjectField.clear();
                createIntroductionText.clear();
                createDifficultyLevel.clear();
                createCourseId.clear();
                createModuleId.clear();

            });

            VBox createFields = new VBox(createNaamField, createSubjectField, createIntroductionText,
                    createDifficultyLevel,
                    createCourseId, createModuleId, addButton);
            createFields.setSpacing(7);

            // CRUD Buttons are created
            Button deleteButton = new Button("Delete");
            Button updateButton = new Button("Update Course");

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
                ArrayList<String> courseNames = courseController.getAllCourses();

                items.setAll(courseNames);
                list.setItems(items);
                list.setStyle("-fx-font-size: 24; -fx-alignment: center;");
                list.setPadding(buttonsMenuPadding);

                BorderPane cursistPage = new BorderPane();

                cursistPage.setCenter(list);
                BorderPane.setMargin(list, new Insets(25));

                Label cursistPageTitle = new Label("Overview all courses");
                cursistPageTitle.setStyle("-fx-font-size: 30;");
                BorderPane.setAlignment(cursistPageTitle, Pos.CENTER);
                cursistPage.setTop(cursistPageTitle);

                HBox cursistPageButtons = new HBox(deleteButton, backHome);
                cursistPageButtons.setSpacing(10);
                Insets cursistPageButtonsPadding = new Insets(0, 15, 0, 15);
                cursistPageButtons.setPadding(cursistPageButtonsPadding);
                cursistPageButtons.setAlignment(Pos.CENTER);
                BorderPane.setMargin(cursistPageButtons, new Insets(0, 0, 25, 0));
                cursistPage.setBottom(cursistPageButtons);

                mainScene = new Scene(cursistPage, 800, 600); // Assign mainScene here

                stage.setTitle("Course overview");
                stage.setScene(mainScene);
                stage.show();
            });

            // Handle delete button action
            deleteButton.setOnAction(h -> {
                String selectedCourse = list.getSelectionModel().getSelectedItem();

                if (selectedCourse != null) {
                    courseController.deleteCourse(selectedCourse);
                    items.remove(selectedCourse);
                }
            });

            Label mainSceneTitle = new Label("Create a new course");
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
            stage.setTitle("Course Management");
            stage.setScene(mainScene);
            stage.show();

            stage.setScene(homeScene);
            stage.setTitle("Course Management");
            stage.show();
            // Create button op homepage
            createButton.setOnAction(j -> {
                stage.setScene(mainScene);
                stage.show();

            });

            editButton.setOnAction(k -> {
                BorderPane editPane = new BorderPane();
                Label title = new Label("Choose course to edit");
                BorderPane.setAlignment(title, Pos.TOP_CENTER);
                title.setStyle("-fx-font-size: 30;");
                Button chooseButton = new Button("Edit");
                chooseButton.setPadding(buttonsMenuPadding);

                // Use the class-level backHome variable
                HBox buttonsEdit = new HBox(chooseButton, backHome);
                buttonsEdit.setSpacing(15);

                Insets buttonsEditPadding = new Insets(0, 15, 0, 15);
                buttonsEdit.setPadding(buttonsEditPadding);
                ArrayList<String> courseNames = courseController.getAllCourses();

                items.setAll(courseNames);
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
                TextField updateNaamField = new TextField();
                updateNaamField.setDisable(true);
                TextField updateSubjectField = new TextField();
                TextField updateIntroductionText = new TextField();
                TextField updateDifficultyLevel = new TextField();
                TextField updateCourseId = new TextField();
                TextField updateModuleId = new TextField();

                chooseButton.setOnAction(f -> {
                    BorderPane editWindow = new BorderPane();
                    Label editWindowTitle = new Label("Edit window");
                    editWindowTitle.setStyle("-fx-font-size: 30;");
                    Button confirmButton = new Button("Confirm");
                    confirmButton.setPadding(buttonsMenuPadding);

                    HBox editButtons = new HBox(backHome, confirmButton);

                    String selectedCourseName = list.getSelectionModel().getSelectedItem();
                    Course selectedCourse = db.getCourseByName(selectedCourseName);

                    updateNaamField.setText(selectedCourse.getName() + " (Name cannot be changed)");
                    updateSubjectField.setText(selectedCourse.getSubject());
                    updateIntroductionText.setText(selectedCourse.getIntroductionText());
                    updateDifficultyLevel.setText(String.valueOf(selectedCourse.getDifficultyLevel()));
                    updateCourseId.setText(String.valueOf(selectedCourse.getCourseId()));
                    updateModuleId.setText(String.valueOf(selectedCourse.getModuleId()));

                    VBox updateFields = new VBox(updateNaamField, updateSubjectField, updateIntroductionText,
                            updateDifficultyLevel, updateCourseId, updateModuleId);
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
                        selectedCourse.setName(updateNaamField.getText());
                        selectedCourse.setSubject(updateSubjectField.getText());
                        selectedCourse.setIntroductionText(updateIntroductionText.getText());
                        selectedCourse.setDifficultyLevel(Integer.parseInt(updateDifficultyLevel.getText()));
                        selectedCourse.setCourseId(Integer.parseInt(updateCourseId.getText()));
                        selectedCourse.setModuleId(Integer.parseInt(updateModuleId.getText()));

                        db.updateCourseFields(selectedCourse);

                        // Add alert pop-up that course has been edited
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Course edited");
                        alert.setHeaderText(null);
                        alert.setContentText("Course has been edited successfully!");
                        alert.showAndWait();

                    });
                });
            });
        });
    };

}
