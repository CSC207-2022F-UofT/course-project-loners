package Use_Cases;

import Entities.Preferences;
import Controllers_Presenters.DataFetchControl;
import Controllers_Presenters.DataSendControl;

public class EditPreferences {
    private String preferredAge;
    private String preferredGender;
    private String preferredLocation;
    private String preferredLocationRange;
    private int id;

    public EditPreferences(Preferences preferences) {
        this.preferredAge = String.valueOf(preferences.getPreferredAge());
        this.preferredGender = preferences.getPreferredGender();
        this.preferredLocation = String.valueOf(preferences.getPreferredLocation()[0]) + " " +
                String.valueOf(preferences.getPreferredLocation()[1]);
        this.preferredLocationRange = String.valueOf(preferences.getPreferredLocationRange());
        this.id = preferences.getID();
    }

    public void writeData() {
        DataFetchControl dataFetchControl = new DataFetchControl();
        Object[] userData = dataFetchControl.fetch_fromid(id);

        // add new preference data, or overwrite if they already exist
        userData[12] = preferredAge;
        userData[13] = preferredGender;
        userData[14] = preferredLocation;
        userData[15] = preferredLocationRange;

        DataSendControl dataSendControl = new DataSendControl();
        dataSendControl.send_toid(id, userData);
    }

//    public static void main(String[] args) {
//        writeData();
//    }
}
