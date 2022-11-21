package UIs;

import Controllers_Presenters.WelcomeControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeUI {
    JFrame frame = new JFrame("Welcome page");
    JButton button_reg = new JButton("Register");
    JButton button_log = new JButton("Login");
    JLabel welcome_msg = new JLabel("Welcome to Loner!", SwingConstants.CENTER);

    public WelcomeUI(){
        // Set up the frame
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3,1));
        frame.add(welcome_msg);
        frame.add(button_log);
        frame.add(button_reg);
        frame.setVisible(true);

        // Set button reactions
        new WelcomeControl(button_reg, button_log, frame);
    }

    public static void main(String[] args) {
        new WelcomeUI();
    }
}
