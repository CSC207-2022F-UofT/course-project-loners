package Use_Cases;

import Controllers_Presenters.tmp_DataSend;
import Entities.Profile;
import Use_Cases.LocationConverter;

public class RegDataStore {
    public boolean success;
    public RegDataStore(String email, String password, String name, int age, String gender, String postcode) {
        LocationConverter converter =  new LocationConverter();
        double[] location = converter.codeToCoords(postcode);
        Profile profile = new Profile(email, password, name, age, gender, location);
        new tmp_DataSend(profile); // TODO: improve after discussion
        this.success = true;
    }
}