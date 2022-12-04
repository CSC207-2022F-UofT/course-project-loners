package contollers;


import usecases.Authenticator;
import UIs.LogUI;

import javax.swing.*;

public class LogController{

    /**
     * This method instantiates a LogController, which displays one of three different messages through a pop-up to
     * the user interface according to the user input.
     * -
     * Scenario 1: email is not registered (not associated to any accounts on our platform). The LogController will
     * prompt the user to head to the register page.
     * Scenario 2: email matches password. The LogController will make the user's view move from the
     * log-in page to the main page.
     * Scenario 3: wrong password. The LogController will prompt the user to try again and stays on the log-in page.
     * -
     * @param email String, user input to the email field on the log-in page
     * @param password String, user input to the password field on the log-in page
     * @param logframe the frame the user sees at the moment
     */
    public LogController(String email, String password, JFrame logframe) {
        if (!Authenticator.emailExists(email)){
            JOptionPane.showMessageDialog(null,
                    "Email is not registered. Head to the Register page to join us!");
        } else{
            if (Authenticator.emailMatchPassword(email, password)){
                JOptionPane.showMessageDialog(null, "Login successful!");
                logframe.setVisible(false);
                new UIController(email).launchMainUI();
            } else{
                JOptionPane.showMessageDialog(null, "Incorrect password. Please try again.");
            }
        }
    }

    /**
     * Our main method for the LogController class. Currently, it has no implementation.
     * @param args a list of Strings
     */
    public static void main(String[] args) {
        new LogUI();
    }
}
