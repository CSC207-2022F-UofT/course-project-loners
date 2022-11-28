package UIs;

import Controllers_Presenters.DataFetchControl;
import Controllers_Presenters.EditProfileControl;
import Controllers_Presenters.UIController;
import Use_Cases.LoadFile;
import Use_Cases.LocationConverter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class EditProfileUI implements ActionListener {
    /*
     *EditProfileUI that enables users to edit their profile
     */
    JFrame f= new JFrame();
    String[] genders = {"male", "female", "other"}; // Options for gender field
    String[] orientations = {"straight", "gay", "bisexual", "other"}; // Options for orientation field
    DataFetchControl dataFetchControl = new DataFetchControl(); // DataFetchControl instance that fetches the user info
    // The following fields are used to take user input
    JTextField nameField;
    JTextField emailField;
    JSpinner ageField;
    JTextField bioField;
    JComboBox genderField;
    JComboBox orientationField;
    JTextField locationField;
    JTextField hobbiesField;
    JTextField socialMediaField;
    LoadFile loadFile;
    int id;

    public EditProfileUI(int id){
        /*
        * Constructor of EditProfileUI.
        * The components in the JFrame will be defined and added.
        * GridLayout is used for this UI.
         */
        this.id = id;
        Object[] data = ((Object[])dataFetchControl.fetchFromId(id)[0]); // Data of the user fetched from the database
        //Set each component accordingly to how they work
        JButton b=new JButton("Update!");
        JButton file_load = new JButton("Upload Profile Image");
        this.nameField = new JTextField(String.format("%s", data[1]), 20);
        JLabel name_label = new JLabel("name: ");
        this.emailField = new JTextField(String.format("%s", data[2]), 20);
        JLabel email_label = new JLabel("email: ");
        this.bioField = new JTextField(String.format("%s", data[5]), 100);
        JLabel bio_label = new JLabel("bio: ");
        this.genderField = new JComboBox<String>(genders);
        JLabel gender_label = new JLabel("gender: ");
        this.orientationField = new JComboBox<String>(orientations);
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
        LoadFile loadFile = new LoadFile(f, file_load, 2);

        // Add components to the JFrame
        f.setLayout(new GridLayout(6,4));
        b.setBounds(130,420,100, 40);
        f.setSize(800,600);
        f.add(name_label);
        f.add(nameField);
        f.add(email_label);
        f.add(emailField);
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
            BufferedImage image = ImageIO.read(new File(String.format("saved_images/%s.jpg", Integer.toString(2))));
            ImageIcon imageIcon = new ImageIcon(image); // load the image to a imageIcon
            Image image_1 = imageIcon.getImage(); // transform it
            Image new_img = image_1.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);
            JLabel label = new JLabel("", new ImageIcon(new_img), 0);
            f.add(label);
        } catch (IOException e){
            System.out.println(e);
        }

        loadFile.setLoader();
        f.add(b);
        f.setVisible(true);
        b.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        /*
        * When the "Update!" button is clicked, this action will be performed.
        * The instance of EditProfileController will receive the revised data, and the user will be redirected to
        * their profile page.
         */
        EditProfileControl control = new EditProfileControl();
        HashMap<String, Object> info = new HashMap<>();
        if (this.loadFile != null){
            try{
                File outputfile = new File(String.format("saved_images/%s.jpg", 2));
                ImageIO.write((BufferedImage)loadFile.image, "jpg", outputfile);
            } catch(IOException error){
                System.out.println(error);
            }
        }
        info.put("name", nameField.getText());
        info.put("email",emailField.getText());
        info.put("age", ageField.getValue());
        info.put("bio", bioField.getText());
        info.put("gender", genderField.getSelectedItem());
        info.put("orientation", orientationField.getSelectedItem());
        info.put("location", LocationConverter.codeToCoords(locationField.getText()));
        info.put("hobbies", Arrays.asList(hobbiesField.getText().split(" ")));
        info.put("socialMedia", socialMediaField.getText());

        control.send(info);
        new UIController(this.id).launchMyProfileUI();
    }
}