public class CursistController {
    private DatabaseManager databaseManager;

    public CursistController() {
        this.databaseManager = new DatabaseManager();
    }

    public void toevoegenCursist(Cursist cursist) {
        databaseManager.saveCursist(cursist);
    }

}
