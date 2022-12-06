package usecases;

import dataaccess.FetchData;
import entities.Preferences;
import java.util.ArrayList;
import java.util.List;

/**
 * A Use Case class that reformats the preferences passed in from a Preferences object, and stores this information
 * in the database.
 */
public class EditPreferences {
    /** The user's preferred age of other users, modified for database storage */
    private final String prefAge;

    /** The user's preferred gender (male, female, or other) of other users */
    private final String prefGender;

    /** The user's preferred location range, modified for database storage */
    private final String prefLocationRange;

    /** The user's ID */
    private final int id;

    /**
     * Construct an EditPreferences object, initializing prefAge, prefGender, and prefLocationRange as Strings and id
     * as an int, corresponding to the information in the Preferences object preferences.
     *
     * @param preferences The Input Data containing the preferred age, preferred gender, preferred location range, and
     *                    ID of the user
     */
    public EditPreferences(Preferences preferences) { // passed in by EditPreferencesControl
        this.prefAge = String.valueOf(preferences.getPreferredAge());
        this.prefGender = preferences.getPreferredGender();
        this.prefLocationRange = String.valueOf(preferences.getPreferredLocationRange());
        this.id = preferences.getID();
    }

    /**
     * Update the database with the user's new preferences.
     */
    public void writeData() {
        // fetch the user's current profile and preference data from the database
        Object[] userData = FetchData.fetchFromId(id);
        userData = (Object[]) userData[0];

        // remove id from the array (to be added back by sendToId method in SendData)
        List<Object> tempUserData = new ArrayList<>(List.of(userData));
        tempUserData.remove(0);
        Object[] userDataNoID = tempUserData.toArray();

        // add new preference data, or overwrite if they already exist
        userDataNoID[11] = prefAge;
        userDataNoID[12] = prefGender;
        userDataNoID[13] = prefLocationRange;

        // send the user's profile and new preference data to the database
        SendData.getInstance().sendToId(id, userDataNoID);
    }
}
