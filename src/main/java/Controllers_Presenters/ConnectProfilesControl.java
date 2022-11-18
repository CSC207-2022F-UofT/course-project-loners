package Controllers_Presenters;

import Entities.Preferences;
import Use_Cases.ConnectProfiles;

import java.util.List;

public class ConnectProfilesControl {
    private int id;

    public ConnectProfilesControl(String id) {
        this.id = Integer.parseInt(id);
    }

    public void passPreferences() {
        DataFetchSendControl d = new DataFetchSendControl();
        Object[] userData = d.fetch_fromid(id);

        // change the type of preferred age and gender
        int prefAge = Integer.parseInt((String) userData[12]);
        String prefGender = (String) userData[13];

        // change the type of preferred location
        String[] preferredLocation = ((String) userData[14]).split(" ");
        double[] prefLocation = new double[preferredLocation.length];
        for (int i = 0; i < preferredLocation.length; i++) {
            prefLocation[i] = Double.parseDouble(preferredLocation[i]);
        }

        // create and pass a new Preferences object to ConnectProfiles
        Preferences preferences = new Preferences(prefAge, prefGender, prefLocation, String.valueOf(id));
        ConnectProfiles connectProfiles = new ConnectProfiles(preferences);
        List<String> connections = connectProfiles.compareTraits();

        // pass connected profiles to ProfileFinderUI, direct user to ProfileFinderUI

    }
}
