package Use_Cases;

import Entities.Profile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ObjectListToProfile {
    //converts Object[] to Profile

    public static Profile returnObjListAsProfile(Object[] objList){
        //Precondition: we are assuming that our objList is of length 2 with the first item being the list we want to
        // turn into a profile. The second item is the image for the profile.

        Object[] data = (Object[]) objList[0];
        BufferedImage myImage = (BufferedImage) objList[1];

        String myName = (String) data[1];
        String myEmail = (String) data[2];
        String myPass = (String) data[3];
        int myAge = Integer.parseInt((String) data[4]);
        String myBio = (String) data[5];
        String myGender = (String) data[6];
        String myOrientation = (String) data[7];
        //Change this! I don't know the format of location in the database yet!
        double[] myLocation = doubleStringToArray((String) data[8]);
        //Change this! I don't know the format of hobbies in the database yet!
        List<String> myHobbies = new ArrayList<>();
        String mySocial = (String) data[10];
        //Change this! I don't know the format of likes in the database yet!
        List<String> myLikes = new ArrayList<>();

        Profile myProfile = new Profile(myName, myAge, myGender, myOrientation, myLocation,
                myImage, myBio, myHobbies, mySocial, myLikes, myEmail, myPass);

        return myProfile;

    }

    private static double[] doubleStringToArray(String og){
        String ogNew = og.substring(1, og.length()-1);
        String[] ogNewArray = ogNew.split(", ");
        double first = Double.parseDouble(ogNewArray[0]);
        double second = Double.parseDouble(ogNewArray[1]);

        return new double[]{first, second};
    }

}
