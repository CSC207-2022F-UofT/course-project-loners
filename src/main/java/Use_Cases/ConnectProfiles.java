package Use_Cases;

import Entities.Preferences;
import Controllers_Presenters.DataFetchControl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConnectProfiles {
    private String prefAge;
    private String prefGender;
    private double preferredLocationRange;
    private int id;

    public ConnectProfiles(Preferences preferences) {
        this.prefAge = String.valueOf(preferences.getPreferredAge());
        this.prefGender = preferences.getPreferredGender();
        this.preferredLocationRange = preferences.getPreferredLocationRange();
        this.id = preferences.getID();
    }

    public List<String> compareTraits() {
        List<String> connectedIDs = new ArrayList<>(); /// change to List<Integer>?
        DataFetchControl dataFetchControl = new DataFetchControl();
        int lastID = dataFetchControl.fetch_lastID(); // number of IDs in the database
        ArrayList<Integer> idsWithinLocation = PreferredLocationConnector.within_preferred_location(id,
                preferredLocationRange); /// new parameters

        for (int userID : idsWithinLocation) {
            if (userID == id) {
                continue;
            }

            Object[] userData = dataFetchControl.fetch_fromid(userID);
            String userAge = (String) userData[3];
            String userGender = (String) userData[5];

            if ((Objects.equals(userAge, prefAge)) && (Objects.equals(userGender, prefGender))) {
                connectedIDs.add(String.valueOf(userID));
            }
        }

        return connectedIDs;
    }
}
