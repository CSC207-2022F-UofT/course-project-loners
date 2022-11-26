package Controllers_Presenters;

import UIs.LogUI;
import Use_Cases.RegChecker;
import Use_Cases.RegDataStore;

import javax.swing.*;

public class RegControl {
    public RegControl(String social, String email, String password, String name, String age, String gender, String postcode, JFrame regf){
        RegChecker checker = new RegChecker(email, password, name, age, gender, postcode);
        if (!checker.pass){ // if checker did not pass, show failure message
            checker_message_presenter(checker);
        } else { // if checker passed, store the data
            int age_int = Integer.parseInt(age);
            RegDataStore store_data = new RegDataStore(social, email, password, name, age_int, gender, postcode);
            if (store_data.success){ // if data stored successfully, show success message and go to login page
                JOptionPane.showMessageDialog(null,"Account created! Click OK to the login page.");
                regf.setVisible(false);
                new UIController().launchLogUI();
            } else{ // if data had not been not stored, show wrong message
                JOptionPane.showMessageDialog(null, "Something went wrong!");
            }
        }
    }

    public void checker_message_presenter(RegChecker checker){
        String message = "Warning: \n" + checker.diagnose + "please try again!";
        JOptionPane.showMessageDialog(null, message);
    }
}
