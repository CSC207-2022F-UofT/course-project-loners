package Controllers_Presenters;

import Use_Cases.PictureHolder;
import Use_Cases.RegChecker;
import Use_Cases.RegDataStore;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class RegControl {
    public RegControl(JFrame regFrame, JButton reg_button, JButton back_button, String[] lst_inputs, PictureHolder picHolder){
        String platform = lst_inputs[0];
        String pf_info = lst_inputs[1];
        String email = lst_inputs[2];
        String pw = lst_inputs[3];
        String name = lst_inputs[4];
        String age = lst_inputs[5];
        String gender = lst_inputs[6];
        String code = lst_inputs[7];


        System.out.println(Arrays.toString(lst_inputs));
        RegChecker checker = new RegChecker(pf_info, email, pw, name, age, gender, code, picHolder);
        if (!checker.pass) { // if checker did not pass, show failure message
            String message = checker.diagnose + "please try again!";
            JOptionPane.showMessageDialog(null, message, "WARNING", JOptionPane.WARNING_MESSAGE);
        } else{ // if checker passed, store the data
            int age_int = Integer.parseInt(age);
            String soc_med = platform + ": " + pf_info;
            RegDataStore store_data = new RegDataStore(soc_med, email, pw, name, age_int, gender, code);
            if (store_data.success){ // if data stored successfully, show success message and go to login page
                upload_pic(picHolder);
                JOptionPane.showMessageDialog(null,"Account created! Click OK to the login page.");
                regFrame.setVisible(false);
                new UIController().launchLogUI();
            } else{ // if data had not been not stored, show wrong message
                JOptionPane.showMessageDialog(null, "Something went wrong when register!");
            }
        }
    }

    public void upload_pic(PictureHolder picHolder){
        try{
            int id = new DataFetchControl().fetch_lastID();
            File outputfile = new File(String.format("saved_images/%s.jpg", id));
            ImageIO.write((BufferedImage)picHolder.image, "jpg", outputfile);
        } catch(IOException error){
            JOptionPane.showMessageDialog(null, "Something went wrong when uploading the image.");
        }
    }

    public static void main(String[] args) {
    }
}