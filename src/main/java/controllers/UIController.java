package controllers;

import uis.*;
import dataaccess.FetchData;

import javax.swing.*;
import java.util.Objects;

/**
 * Opening different UIs and unify the frame size of all UIs
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
     * @return true if the user has set up Preferences
     */
    private boolean checkHasPreference(){
        Object[] dat = (Object[]) this.data[0];
        return (Objects.equals(dat[12], "null") | Objects.equals(dat[13], "null") | Objects.equals(dat[14], "null"));
    }

    /**
     * Set the size for the frame.
     * @param frame A frame to set
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
