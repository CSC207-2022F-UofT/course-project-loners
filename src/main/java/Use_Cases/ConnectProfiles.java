package Use_Cases;

import Entities.Preferences;

import java.util.ArrayList;
import java.util.List;

public class ConnectProfiles {
    private String preferredAge;
    private String preferredGender;
    private String preferredLocation;
    private String id;

    public ConnectProfiles(Preferences preferences) {
        this.preferredAge = String.valueOf(preferences.getPreferredAge());
        this.preferredGender = preferences.getPreferredGender();
        this.preferredLocation = String.valueOf(preferences.getPreferredLocation()[0]) + " " +
                String.valueOf(preferences.getPreferredLocation()[1]);
        this.id = preferences.getID();
    }

    public List<String> compareTraits() {
        List<String> connectedIDs = new ArrayList<>();

        // for each id userID
            Object[] userData = DataFetchControl.fetch_byid(userID);
            String userAge = (String) userData[3];
            String userGender = (String) userData[5];
            String userLocation = (String) userData[7];

            if ((userAge == preferredAge) && (userGender == preferredGender) &&
                    (userLocation == preferredLocation)) { // use PreferredLocationConnector
                connectedIDs.add(userID);
            }


    }

    // direct user to ProfileFinderUI

}
