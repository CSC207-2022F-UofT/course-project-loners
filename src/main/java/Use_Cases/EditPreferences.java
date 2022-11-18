package Use_Cases;

import Entities.Preferences;

public class EditPreferences {
    private String preferredAge;
    private String preferredGender;
    private String preferredLocation;
    private String id;

    public EditPreferences(Preferences preferences) {
        this.preferredAge = String.valueOf(preferences.getPreferredAge());
        this.preferredGender = preferences.getPreferredGender();
        this.preferredLocation = String.valueOf(preferences.getPreferredLocation()[0]) + " " +
                String.valueOf(preferences.getPreferredLocation()[1]);
        this.id = preferences.getID();
    }

    public void writeData() {
        Object[] userData = DataFetchControl.fetch_byid(id);

        // add new preference data, or overwrite if they already exist
        userData[12] = preferredAge;
        userData[13] = preferredGender;
        userData[14] = preferredLocation;

        DataFetchControl.send_toid(id, userData);
    }

//    public static void main(String[] args) {
//        writeData();
//    }
}
