package Use_Cases;
import static java.lang.Math.*;
import Controllers_Presenters.DataFetchControl;
import Entities.Preferences;
import Entities.Profile;
import java.util.ArrayList;

// how I derive this method - https://docs.google.com/document/d/18K6XwpYKONmStDr31H1RCc-zm5dfxP9T8k1lpqS7pco/edit?usp=sharing

// Returns an arraylist of IDs that are within the range of the user's preferred loaction
public class PreferredLocationConnector{
    public static ArrayList within_preferred_location(Profile user, Preferences preferences) {
        int last_id = new DataFetchControl().fetch_lastID(); // get the number of users stored in database
        double range = preferences.getPreferredLocationRange();
        ArrayList<Integer> ids = new ArrayList<>(); // list of the ids who are in the preferred location
        double[] user_address = user.getLocation(); // location of the user
        double user_lat_rad = (toRadians(user_address[0]));
        double user_long_rad = (toRadians(user_address[1]));
        // when the preferred_Location doesn't exist
        if (last_id > 0) {
            for (int i = 0; i < last_id + 1; i++) {
                double[] data = new DataFetchControl().fetch_address_from_id(i);
                double other_lat_rad = (toRadians(data[0]));
                double other_long_rad = (toRadians(data[1]));
                double dis = (3440.1 * acos((sin(user_lat_rad) * sin(other_lat_rad)) + cos(user_lat_rad) *
                        cos(other_lat_rad) * cos(other_long_rad - user_long_rad)));
                double dis_kilo = dis / 1.852;
                System.out.println(dis_kilo);
                if (dis_kilo <= range) {
                    ids.add(i);
                }
            }
            System.out.println(ids);
            return ids;
        } else {
            System.out.println("There is no other profile");
            return null;
        }
    }

    public static void main(String[] args){
        double [] location  = {43.667400, -79.402443};
        Profile profile = new Profile("Rick", 21, "male",
                "straight", location, null, "This is Rick", null, null);
        Preferences preferences = new Preferences(20, "male",5);

        ArrayList<Integer> abc = new PreferredLocationConnector().within_preferred_location(profile, preferences);
        System.out.println(abc);

    }
}
