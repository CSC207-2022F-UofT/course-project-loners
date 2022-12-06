package controllers;

import dataaccess.FetchData; // implements a Use Case interface
import usecases.ProfileFactory;
import usecases.RegChecker;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Dealing with inputs that collect from RegUI and giving appropriate feedback to user.
 */
public class RegControl {
    /**
     * First verify inputs, then save inputs to the database and direct user to the login page(LogUI) if verification passed.
     * Also pops any (failure/success) messages to user if needed.
     *
     * @param regFrame the frame of RegUI
     * @param lstInputs a list of inputs that user typed from the registration page. The list is ordered by:
     *                   {social media platform, username/url of that platform,
     *                   email, password, name, age, gender, postal code}
     */
    public RegControl(JFrame regFrame, String[] lstInputs, Image image){
        // Extract individual inputs from the list
        String platform = lstInputs[0];
        String pfInfo = lstInputs[1];
        String email = lstInputs[2];
        String pw = lstInputs[3];
        String name = lstInputs[4];
        String age = lstInputs[5];
        String gender = lstInputs[6];
        String code = lstInputs[7];
        // check if image has been


        // Run checker to verify inputs
        boolean picLoaded = image != null;
        RegChecker checker = new RegChecker(pfInfo, email, pw, name, age, code, picLoaded);
        if (!checker.pass) { // If checker did not pass, pop up a failure message window
            String message = checker.diagnose + "please try again!";
            JOptionPane.showMessageDialog(null, message, "WARNING", JOptionPane.WARNING_MESSAGE);
        } else{
            try{ // Save user's image
                int id = FetchData.fetchLastID();
                File fileSavePic = new File(String.format("saved_images/%s.jpg", id + 1));
                assert image != null;
                ImageIO.write((BufferedImage)image, "jpg", fileSavePic);
            } catch(IOException error){ // pop up a warning window and stop the saving actions if image can not upload
                JOptionPane.showMessageDialog(null, "Something went wrong when uploading the image.", "WARNING", JOptionPane.WARNING_MESSAGE);
                return;
            }
            boolean dataSaveSuccess = regDataStore(platform, pfInfo, email, pw, name, age, gender, code); // save inputs to database
            if (dataSaveSuccess){
                // if inputs have been successfully saved, pop up a success message window and go to login page
                JOptionPane.showMessageDialog(null,"Account created! Click OK to the login page.",
                        "CONGRATULATION", JOptionPane.INFORMATION_MESSAGE);
                regFrame.setVisible(false);
                new UIController().launchLogUI();
            } else {
                // if inputs did not save, show an error message
                JOptionPane.showMessageDialog(null, "Something went wrong when saving you submission, please try again!");
            }
        }
    }

    /**
     * Save all the user inputs to the database.
     * Precondition: all user inputs are valid and not null
     *
     * @param platform a valid user input, for social media platform
     * @param pfInfo a valid user input, for social media platform information
     * @param email a valid user input, for email
     * @param pw a valid user input, for password
     * @param name a valid user input, for name
     * @param age a valid user input, for age
     * @param gender a valid user input, for gender
     * @param postcode a valid user input, for postcode
     * @return true if data has been saved to the database
     */
    private boolean regDataStore(String platform, String pfInfo,String email, String pw, String name, String age, String gender, String postcode) {
        try{ // DataSendControl did not have exception i.e. data can be stored to the database
            DataController.sendData(ProfileFactory.generateProfile(platform, pfInfo, email, pw, name, age, gender, postcode));
        } catch (Exception e) { // If DataSendControl have exception i.e. data not did store to the database
            return false;
        }
        return true;
    }
}