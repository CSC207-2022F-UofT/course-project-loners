package controllers;

import uis.*;
import dataaccess.FetchData;

import javax.swing.*;
import java.util.Objects;

/**
 * UIController class manages the transitions between UIs
 */
public class UIController {
    /** id of the user */
    private int id;
    /** profile data of the user associated to above id */
    private Object[] data;

    public UIController() {}

    /**
     *  Constructor for this class that will set the id and data to the given user info
     * @param id of the user
     */
    public UIController(int id){
        this.id = id;
        this.data = FetchData.fetchFromID(id);
    }

    /**
     * Constructor for this class that will set the id based on the given email
     * @param email of the user
     */
    public UIController(String email){ this.id = FetchData.fetchIDFromEmail(email); }

    /**
     * Following methods launch each UI accordingly
     */
    public void launchMyProfileUI(){ new MyProfileUI(id); }
    public static void launchWelcomeUI() { WelcomeUI welUI = new WelcomeUI(); welUI.show(); }
    public void launchLogUI() { new LogUI(); }
    public void launchRegUI() { RegUI regUI = new RegUI(); regUI.show(); }
    public void launchMainUI() { MainUI mainUI = new MainUI(id); mainUI.show(); }
    public void launchEditPreferencesUI() {EditPreferencesUI.buildUI(id); }
    public void launchEditProfileUI(){
        new EditProfileUI(this.id);
    }
    public void launchProfileFinderUI(){ new ProfileFinderUI(0, Integer.toString(id)); }

    /**

     * This method checks if the user has preference info registered
     * @return boolean value representing whether the user has preferences info registered

     */
    public boolean checkHasPreference(){
        Object[] dat = (Object[]) this.data[0];
        return (Objects.equals(dat[12], "null") | Objects.equals(dat[13], "null") | Objects.equals(dat[14], "null"));
    }

    /**

     * This method sets the frame size of the UI.

     * @param frame of the UI
     */
    public static void setFrameSize(JFrame frame){
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null); // open the window at the center of the screen
    }

    /**
     * This method adds a back button to a UI
     * @param frame of the UI
     * @param targetUI is the UI we want to add a back button to

     */
    public static void addBackButton(JFrame frame, String targetUI){
        JButton backButton = new JButton("Back to previous page");
        frame.add(backButton);
        backButton.addActionListener(e -> {
            frame.setVisible(false);
            if (Objects.equals(targetUI, "WelcomeUI")){
                launchWelcomeUI();
            }
        });
    }
}
