package UIs;

import Controllers_Presenters.EditProfileControl;
import Entities.Profile;
import Use_Cases.LoadFile;
import Use_Cases.ToBuffer;
import org.w3c.dom.ranges.Range;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class EditProfileUI implements ActionListener {
    JFrame f= new JFrame();
    SpringLayout layout = new SpringLayout();
    GridLayout experimentLayout = new GridLayout(4,2,10, 20);
    String[] genders = {"male", "female", "other"};
    String[] orientations = {"straight", "gay", "bisexual", "other"};


    public static String name;
    JButton b=new JButton("Update!");
    JButton file_load = new JButton("Upload Image");
    JTextField nameField = new JTextField("Name", 20);
    JTextField emailField = new JTextField("email", 20);
    JTextField bioField = new JTextField("bio", 100);
    JComboBox genderField = new JComboBox<String>(genders);
    JComboBox orientationField = new JComboBox<String>(orientations);
    SpinnerModel model = new SpinnerNumberModel(20, 0,100, 1);
    JSpinner ageField = new JSpinner(model);
    LoadFile loadFile = new LoadFile(f, file_load);

    public EditProfileUI(){

        experimentLayout.setColumns(2);
        experimentLayout.setRows(4);

        //nameField.setBounds(20, 20, 80, 30);
        emailField.setBounds(20, 55, 200, 30);
        ageField.setBounds(20, 90, 50, 30);
        bioField.setBounds(20, 125, 300, 60);
        genderField.setBounds(20, 190, 90, 30);
        orientationField.setBounds(20, 225, 90, 30);
        b.setBounds(130,420,100, 40);

        f.setSize(600,300);
        f.add(nameField);
        f.add(emailField);
        f.add(ageField);
        f.add(bioField);
        f.add(genderField);
        f.add(orientationField);
        f.add(b);
        loadFile.setLoader();
        ToBuffer toBuffer = new ToBuffer();
        //BufferedImage bimage = toBuffer.toBufferedImage(loadFile.image);
        f.setLayout(experimentLayout);
        f.setVisible(true);
        b.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        EditProfileControl control = new EditProfileControl();
        HashMap<String, Object> info = new HashMap<>();
        info.put("name", nameField.getText());
        info.put("email",emailField.getText());
        info.put("age", ageField.getValue());
        info.put("bio", bioField.getText());
        info.put("gender", genderField.getSelectedItem());
        info.put("orientation", orientationField.getSelectedItem());
        info.put("image", loadFile.image);
        control.send(info);
    }


    public static void main(String[] args) {
        new EditProfileUI();
    }
}