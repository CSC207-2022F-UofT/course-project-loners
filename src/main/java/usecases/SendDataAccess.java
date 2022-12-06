package usecases;

/**
 * A Use Case interface implemented by SendData.
 */
public interface SendDataAccess {

    /**
     * @return instance of SendDataAccess
     */
    static SendDataAccess getInstance() {
        return null;
    }

    /**
     @param (id, data) id represents the id of the profile the use wants to edit. data represents the updated data
     of the profile
     */
    static void sendToID(int id, Object[] data) {}
}
