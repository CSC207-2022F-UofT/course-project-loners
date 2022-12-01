package Controllers_Presenters;

import Entities.Preferences;
import Use_Cases.ConnectProfiles;

import java.util.List;

public class ConnectProfilesControl {

    public static List<Integer> gatherConnections(int id) { // called by ProfileFinderUI
        Object[] userData = DataFetchControl.fetch_fromid(id);
        userData = (Object[]) userData[0];

        // change the types of each preference
        int preferredAge = Integer.parseInt((String) userData[12]);
        String preferredGender = (String) userData[13];
        double preferredLocationRange = Double.parseDouble((String) userData[14]);

        // create and pass a new Preferences object to ConnectProfiles
        Preferences preferences = new Preferences(preferredAge, preferredGender, preferredLocationRange, id);
        ConnectProfiles connectProfiles = new ConnectProfiles(preferences);
        List<Integer> connections = connectProfiles.compareTraits();

        return connections; // for ProfileFinderUI
    }

//    public static void main(String[] args) { /// for testing
//        List<Integer> testConnections = ConnectProfilesControl.gatherConnections(3);
//        for (int testConnection : testConnections) {
//            System.out.println(testConnection);
//        }
//    }
}
