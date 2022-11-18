package Use_Cases;

import Controllers_Presenters.DataSendControl;
import Entities.Profile;

public class RegDataStore {
    public boolean success;
    public RegDataStore(String email, String password, String name, int age, String gender, String postcode) {
        LocationConverter converter =  new LocationConverter();
        double[] location = converter.codeToCoords(postcode);
        Profile profile = new Profile(email, password, name, age, gender, location);
        new DataSendControl(profile); // TODO: improve after discussion
        this.success = true;
    }
}