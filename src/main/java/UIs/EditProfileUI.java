package UIs;

import Controllers_Presenters.DataFetchControl;
import Controllers_Presenters.EditProfileControl;
import Use_Cases.LoadFile;

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
    JFrame f= new JFrame();
    GridLayout experimentLayout = new GridLayout(4,2,10, 20);
    String[] genders = {"male", "female", "other"};
    String[] orientations = {"straight", "gay", "bisexual", "other"};
    DataFetchControl dataFetchControl = new DataFetchControl();
    Object[] data = ((Object[])dataFetchControl.fetch_fromid(2)[0]);

    public static String name;
    JButton b=new JButton("Update!");
    JButton file_load = new JButton("Upload Profile Image");
    JTextField nameField = new JTextField(String.format("%s", data[1]), 20);
    JLabel name_label = new JLabel("name: ");
    JTextField emailField = new JTextField(String.format("%s", data[2]), 20);
    JLabel email_label = new JLabel("email: ");
    JTextField bioField = new JTextField(String.format("%s", data[5]), 100);
    JLabel bio_label = new JLabel("bio: ");
    JComboBox genderField = new JComboBox<String>(genders);
    JLabel gender_label = new JLabel("gender: ");
    JComboBox orientationField = new JComboBox<String>(orientations);
    JLabel orientation_label = new JLabel("orientation: ");
    JTextField locationField = new JTextField("location", 100);
    JLabel location_label = new JLabel("location: ");
    JTextField hobbiesField = new JTextField(String.format("%s", ((String)data[9]).replaceAll(":", "")), 100);
    JLabel hobbies_label = new JLabel("hobbies: ");
    JTextField socialMediaField = new JTextField("socialMedia", 100);
    JLabel socialMedia_label = new JLabel("socialMedia: ");
    SpinnerModel model = new SpinnerNumberModel(20, 0,100, 1);
    JSpinner ageField = new JSpinner(model);
    JLabel age_label = new JLabel("age: ");
    JTextField pf_ageField = new JTextField(String.format("%s", data[12].toString()), 20);
    JLabel pf_age_label = new JLabel("Preferred Age: ");
    JComboBox pf_genderField = new JComboBox<String>(genders);
    JLabel pf_gender_label = new JLabel("Preferred Gender: ");
    JTextField pf_locationField = new JTextField(String.format("%s", data[13].toString()), 100);
    JLabel pf_location_label = new JLabel("Preferred Location: ");


    LoadFile loadFile = new LoadFile(f, file_load, 2);

    public EditProfileUI(){
        System.out.println(data);


        experimentLayout.setColumns(2);
        experimentLayout.setRows(7);
        b.setBounds(130,420,100, 40);
        f.setSize(600,300);
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
        f.add(pf_age_label);
        f.add(pf_ageField);
        f.add(pf_gender_label);
        f.add(pf_genderField);
        f.add(pf_location_label);
        f.add(pf_locationField);
        loadFile.setLoader();

        f.add(b);
        f.setLayout(experimentLayout);
        f.setVisible(true);
        b.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        EditProfileControl control = new EditProfileControl();
        HashMap<String, Object> info = new HashMap<>();
        try{
            File outputfile = new File(String.format("saved_images/%s.jpg", 2));
            System.out.println(outputfile);
            ImageIO.write((BufferedImage)loadFile.image, "jpg", outputfile);
        } catch(IOException error){
            System.out.println(error);
        }
        info.put("name", nameField.getText());
        info.put("email",emailField.getText());
        info.put("age", ageField.getValue());
        info.put("bio", bioField.getText());
        info.put("gender", genderField.getSelectedItem());
        info.put("orientation", orientationField.getSelectedItem());
        info.put("hobbies", Arrays.asList(hobbiesField.getText().split(" ")));
        info.put("socialMedia", socialMediaField.getText());

        control.send(info);
        new MyProfileUI(2);
    }


    public static void main(String[] args) {
        new EditProfileUI();
    }
}