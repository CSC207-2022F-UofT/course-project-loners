package UI;

import Entity.EditProfileControl;
import Entity.Profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class EditProfileUI implements ActionListener {
    JFrame f;
    String[] genders = {"male", "female", "other"};
    String[] orientations = {"straight", "gay", "bisexual", "other"};

    Color red  = new Color(255, 0, 0);
    public static String name;
    JButton b=new JButton("Change");
    JTextField nameField = new JTextField("Name", 20);
    JTextField emailField = new JTextField("email", 20);
    JTextField ageFiled = new JTextField("age", 20);
    JTextField bioField = new JTextField("bio", 100);
    JComboBox genderField = new JComboBox<String>(genders);
    JComboBox orientationField = new JComboBox<String>(orientations);

    public EditProfileUI(){

        f=new JFrame();



        nameField.setBounds(20, 20, 80, 30);
        emailField.setBounds(20, 55, 200, 30);
        ageFiled.setBounds(20, 90, 50, 30);
        bioField.setBounds(20, 125, 300, 60);
        genderField.setBounds(20, 190, 90, 30);
        orientationField.setBounds(20, 225, 90, 30);
        b.setBounds(130,420,100, 40);
        f.setSize(400,500);
        f.setLayout(null);
        f.setVisible(true);

        f.add(nameField);
        f.add(emailField);
        f.add(ageFiled);
        f.add(bioField);
        f.add(genderField);
        f.add(orientationField);
        f.add(b);
        b.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        EditProfileControl control = new EditProfileControl();
        Map<String, Object> info = new HashMap<>();
        info.put("name", nameField.getText());
        info.put("email",emailField.getText());
        info.put("age", ageFiled.getText());
        info.put("bio", bioField.getText());
        info.put("gender", genderField.getSelectedItem());
        info.put("orientation", orientationField.getSelectedItem());
        control.send(info);
    }


    public static void main(String[] args) {
        new EditProfileUI();
    }
}
