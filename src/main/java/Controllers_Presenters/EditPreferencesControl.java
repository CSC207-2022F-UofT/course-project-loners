/*
A controller class that reformats the preferences inputted by the user for use by the EditPreferences class.
 */

package Controllers_Presenters;

import Use_Cases.LocationConverter;
import Entities.Preferences;
import Use_Cases.EditPreferences;

import java.util.HashMap;

public class EditPreferencesControl {
    private int preferredAge;
    private String preferredGender;
    private double preferredLocationRange;
    private int id;

    /**
     * Construct an EditPreferencesControl, initializing preferredAge, preferredGender, and preferredLocationRange
     * to the corresponding information of the appropriate type in the preferenceMap HashMap,
     * and id to the ID of the user.
     *
     * @param preferenceMap
     * @param id
     */
    public EditPreferencesControl(HashMap<String, String> preferenceMap, int id) { // passed in by EditPreferencesUI
        this.preferredAge = Integer.parseInt(preferenceMap.get("preferred age"));
        this.preferredGender = preferenceMap.get("preferred gender");
        this.preferredLocationRange = Double.parseDouble(preferenceMap.get("preferred location range"));
        this.id = id;
    }

    /**
     * Create a new Preferences object containing the reformatted preference information, and pass the object to
     * EditPreferences.
     */
    public void passPreferences() {
        // create and pass a new Preferences object to EditPreferences
        Preferences preferences = new Preferences(preferredAge, preferredGender, preferredLocationRange, id);
        EditPreferences editPreferences = new EditPreferences(preferences);
        editPreferences.writeData();
    }
}
