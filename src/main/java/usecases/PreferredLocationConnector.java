package usecases;

import dataaccess.FetchData; // implements a Use Case interface

import static java.lang.Math.*;
import java.util.ArrayList;
import java.util.List;

/**
 * PreferredLocationConnector class connects a user with other users based on the user's preferred location
 */

// how I derive this method - https://docs.google.com/document/d/18K6XwpYKONmStDr31H1RCc-zm5dfxP9T8k1lpqS7pco/edit?usp=sharing
public class PreferredLocationConnector{

    /**
     * A method that finds ids' of people in the database, those are within the preferred location range from
     * the location of the user with a specific id.
     * @param id id of a user
     * @param locationRange the range of preferred location from the user location who has id
     * @return the list of ids that are within the preferred location range from the user's address
     */
    public static List<Integer> withinPreferredLocation(int id, double locationRange) {
        int lastId = FetchData.fetchLastID(); // get the number of users stored in database
        ArrayList<Integer> ids = new ArrayList<>(); // list of the ids who are in the preferred location

        double[] userAddress = FetchData.fetchAddressFromID(id);
        double userLatRad = (toRadians(userAddress[0]));
        double userLongRad = (toRadians(userAddress[1]));
        if (lastId > 0) {
            for (int i = 0; i < lastId + 1; i++) {
                if (i != id) { // when the id matches with i, the for loop should continue
                    double[] data = FetchData.fetchAddressFromID(i);
                    double otherLatRad = (toRadians(data[0]));
                    double otherLongRad = (toRadians(data[1]));
                    double dis = (3440.1 * acos((sin(userLatRad) * sin(otherLatRad)) + cos(userLatRad) *
                            cos(otherLatRad) * cos(otherLongRad - userLongRad)));
                    double disKilo = dis / 1.852;
                    if (disKilo <= locationRange) {
                        ids.add(i);
                    }
                }
            }
            return ids;
        } else
        { // case when there is no profile in the database
        System.out.println("There is no other profile");
        return null;
        }
    }

    public static void main(String[] args){
        List<Integer> abc = withinPreferredLocation(2, 5.0);
        System.out.println(abc);
    }
}
