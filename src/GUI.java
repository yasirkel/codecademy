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
    @Override
    public void start(Stage stage) {
        stage.setTitle("Codecademy");

        BorderPane mainPane = new BorderPane();
        mainPane.setPadding(new Insets(5));

        // mainPane.setTop(inputFields);

        Scene mainView = new Scene(mainPane);

        stage.setScene(mainView);
        stage.show();
    }

}
