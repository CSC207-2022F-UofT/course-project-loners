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
        double[] myLocation = doubleStringToArray((String) data[8]);
        List<String> myHobbies = parseColonSeperatedString((String)data[9]);
        String mySocial = (String) data[10];
        List<String> myLikes = parseColonSeperatedString((String)data[11]);

        Profile myProfile = new Profile(myName, myAge, myGender, myOrientation, myLocation,
                myImage, myBio, myHobbies, mySocial, myLikes, myEmail, myPass);

        return myProfile;

    }

    private static double[] doubleStringToArray(String og){
        if (og.contains("null")){
            return null;
        }
        String[] ogNewArray = og.split(": ");
        double first = Double.parseDouble(ogNewArray[0]);
        double second = Double.parseDouble(ogNewArray[1]);

        return new double[]{first, second};
    }

    public static List<String> parseColonSeperatedString(String og){
        if (og.contains("null")){
            return null;
        }
        List<String> myFinalList = new ArrayList<String>();
        String[] myOg = og.split(": ");
        for (String s : myOg){
            myFinalList.add(s);
        }
        return myFinalList;
    }

    public static void main(String[] args){
        doubleStringToArray("43.1245123: 12.12342623");
    }

}
