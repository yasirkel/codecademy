import java.util.ArrayList;

public class courseController {
    private DatabaseManager databaseManager;

    public courseController() {
        this.databaseManager = new DatabaseManager();
    }

    public void addCourse(Course course) {
        databaseManager.saveCourse(course);
    }

    public ArrayList<String> getAllCourses() {
        return databaseManager.getAllCourses();
    }

    public void deleteCourse(String courseName) {
        databaseManager.deleteCourse(courseName);
    }

    // Implement other controller methods for updating, retrieving, etc.
}