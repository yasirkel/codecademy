import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

    public ResultSet query(String sqlQuery) {
        try {
            Statement stmt = connection.createStatement();
            return stmt.executeQuery(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> getAllCursist() {
        ArrayList<String> cursistNames = new ArrayList<>();

        try {
            ResultSet rs = query("SELECT * FROM Cursist");

            while (rs.next()) {
                String name = rs.getString("Name");
                cursistNames.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cursistNames;
    }

    public void saveCursist(Cursist cursist) {
        try {
            String query = "INSERT INTO Cursist (EmailAddress, Name, BirthDate, Sex, Address, City, Country) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, cursist.getEmailAddress());
                statement.setString(2, cursist.getName());
                statement.setObject(3, cursist.getBirthDate());
                statement.setString(4, cursist.isSex());
                statement.setString(5, cursist.getAddress());
                statement.setString(6, cursist.getCity());
                statement.setString(7, cursist.getCountry());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCursist(String cursistName) {
        try {
            String query = "DELETE FROM Cursist WHERE Name = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, cursistName);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Implement other CRUD operations
}
