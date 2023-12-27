import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModuleController {
    private DatabaseManagerCourse databaseManager;

    public ModuleController() {
        this.databaseManager = new DatabaseManagerCourse();
    }

    public ArrayList<String> getAllModules() {
        ArrayList<String> moduleNames = new ArrayList<>();

        try {
            ResultSet rs = databaseManager.query("SELECT * FROM Module");

            while (rs.next()) {
                String title = rs.getString("Title");
                moduleNames.add(title);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return moduleNames;
    }

}