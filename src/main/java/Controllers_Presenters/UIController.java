package Controllers_Presenters;

import Entities.Profile;
import UIs.*;
import Use_Cases.ObjectListToProfile;

import javax.swing.*;
import java.awt.*;

public class UIController {
    public MyProfileUI myProfileUI;
    public WelcomeUI welUI;
    public LogUI logUI;
    public RegUI regUI;
    public MainUI mainUI;
    public EditPreferencesUI edpfUI;
    public ProfileFinderUI pffdUI;
    private EditProfileUI editProfileUI;
    int id;
    String email;

    public UIController(int id){
        this.id = id;
        Object[] lst = DataFetchControl.fetchFromId(id);
        Profile p = ObjectListToProfile.returnObjListAsProfile(lst);
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
    public void launchWelcomeUI() { welUI = new WelcomeUI(); }
    public void launchLogUI() { logUI = new LogUI(); }
    public void launchRegUI() { regUI = new RegUI(); }
    public void launchMainUI() { mainUI = new MainUI(id, email); }
    public void launchEditPreferencesUI() {
        EditPreferencesUI.buildUI(id);
    }
    public void launchEditProfileUI(){
        editProfileUI = new EditProfileUI(this.id);
    }

    public void launchProfileFinderUI(){ pffdUI = new ProfileFinderUI(email); }


    public static void makeFrameFullSize(JFrame frame){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
    }


}
