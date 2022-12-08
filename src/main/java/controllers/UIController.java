package controllers;

import uis.*;
import dataaccess.FetchData;

import javax.swing.*;
import java.util.Objects;

public class UIController {
    private final int id;
    private Object[] data;

    public UIController(int id){
        this.id = id;
        this.data = FetchData.fetchFromID(id);
    }

    public UIController(String email){ this.id = FetchData.fetchIDFromEmail(email); }

    public void launchMyProfileUI(){ new MyProfileUI(id); }
    public static void launchWelcomeUI() { WelcomeUI welUI = new WelcomeUI(); welUI.show(); }
    public static void launchLogUI() { new LogUI(); }
    public static void launchRegUI() { RegUI regUI = new RegUI(); regUI.show(); }
    public void launchMainUI() { MainUI mainUI = new MainUI(id); mainUI.show(); }
    public void launchEditPreferencesUI() {EditPreferencesUI.buildUI(id); }
    public void launchEditProfileUI(){new EditProfileUI(id);}
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

    private boolean checkHasPreference(){
        Object[] dat = (Object[]) this.data[0];
        return (Objects.equals(dat[12], "null") | Objects.equals(dat[13], "null") | Objects.equals(dat[14], "null"));
    }

    public static void setFrameSize(JFrame frame){
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null); // open the window at the center of the screen
    }
}
