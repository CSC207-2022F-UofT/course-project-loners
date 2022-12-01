package Controllers_Presenters;

import Entities.Preferences;
import Use_Cases.ConnectProfiles;

import java.util.List;
import java.util.Objects;

/**
 * A Controller class that reformats the preferences gathered from the database for use by the ConnectProfiles class.
 */
public class ConnectProfilesControl {

    /**
     * Create a new Preferences object containing reformatted preference information, and pass the object to
     * ConnectProfiles.
     *
     * @param id ID of the user
     * @return a list of IDs corresponding to users whose age, gender, and location match the preferences of the user
     */
    public static List<Integer> gatherConnections(int id) { // called by ProfileFinderUI
        // fetch the user's profile and preference data from the database
        Object[] userData = DataFetchControl.fetch_fromid(id);
        try {
            userData = (Object[]) Objects.requireNonNull(userData)[0];

            // reformat each of the preferences
            int preferredAge = Integer.parseInt((String) userData[12]);
            String preferredGender = (String) userData[13];
            double preferredLocationRange = Double.parseDouble((String) userData[14]);

            // create and pass a new Preferences object to ConnectProfiles
            Preferences preferences = new Preferences(preferredAge, preferredGender, preferredLocationRange, id);
            ConnectProfiles connectProfiles = new ConnectProfiles(preferences);

            return connectProfiles.compareTraits(); // for ProfileFinderUI

        } catch (NullPointerException e) {
            System.out.println("An error occurred.");
            return null;
        }
    }

//    public static void main(String[] args) { /// for testing
//        List<Integer> testConnections = ConnectProfilesControl.gatherConnections(3);
//        for (int testConnection : testConnections) {
//            System.out.println(testConnection);
//        }
//    }
}
