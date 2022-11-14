package UI;

import Controllers_Presenters.EditProfileControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class EditProfileUI implements ActionListener {
    JFrame f;
    SpringLayout layout = new SpringLayout();
    GridLayout experimentLayout = new GridLayout(4,2,10, 20);
    String[] genders = {"male", "female", "other"};
    String[] orientations = {"straight", "gay", "bisexual", "other"};


    public static String name;
    JButton b=new JButton("Update!");
    JTextField nameField = new JTextField("Name", 20);
    JTextField emailField = new JTextField("email", 20);
    JTextField bioField = new JTextField("bio", 100);
    JComboBox genderField = new JComboBox<String>(genders);
    JComboBox orientationField = new JComboBox<String>(orientations);
    SpinnerModel model = new SpinnerNumberModel(20, 0,100, 1);
    JSpinner ageField = new JSpinner(model);
    JSpinner preferredAgeField = new JSpinner(model);

    public EditProfileUI(){

        f=new JFrame();
        experimentLayout.setColumns(2);
        experimentLayout.setRows(4);

        //nameField.setBounds(20, 20, 80, 30);
        emailField.setBounds(20, 55, 200, 30);
        ageField.setBounds(20, 90, 50, 30);
        bioField.setBounds(20, 125, 300, 60);
        genderField.setBounds(20, 190, 90, 30);
        orientationField.setBounds(20, 225, 90, 30);
        preferredAgeField.setBounds(20, 260, 90, 30);
        b.setBounds(130,420,100, 40);

        f.setSize(600,300);
        f.add(nameField);
        f.add(emailField);
        f.add(ageField);
        f.add(bioField);
        f.add(genderField);
        f.add(orientationField);
        f.add(preferredAgeField);
        f.add(b);
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
        control.send(info);
    }


    public static void main(String[] args) {
        new EditProfileUI();
    }
}