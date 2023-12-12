import java.sql.ResultSet;
import java.sql.SQLException;

import db.Db;
import javafx.application.Application;

/**
 * CodeCademy
 */
public class CodeCademy {

    public static void main(String[] args) {
        System.out.println("Hij werkt!: Yasir");
        System.out.println("Hij werkt: Amin");
        System.out.println("hij werk: Khalil");

        // db klasse aangemaakt en connectie gestart
        Db db = new Db();
        db.connect();

        // sla het resultaat van de query op in de resultset.
        ResultSet resultSet = db.query("SELECT * FROM Users WHERE firstname = 'Yasir'");

        try {
            // loop door de results
            while (resultSet.next()) {
                // haal van de resultaten de columnaam op met de value daarin
                String firstname = resultSet.getString("firstname");
                System.out.println(firstname);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // start javafx vanuit class
        Application.launch(GUI.class);

    }
}