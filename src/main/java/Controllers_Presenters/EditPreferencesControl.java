package Controllers_Presenters;

import Use_Cases.LocationConverter;
import Entities.Preferences;
import Use_Cases.EditPreferences;

import java.util.HashMap;

public class EditPreferencesControl {
    private int preferredAge;
    private String preferredGender;
    private double[] preferredLocation;
    private double preferredLocationRange;
    private int id;

    public EditPreferencesControl(HashMap<String, String> preferenceMap, int id) { // passed in by EditPreferencesUI
        this.preferredAge = Integer.parseInt(preferenceMap.get("preferred age"));
        this.preferredGender = preferenceMap.get("preferred gender");
        String postalCode = preferenceMap.get("preferred location");
        this.preferredLocation = LocationConverter.codeToCoords(postalCode);
        this.preferredLocationRange = Double.parseDouble(preferenceMap.get("location range"));
        this.id = id;
    }

    public void passPreferences() {
        // create and pass a new Preferences object to EditPreferences
        Preferences preferences = new Preferences(preferredAge, preferredGender, preferredLocation,
                preferredLocationRange, id);
        EditPreferences editPreferences = new EditPreferences(preferences);
        editPreferences.writeData();
    }
}
