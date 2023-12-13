import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {
    private Connection connection;

    public DatabaseManager() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost;databaseName=CodeCademy;username=admin;password=admin123;integratedSecurity=false;encrypt=true;trustServerCertificate=true;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveCursist(Cursist cursist) {
        try {
            String query = "INSERT INTO Cursist (EmailAddress, Name, BirthDate, Sex, Address, Residence, Country) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, cursist.getEmailAddress());
                statement.setString(2, cursist.getName());
                statement.setObject(3, cursist.getBirthDate());
                statement.setString(4, cursist.isSex());
                statement.setString(5, cursist.getAddress());
                statement.setString(6, cursist.getResidence());
                statement.setString(7, cursist.getCountry());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Implementeer andere CRUD-operaties
}
