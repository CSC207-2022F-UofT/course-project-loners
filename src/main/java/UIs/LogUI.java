package UIs;

import Controllers_Presenters.EditProfileControl;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class LogUI implements ActionListener {
    JFrame f;
    GridLayout layout = new GridLayout(3, 1, 10, 20);

    JButton b = new JButton("Login");
    JTextField emailField = new JTextField("Email", 20);
    JTextField passwordField = new JTextField("Password", 20);

    public LogUI() {
        f = new JFrame();
        layout.setColumns(1);
        layout.setRows(3);

        f.setSize(600, 300);
        f.add(emailField);
        f.add(passwordField);
        f.setLayout(layout);
        f.setVisible(true);
        f.add(b);
        b.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        EditProfileControl control = new EditProfileControl();
        HashMap<String, Object> info = new HashMap<>();
        info.put("email", emailField.getText());
        info.put("password", passwordField.getText());
        control.send(info);
    }


    public static void main(String[] args) {
        new LogUI();
    }
}
