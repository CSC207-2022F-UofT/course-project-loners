package Controllers_Presenters;

import Entities.Profile;
import UIs.*;
import Use_Cases.ObjectListToProfile;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/*
* UIController is responsible for UI transitions
 */
public class UIController {
    static int id;
    Object[] data;
    String email;
    public UIController(int id){
        this.id = id;
        this.data = DataFetchControl.fetchFromId(id);
        Profile p = ObjectListToProfile.returnObjListAsProfile(this.data);
        this.email = p.getEmail();
    }
    public UIController() {}
    public UIController(String email) {
        this.email = email;
        this.id = DataFetchControl.fetchIdFromEmail(email);
    }

    /**
     * Following methods are responsible for transitioning to each UI.
     */

    public void launchMyProfileUI(){
        new MyProfileUI(id);
    }
    public void launchWelcomeUI() { new WelcomeUI().build_n_show();}
    public void launchLogUI() { new LogUI(); }
    public void launchRegUI() { new RegUI().show();}
    public void launchMainUI() { new MainUI(id, email); }
    public void launchEditPreferencesUI() {
        EditPreferencesUI.buildUI(id);
    }
    public void launchEditProfileUI(){
        new EditProfileUI(this.id);
    }

    public void launchProfileFinderUI(){ new ProfileFinderUI(0, Integer.toString(id)); }

    /**
     * This method
     * @return boolean value representing whether the preferences are filled up or not,
     */
    public boolean checkIfPreference(){
        Object[] dat = (Object[]) this.data[0];
        return (Objects.equals(dat[12], "null") | Objects.equals(dat[13], "null") | Objects.equals(dat[14], "null"));
    }

    /**
     * This method makes the screen to full size
     * @param frame of the UI
     */
    public static void makeFrameFullSize(JFrame frame){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
    }


}
