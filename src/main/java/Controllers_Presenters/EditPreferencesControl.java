package Controllers_Presenters;

import Use_Cases.LocationConverter;
import Entities.Preferences;
import Use_Cases.EditPreferences;

import java.util.HashMap;

public class EditPreferencesControl {
    private int prefAge;
    private String prefGender;
    private double[] prefLocation;
    private double prefLocationRange;
    private int id;

    // arguments for constructor passed in by EditPreferencesUI
    public EditPreferencesControl(HashMap<String, String> preferenceMap, int id) {
        this.prefAge = Integer.parseInt(preferenceMap.get("preferred age"));
        this.prefGender = preferenceMap.get("preferred gender");

        String postalCode = preferenceMap.get("preferred location");
        this.prefLocation = LocationConverter.codeToCoords(postalCode);
        this.prefLocationRange = Double.parseDouble(preferenceMap.get("location range"));

        this.id = id;
    }

    public void passPreferences() {
        // create and pass a new Preferences object to EditPreferences
        Preferences preferences = new Preferences(prefAge, prefGender, prefLocation, prefLocationRange, id);
        EditPreferences editPreferences = new EditPreferences(preferences);
        editPreferences.writeData();

        // direct user to MyProfileUI

    }

//    public static void main(String[] args) {
//        passPreferences();
//    }
}
