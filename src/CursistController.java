import java.util.ArrayList;

public class CursistController {
    private DatabaseManager databaseManager;

    public CursistController() {
        this.databaseManager = new DatabaseManager();
    }

    public void toevoegenCursist(Cursist cursist) {
        databaseManager.saveCursist(cursist);
    }

    public ArrayList<String> getAllCursists() {
        return databaseManager.getAllCursist();
    }

    public void deleteCursist(String cursistName) {
        databaseManager.deleteCursist(cursistName);
    }

    // Implement other controller methods for updating, retrieving, etc.
}
