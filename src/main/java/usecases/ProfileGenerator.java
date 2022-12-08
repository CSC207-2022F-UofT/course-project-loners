package usecases;

import entities.Profile;

public class ProfileGenerator {
    public static Profile generateProfile(String platform, String pfInfo,String email, String pw,
                                          String name, String age, String gender, String postcode){
        int ageInt = Integer.parseInt(age);
        String socMed = platform + ": " + pfInfo;
        double[] location = LocationConverter.codeToCoords(postcode);
        return new Profile(socMed, email, pw, name, ageInt, gender, location);
    }
}
