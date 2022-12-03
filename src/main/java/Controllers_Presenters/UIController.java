package Controllers_Presenters;

import Entities.Profile;
import UIs.*;
import Use_Cases.ObjectListToProfile;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class UIController {
    public MyProfileUI myProfileUI;
    public WelcomeUI welUI;
    public LogUI logUI;
    public RegUI regUI;
    public MainUI mainUI;
    public ProfileFinderUI pffdUI;
    public EditProfileUI editProfileUI;
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

    public void launchMyProfileUI(){
        myProfileUI = new MyProfileUI(id);
    }
    public void launchWelcomeUI() { welUI = new WelcomeUI(); welUI.build_n_show();}
    public void launchLogUI() { logUI = new LogUI(); }
    public void launchRegUI() { regUI = new RegUI(); regUI.show();}
    public void launchMainUI() { mainUI = new MainUI(id, email); }
    public void launchEditPreferencesUI() {
        EditPreferencesUI.buildUI(id);
    }
    public void launchEditProfileUI(){
        editProfileUI = new EditProfileUI(this.id);
    }

    public void launchProfileFinderUI(){ pffdUI = new ProfileFinderUI(0, Integer.toString(id)); }

    public boolean checkifPreference(){
        Object[] dat = (Object[]) this.data[0];
        return (Objects.equals(dat[12], "null") | Objects.equals(dat[13], "null") | Objects.equals(dat[14], "null"));
    }


    public static void makeFrameFullSize(JFrame frame){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
    }


}
