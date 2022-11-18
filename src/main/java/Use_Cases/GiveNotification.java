package Use_Cases;
import Entities.*;

import java.util.List;

// This class gives us a notification as an array of format {match indicator, notification} if two profiles are matched.

public class GiveNotification {
    private Profile profile1;
    private List<Profile> profiles;

    private void setProfiles(Profile profile1, List<Profile> profile2) {
        this.profile1 = profile1;
        this.profiles = profile2;
    }

    public String[] notif(Profile me, List<Profile> yous){
        setProfiles(me, yous);
        //Returns null if we do not have a match, return an array with match indicator and notif if a match occurs
        //between profiles
        // We do this because we want to know if we have a match and we want the match message to show in the inbox
        for (Profile profile : profiles) {
            List<String> likes2 = profile.getLikes();
            //profile1 is our profile while profile is the other profile
            for (String emails : likes2) {
                if (profile1.getEmail().equals(emails)) {
                    return new String[]{"match", "You have a match with " + profile.getName() + "!"};
                }
            }
        }
        return null;
    }
}