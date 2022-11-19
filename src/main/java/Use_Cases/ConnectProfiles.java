package Use_Cases;

import Entities.Preferences;
import Controllers_Presenters.DataFetchControl;

import java.util.ArrayList;
import java.util.List;

public class ConnectProfiles {
    private String preferredAge;
    private String preferredGender;
    private String preferredLocation;
    private double preferredLocationRange;
    private int id;

    public ConnectProfiles(Preferences preferences) {
        this.preferredAge = String.valueOf(preferences.getPreferredAge());
        this.preferredGender = preferences.getPreferredGender();
        this.preferredLocation = String.valueOf(preferences.getPreferredLocation()[0]) + " " +
                String.valueOf(preferences.getPreferredLocation()[1]);
        this.preferredLocationRange = preferences.getPreferredLocationRange();
        this.id = preferences.getID();
    }

    public List<String> compareTraits() {
        List<String> connectedIDs = new ArrayList<>();
        DataFetchControl dataFetchControl = new DataFetchControl();
        int lastID = dataFetchControl.fetch_lastID(); // number of IDs in the database

        for (int userID = 1; userID <= lastID; userID++) {
            if (userID != id) { // and userID is in PreferredLocationConnector id list
                Object[] userData = dataFetchControl.fetch_fromid(userID);
                String userAge = (String) userData[3];
                String userGender = (String) userData[5];
                String userLocation = (String) userData[7];

                if ((userAge == preferredAge) && (userGender == preferredGender) &&
                        (userLocation == preferredLocation)) { // use PreferredLocationConnector
                    connectedIDs.add(String.valueOf(userID));
                }
            }
        }

        return connectedIDs;
    }
}
