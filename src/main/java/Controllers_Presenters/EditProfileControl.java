package Controllers_Presenters;

import Use_Cases.EditProfile;
import java.util.*;

public class EditProfileControl{
    /*
    * EditProfileControl class
    * This class manages editing the profile.
     */
    public String name;
    public DataSendControl dataSend = new DataSendControl();
    public  EditProfileControl(){
    }
    EditProfile editProfile = new EditProfile();
    /**
     * This method sends the given updated profile data to the database
     @param info representing the updated data.
     */
    public void send(HashMap<String, Object> info) {

        if(editProfile.edit(info)) {

            String likes;
            if (info.get("likes") == null) {
                likes = null;
            } else {
                likes = String.join(": ", (String) info.get("likes"));
            }

            String str_data = info.get("name") + ", " + info.get("email") + ", " + info.get("password") + ", " + info.get("age") + ", " +
                    info.get("bio") + ", " + info.get("gender") + ", " + info.get("orientation") + ", " +
                    info.get("location") + ", " + String.join(": ", (String) info.get("hobbies")) + ", " +
                    info.get("socialMedia") + ", " + likes + ", " + info.get("preferredAge") + ", " +
                    info.get("preferredGender") + ", " + info.get("preferredLocation");
            dataSend.send_toid(DataFetchControl.fetch_id_fromEmail((String) info.get("email")), str_data.split(", "));

        }
    }

}