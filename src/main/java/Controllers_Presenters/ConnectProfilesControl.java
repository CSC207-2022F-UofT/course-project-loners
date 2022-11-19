package Controllers_Presenters;

import Entities.Preferences;
import Use_Cases.ConnectProfiles;

import java.util.List;

public class ConnectProfilesControl {
    private int id;

    public ConnectProfilesControl(int id) {
        this.id = id;
    }

    public void passPreferences() {
        DataFetchControl dataFetchControl = new DataFetchControl();
        Object[] userData = dataFetchControl.fetch_fromid(id);

        // change the types of preferred age and gender
        int preferredAge = Integer.parseInt((String) userData[13]);
        String preferredGender = (String) userData[14];

        // change the types of preferred location and preferred location range
        String[] prefLocation = ((String) userData[15]).split(" ");
        double[] preferredLocation = new double[prefLocation.length];
        for (int i = 0; i < prefLocation.length; i++) {
            preferredLocation[i] = Double.parseDouble(prefLocation[i]);
        }
        double preferredLocationRange = Double.parseDouble((String) userData[16]);

        // create and pass a new Preferences object to ConnectProfiles
        Preferences preferences = new Preferences(preferredAge, preferredGender, preferredLocation,
                preferredLocationRange, id);
        ConnectProfiles connectProfiles = new ConnectProfiles(preferences);
        List<String> connections = connectProfiles.compareTraits();
        /// pass connected profiles to ProfileFinderUI, direct user to ProfileFinderUI

    }
}