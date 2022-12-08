package uis;

import dataaccess.FetchData;
import controllers.UIController;

import javax.swing.*;
import java.awt.*;

/**
 * A "main page" for this application, which allows user go to myProfileUI, editPreferenceUI, profileFinderUI,
 * or log-out by clicking different buttons.
 */
public class MainUI {
    /**
     * A frame for main page.
     */
    public final JFrame frame = new JFrame("Main page");
    /**
     * A button that will lead user to myProfileUI if clicked.
     */
    JButton profileButton = new JButton("User Info");
    /**
     * A button that will lead user to editPreferenceUI if clicked.
     */
    JButton editPreferenceButton = new JButton("Filter settings");
    /**
     * A button that will lead user to profileFinderUI if clicked.
     */
    JButton profileFinderButton = new JButton("Click here to match new people!");
    /**
     * A button that will log out the user, which will lead user to welcomeUI, if clicked.
     */
    JButton logoutButton = new JButton("Log out");
    /**
     * The id of user who opened this page.
     */
    int id;

    /**
     * Construct a main page, which have a welcome message and four buttons that head user to different UIs.
     *
     * @param id a user id, assuming it is valid
     */
    public MainUI(int id){
        // setting the frame
        frame.setLayout(new GridLayout(5,1));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // terminate the program when you closed the window
        UIController.setFrameSize(frame); // set size to full screen

        // get user's name to show in the main page
        this.id = id;
        Object[] userData = (Object[]) FetchData.fetchFromID(id)[0]; // get user info based on id
        JLabel welcomeMessage = new JLabel("Welcome back, " + userData[1]);

        // add components to the frame
        frame.add(welcomeMessage);
        frame.add(profileButton);
        frame.add(editPreferenceButton);
        frame.add(profileFinderButton);
        frame.add(logoutButton);
    }

    /**
     * Show the main page to user.
     */
    public void show(){
        setButtonReact();
        frame.setVisible(true);
    }

    /**
     * Set responds to different button press.
     */
    private void setButtonReact(){
        profileButton.addActionListener(e -> {
            frame.setVisible(false);
            new UIController(id).launchMyProfileUI();
        });
        editPreferenceButton.addActionListener(e -> {
            frame.setVisible(false);
            new UIController(id).launchEditPreferencesUI();
        });
        profileFinderButton.addActionListener(e -> {
            UIController controller = new UIController(id);
            if (controller.launchProfileFinderUI()){
                frame.setVisible(false);
            }
        });
        logoutButton.addActionListener(e -> {
            frame.setVisible(false);
            UIController.launchWelcomeUI();
        });
    }
}