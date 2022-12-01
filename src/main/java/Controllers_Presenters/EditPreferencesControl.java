package Controllers_Presenters;

import Entities.Preferences;
import Use_Cases.EditPreferences;

import java.util.HashMap;

/**
 * A Controller class that reformats the preferences inputted by the user for use by the EditPreferences class.
 */
public class EditPreferencesControl {
    private final int preferredAge;
    private final String preferredGender;
    private final double preferredLocationRange;
    private final int id;

    /**
     * Construct an EditPreferencesControl object, initializing to the appropriate type preferredAge, preferredGender,
     * and preferredLocationRange, corresponding to the information in the HashMap preferenceMap, and initializing id
     * to the ID of the user.
     *
     * @param preferenceMap a mapping of preference labels to their corresponding text input
     * @param id ID of the user with this preferredAge, preferredGender, and preferredLocationRange
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
        Preferences preferences = new Preferences(preferredAge, preferredGender, preferredLocationRange, id);
        EditPreferences editPreferences = new EditPreferences(preferences);
        editPreferences.writeData();
    }
}
