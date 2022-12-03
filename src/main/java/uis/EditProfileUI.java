package uis;

import controllers.DataFetchControl;
import controllers.EditProfileControl;
import controllers.UIController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class EditProfileUI{
    /*
     *EditProfileUI that enables users to edit their profile
     */
    JFrame f= new JFrame();
    String[] genders = {"male", "female", "other"}; // Options for gender field
    String[] orientations = {"straight", "gay", "bisexual", "other"}; // Options for orientation field
    // The following fields are used to take user input
    JTextField nameField;
    JTextField emailField;
    JTextField passwordField;
    JSpinner ageField;
    JComboBox<String> genderField;
    JComboBox<String> orientationField;
    JTextField bioField;
    JTextField locationField;
    JTextField hobbiesField;
    JTextField socialMediaField;
    Object[] data;
    EditProfileControl control = new EditProfileControl();
    int id;
    boolean isImageUploaded;

    public EditProfileUI(int id){
        /*
        * Constructor of EditProfileUI.
        * The components in the JFrame will be defined and added.
        * GridLayout is used for this UI.
         */
        this.id = id;
        this.data = ((Object[]) DataFetchControl.fetch_fromid(id)[0]); // Data of the user fetched from the database

        //Set each component accordingly to how they work
        JButton b=new JButton("Update!");
        JButton file_load = new JButton("Upload Profile Image");
        this.nameField = new JTextField(String.format("%s", data[1]), 20);
        JLabel name_label = new JLabel("name: ");
        this.emailField = new JTextField(String.format("%s", data[2]), 20);
        JLabel passwordLabel = new JLabel("password: ");
        this.passwordField = new JTextField(String.format("%s", data[3]), 20);
        JLabel email_label = new JLabel("email: ");
        this.bioField = new JTextField(String.format("%s", data[5]), 100);
        JLabel bio_label = new JLabel("bio: ");
        this.genderField = new JComboBox<>(genders);
        JLabel gender_label = new JLabel("gender: ");
        this.orientationField = new JComboBox<>(orientations);
        JLabel orientation_label = new JLabel("orientation: ");
        this.locationField = new JTextField("location", 100);
        JLabel location_label = new JLabel("location: ");
        this.hobbiesField = new JTextField(String.format("%s", ((String)data[9]).replaceAll(":", "")), 100);
        JLabel hobbies_label = new JLabel("hobbies: ");
        JLabel socialMedia_label = new JLabel("socialMedia: ");
        this.socialMediaField = new JTextField(String.format("%s", ((String)data[10]).replaceAll(":", "")), 100);
        SpinnerModel model = new SpinnerNumberModel(20, 0,100, 1);
        this.ageField = new JSpinner(model);
        JLabel age_label = new JLabel("age: ");
        JLabel image_label = new JLabel("Profile Image:");
        JButton backToMyProfile = new JButton("Back to MyProfile");

        // Add components to the JFrame
        f.setLayout(new GridLayout(7,4));
        b.setBounds(130,420,100, 40);
        f.setSize(800,600);
        f.add(name_label);
        f.add(nameField);
        f.add(email_label);
        f.add(emailField);
        f.add(passwordLabel);
        f.add(passwordField);
        f.add(age_label);
        f.add(ageField);
        f.add(bio_label);
        f.add(bioField);
        f.add(gender_label);
        f.add(genderField);
        f.add(orientation_label);
        f.add(orientationField);
        f.add(location_label);
        f.add(locationField);
        f.add(hobbies_label);
        f.add(hobbiesField);
        f.add(socialMedia_label);
        f.add(socialMediaField);
        f.add(image_label);
        // Fetch image associated to the user from saved_images folder. The size will be set to 120 by 120
        try {
            BufferedImage image = ImageIO.read(new File(String.format("saved_images/%s.jpg", this.id)));
            ImageIcon imageIcon = new ImageIcon(image); // load the image to a imageIcon
            Image image_1 = imageIcon.getImage(); // transform it
            Image new_img = image_1.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);
            JLabel label = new JLabel("", new ImageIcon(new_img), SwingConstants.CENTER);
            f.add(label);
        } catch (IOException e){
            return;
        }
        f.add(file_load);
        f.add(b);
        f.add(backToMyProfile);
        f.setVisible(true);
        file_load.addActionListener((e) ->this.isImageUploaded = control.withHoldImage(f));


        b.addActionListener((e) -> {
            Object[] data = ((Object[]) DataFetchControl.fetch_fromid(id)[0]);
            HashMap<String, Object> info = new HashMap<>();
            info.put("name", nameField.getText());
            info.put("email", emailField.getText());
            info.put("password", passwordField.getText());
            info.put("age", ageField.getValue());
            info.put("bio", bioField.getText());
            info.put("gender", genderField.getSelectedItem());
            info.put("orientation", orientationField.getSelectedItem());
            info.put("location", control.convertLocation(locationField.getText()));
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