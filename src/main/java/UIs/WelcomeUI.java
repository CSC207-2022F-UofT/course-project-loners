package UIs;

import Controllers_Presenters.UIController;
import Controllers_Presenters.WelcomeControl;

import javax.swing.*;
import java.awt.*;

/**
 * A "welcome page" for this application.
 */
public class WelcomeUI {
    JFrame frame = new JFrame("Welcome page");
    JButton button_reg = new JButton("Don't have an account? Create account");
    JButton button_log = new JButton("Sign in");
    JLabel welcome_msg = new JLabel("Welcome to Loners!", SwingConstants.CENTER);

    /**
     * The constructor of WelcomeUI. When this is called, a welcome page will show to the user.
     * It has buttons to let users choose whether they want to log in or sign up.
     */
    public WelcomeUI(){
        // Add components to the frame
        frame.add(welcome_msg);
        frame.add(button_log);
        frame.add(button_reg);

        // Set up the frame
        frame.setLayout(new GridLayout(3,1));
        frame.setVisible(true); //make frame visible
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // this line will terminate the program when you closed the window
        UIController.makeFrameFullSize(frame);

        // Call a controller to set button reactions
        new WelcomeControl(button_reg, button_log, frame);
    }

    public static void main(String[] args) { new WelcomeUI();}
}
