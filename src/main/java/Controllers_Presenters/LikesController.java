package Controllers_Presenters;


import java.util.Arrays;

/**
 * Add a class to simplify adding likes to profiles.
 */
public class LikesController {
    /**
     * Changing our likes list in the database
     * @param myLikes my current list of likes, currently formatted as a string of numbers seperated by ": " or null
     * @param otherId the id of the other profile that I want to add to my likes list
     * @param myProfile the list that represents a profile in the database
     */
    public static void modifyLikes(String myLikes, int otherId, Object[] myProfile){
        String newLikes = "";
        newLikes = myLikes;
        if (myLikes.contains("likes") || myLikes.contains("null")){
            newLikes = "";
        }
        newLikes = newLikes + otherId +": ";
        myProfile[11] = newLikes;
        int myId = Integer.parseInt((String)myProfile[0]);
        DataSendControl c = new DataSendControl();
        Object[] myProfileClone = Arrays.copyOfRange(myProfile, 1, 15);
        c.send_toid(myId, myProfileClone);
    }
}
