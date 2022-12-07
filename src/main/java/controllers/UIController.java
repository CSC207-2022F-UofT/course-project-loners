package controllers;

import uis.*;

import javax.swing.*;
import java.util.Objects;

/**
 * UIController class manages the transitions between UIs
 */
public class UIController {
    private int id;
    private Object[] data;

    public UIController() {}

    public UIController(int id){
        this.id = id;
        this.data = DataController.fetchFromId(id);
    }


    public UIController(String email){ this.id = DataController.fetchIdFromEmail(email); }

    /**
     * Following methods launch each UI accordingly to their names.
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
     *
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

    /**
     * If back button is clicked, direct user back to the previous page(WelcomeUI).
     * @param frame is the frame of the UI
     * @param targetUI is the UI we want to add back button to.
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
