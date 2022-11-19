package Controllers_Presenters;


import Entities.Preferences;
import Entities.Profile;
import Use_Cases.Authenticator;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class EditProfileControl{
    public String name;
    public Set<Object> objects = new HashSet<>();
    public Authenticator authenticator = new Authenticator();


    static Profile profile = new Profile("Rick", 21, "male",
            "straight", null, null, "This is Rick", null, null);
    static Preferences preferences = new Preferences(20, "male",null);
    public DataSendControl dataSend = new DataSendControl(profile);
    public DataFetchControl dataFetchControl = new DataFetchControl();
    public  EditProfileControl(){

    }

    public boolean edit(HashMap<String, Object> info){
        Object[] keys = info.keySet().toArray();
        Object[] values = info.values().toArray();
        if (info.keySet().size() == 0){
            System.out.println("No change is made");
            return false;
        }
        for (int k = 0; k<info.keySet().size();k++){
            if(keys[k] == "name"){
                if(authenticator.is_valid_name((String) values[k])){
                    profile.setName((String) values[k]);
                }else {
                    System.out.println("Invalid Name");
                    return false;
                }
            } else if (keys[k] == "email") {
                if(authenticator.is_valid_email((String) values[k])){
                    profile.setEmail( (String) values[k]);
                } else {
                    System.out.println("Invalid Email");
                    return false;
                }
            } else if (keys[k] == "age") {
                if(authenticator.is_valid_age((int) values[k])){
                    profile.setAge((int) values[k]);
                } else {
                    return false;
                }
            } else if (keys[k] == "gender") {
                profile.setGender((String) values[k]);
            } else if (keys[k] == "location") {
                profile.setLocation((double[]) values[k]);
            } else if (keys[k] == "bio") {
                if(authenticator.is_valid_bio((String) values[k])) {
                    profile.setBio((String) values[k]);
                } else {
                    return false;
                }
            } else if (keys[k] == "hobbies") {
                if(authenticator.is_valid_hobbies((List<String>) values[k])){
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

    public boolean send(HashMap<String, Object> info) {
        /*String.format("User1: %s", this.name))*/
        System.out.println("This part is successfully executed");
        if(this.edit(info)){
            System.out.println("This part is successfully executed2");
            System.out.println(dataFetchControl.fetch_fromid(2)[0]);

            String str_data = profile.getName()+", "+profile.getEmail()+", "+ profile.getPassword()+", "+profile.getAge()+", "+
                    profile.getBio()+ ", "+profile.getGender()+", "+profile.getOrientation()+", "+
                    profile.getLocation()+", "+profile.getHobbies()+", "+
                    profile.getSocialMedia()+", "+profile.getLikes()+", "+preferences.getPreferredAge()+", "+
                    preferences.getPreferredGender()+", "+preferences.getPreferredLocation();
            dataSend.send_toid(2, str_data.split(", "));

            System.out.println("Successfully wrote to the file.");

            return true;


        }else{
            System.out.println("Couldn't successfully edited");
            return false;
        }

    }

}