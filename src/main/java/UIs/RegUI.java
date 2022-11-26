package UIs;
import Controllers_Presenters.RegControl;
import Controllers_Presenters.UIController;
import Use_Cases.PictureHolder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class RegUI {
    JFrame frame = new JFrame("Registration page");
    JButton reg_button = new JButton("Create account");
    JLabel emailLabel = new JLabel("Email: ");
    JTextField email = new JTextField();
    JLabel pwLabel = new JLabel("Password: ");
    JTextField pw = new JTextField();
    JLabel nameLabel = new JLabel("Name: ");
    JTextField name = new JTextField();
    JLabel ageLabel = new JLabel("Age: ");
    JTextField age = new JTextField();
    JLabel codeLabel = new JLabel("Postal code (with a white space e.g. M5S 1A4): ");
    JTextField code = new JTextField();
    String[] genderOption = {"male", "female", "other"};
    JLabel genderLabel = new JLabel("Gender: ");
    JComboBox<String> gender = new JComboBox<String>(genderOption);
    JLabel picLabel = new JLabel("Upload an image as your icon: ");
    JButton pic = new JButton("Select image");
    JButton back_button = new JButton("Back to previous page");
    JLabel socialLabel = new JLabel("Social media (Please select a social media platform then enter your user name or url in the following box): ");
    String[] platformOption = {"Instagram", "Snapchat", "Facebook", "Twitter"};
    JComboBox<String> platform = new JComboBox<String>(platformOption);
    JTextField platform_info = new JTextField();

    /**
     * The constructor of RegUI, which function as a "registration page" for this application,
     * which allows user to enter their student email, password, age, gender, postal code and social media
     * and upload a picture as the icon.
     */
    public RegUI(){
        // add components to the frame
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
        JPanel social_media = new JPanel();
        social_media.setLayout(new GridLayout(2,1));
        social_media.add(platform);
        social_media.add(platform_info);
        frame.add(social_media);
        frame.add(picLabel);
        PictureHolder loadFile = new PictureHolder(frame, pic);
        loadFile.setLoader(); // add the load picture button into the frame
        frame.add(back_button);
        frame.add(reg_button);

        // Set up the frame
        frame.setLayout(new GridLayout(9,2, 0,15));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // terminate the program when you closed the window
        UIController.makeFrameFullSize(frame); // set size to full screen

        // Set reaction to the buttons
        reg_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] lst_inputs = {Objects.requireNonNull(platform.getSelectedItem()).toString(), platform_info.getText(),
                        email.getText(), pw.getText(), name.getText(), age.getText(),
                        Objects.requireNonNull(gender.getSelectedItem()).toString(), code.getText()};
                new RegControl(frame, reg_button, back_button, lst_inputs, loadFile);
            }
        });
        back_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new WelcomeUI();
            }
        });
    }

    public static void main(String[] args) {
        new RegUI();
    }
}
