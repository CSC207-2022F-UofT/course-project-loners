package Controllers_Presenters;

import Entities.Profile;
import UIs.*;
import com.sun.tools.javac.Main;

public class UIController {
    public MyProfileUI myProfileUI;
    public EditProfileUI editProfileUI;
    public EditPreferencesUI editPreferencesUI;
    public ProfileFinderUI profileFinderUI;
    public DataFetchControl dataFetchControl = new DataFetchControl();
    public MainUI mainUI;
    public LogUI logUI;
    public RegUI regUI;
    public int id;
    public String email;
    public Profile profile;



    public UIController(String email){
        this.email = email;
        this.id = dataFetchControl.fetch_id_fromEmail(email);
    }

    public void launchMyProfileUI(){
        myProfileUI = new MyProfileUI(this.email);

    }
    public void launchEditProfileUI(){
        editProfileUI = new EditProfileUI(this.email);
    }
    public void backToMyProfileUI(){
        myProfileUI = new MyProfileUI(this.email);
    }
    public void backToMainUI(){
        mainUI = new MainUI(this.email);
    }
    public void launchEditPreferenceUI(){
        editPreferencesUI = new EditPreferencesUI(this.id);
        editPreferencesUI.setVisible(true);
    }
    public void launchProfileFinderUI(){
    }



}
