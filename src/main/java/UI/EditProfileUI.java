package UI;

import Entity.EditProfileControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditProfileUI implements ActionListener {
    JFrame f;

    Color red  = new Color(255, 0, 0);
    public static String name;
    JButton b=new JButton("click");
    JTextField nameField = new JTextField("Name", 20);

    public EditProfileUI(){

        f=new JFrame();



        nameField.setBounds(50, 50, 100, 40);
        b.setBounds(130,100,100, 40);
        f.add(nameField);
        f.setSize(400,500);
        f.setLayout(null);
        f.setVisible(true);
        f.add(b);
        b.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        EditProfileControl control = new EditProfileControl(nameField.getText());
        control.send();
    }


    public static void main(String[] args) {
        new EditProfileUI();
    }
}
