package UIs;

import Controllers_Presenters.UIController;
import Controllers_Presenters.WelcomeControl;

import javax.swing.*;
import java.awt.*;

public class WelcomeUI {
    JFrame frame = new JFrame("Welcome page");
    JButton button_reg = new JButton("Don't have an account? Sign up now!");
    JButton button_log = new JButton("Login");
    JLabel welcome_msg = new JLabel("Welcome to Loners!", SwingConstants.CENTER);

    /**
     * The constructor of WelcomeUI, which function as the "welcome page" for this application.
     * It has buttons to let users choose whether they want to log in or sign up.
     */
    public WelcomeUI(){
        // Set up the frame
        frame.add(welcome_msg);
        frame.add(button_log);
        frame.add(button_reg);
        frame.setLayout(new GridLayout(3,1));
        frame.setVisible(true); //should put this after you added all the components into the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // this line will end the execution when you closed the window
        UIController.makeFrameFullSize(frame);

        // Set button reactions
        new WelcomeControl(button_reg, button_log, frame);
    }

    // public static void main(String[] args) { new WelcomeUI();}
}
