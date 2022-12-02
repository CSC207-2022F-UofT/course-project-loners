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

    public List<Integer> compareTraits() {
        List<Integer> connectedIDs = new ArrayList<>();
        ArrayList<Integer> idsWithinLocation = PreferredLocationConnector.withinPreferredLocation(id,
                preferredLocationRange);

        for (int userID : idsWithinLocation) {
            if (userID == id) {
                continue;
            }

            Object[] userData = DataFetchControl.fetch_fromid(userID);
            userData = (Object[]) userData[0];
            String userAge = (String) userData[4];
            String userGender = (String) userData[6];

            if ((Objects.equals(userAge, prefAge)) && (Objects.equals(userGender, prefGender))) {
                connectedIDs.add(userID);
            }
        }

        return connectedIDs;
    }
}
