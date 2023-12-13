import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application {
    private CursistController cursistController;

    @Override
    public void start(Stage stage) {
        cursistController = new CursistController();

        TextField naamField = new TextField("Naam");
        TextField emailField = new TextField("Email");
        Button addButton = new Button("Voeg Cursist Toe");

        addButton.setOnAction(e -> {
            String naam = naamField.getText();
            String email = emailField.getText();

            Cursist nieuweCursist = new Cursist();
            nieuweCursist.setName(naam);
            nieuweCursist.setEmailAddress(email);

            cursistController.toevoegenCursist(nieuweCursist);

            // Voeg hier code toe om feedback aan de gebruiker te tonen
            System.out
                    .println("Cursist toegevoegd: " + nieuweCursist.getName() + ", " + nieuweCursist.getEmailAddress());
        });

        VBox root = new VBox(naamField, emailField, addButton);
        Scene scene = new Scene(root, 300, 200);

        stage.setTitle("Cursist");
        stage.setScene(scene);
        stage.show();
    }

}
