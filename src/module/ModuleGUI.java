package module;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.GUI;

public class ModuleGUI {
    private ModuleController moduleController;
    private ListView<String> list;
    private ObservableList<String> items;
    private Scene mainScene;
    private Scene homeScene;
    private Button backHome;
    private Button backToCodeCademy;
    private BorderPane homePane;

    // public scene to get the cursist scene
    public Scene moduleScene(Stage stage) {
        moduleController = new ModuleController();
        GUI gui = new GUI();

        Button backToHomeButton = new Button("< Home");
        backHome = new Button("< Home");
        backHome.setOnAction(k -> {
            stage.setScene(homeScene);
            stage.show();
        });

        // Create a welcome message for the homepage
        Button createButton = new Button("Create Module");
        Button readButton = new Button("All Modules");
        Label welcomeLabel = new Label("Welcome to Module Manager");
        Insets welcomeLabelPadding = new Insets(
                25);
        welcomeLabel.setPadding(welcomeLabelPadding);
        welcomeLabel.setStyle("-fx-font-size: 24;");
        Button editButton = new Button("Edit Module");
        backToCodeCademy = new Button("< CodeCademy");
        backToCodeCademy.setOnAction(l -> {
            stage.setScene(gui.getHomeScene(stage));
            stage.show();
        });

        // Create layout for the homepage
        homePane = new BorderPane();
        BorderPane.setAlignment(welcomeLabel, Pos.CENTER);
        homePane.setTop(welcomeLabel);
        VBox homeLayout = new VBox(10, createButton, readButton, editButton, backToCodeCademy);

        createButton.setPrefSize(150, 50);
        createButton.setStyle("-fx-font-size: 18;");
        readButton.setPrefSize(150, 50);
        readButton.setStyle("-fx-font-size: 18;");
        editButton.setPrefSize(150, 50);
        editButton.setStyle("-fx-font-size: 18;");
        backToCodeCademy.setPrefSize(150, 50);
        backToCodeCademy.setStyle("-fx-font-size: 18;");

        homeLayout.setAlignment(Pos.CENTER);
        homePane.setCenter(homeLayout);
        Insets padding = new Insets(100);
        homePane.setPadding(padding);
        homeScene = new Scene(homePane, 800, 600);

        // CRUD (read) functionality...
        readButton.setOnAction(g -> {
            items = FXCollections.observableArrayList();
            list = new ListView<>();
            // arraylist with all course names
            ArrayList<String> moduleNames = moduleController.getAllModules();

            items.setAll(moduleNames);
            list.setItems(items);
            list.setStyle("-fx-font-size: 24; -fx-alignment: center;");
            // TODO: fix list padding
            // list.setPadding(buttonsMenuPadding);

            BorderPane cursistPage = new BorderPane();

            cursistPage.setCenter(list);
            BorderPane.setMargin(list, new Insets(25));

            Label ModulePageTitle = new Label("Overview all courses");
            ModulePageTitle.setStyle("-fx-font-size: 30;");
            BorderPane.setAlignment(ModulePageTitle, Pos.CENTER);
            cursistPage.setTop(ModulePageTitle);

            HBox cursistPageButtons = new HBox(backHome);
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

        return homeScene;
    }

    public ModuleGUI(ModuleController moduleController) {
    }
}
