package UIs;

import Controllers_Presenters.UIController;
import Controllers_Presenters.WelcomeControl;

import javax.swing.*;
import java.awt.*;

/**
 * A "welcome page" for this application, which has “Sign Up” and “Log in” buttons to let users choose
 * whether they want to log in or register.
 */
public class WelcomeUI {

    public WelcomeUI(){}

    /**
     * Construct a welcome page and show it to the user.
     */
    public void build_n_show(){
        // Set up a frame
        JFrame frame = new JFrame("Welcome page");
        frame.setLayout(new GridLayout(3,1));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // this line will terminate the program when you closed the window
        UIController.makeFrameFullSize(frame);

        // Claim components
        JLabel welcome_msg = new JLabel("Welcome to Loners!", SwingConstants.CENTER);
        JButton button_reg = new JButton("Don't have an account? Create account");
        JButton button_log = new JButton("Sign in");

        // Add components to the frame
        frame.add(welcome_msg);
        frame.add(button_log);
        frame.add(button_reg);

        new WelcomeControl(button_reg, button_log, frame); // Call a controller to set button reactions

        frame.setVisible(true); //make frame visible for user
    }

    public static void main(String[] args) { WelcomeUI ui = new WelcomeUI(); ui.build_n_show();}
}
