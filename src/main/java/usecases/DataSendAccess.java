package usecases;

import entities.Profile;

/**
 * A Use Case interface specifying how to access DataSendControl, to be implemented by this Data Access.
 */
public interface DataSendAccess {

    /**
     *
     *
     * @param (id, data) id represents the id of the profile the use wants to edit. data represents the updated data
     *                   of the profile
     */
    void sendToid(int id, Object[] data);

    /**
     * A helper method used in DataSendControl class.
     *
     * @param profile A Profile object
     * @return String representation of the profile data.
     */
    String ProfileConvertStr(Profile profile);
}
