package controllers;

import entities.Preferences;
import usecases.ConnectProfiles;

import java.util.List;

/**
 * A Controller class that reformats the preferences gathered from the database for use by the ConnectProfiles class.
 */
public class ConnectProfilesControl {

    /**
     * Create a new Preferences object containing reformatted preference information, and pass the object to
     * ConnectProfiles.
     *
     * @param id ID of the user
     * @return A list of IDs corresponding to users whose age, gender, and location match the preferences of the user
     */
    public static List<Integer> gatherConnections(int id) { // called by ProfileFinderUI
        // fetch the user's profile and preference data from the database
        Object[] userData = DataFetchControl.fetch_fromid(id);
        userData = (Object[]) userData[0];

        // reformat each of the preferences
        int preferredAge = Integer.parseInt((String) userData[12]);
        String preferredGender = (String) userData[13];
        double preferredLocationRange = Double.parseDouble((String) userData[14]);

        // create and pass a new Preferences object to ConnectProfiles
        Preferences preferences = new Preferences(preferredAge, preferredGender, preferredLocationRange, id);
        ConnectProfiles connectProfiles = new ConnectProfiles(preferences);
        List<Integer> connections = connectProfiles.compareTraits();

        // call the method implemented in ConnectProfilesPresenter
        ConnectProfilesPresenter connectProfilesPresenter = new ConnectProfilesPresenter();
        return connectProfilesPresenter.passConnections(connections); // for ProfileFinderUI
    }

//    public static void main(String[] args) { /// for testing with an appropriately set up database.txt file and
//    saved_images folder
//        List<Integer> testConnections = ConnectProfilesControl.gatherConnections(3);
//        for (int testConnection : testConnections) {
//            System.out.println(testConnection);
//        }
//    }
}
