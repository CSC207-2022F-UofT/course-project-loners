package Use_Cases;
import static java.lang.Math.*;
import Controllers_Presenters.DataFetchControl;
import Entities.Preferences;
import Entities.Profile;
import java.util.ArrayList;

// how I derive this method - https://docs.google.com/document/d/18K6XwpYKONmStDr31H1RCc-zm5dfxP9T8k1lpqS7pco/edit?usp=sharing

// Returns an arraylist of IDs that are within the range of the user's preferred loaction
public class PreferredLocationConnector extends LocationConverter {
    public static ArrayList within_preferred_location(Profile user, Preferences preferences) {
        int last_id = new DataFetchControl().fetch_lastID(); // get the number of users stored in database
        double range = preferences.getPreferredLocationRange();
        double[] preferred_Location = preferences.getPreferredLocation();
        ArrayList<Integer> ids = new ArrayList<>(); // list of the ids who are in the preferred location
        double[] user_address = user.getLocation(); // location of the user
        double user_lat_rad = (toRadians(user_address[0]));
        double user_long_rad = (toRadians(user_address[1]));
        // when the preferred_Location doesn't exist
        if ((preferred_Location == null) & last_id > 0) {
            for (int i = 0; i < last_id + 1; i++) {
                Object[] data = new DataFetchControl().fetch_fromid(i);
                double[] profiles_address = (double[]) data[4];
                double dis_kilo = extractLocationInfo(user_lat_rad, user_long_rad, profiles_address);
                if (dis_kilo <= range) { // here 10 would be changed to user's range of preferredLocation
                    ids.add(i);
                }
            }
            return ids;
        }
        // when the preferred_Location exist
        else if ((preferred_Location != null) & last_id > 0) {
            for (int i = 0; i < last_id + 1 ; i++) {
                Object[] data = new DataFetchControl().fetch_fromid(i);
                double preferred_location_lat_rad = (toRadians(preferred_Location[0]));
                double preferred_location_long_rad = (toRadians(preferred_Location[1]));
                double[] profiles_address = (double[]) data[4];
                double dis_kilo = extractLocationInfo(preferred_location_lat_rad, preferred_location_long_rad, profiles_address);
                if (dis_kilo <= range) { // here 10 would be changed to user's range of preferredLocation
                    ids.add(i);
                }
            }
            return ids;
        } else{
            System.out.println("There is no other profile");
            return null;
        }
    }

    private static double extractLocationInfo(double user_lat_rad, double user_long_rad, double[] profiles_address) {
        double other_lat_rad = (toRadians(profiles_address[0]));
        double other_long_rad = (toRadians(profiles_address[1]));
        double dis = 3440.1 * acos(sin(user_lat_rad) * sin(other_lat_rad) +
                cos(other_long_rad) * cos(user_long_rad - other_long_rad));
        double dis_kilo = dis / 1.852;
        return dis_kilo;
    }

    public static void main(String[] args){
        Profile profile = new Profile("Rick", 21, "male",
                "straight", null, null, "This is Rick", null, null);
        Preferences preferences = new Preferences(20, "male",null, 5, 2);
        PreferredLocationConnector abc = new PreferredLocationConnector();
        System.out.println(abc.within_preferred_location(profile, preferences));

    }
}
