package Use_Cases;

import Entities.Preferences;
import Controllers_Presenters.DataFetchControl;
import Controllers_Presenters.DataSendControl;

import java.util.ArrayList;
import java.util.List;

/**
 * A Use Case class that reformats the preferences passed in from a Preferences object, and stores this information
 * in the database.
 */
public class EditPreferences {
    private final String prefAge;
    private final String prefGender;
    private final String prefLocationRange;
    private final int id;

    /**
     * Construct an EditPreferences object, initializing prefAge, prefGender, and prefLocationRange as Strings and id
     * as an int, corresponding to the information in the Preferences object preferences.
     *
     * @param preferences the Input Data containing the preferred age, preferred gender, preferred location range, and
     *                    ID of the user
     */
    public EditPreferences(Preferences preferences) { // passed in by EditPreferencesControl
        this.prefAge = String.valueOf(preferences.getPreferredAge());
        this.prefGender = preferences.getPreferredGender();
        this.prefLocationRange = String.valueOf(preferences.getPreferredLocationRange());
        this.id = preferences.getID();
    }

    /**
     * Update the database with the user's new preferences.
     */
    public void writeData() {
        // fetch the user's current profile and preference data from the database
        Object[] userData = DataFetchControl.fetch_fromid(id);
        userData = (Object[]) userData[0];

        // remove id from the array (to be added back by DataSendControl.send_toid)
        List<Object> tempUserData = new ArrayList<>(List.of(userData));
        tempUserData.remove(0);
        Object[] userDataNoID = tempUserData.toArray();

        // add new preference data, or overwrite if they already exist
        userDataNoID[11] = prefAge;
        userDataNoID[12] = prefGender;
        userDataNoID[13] = prefLocationRange;

        // send the user's profile and new preference data to the database
        DataSendControl dataSendControl = new DataSendControl();
        dataSendControl.send_toid(id, userDataNoID);
    }
}
