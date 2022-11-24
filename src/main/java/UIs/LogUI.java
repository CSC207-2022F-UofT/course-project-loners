package UIs;

import Controllers_Presenters.LogController;
import Controllers_Presenters.UIController;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogUI implements ActionListener {
    JFrame f;
    GridLayout layout = new GridLayout(3, 1, 10, 20);

    JButton b = new JButton("Login");
    JLabel label1 = new JLabel("Email: ");
    JTextField emailField = new JTextField(20);
    JLabel label2 = new JLabel("Password: ");
    JTextField passwordField = new JTextField(20);
    JButton back = new JButton("Back to Welcome page");


    public LogUI() {
        f = new JFrame();
        f.setLayout(layout);

//        f.setSize(600, 300);
        f.add(label1);
        f.add(emailField);
        f.add(label2);
        f.add(passwordField);
        f.add(back);
        f.add(b);

        f.setVisible(true); //should put this After added all the components into the frame
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // terminate the program when you closed the window
        UIController.makeFrameFullSize(f); // set size to full screen


        b.addActionListener(this);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                new UIController().launchWelcomeUI();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new LogController(emailField.getText(), passwordField.getText(), f);
    }

    public static void main(String[] args) {
        new LogUI();
    }
}
