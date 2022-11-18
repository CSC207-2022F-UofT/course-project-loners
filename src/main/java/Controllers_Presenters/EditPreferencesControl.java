package Controllers_Presenters;

import Use_Cases.LocationConverter;
import Entities.Preferences;
import Use_Cases.EditPreferences;

import java.util.HashMap;

public class EditPreferencesControl {
    private static int prefAge;
    private static String prefGender;
    private static double[] prefLocation;

    // arguments for constructor passed in by EditPreferencesUI
    public EditPreferencesControl(HashMap<String, String> preferenceMap) {
        this.prefAge = Integer.parseInt(preferenceMap.get("preferred age"));
        this.prefGender = preferenceMap.get("preferred gender");

        String postalCode = preferenceMap.get("preferred location");
        this.prefLocation = LocationConverter.codeToCoords(postalCode);
    }

    public void passPreferences() {
        // create and pass a new Preferences object to EditPreferences
        Preferences preferences = new Preferences(this.prefAge, this.prefGender, this.prefLocation);
        EditPreferences editPreferences = new EditPreferences(preferences);
        editPreferences.writeData();
    }

//    public static void main(String[] args) {
//        passPreferences();
//    }
}
