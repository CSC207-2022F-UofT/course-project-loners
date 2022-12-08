package usecases;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;

public class EditProfile {
    public static final EditProfile e = new EditProfile();
    public static EditProfile getInstance(){
        return e;
    }
    @SuppressWarnings("unchecked")
    public boolean edit(HashMap<String, Object> info){
        Object[] keys = info.keySet().toArray();
        Object[] values = info.values().toArray();
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
                if(!Authenticator.isValidHobbies((List<String>) values[k])){
                    JOptionPane.showMessageDialog(null, "Invalid Hobbies!");
                    return false;
                }

            }
        }
        return true;
    }
}
