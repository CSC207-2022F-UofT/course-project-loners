package Use_Cases;

import Entities.Preferences;
import Controllers_Presenters.DataFetchSendControl;

public class EditPreferences {
    private String preferredAge;
    private String preferredGender;
    private String preferredLocation;
    private int id;

    public EditPreferences(Preferences preferences) {
        this.preferredAge = String.valueOf(preferences.getPreferredAge());
        this.preferredGender = preferences.getPreferredGender();
        this.preferredLocation = String.valueOf(preferences.getPreferredLocation()[0]) + " " +
                String.valueOf(preferences.getPreferredLocation()[1]);
        this.id = Integer.parseInt(preferences.getID());
    }

    public void writeData() {
        DataFetchSendControl d = new DataFetchSendControl();
        Object[] userData = d.fetch_fromid(id);

        // add new preference data, or overwrite if they already exist
        userData[12] = preferredAge;
        userData[13] = preferredGender;
        userData[14] = preferredLocation;

        d.send_toid(id, userData);
    }

//    public static void main(String[] args) {
//        writeData();
//    }
}
