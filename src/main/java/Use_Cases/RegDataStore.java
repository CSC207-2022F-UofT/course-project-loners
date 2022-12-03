package Use_Cases;

import Controllers_Presenters.DataSendControl;
import Entities.Preferences;
import Entities.Profile;

public class RegDataStore {
    public boolean success;
    public RegDataStore(String social, String email, String password, String name, int age, String gender, String postcode) {
        LocationConverter converter =  new LocationConverter();
        double[] location = converter.codeToCoords(postcode);
        Profile profile = new Profile(social, email, password, name, age, gender, location);
        new DataSendControl(profile);
        this.success = true;
    }
}