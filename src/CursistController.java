import java.util.ArrayList;

public class CursistController {
    private DatabaseManagerCursist databaseManager;

    public CursistController() {
        this.databaseManager = new DatabaseManagerCursist();
    }

    public void addCursist(Cursist cursist) {
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
