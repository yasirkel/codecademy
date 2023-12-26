import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
        String query = "UPDATE Cursist SET Name = ?, BirthDate = ?, Sex = ?, Address = ?, City = ?, Country = ? WHERE EmailAddress = ?";
        try (PreparedStatement updateStatement = connection.prepareStatement(query)) {
            updateStatement.setString(1, cursist.getName());
            updateStatement.setObject(2, cursist.getBirthDate());
            updateStatement.setString(3, cursist.isSex());
            updateStatement.setString(4, cursist.getAddress());
            updateStatement.setString(5, cursist.getCity());
            updateStatement.setString(6, cursist.getCountry());
            updateStatement.setString(7, cursist.getEmailAddress());
            updateStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // COURSE
    public ArrayList<String> getAllCourses() {
        ArrayList<String> courseNames = new ArrayList<>();

        try {
            ResultSet rs = query("SELECT * FROM Course");

            while (rs.next()) {
                String name = rs.getString("Name");
                courseNames.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courseNames;
    }

    public void saveCourse(Course course) {
        try {
            String query = "INSERT INTO Course (Name, Subject, IntroductionText, DifficultyLevel, CourseID, ModuleID) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, course.getName());
                statement.setString(2, course.getSubject());
                statement.setString(3, course.getIntroductionText());
                statement.setInt(4, course.getDifficultyLevel());
                statement.setInt(5, course.getCourseId());
                statement.setInt(6, course.getModuleId());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCourse(String courseName) {
        try {
            String query = "DELETE FROM Course WHERE Name = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, courseName);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Course getCourseByName(String name) {
        try {
            String query = "SELECT * FROM Course WHERE Name = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, name);
                ResultSet rs = statement.executeQuery();

                if (rs.next()) {
                    Course course = new Course();
                    course.setName(rs.getString("Name"));
                    course.setSubject(rs.getString("Subject"));
                    course.setIntroductionText(rs.getString("IntroductionText"));
                    course.setDifficultyLevel(rs.getInt("DifficultyLevel"));
                    course.setCourseId(rs.getInt("CourseID"));
                    course.setModuleId(rs.getInt("ModuleID"));

                    return course;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateCourseFields(Course course) {
        String query = "UPDATE Course SET Subject = ?, IntroductionText = ?, DifficultyLevel = ?, CourseID = ?, ModuleID = ? WHERE Name = ?";
        try (PreparedStatement updateStatement = connection.prepareStatement(query)) {
            updateStatement.setString(1, course.getSubject());
            updateStatement.setString(2, course.getIntroductionText());
            updateStatement.setInt(3, course.getDifficultyLevel());
            updateStatement.setInt(4, course.getCourseId());
            updateStatement.setInt(5, course.getModuleId());
            updateStatement.setString(6, course.getName());

            updateStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
