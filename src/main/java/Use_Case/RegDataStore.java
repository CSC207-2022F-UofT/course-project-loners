package Use_Case;

import Controllers_Presenters.tmp_DataSend;
import Entity.Profile;

public class RegDataStore {
    public boolean success;
    public RegDataStore(String email, String password, String name, int age, String gender, String postcode) {
        double[] location = LocationConverter.codeToCoords(postcode);
        Profile profile = new Profile(email, password, name, age, gender, location);
        new tmp_DataSend(profile); // TODO: improve after discussion
        this.success = true;
    }
}


