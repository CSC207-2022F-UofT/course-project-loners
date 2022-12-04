package UIs;

import contollers.LogController;
import contollers.UIController;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A login page for the application where users enter the email and password they registered with.
 */
public class LogUI implements ActionListener {
    JFrame f;
    GridLayout layout = new GridLayout(3, 1, 10, 20);

    JButton b = new JButton("Login");
    JLabel label1 = new JLabel("Email: ");
    JTextField emailField = new JTextField(20);
    JLabel label2 = new JLabel("Password: ");
    JTextField passwordField = new JTextField(20);
    JButton back = new JButton("Back to Welcome page");

    /**
     * Builds a frame for the login page. There are two text boxes for email and password input.
     * A button for users to get back to the welcome page, and another button for login.
     */
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
        UIController.setFrameSize(f); // set size to full screen


        b.addActionListener(this);
        back.addActionListener(new ActionListener() {
            /**
             * This method overrides the actionPerformed method that comes with the package ActionListener.
             * It closes the login page and calls the UIController to launch the welcome UI page.
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                new UIController();
                UIController.launchWelcomeUI();
            }
        });
    }

    /**
     * This method calls the LogController class and passes in the user's input in the email and password, as well
     * as the login window to the controller.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        new LogController(emailField.getText(), passwordField.getText(), f);
    }

    /**
     * Our main method for the LogUI class. It creates a new LogUI object.
     * @param args a list of Strings.
     */
    public static void main(String[] args) {
        new LogUI();
    }
}
