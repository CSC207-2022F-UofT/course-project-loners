package uis;

import dataaccess.FetchData;
import controllers.EditProfileControl;
import controllers.UIController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;


/*
* EditProfileUI is where the user edits their profile information.
* This UI will pop up after the user clicked a button from MyProfileUI
 */
public class EditProfileUI{
    /* Frame on which we put UI components */
    JFrame f= new JFrame();
    /* genders list that will be used in genderField */
    String[] genders = {"male", "female", "other"}; // Options for gender field
    /* orientations list that will be used in orientationField */
    String[] orientations = {"straight", "gay", "bisexual", "other"}; // Options for orientation field
    /* Name input field  */
    JTextField nameField;
    /* email input field  */
    JTextField emailField;
    /* password input field  */
    JTextField passwordField;
    /* age input field  */
    JSpinner ageField;
    /* gender input field  */
    JComboBox<String> genderField;
    /* orientation input field  */
    JComboBox<String> orientationField;
    /* bio input field  */
    JTextField bioField;
    /* location input field  */
    JTextField locationField;
    /* hobbies input field  */
    JTextField hobbiesField;
    /* socialMedia input field  */
    JTextField socialMediaField;
    /*  data of the user */
    Object[] data;
    /* EditProfileControl instance used for image uploading and sending data  */
    EditProfileControl control = new EditProfileControl();
    /* id of the user editing the profile */
    int id;
    /* whether image is uploaded or not  */
    boolean isImageUploaded;

    /**
     * This constructor builds the edit profile UI.
     * @param id of the user editing the profile
     */
    public EditProfileUI(int id){
        /*
        * Constructor of EditProfileUI.
        * The components in the JFrame will be defined and added.
        * GridLayout is used for this UI.
         */
        this.id = id;
        this.data = ((Object[]) FetchData.fetchFromID(id)[0]); // Data of the user fetched from the database

        //Set each component accordingly to how they work
        JButton b=new JButton("Update!");
        JButton fileLoad = new JButton("Upload Profile Image");
        this.nameField = new JTextField(String.format("%s", data[1]), 20);
        JLabel nameLabel = new JLabel("name: ");
        this.emailField = new JTextField(String.format("%s", data[2]), 20);
        JLabel passwordLabel = new JLabel("password: ");
        this.passwordField = new JTextField(String.format("%s", data[3]), 20);
        JLabel emailLabel = new JLabel("email: ");
        this.bioField = new JTextField(String.format("%s", data[5]), 100);
        JLabel bioLabel = new JLabel("bio: (no commas please)");
        this.genderField = new JComboBox<>(genders);
        JLabel genderLabel = new JLabel("gender: ");
        this.orientationField = new JComboBox<>(orientations);
        JLabel orientationLabel = new JLabel("orientation: ");
        this.locationField = new JTextField("location", 100);
        JLabel locationLabel = new JLabel("location: ");
        this.hobbiesField = new JTextField(String.format("%s", ((String)data[9]).replaceAll(":", "")), 100);
        JLabel hobbiesLabel = new JLabel("hobbies: ");
        JLabel socialMediaLabel = new JLabel("socialMedia: ");
        this.socialMediaField = new JTextField(String.format("%s", ((String)data[10]).replaceAll(":", "")), 100);
        SpinnerModel model = new SpinnerNumberModel(20, 0,100, 1);
        this.ageField = new JSpinner(model);
        JLabel ageLabel = new JLabel("age: ");
        JLabel imageLabel = new JLabel("Profile Image:");
        JButton backToMyProfile = new JButton("Back to MyProfile");

        // Add components to the JFrame
        f.setLayout(new GridLayout(7,4));
        b.setBounds(130,420,100, 40);
        f.setSize(800,600);
        f.add(nameLabel);
        f.add(nameField);
        f.add(emailLabel);
        f.add(emailField);
        f.add(passwordLabel);
        f.add(passwordField);
        f.add(ageLabel);
        f.add(ageField);
        f.add(bioLabel);
        f.add(bioField);
        f.add(genderLabel);
        f.add(genderField);
        f.add(orientationLabel);
        f.add(orientationField);
        f.add(locationLabel);
        f.add(locationField);
        f.add(hobbiesLabel);
        f.add(hobbiesField);
        f.add(socialMediaLabel);
        f.add(socialMediaField);
        f.add(imageLabel);
        // Fetch image associated to the user from saved_images folder. The size will be set to 120 by 120
        try {
            BufferedImage image = ImageIO.read(new File(String.format("saved_images/%s.jpg", this.id)));
            ImageIcon imageIcon = new ImageIcon(image); // load the image to a imageIcon
            Image image1 = imageIcon.getImage(); // transform it
            Image newImg = image1.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);
            JLabel label = new JLabel("", new ImageIcon(newImg), SwingConstants.CENTER);
            f.add(label);
        } catch (IOException e){
            return;
        }
        f.add(fileLoad);
        f.add(b);
        f.add(backToMyProfile);
        f.setVisible(true);
        fileLoad.addActionListener((e) ->this.isImageUploaded = control.withHoldImage(f));


        b.addActionListener((e) -> {
            Object[] data = ((Object[]) FetchData.fetchFromID(id)[0]);
            HashMap<String, Object> info = new HashMap<>();
            info.put("name", nameField.getText());
            info.put("email", emailField.getText());
            info.put("password", passwordField.getText());
            info.put("age", ageField.getValue());
            info.put("bio", bioField.getText());
            info.put("gender", genderField.getSelectedItem());
            info.put("orientation", orientationField.getSelectedItem());
            info.put("location", locationField.getText());
            info.put("hobbies", hobbiesField.getText());
            info.put("socialMedia", socialMediaField.getText());
            info.put("likes", data[11]);
            info.put("preferredAge", data[12]);
            info.put("preferredGender", data[13]);
            info.put("preferredLocation", data[14]);


            if (control.send(info, id)) {
                JOptionPane.showMessageDialog(null,"Successfully edited your profile!",
                        "CONGRATULATION", JOptionPane.INFORMATION_MESSAGE);
                if(isImageUploaded){
                    control.sendImage(id);
                }
                new UIController(id).launchMyProfileUI();
                f.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Something went wrong.",
                        "Sorry...", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        backToMyProfile.addActionListener((e) -> {
            JOptionPane.showMessageDialog(null,"Back to your profile!",
                    "CONGRATULATION", JOptionPane.INFORMATION_MESSAGE);
            new UIController(id).launchMyProfileUI();
            f.setVisible(false);
        });
    }
}