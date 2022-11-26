package UIs;
import Controllers_Presenters.DataFetchControl;
import Controllers_Presenters.RegControl;
import Controllers_Presenters.UIController;
import Use_Cases.PicHolder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class RegUI {
    JFrame frame = new JFrame("Registration page");
    JButton register = new JButton("Create account");
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
    JButton back = new JButton("Back to previous page");
    JLabel socialLabel = new JLabel("Social media (Please select a social media platform then enter your user name or url in the following box): ");
    String[] platformOption = {"Instagram", "Snapchat", "Facebook", "Twitter"};
    JComboBox<String> platform = new JComboBox<String>(platformOption);
    JTextField social = new JTextField();

    public RegUI(){
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
        social_media.add(social);
        frame.add(social_media);

        frame.add(picLabel);
        PicHolder loadFile = new PicHolder(frame, pic);
        loadFile.setLoader();

        frame.add(back);
        frame.add(register);

        frame.setLayout(new GridLayout(9,2, 0,15));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // terminate the program when you closed the window
        UIController.makeFrameFullSize(frame); // set size to full screen

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Give notification if there is missing input(s)
                if (email.getText().isEmpty() | pw.getText().isEmpty() | name.getText().isEmpty() |
                        age.getText().isEmpty()| Objects.requireNonNull(gender.getSelectedItem()).toString().isEmpty() |
                        code.getText().isEmpty() | social.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Warning: \nmissing input(s), please try again");
                } else if(!loadFile.saved){
                    JOptionPane.showMessageDialog(null, "Warning: \nyou did not select any image to upload, please try again");
                }

                try{
                    int id = new DataFetchControl().fetch_lastID() + 1;
                    File outputfile = new File(String.format("saved_images/%s.jpg", id));
                    ImageIO.write((BufferedImage)loadFile.image, "jpg", outputfile);
                } catch(IOException error){
                    JOptionPane.showMessageDialog(null, "Something went wrong when uploading the image.");
                }

                String social_media = Objects.requireNonNull(platform.getSelectedItem()).toString() + ": " + social.getText();
                new RegControl(social_media, email.getText(), pw.getText(), name.getText(), age.getText(), gender.getSelectedItem().toString(), code.getText(), frame);
            }
        });

        back.addActionListener(new ActionListener() {
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
