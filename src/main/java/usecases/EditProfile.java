package usecases;


import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class EditProfile {
    public static final EditProfile e = new EditProfile();
    public static EditProfile getInstance(){
        return e;
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
                if(!Authenticator.isValidName((String) values[k])){
                    JOptionPane.showMessageDialog(null,"Invalid name",
                            "Error", JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }
            } else if (keys[k] == "email") {
                if(!Authenticator.isValidEmail((String) values[k])){
                    JOptionPane.showMessageDialog(null,"Invalid email",
                            "Error", JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }
            } else if (keys[k] == "age") {
                if (!Authenticator.isValidAge((int) values[k])){
                    JOptionPane.showMessageDialog(null,"Invalid age",
                            "Error", JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }
            } else if (keys[k] == "bio") {
                if (!Authenticator.isValidBio((String) values[k])){
                    JOptionPane.showMessageDialog(null,"Invalid bio",
                            "Error", JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }
            } else if (keys[k] == "hobbies") {
                List<String> hobby_list = new ArrayList<>(Arrays.asList(((String)values[k]).split(",")));
                if (!Authenticator.isValidHobbies(hobby_list)){
                    JOptionPane.showMessageDialog(null,"Invalid hobbies",
                            "Error", JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }
            }
        }
        return true;
    }
}
