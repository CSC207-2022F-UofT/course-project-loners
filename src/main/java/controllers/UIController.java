package controllers;

import uis.*;
import dataaccess.FetchData;

import javax.swing.*;
import java.util.Objects;

/**
 * UIController class manages the transitions between UIs and unify the frame size of all UIs
 */
public class UIController {

    /**
     * id of the user.
     */
    private final int id;
    /**
     * list of data of user. The list contains a list of their information and their icon image.
     */
    private final Object[] data;

    /**
     * Initialize the user id, and also using id to get the data of the user with this id.
     * @param id id of the user
     */
    public UIController(int id){
        this.id = id;
        this.data = FetchData.fetchFromID(id);
    }

    /**
     * Using email to get the user id, and using that id to get the data of the user.
     * @param email email of the user
     */
    public UIController(String email){
        this.id = FetchData.fetchIDFromEmail(email);
        this.data = FetchData.fetchFromID(this.id);}

    /**
     * Open MyProfileUI.
     */
    public void launchMyProfileUI(){ new MyProfileUI(id); }

    /**
     * Open WelcomeUI.
     */
    public static void launchWelcomeUI() { WelcomeUI welUI = new WelcomeUI(); welUI.show(); }

    /**
     * Open LogUI.
     */
    public static void launchLogUI() { new LogUI(); }

    /**
     * Open RegUI.
     */
    public static void launchRegUI() { RegUI regUI = new RegUI(); regUI.show(); }

    /**
     * Open MainUI.
     */
    public void launchMainUI() { MainUI mainUI = new MainUI(id); mainUI.show(); }

    /**
     * Open EditPreferencesUI.
     */
    public void launchEditPreferencesUI() {EditPreferencesUI.buildUI(id); }

    /**
     * Open EditProfileUI.
     */
    public void launchEditProfileUI(){new EditProfileUI(id);}

    /**
     * Open ProfileFinderUI if the preference has been set.
     * Else shows a warning window to ask user to set up their preferences first.
     * @return true if the ProfileFinderUI has been successfully opened.
     */
    public boolean launchProfileFinderUI(){
        if (checkHasPreference()){
            // if user doesn't have preference, show a warning and not prevent them open the profileFinderUI
            JOptionPane.showMessageDialog(null, "Please set up the filter first.", "WARNING", JOptionPane.WARNING_MESSAGE);
            return false;
        } else{
            new ProfileFinderUI(0, Integer.toString(id));
            return true;
        }
    }

    /**
     * Check if the user has set up Preferences.
     * @return boolean value representing whether the profile information of
     * the logged-in user contains preferences information or not.
     */
    public boolean checkHasPreference(){
        Object[] dat = (Object[]) this.data[0];
        return (Objects.equals(dat[12], "null") | Objects.equals(dat[13], "null") | Objects.equals(dat[14], "null"));
    }

    /**
     * This method sets the frame size of the selected UI
     * @param frame of the UI
     */
    public static void setFrameSize(JFrame frame){
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null); // open the window at the center of the screen
    }
}
