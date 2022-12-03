package Use_Cases;

import Entities.Profile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class is to convert a profile of type Object[] to profile of type Profile
 */
public class ObjectListToProfile {
    //converts Object[] to Profile

    /**
     * Returns profile as a Profile entity
     * @param objList The profile as an Object[]
     * @return the profile as type Profile
     */
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
        double[] myLocation = doubleStringToArray((String) data[8]);
        List<String> myHobbies = parseColonSeperatedString((String)data[9]);
        String mySocial = (String) data[10];
        List<String> myLikes = parseColonSeperatedString((String)data[11]);

        return new Profile(myName, myAge, myGender, myOrientation, myLocation,
                myImage, myBio, myHobbies, mySocial, myLikes, myEmail, myPass);

    }

    /**
     * Converts a string of doubles to an array of doubles
     * @param og a string of a double: double
     * @return the double[] of these two numbers
     */
    private static double[] doubleStringToArray(String og){
        if (og.contains("null")){
            return null;
        }

        String[] ogNewArray = og.split(": ");
        double first = Double.parseDouble(ogNewArray[0]);
        double second = Double.parseDouble(ogNewArray[1]);

        return new double[]{first, second};
    }

    /**
     * Converts a string to a list of separate strings delimited by a colon
     * @param og our string of doubles seperated by a colon
     * @return a list of these two strings of doubles
     */
    private static List<String> parseColonSeperatedString(String og){
        if (og.contains("null")){
            return null;
        }
        List<String> myFinalList = new ArrayList<>();
        String[] myOg = og.split(": ");
        Collections.addAll(myFinalList, myOg);
        return myFinalList;
    }
}
