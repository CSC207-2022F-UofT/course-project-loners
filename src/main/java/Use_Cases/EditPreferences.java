package Use_Cases;

import Entities.Preferences;
import Controllers_Presenters.DataFetchControl;
import Controllers_Presenters.DataSendControl;

public class EditPreferences {
    private String prefAge;
    private String prefGender;
    private String prefLocation;
    private String prefLocationRange;
    private int id;

    public EditPreferences(Preferences preferences) { // passed in by EditPreferencesControl
        this.prefAge = String.valueOf(preferences.getPreferredAge());
        this.prefGender = preferences.getPreferredGender();
        this.prefLocation = preferences.getPreferredLocation()[0] + " " + preferences.getPreferredLocation()[1];
        this.prefLocationRange = String.valueOf(preferences.getPreferredLocationRange());
        this.id = preferences.getID();
    }

    public void writeData() {
        DataFetchControl dataFetchControl = new DataFetchControl();
        Object[] userData = dataFetchControl.fetch_fromid(id);

        // add new preference data, or overwrite if already existing
        userData[12] = prefAge;
        userData[13] = prefGender;
        userData[14] = prefLocation;
        userData[15] = prefLocationRange;

        DataSendControl dataSendControl = new DataSendControl();
        dataSendControl.send_toid(id, userData);
    }
}
