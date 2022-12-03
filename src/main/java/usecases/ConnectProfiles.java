package usecases;

import Entities.Preferences;
import Controllers_Presenters.DataFetchControl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A Use Case class that reformats the preferences passed in from a Preferences object, and compares these against the
 * age, gender, and location of other users to gather a list of users with whom the user might match with.
 */
public class ConnectProfiles {
    /** The user's preferred age of other users, modified for comparison with database entries */
    private final String prefAge;

    /** The user's preferred gender (male, female, or other) of other users */
    private final String prefGender;

    /** The user's preferred location range */
    private final double preferredLocationRange;

    /** The user's ID */
    private final int id;

    /**
     * Construct an ConnectProfiles object, initializing prefAge and prefGender as Strings, preferredLocationRange as
     * a double, and id as an int, corresponding to the information in the Preferences object preferences.
     *
     * @param preferences The Input Data containing the preferred age, preferred gender, preferred location range, and
     *                    ID of the user
     */
    public ConnectProfiles(Preferences preferences) {
        this.prefAge = String.valueOf(preferences.getPreferredAge());
        this.prefGender = preferences.getPreferredGender();
        this.preferredLocationRange = preferences.getPreferredLocationRange();
        this.id = preferences.getID();
    }

    /**
     * Gather an initial list of IDs within the user's preferred location range using PreferredLocationConnector's
     * within_preferred_location method. Store in another list only the IDs from this initial list that correspond to
     * users whose age and gender match the preferred age and gender of the user.
     *
     * @return A list of IDs corresponding to users whose age, gender, and location match the preferences of the user,
     * which corresponds with the parameter type of ConnectProfilesOutputBoundary.passConnections
     */
    public List<Integer> compareTraits() {
        List<Integer> connectedIDs = new ArrayList<>();
        List<Integer> idsInLocationRange = PreferredLocationConnector.withinPreferredLocation(id,
                preferredLocationRange);

        for (int userID : idsInLocationRange) {
            if (userID == id) { // don't include the user's own ID in the connected profiles list
                continue;
            }

            // fetch the possible connected user's profile and preference data from the database
            Object[] userData = DataFetchControl.fetch_fromid(userID);
            userData = (Object[]) userData[0];
            String userAge = (String) userData[4]; // other user's age
            String userGender = (String) userData[6]; // other user's gender

            // add the possible connected user's ID to the list of connected profiles if their age and gender match
            // the user's preferred age and gender
            if ((Objects.equals(userAge, prefAge)) && (Objects.equals(userGender, prefGender))) {
                connectedIDs.add(userID);
            }
        }

        return connectedIDs;
    }
}
