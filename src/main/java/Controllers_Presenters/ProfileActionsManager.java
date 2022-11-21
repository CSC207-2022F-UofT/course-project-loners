package Controllers_Presenters;

import Entities.Profile;
import Use_Cases.ObjectListToProfile;

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

    public void addLikeToProfile(String email){
        DataFetchControl myData = new DataFetchControl();
        Object[] data = myData.fetch_fromid(myId);
        Profile myProf = ObjectListToProfile.returnObjListAsProfile(data);
        myProf.addLike(email);
        new DataSendControl(myProf);
    }
}
