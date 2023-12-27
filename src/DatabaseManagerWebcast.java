import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseManagerWebcast {
    private Connection connection;

    public DatabaseManagerWebcast() {
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

    public ArrayList<String> getAllWebcasts() {
        ArrayList<String> webcastNames = new ArrayList<>();

        try {
            ResultSet rs = query("SELECT * FROM Webcast");

            while (rs.next()) {
                String name = rs.getString("TitleWebcast");
                webcastNames.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return webcastNames;
    }

    public void saveWebcast(Webcast webcast) {
        try {
            String query = "INSERT INTO Webcast (TitleWebcast, LengthWebcast, DatePublication, URL, NameSpeaker, OrganisationSpeaker, ContentItemID) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, webcast.getTitleWebcast());
                statement.setString(2, String.valueOf(webcast.getLengthWebcast()));
                statement.setString(3, webcast.getDatePublication());
                statement.setString(4, webcast.getURL());
                statement.setString(5, webcast.getNameSpeaker());
                statement.setString(6, webcast.getOrganisationSpeaker());
                statement.setString(7, String.valueOf(webcast.getContentItemID()));

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteWebcast(String titleWebcast) {
        try {
            String query = "DELETE FROM Webcast WHERE TitleWebcast = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, titleWebcast);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Webcast getWebcastByName(String title) {
        try {
            String query = "SELECT * FROM Webcast WHERE TitleWebcast = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, title);
                ResultSet rs = statement.executeQuery();

                if (rs.next()) {
                    Webcast webcast = new Webcast();
                    webcast.setTitleWebcast(rs.getString("TitleWebcast"));
                    webcast.setLengthWebcast(rs.getInt("LengthWebcast"));
                    webcast.setDatePublication(rs.getString("DatePublication"));
                    webcast.setURL(rs.getString("URL"));
                    webcast.setNameSpeaker(rs.getString("NameSpeaker"));
                    webcast.setOrganisationSpeaker(rs.getString("OrganisationSpeaker"));
                    webcast.setContentItemID(rs.getInt("ContentItemID"));
                    return webcast;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updatewebcastFields(Webcast webcast) {
        String query = "UPDATE Webcast SET LengthWebcast = ?, DatePublication = ?, URL = ?, NameSpeaker = ?, OrganisationSpeaker = ?, ContentItemID = ? WHERE TitleWebcast = ?";
        try (PreparedStatement updateStatement = connection.prepareStatement(query)) {
            updateStatement.setInt(1, webcast.getLengthWebcast());
            updateStatement.setObject(2, webcast.getDatePublication());
            updateStatement.setString(3, webcast.getURL());
            updateStatement.setString(4, webcast.getNameSpeaker());
            updateStatement.setString(5, webcast.getOrganisationSpeaker());
            updateStatement.setInt(6, webcast.getContentItemID());
            updateStatement.setString(7, webcast.getTitleWebcast());

            updateStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
