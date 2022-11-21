package Use_Cases;

import Entities.Preferences;
import Controllers_Presenters.DataFetchControl;
import Controllers_Presenters.DataSendControl;

import java.util.ArrayList;
import java.util.List;

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

        // remove id from the array (to be added back by DataSendControl.send_toid)
        List<Object> tempUserData = new ArrayList<>(List.of(userData));
        tempUserData.remove(0);
        Object[] userDataNoID = tempUserData.toArray();

        // add new preference data, or overwrite if already existing
        userDataNoID[12] = prefAge; /// change to 11
        userDataNoID[13] = prefGender; /// change to 12
        userDataNoID[14] = prefLocation; /// change to 13
        userDataNoID[15] = prefLocationRange; /// change to 14

        DataSendControl dataSendControl = new DataSendControl();
        dataSendControl.send_toid(id, userDataNoID);
    }
}
