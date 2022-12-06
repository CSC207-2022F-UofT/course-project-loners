package usecases;

import java.util.ArrayList;

/**
 * A Use Case interface implemented by FetchData.
 */
public interface FetchDataAccess {

    /**
     * This method fetch the profile data associated to id.
     @param id of the user
     @return Array Object that contains profile data
     */
    static Object[] fetchFromID(int id) {
        return new Object[0];
    }

    /**
     * This method uses email to find id associated to that email.
     @param email of the profile
     @return id
     */
    static int fetchIDFromEmail(String email) {
        return 0;
    }

    /**
     * This method fetches the last id in the database.
     @return last id in the database
     */
    static int fetchLastID() {
        return 0;
    }

    /**
     *
     @return ArrayList that contains all the emails
     */
    static ArrayList<String> fetchEmails() {
        return null;
    }

    static String fetchPassword(String email) {
        return null;
    }

    static double[] fetchAddressFromID(int inputId) {
        return new double[0];
    }
}
