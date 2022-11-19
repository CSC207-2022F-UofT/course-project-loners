package Controllers_Presenters;

import java.util.List;

public class ProfileActionsManager {

    int myId;
    public ProfileActionsManager(int myId) {
        this.myId = myId;
    }

    public List<String> ListOfConnections(){
        ConnectProfilesControl c = new ConnectProfilesControl(myId);
        return c.passPreferences();
    }
}
