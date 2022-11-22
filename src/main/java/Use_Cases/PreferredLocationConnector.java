package Use_Cases;
import static java.lang.Math.*;
import Controllers_Presenters.DataFetchControl;
import java.util.ArrayList;

// how I derive this method - https://docs.google.com/document/d/18K6XwpYKONmStDr31H1RCc-zm5dfxP9T8k1lpqS7pco/edit?usp=sharing

// Returns an arraylist of IDs that are within the range of the user's preferred location
public class PreferredLocationConnector{
    public static ArrayList within_preferred_location(int id, double locationRange) {
        int last_id = new DataFetchControl().fetch_lastID(); // get the number of users stored in database
        ArrayList<Integer> ids = new ArrayList<>(); // list of the ids who are in the preferred location
        double[] input_id_address = new DataFetchControl().fetch_address_from_id(id); // get the address of the input_id
        double user_lat_rad = (toRadians(input_id_address[0])); // latitude of the address
        double user_long_rad = (toRadians(input_id_address[1])); // longitude of the address
        // when the database is not empty
        if (last_id > 0) {
            for (int i = 0; i < last_id + 1; i++) {
                if(i == id){
                    continue; // when the user id matches in the database it moves to other data
                }
                else{
                    double[] data = new DataFetchControl().fetch_address_from_id(i);
                    double other_lat_rad = (toRadians(data[0])); // latitude of the address of the user with i id
                    double other_long_rad = (toRadians(data[1])); // longitude of the address of the user with i id
                    double dis = (3440.1 * acos((sin(user_lat_rad) * sin(other_lat_rad)) + cos(user_lat_rad) *
                            cos(other_lat_rad) * cos(other_long_rad - user_long_rad)));
                    double dis_kilo = dis / 1.852; // conversion from nautical mile to kilo meter
                    System.out.println(dis_kilo);
                    if (dis_kilo <= locationRange) { // if the distance is within the locationRange the user it adds the id
                        ids.add(i);
                    }
                }
            }
            System.out.println(ids);
            return ids;
        } else {
            // when the database is empty
            System.out.println("There is no other profile");
            return null;
        }
    }

    public static void main(String[] args){
        //Test case
        ArrayList<Integer> abc = new PreferredLocationConnector().within_preferred_location(2, 5.0);
        System.out.println(abc);
    }
}
