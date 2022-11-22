package Controllers_Presenters;

import Entities.Preferences;
import Use_Cases.ConnectProfiles;

import java.util.List;

public class ConnectProfilesControl {
    private int id;

    public ConnectProfilesControl(int id) {
        this.id = id;
    }

    public List<String> passPreferences() { /// called with an instance by ProfileFinderUI
        DataFetchControl dataFetchControl = new DataFetchControl();
        Object[] userData = dataFetchControl.fetch_fromid(id);
        userData = (Object[]) userData[0];

        // change the types of each preference
        int preferredAge = Integer.parseInt((String) userData[12]);
        String preferredGender = (String) userData[13];
        double preferredLocationRange = Double.parseDouble((String) userData[14]);

        // create and pass a new Preferences object to ConnectProfiles
        Preferences preferences = new Preferences(preferredAge, preferredGender, preferredLocationRange, id);
        ConnectProfiles connectProfiles = new ConnectProfiles(preferences);
        List<String> connections = connectProfiles.compareTraits();

        return connections; // for ProfileFinderUI
    }
}
