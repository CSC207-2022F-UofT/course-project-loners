package UseCases;

import Entities.Profile;

import java.awt.image.BufferedImage;
import java.util.List;

public class ProfileGenerator {
    public ProfileGenerator(String email, String password, String name, int age, String gender, String postcode){
        double[] location = {};// TODO: implement this line after LocationConverter is done
        Profile profile = new Profile(email, password, name, age, gender, location);
        // TODO: save profile using DataFetchSend
    }
}
