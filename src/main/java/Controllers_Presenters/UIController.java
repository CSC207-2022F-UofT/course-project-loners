package Controllers_Presenters;

import UIs.EditProfileUI;
import UIs.LogUI;
import UIs.MyProfileUI;
import UIs.RegUI;

public class UIController {
    public MyProfileUI myProfileUI;
    public LogUI logUI;
    public RegUI regUI;
    int id;

    public UIController(int id){
        this.id = id;
    }

    public void launchMyProfileUI(){
        myProfileUI = new MyProfileUI(this.id);
    }


}
