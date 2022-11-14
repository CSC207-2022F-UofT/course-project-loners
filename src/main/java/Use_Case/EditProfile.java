package Use_Case;

import Entity.Authenticator;
import Entity.Profile;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;

public class EditProfile {
    public Authenticator authenticator = new Authenticator();
    public boolean edit(HashMap<String, Object> info, Profile profile){
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
            } else if (keys[k] == "image") {
                profile.setImage((BufferedImage) values[k]);
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
            } else if (keys[k] == "likes") {
                profile.setLikes((List<String>) values[k]);
            } else if (keys[k] == "orientation") {
                profile.setOrientation((String) values[k]);
            }
        }
        return true;
    }
}
