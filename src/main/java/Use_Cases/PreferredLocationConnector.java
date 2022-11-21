package Use_Cases;
import static java.lang.Math.*;
import Controllers_Presenters.DataFetchControl;
import Entities.Preferences;
import Entities.Profile;
import java.util.ArrayList;

// how I derive this method - https://docs.google.com/document/d/18K6XwpYKONmStDr31H1RCc-zm5dfxP9T8k1lpqS7pco/edit?usp=sharing

// Returns an arraylist of IDs that are within the range of the user's preferred loaction
public class PreferredLocationConnector extends LocationConverter {
    public static ArrayList<Integer> within_preferred_location(int id, double range) {
        int last_id = new DataFetchControl().fetch_lastID(); // get the number of users stored in database
        ArrayList<Integer> ids = new ArrayList<>(); // list of the ids who are in the preferred location
        double[] user_address = DataFetchControl.fetch_address_from_id(id); // location of the user
        double user_lat_rad = (toRadians(user_address[0]));
        double user_long_rad = (toRadians(user_address[1]));

        if (last_id > 0) {
            for (int i = 0; i < last_id + 1; i++) {
                double dis_kilo = extractLocationInfo(user_lat_rad, user_long_rad, user_address);
                if (dis_kilo <= range) { // here 10 would be changed to user's range of preferredLocation
                    ids.add(i);
                }
            }
            return ids;
        }
        else{
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
        double [] location  = {43.667225, -79.402443};
        Profile profile = new Profile("Rick", 21, "male",
                "straight", location, null, "This is Rick", null, null);
        Preferences preferences = new Preferences(20, "male",5, 2);

        ArrayList<Integer> abc = new PreferredLocationConnector().within_preferred_location(2, 5);
        System.out.println(abc);

    }
}
