package tests.Use_Cases;

import Entities.Profile;
import usecases.ObjectListToProfile;
import org.junit.Assert;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Testing objectListToProfile
 */
public class ObjectListToProfileTest {
    /**
     * Test if objectListToProfile returns a proper profile
     */
    @Test
    public void objectListToProfileTest(){
        //7, Name8, email8, password, 19, bio, male, orientation, 20.0: 30.0001, hobbies, socialMedia, 3: , 19, male, 5.0
        Profile myProf = new Profile("socialMedia", "email8", "password", "Name8", 19, "male", new double[]{20.0, 30.0001});
        myProf.setBio("bio");
        List<String> hob = new ArrayList<>();
        hob.add("hobbies");
        myProf.setHobbies(hob);
        List<String> likes = new ArrayList<>();
        likes.add("3");
        myProf.setLikes(likes);
        myProf.setOrientation("orientation");
        try {
            BufferedImage image = ImageIO.read(new File(String.format("saved_images/%s.jpg", 7)));
            myProf.setImage(image);
            Object[] theObjectList = {"7", "Name8", "email8", "password", "19", "bio", "male", "orientation", "20.0: 30.0001", "hobbies", "socialMedia", "3: ", "19", "male", "5.0"};
            Object[] objListWithImage = {theObjectList, image};
            Profile expected = ObjectListToProfile.returnObjListAsProfile(objListWithImage);
            // Compare equality with each attribute
            Assert.assertEquals(expected.getName(), myProf.getName());
            Assert.assertEquals(expected.getBio(), myProf.getBio());
            Assert.assertEquals(expected.getAge(), myProf.getAge());
            Assert.assertEquals(expected.getEmail(), myProf.getEmail());
            Assert.assertEquals(expected.getPassword(), myProf.getPassword());
            Assert.assertEquals(expected.getGender(), myProf.getGender());
            Assert.assertEquals(expected.getHobbies(), myProf.getHobbies());
            Assert.assertEquals(expected.getLikes(), myProf.getLikes());
            Assert.assertArrayEquals(new double[][]{expected.getLocation()}, new double[][]{myProf.getLocation()});
            Assert.assertEquals(expected.getOrientation(), myProf.getOrientation());
            Assert.assertEquals(expected.getSocialMedia(), myProf.getSocialMedia());
            System.out.println("This worked");
        } catch (IOException e) {
            System.out.println("This did not worked");
            throw new RuntimeException(e);
        }
    }
}
