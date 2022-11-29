package Use_Cases;


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
                if(authenticator.isValidEmail((String) values[k])){
                    return true;
                } else {
                    System.out.println("Invalid Email");
                    return false;
                }
            } else if (keys[k] == "age") {
                if(authenticator.isValidAge((int) values[k])){
                    return true;
                } else {
                    return false;
                }
            } else if (keys[k] == "gender") {
                return true;
            } else if (keys[k] == "location") {
                return true;
            } else if (keys[k] == "image") {
                return true;
            } else if (keys[k] == "bio") {
                if(authenticator.isValidBio((String) values[k])) {
                    return true;
                } else {
                    return false;
                }
            } else if (keys[k] == "hobbies") {
                if(authenticator.isValidHobbies((List<String>) values[k])){
                    return true;
                } else {
                    return false;
                }
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
