package Use_Cases;
import static java.lang.Math.*;
import Controllers_Presenters.DataFetchControl;
import java.util.ArrayList;
import java.util.List;

// how I derive this method - https://docs.google.com/document/d/18K6XwpYKONmStDr31H1RCc-zm5dfxP9T8k1lpqS7pco/edit?usp=sharing
// in terms of VIOLATIONS OF CLEAN ARCHITECTURE since PreferredLocationConnector (use case) reference DataFetchControl (controller)
// Returns an arraylist of IDs that are within the range of the user's preferred location
public class PreferredLocationConnector{
    public static List<Integer> withinPreferredLocation(int id, double locationRange) {
        int last_id = new DataFetchControl().fetch_lastID(); // get the number of users stored in database
        List<Integer> ids = new ArrayList<>(); // list of the ids who are in the preferred location
        new DataFetchControl();
        double[] userAddress = DataFetchControl.fetch_address_from_id(id);
        double userLatRad = (toRadians(userAddress[0]));
        double userLongRad = (toRadians(userAddress[1]));
        // when the preferred_Location doesn't exist
        if (last_id > 0) {
            for (int i = 0; i < last_id + 1; i++) {
                if(i == id){
                    continue;
                }
                else{
                    new DataFetchControl();
                    double[] data = DataFetchControl.fetch_address_from_id(i);
                    double otherLatRad = (toRadians(data[0]));
                    double otherLongRad = (toRadians(data[1]));
                    double dis = (3440.1 * acos((sin(userLatRad) * sin(otherLatRad)) + cos(userLatRad) *
                            cos(otherLatRad) * cos(otherLongRad - userLongRad)));
                    double disKilo = dis / 1.852;
                    System.out.println(disKilo);
                    if (disKilo <= locationRange) {
                        ids.add(i);
                    }
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
        new PreferredLocationConnector();
        List<Integer> abc = withinPreferredLocation(2, 5.0);
        System.out.println(abc);
    }
}
