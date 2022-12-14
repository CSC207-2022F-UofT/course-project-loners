package usecases;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
* Use case representing profile editing
 */
public class EditProfile {
    /* Singleton Design Pattern */
    public static final EditProfile e = new EditProfile();
    public static EditProfile getInstance(){
        return e;
    }
    /**
     * This method checks whether the user has successfully edited their profile or not.
     * @param info is the new profile data after the user has edited
     * @return whether user successfully edited the profile or not
     */
    public boolean edit(HashMap<String, Object> info){
        Object[] keys = info.keySet().toArray();
        Object[] values = info.values().toArray();
        List<String> hobbies = new ArrayList<>(Arrays.asList(((String)values[7]).split(",")));
        if (info.keySet().size() == 0){
            JOptionPane.showMessageDialog(null, "No changes were made to your profile!");
            return false;
        }
        for (int k = 0; k<info.keySet().size();k++){
            if(keys[k] == "name"){
                if(!Authenticator.isValidName((String) values[k])){
                    JOptionPane.showMessageDialog(null, "Invalid Name!");
                    return false;
                }
            } else if (keys[k] == "email") {
                if(!Authenticator.isValidEmail((String) values[k])){
                    JOptionPane.showMessageDialog(null, "Invalid Email!");
                    return false;
                }
            } else if (keys[k] == "age") {
                if(!Authenticator.isValidAge((int) values[k])){
                    JOptionPane.showMessageDialog(null, "Invalid Age!");
                    return false;
                }
            } else if (keys[k] == "bio") {
                if(!Authenticator.isValidBio((String) values[k])){
                    JOptionPane.showMessageDialog(null, "Invalid Bio!");
                    return false;
                }

            } else if (keys[k] == "hobbies") {
                if(!Authenticator.isValidHobbies(hobbies)){
                    JOptionPane.showMessageDialog(null, "Invalid Hobbies!");
                    return false;
                }

            }
        }
        return true;
    }
}
