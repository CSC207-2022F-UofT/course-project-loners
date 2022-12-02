package Use_Cases;

import Entities.Preferences;
import Entities.Profile;

import java.io.IOException;

/**
 * A Use Case interface specifying how to access DataSendControl, to be implemented by this Data Access.
 */
public interface DataSendAccess {

    /**
     *
     *
     * @param (id, data) id represents the id of the profile the use wants to edit. data represents the updated data
     *                   of the profile
     * @return boolean representing whether it has successfully modified the data in the database or not.
     */
    boolean send_toid(int id, Object[] data);

    /**
     * A helper method used in DataSendControl class.
     *
     * @param profile A Profile object
     * @return String representation of the profile data.
     */
    String ProfileConvertStr(Profile profile);

    /**
     *
     *
     * @param preferences
     * @return
     */
    String PreferencesConvertStr(Preferences preferences);
}
