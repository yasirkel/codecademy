import java.util.ArrayList;

public class WebcastController {

    private DatabaseManagerWebcast databaseManager;

    public WebcastController() {
        this.databaseManager = new DatabaseManagerWebcast();
    }

    public void addWebcast(Webcast webcast) {
        databaseManager.saveWebcast(webcast);
    }

    public ArrayList<String> getAllWebcasts() {
        return databaseManager.getAllWebcasts();
    }

    public void deleteWebcast(String webcastName) {
        databaseManager.deleteWebcast(webcastName);
    }

}