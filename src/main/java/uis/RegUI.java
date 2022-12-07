package uis;
import controllers.RegControl;
import controllers.UIController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * A "registration page" for this application, which allows user to enter their student email, password,
 * age, gender, postal code and social media, and also upload a picture as the icon.
 */
public class RegUI {
    /**
     * A frame for registration page.
     */
    private final JFrame frame = new JFrame("Registration page");
    /**
     * A register button that will register user if clicked.
     */
    private final JButton regButton = new JButton("Create account");
    /**
     * A button that let user select an image to upload as their icon.
     */
    private final JButton picButton = new JButton("Select image");
    /**
     * A text box that let user type in their email.
     */
    private final JTextField email = new JTextField();
    /**
     * A text box that let user type in their password.
     */
    private final JTextField pw = new JTextField();
    /**
     * A text box that let user type in their name.
     */
    private final JTextField name = new JTextField();
    /**
     * A text box that let user type in their age.
     */
    private final JTextField age = new JTextField();
    /**
     * A text box that let user type in their postal code.
     */
    private final JTextField code = new JTextField();
    /**
     * A list of options for gender
     */
    private final String[] genderOption = {"male", "female", "other"};
    /**
     * A drop-down list for user to select their gender
     */
    private final JComboBox<String> gender = new JComboBox<>(genderOption);
    /**
     * A list of options for social media platform
     */
    private final String[] platformOption = {"Instagram", "Snapchat", "Facebook", "Twitter"};
    /**
     * A drop-down list for user to select their gender
     */
    private final JComboBox<String> platform = new JComboBox<>(platformOption);
    /**
     * A text box that let user type in their social media information.
     */
    private final JTextField platformInfo = new JTextField();
    /**
     * An image user selected to be their icon, null if not selected yet
     */
    private Image image;

    /**
     * Construct the registration page, which have
     * two buttons for back to previous page and submit their information, and
     * text boxes for user to enter their email, password, name, postal code, social media, and
     * option lists for user to choose their age, gender, social media platform.
     * User also have to upload an image as their icon.
     */
    public RegUI(){
        // set up the frame
        frame.setLayout(new GridLayout(9,2, 0,15));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // terminate the program when you closed the window
        UIController.setFrameSize(frame); // set size to full screen

        // Claim the rest of components (labels)
        JLabel emailLabel = new JLabel("Email: ");
        JLabel pwLabel = new JLabel("Password: ");
        JLabel nameLabel = new JLabel("Name: ");
        JLabel ageLabel = new JLabel("Age: ");
        JLabel codeLabel = new JLabel("Postal code (with a white space e.g. M5S 1A4): ");
        JLabel genderLabel = new JLabel("Gender: ");
        JLabel socialLabel = new JLabel("Social media (Please select a social media platform then enter your user name or url in the following box): ");
        JLabel picLabel = new JLabel("Upload an image as your icon: ");

        // add all components to frame
        frame.add(emailLabel);
        frame.add(email);
        frame.add(pwLabel);
        frame.add(pw);
        frame.add(nameLabel);
        frame.add(name);
        frame.add(ageLabel);
        frame.add(age);
        frame.add(genderLabel);
        frame.add(gender);
        frame.add(codeLabel);
        frame.add(code);
        frame.add(socialLabel);
        JPanel socialMedia = new JPanel(); // Claim a panel for asking social media
        socialMedia.setLayout(new GridLayout(2,1));
        socialMedia.add(platform);
        socialMedia.add(platformInfo);
        frame.add(socialMedia); // Add the panel to frame
        frame.add(picLabel);
        // uploader.setLoader(); // add the upload picture button into the frame
        frame.add(picButton);
        UIController.addBackButton(frame, "WelcomeUI"); // add the back button into the frame
        frame.add(regButton);
    }

    /**
     * Show the registration page to the user.
     */
    public void show(){
        setButtonReact();
        frame.setVisible(true); // make frame visible for user
    }

    /**
     * Set responds to different button press.
     * If picture selecting button is clicked, a window will show to user and let them select an image to upload.
     * If register button is clicked, grab all the user's inputs and pass them to RegControl.
     */
    private void setButtonReact(){
        picButton.addActionListener(e -> {
            // a window will show to the user to select the picture that they want to upload from their computer
            FileDialog selectFile = new FileDialog(frame, "Open", FileDialog.LOAD);
            selectFile.setVisible(true);
            // get the directory and name of the selected file
            String fileDirectory = selectFile.getDirectory();
            String fileName = selectFile.getFile();
            try{
                image = ImageIO.read(new File(fileDirectory, fileName)); // load the selected image from user computer
            } catch (IOException error){
                JOptionPane.showMessageDialog(null,"Something went wrong when uploading image",
                        "Error", JOptionPane.INFORMATION_MESSAGE);
            } catch (NullPointerException error){
                JOptionPane.showMessageDialog(null,"select image window cancelled",
                        "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        regButton.addActionListener(e -> {
            String[] lstInputs = {Objects.requireNonNull(platform.getSelectedItem()).toString(), platformInfo.getText(),
                    email.getText(), pw.getText(), name.getText(), age.getText(), Objects.requireNonNull(
                            gender.getSelectedItem()).toString(), code.getText()}; // a list of all user's inputs
            new RegControl(frame, lstInputs, image); // pass the inputs to the controller
        });
    }
}
