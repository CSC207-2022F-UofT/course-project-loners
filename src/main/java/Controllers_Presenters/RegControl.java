package Controllers_Presenters;

import Use_Cases.PictureHolder;
import Use_Cases.RegChecker;
import Use_Cases.RegDataStore;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Dealing with inputs that collect from RegUI and giving appropriate feedback to user.
 */
public class RegControl {
    /** First verify inputs, save inputs to the databse and direct user to the login page(LogUI) if verification passed.
     * Also pops any messages to user if needed.
     * @param regFrame the frame of RegUI
     * @param lst_inputs a list of inputs that user typed from the registration page. The list is ordered by:
     *                   {social media platform, username/url of that platform,
     *                   email, password, name, age, gender, postal code}
     * @param picHolder the PictureHolder from RugUI
     */
    public RegControl(JFrame regFrame, String[] lst_inputs, PictureHolder picHolder){
        // Extract individual inputs from the list
        String platform = lst_inputs[0];
        String pf_info = lst_inputs[1];
        String email = lst_inputs[2];
        String pw = lst_inputs[3];
        String name = lst_inputs[4];
        String age = lst_inputs[5];
        String gender = lst_inputs[6];
        String code = lst_inputs[7];

        // Run the checker to verify the inputs
        RegChecker checker = new RegChecker(pf_info, email, pw, name, age, gender, code, picHolder);
        if (!checker.pass) {
            // If checker did not pass, pops failure message
            String message = checker.diagnose + "please try again!";
            JOptionPane.showMessageDialog(null, message, "WARNING", JOptionPane.WARNING_MESSAGE);
        } else{
            // If checker passed, store the data
            int age_int = Integer.parseInt(age);
            String soc_med = platform + ": " + pf_info;
            RegDataStore store_data = new RegDataStore(soc_med, email, pw, name, age_int, gender, code);
            if (store_data.success){
                // If data stored...
                // First, save user's image to the file
                try{
                    int id = new DataFetchControl().fetch_lastID();
                    File fileSavePic = new File(String.format("saved_images/%s.jpg", id));
                    ImageIO.write((BufferedImage)picHolder.image, "jpg", fileSavePic);
                } catch(IOException error){
                    JOptionPane.showMessageDialog(null, "Something went wrong when uploading the image.");
                }
                // Then, pops success message and go to login page
                JOptionPane.showMessageDialog(null,"Account created! Click OK to the login page.",
                        "CONGRATULATION", JOptionPane.INFORMATION_MESSAGE);
                regFrame.setVisible(false);
                new UIController().launchLogUI();
            } else{
                // If data did not store, show error message
                JOptionPane.showMessageDialog(null, "Something went wrong when register!");
            }
        }
    }
}