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
     * A frame for welcome page.
     */
    private final JFrame frame = new JFrame("Welcome page");
    /**
     * A button that will head user to the registration page if clicked.
     */
    private final JButton buttonReg = new JButton("Don't have an account? Create account");
    /**
     * A button that will head user to the log-in page if clicked.
     */
    private final JButton buttonLog = new JButton("Sign in");

    /**
     * Construct a welcome page, which has a welcome massage, sign-up and log-in buttons.
     */
    public WelcomeUI(){
        // Set up a frame
        frame.setLayout(new GridLayout(3,1));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // this line will terminate the program when you closed the window
        UIController.setFrameSize(frame);

        // Claim the last component(the message)
        JLabel welcomeMsg = new JLabel("Welcome to Loners!", SwingConstants.CENTER);

        // Add components to the frame
        frame.add(welcomeMsg);
        frame.add(buttonLog);
        frame.add(buttonReg);
    }

    /**
     * Show the welcome page to the user.
     */
    public void show(){
        new WelcomeControl(buttonReg, buttonLog, frame); // Call a controller to set button reactions
        frame.setVisible(true); // make frame visible for user
    }
}
