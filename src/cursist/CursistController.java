package cursist;

import DatabaseManager.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class CursistController {
    private DatabaseManager databaseManager;
    private Connection connection;

    public CursistController() {
        this.databaseManager = new DatabaseManager();

    }

    public ResultSet query(String sqlQuery) {
        try {
            connection = databaseManager.getConnection();
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
                statement.setString(4, cursist.getSex());
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

    public Cursist getCursistByName(String name) {
        try {
            String query = "SELECT * FROM Cursist WHERE Name = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, name);
                ResultSet rs = statement.executeQuery();

                if (rs.next()) {
                    Cursist cursist = new Cursist();
                    cursist.setName(rs.getString("Name"));
                    cursist.setEmailAddress(rs.getString("EmailAddress"));
                    cursist.setBirthDate(rs.getObject("BirthDate", LocalDate.class));
                    cursist.setSex(rs.getString("Sex"));
                    cursist.setAddress(rs.getString("Address"));
                    cursist.setCity(rs.getString("City"));
                    cursist.setCountry(rs.getString("Country"));

                    return cursist;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateCursistFields(Cursist cursist) {
        try {
            String query = "UPDATE Cursist SET Name = ?, BirthDate = ?, Sex = ?, Address = ?, City = ?, Country = ? WHERE EmailAddress = ?";
            try (PreparedStatement updateStatement = connection.prepareStatement(query)) {
                updateStatement.setString(1, cursist.getName());
                updateStatement.setObject(2, cursist.getBirthDate());
                updateStatement.setString(3, cursist.getSex());
                updateStatement.setString(4, cursist.getAddress());
                updateStatement.setString(5, cursist.getCity());
                updateStatement.setString(6, cursist.getCountry());
                updateStatement.setString(7, cursist.getEmailAddress());

                System.out.println(cursist.getEmailAddress());

                int rowsAffected = updateStatement.executeUpdate();
                connection.commit();

                if (rowsAffected == 0) {
                    SQLException e = new SQLException();
                    e.printStackTrace();
                }

            } catch (SQLException e) {
                e.printStackTrace();

            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public ArrayList<String> getAllCursistEmailAddress() {
        ArrayList<String> cursistEmailAddress = new ArrayList<>();

        try {
            ResultSet rs = query("SELECT * FROM Cursist");

            while (rs.next()) {
                String name = rs.getString("EmailAddress");
                cursistEmailAddress.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cursistEmailAddress;
    }

}
