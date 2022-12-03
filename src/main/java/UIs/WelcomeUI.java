package UIs;

import Controllers_Presenters.UIController;
import Controllers_Presenters.WelcomeControl;

import javax.swing.*;
import java.awt.*;

/**
 * A "welcome page" for this application, which has Sign-up and Log-in buttons to let users choose
 * whether they want to log in or register.
 */
public class WelcomeUI {
    /**
     * Construct a welcome page and show it to the user.
     */
    public void buildAndShow(){
        // Set up a frame
        JFrame frame = new JFrame("Welcome page");
        frame.setLayout(new GridLayout(3,1));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // this line will terminate the program when you closed the window
        UIController.makeFrameFullSize(frame);

        // Claim components
        JLabel welcomeMsg = new JLabel("Welcome to Loners!", SwingConstants.CENTER);
        JButton buttonReg = new JButton("Don't have an account? Create account");
        JButton buttonLog = new JButton("Sign in");

        // Add components to the frame
        frame.add(welcomeMsg);
        frame.add(buttonLog);
        frame.add(buttonReg);

        new WelcomeControl(buttonReg, buttonLog, frame); // Call a controller to set button reactions

        frame.setVisible(true); // make frame visible for user
    }
}
