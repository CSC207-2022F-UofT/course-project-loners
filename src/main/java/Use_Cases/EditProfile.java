package Use_Cases;


import java.util.HashMap;
import java.util.List;
/*
* This use case is used in EditProfileControl to edit the profile by validating the new input
*
 */
public class EditProfile {

    /**
     *
     * @param info of the user we want to edit
     * @return boolean representing whether the editing was successful or not.
     */
    public boolean edit(HashMap<String, Object> info){
        Object[] keys = info.keySet().toArray();
        Object[] values = info.values().toArray();
        if (info.keySet().size() == 0){
            System.out.println("No change is made");
            return false;
        }
        for (int k = 0; k<info.keySet().size();k++){
            if(keys[k] == "name"){
                if(Authenticator.isValidName((String) values[k])){
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
            } else if (keys[k] == "location") {
                return Authenticator.isValidAddress((String) values[k]);
            } else if (keys[k] == "bio") {
                return Authenticator.isValidBio((String) values[k]);
            } else if (keys[k] == "hobbies") {
                return Authenticator.isValidHobbies((List<String>) values[k]);
            }
        }
        return true;
    }
}
