package Controllers_Presenters;

import Entities.Preferences;
import Use_Cases.ConnectProfiles;

public class ConnectProfilesControl {
    private String id;

    public ConnectProfilesControl(String id) {
        this.id = id;
    }

    public void passPreferences() {
        // change the type of preferred age and gender
        Object[] userData = DataFetchControl.fetch_byid(id);
        int prefAge = Integer.parseInt((String) userData[12]);
        String prefGender = (String) userData[13];

        // change the type of preferred location
        String[] preferredLocation = ((String) userData[14]).split(" ");
        double[] prefLocation = new double[preferredLocation.length];
        for (int i = 0; i < preferredLocation.length; i++) {
            prefLocation[i] = Double.parseDouble(preferredLocation[i]);
        }

        // create and pass a new Preferences object to ConnectProfiles
        Preferences preferences = new Preferences(prefAge, prefGender, prefLocation, id);
        ConnectProfiles connectProfiles = new ConnectProfiles(preferences);

    }
}
