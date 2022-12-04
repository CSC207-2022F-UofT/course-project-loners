package usecases;


import java.util.HashMap;
import java.util.List;

public class EditProfile {
    public Authenticator authenticator = new Authenticator();
    public boolean edit(HashMap<String, Object> info){
        Object[] keys = info.keySet().toArray();
        Object[] values = info.values().toArray();
        if (info.keySet().size() == 0){
            System.out.println("No change is made");
            return false;
        }
        for (int k = 0; k<info.keySet().size();k++){
            if(keys[k] == "name"){
                if(authenticator.isValidName((String) values[k])){
                    return true;
                }else {
                    System.out.println("Invalid Name");
                    return false;
                }
            } else if (keys[k] == "email") {
                if(Authenticator.isValidEmail((String) values[k])){
                    return true;
                } else {
                    System.out.println("Invalid Email");
                    return false;
                }
            } else if (keys[k] == "age") {
                return Authenticator.isValidAge((int) values[k]);
            } else if (keys[k] == "gender") {
                return true;
            } else if (keys[k] == "location") {
                return true;
            } else if (keys[k] == "image") {
                return true;
            } else if (keys[k] == "bio") {
                return authenticator.isValidBio((String) values[k]);
            } else if (keys[k] == "hobbies") {
                return authenticator.isValidHobbies((List<String>) values[k]);
            } else if (keys[k] == "socialMedia") {
                return true;
            } else if (keys[k] == "likes") {
                return true;
            } else if (keys[k] == "orientation") {
                return true;
            }
        }
        return true;
    }
}
