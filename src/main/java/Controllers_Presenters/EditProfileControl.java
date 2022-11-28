package Controllers_Presenters;


import Entities.Preferences;
import Entities.Profile;
import Use_Cases.Authenticator;
import java.util.*;

public class EditProfileControl{
    /*
    * EditProfileControl class
    * This class manages editing the profile.
     */
    public String name;
    public Authenticator authenticator = new Authenticator();


    static Profile profile = new Profile("Rick", 21, "male",
            "straight", null, null, "This is Rick", null, null);
    static Preferences preferences = new Preferences(20, "male", 5, 2);
    public DataSendControl dataSend = new DataSendControl();
    public  EditProfileControl(){
    }

    /**
     * This method edits changes the data of the profile based on the data; info
     @param info represent the updated data of the user.
     @return boolean value representing whether the user has successfully edited the profile or not.
     */
    public boolean edit(HashMap<String, Object> info){
        Object[] keys = info.keySet().toArray();
        Object[] values = info.values().toArray();
        // If there is no data in info, we will do nothing and return false
        if (info.keySet().size() == 0){
            System.out.println("No change is made");
            return false;
        }
        // If the data is modified to some extent, we use authenticator to check is the user modified the data properly
        for (int k = 0; k<info.keySet().size();k++){
            if(keys[k] == "name"){
                if(authenticator.isValidName((String) values[k])){
                    profile.setName((String) values[k]);
                }else {
                    System.out.println("Invalid Name");
                    return false;
                }
            } else if (keys[k] == "email") {
                if(authenticator.isValidEmail((String) values[k])){
                    profile.setEmail( (String) values[k]);
                } else {
                    System.out.println("Invalid Email");
                    return false;
                }
            } else if (keys[k] == "age") {
                if(authenticator.isValidAge((int) values[k])){
                    profile.setAge((int) values[k]);
                } else {
                    return false;
                }
            } else if (keys[k] == "gender") {
                profile.setGender((String) values[k]);
            } else if (keys[k] == "location") {
                profile.setLocation((double[]) values[k]);
            } else if (keys[k] == "bio") {
                if(authenticator.isValidBio((String) values[k])) {
                    profile.setBio((String) values[k]);
                } else {
                    return false;
                }
            } else if (keys[k] == "hobbies") {
                if(authenticator.isValidHobbies((List<String>) values[k])){
                    profile.setHobbies((List<String>) values[k]);
                } else {
                    return false;
                }
            } else if (keys[k] == "socialMedia") {
                profile.setSocialMedia((String) values[k]);
            } else if (keys[k] == "orientation") {
                profile.setOrientation((String) values[k]);
            }
        }
        return true;
    }

    /**
     * This method sends the given updated profile data to the database
     @param info representing the updated data.
     */
    public void send(HashMap<String, Object> info) {

        if(this.edit(info)) {

            String likes;
            if (profile.getLikes() == null) {
                likes = null;
            } else {
                likes = String.join(": ", profile.getLikes());
            }

            String str_data = profile.getName() + ", " + profile.getEmail() + ", " + profile.getPassword() + ", " + profile.getAge() + ", " +
                    profile.getBio() + ", " + profile.getGender() + ", " + profile.getOrientation() + ", " +
                    profile.getLocation() + ", " + String.join(": ", profile.getHobbies()) + ", " +
                    profile.getSocialMedia() + ", " + likes + ", " + preferences.getPreferredAge() + ", " +
                    preferences.getPreferredGender() + ", " + preferences.getPreferredLocationRange();
            dataSend.send_toid(2, str_data.split(", "));

        }
    }

}