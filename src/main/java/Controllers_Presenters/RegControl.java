package Controllers_Presenters;

import Entities.Profile;
import Use_Cases.LocationConverter;
import Use_Cases.PicUploader;
import Use_Cases.RegChecker;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Dealing with inputs that collect from RegUI and giving appropriate feedback to user.
 */
public class RegControl {
    /** First verify inputs, save inputs to the database and direct user to the login page(LogUI) if verification passed.
     * Also pops any messages to user if needed.
     *
     * @param regFrame the frame of RegUI
     * @param lstInputs a list of inputs that user typed from the registration page. The list is ordered by:
     *                   {social media platform, username/url of that platform,
     *                   email, password, name, age, gender, postal code}
     * @param picUploader the PicUploader from RugUI
     */
    public RegControl(JFrame regFrame, String[] lstInputs, PicUploader picUploader){
        // Extract individual inputs from the list
        String platform = lstInputs[0];
        String pfInfo = lstInputs[1];
        String email = lstInputs[2];
        String pw = lstInputs[3];
        String name = lstInputs[4];
        String age = lstInputs[5];
        String gender = lstInputs[6];
        String code = lstInputs[7];

        // Run checker to verify inputs
        RegChecker checker = new RegChecker(pfInfo, email, pw, name, age, code, picUploader);
        if (!checker.pass) {
            // If checker did not pass, pop up a failure message window
            String message = checker.diagnose + "please try again!";
            JOptionPane.showMessageDialog(null, message, "WARNING", JOptionPane.WARNING_MESSAGE);
        } else{
            // If checker passed, store the inputs to database
            // Create a profile the inputs
            int ageInt = Integer.parseInt(age);
            String socMed = platform + ": " + pfInfo;
            double[] location = LocationConverter.codeToCoords(code);
            Profile profile = new Profile(socMed, email, pw, name, ageInt, gender, location);
            try{ // If DataSendControl did not have exception i.e. data can be stored to the database
                new DataSendControl(profile);
                // Save user's image to the file
                try{
                    int id = new DataFetchControl().fetch_lastID();
                    File fileSavePic = new File(String.format("saved_images/%s.jpg", id));
                    ImageIO.write((BufferedImage)picUploader.image, "jpg", fileSavePic);
                } catch(IOException error){
                    JOptionPane.showMessageDialog(null, "Something went wrong when uploading the image.");
                }
                // Pop up a success message window and go to login page
                JOptionPane.showMessageDialog(null,"Account created! Click OK to the login page.",
                        "CONGRATULATION", JOptionPane.INFORMATION_MESSAGE);
                regFrame.setVisible(false);
                new UIController().launchLogUI();
            } catch (Exception e){ // If DataSendControl have exception i.e. data not did store to the database
                // Show error message
                JOptionPane.showMessageDialog(null, "We cannot save you submission!");
            }
        }
    }
}