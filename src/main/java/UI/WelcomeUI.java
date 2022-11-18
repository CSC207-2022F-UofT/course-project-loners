package UI;

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
        button_log.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == button_log) {
                    LogUI logui = new LogUI();
                    frame.setVisible(false);
                    logui.setVisible(true);
                }}
        });

        button_reg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == button_reg) {
                    RegUI regui = new RegUI();
                    frame.setVisible(false);
                    regui.setVisible(true);
                }}
        });
    }

    public static void main(String[] args) {
        new WelcomeUI();
    }
}
