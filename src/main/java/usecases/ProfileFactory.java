package usecases;

import Entities.Profile;

public class ProfileFactory {
    public static Profile generateProfile(String platform, String pfInfo,String email, String pw,
                                          String name, String age, String gender, String postcode){
        int ageInt = Integer.parseInt(age);
        String socMed = platform + ": " + pfInfo;
        double[] location = LocationConverter.codeToCoords(postcode);
        return new Profile(socMed, email, pw, name, ageInt, gender, location);
    }
}
