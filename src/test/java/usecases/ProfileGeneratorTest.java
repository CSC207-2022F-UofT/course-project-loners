package usecases;

import entities.Profile;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Test of the use case ProfileGenerator.
 */
public class ProfileGeneratorTest {

    /**
     * Test if generateProfile has created a profile correctly.
     */
    @Test
    public void testGenerateProfile(){
        String platform = "Instagram";
        String platformInfo = "social media";
        String email = "@email";
        String pw = "pw";
        String name = "name";
        String age = "18";
        String gender = "female";
        String postcode = "M5S 1A4";

        Profile p = ProfileGenerator.generateProfile(platform, platformInfo, email, pw, name, age, gender, postcode);
        assertEquals(p.getSocialMedia(), "Instagram: social media");
        assertEquals(p.getEmail(), email);
        assertEquals(p.getPassword(), pw);
        assertEquals(p.getName(), name);
        assertEquals(p.getAge(), 18);
        assertEquals(p.getGender(), gender);
        assertEquals(Arrays.toString(p.getLocation()), Arrays.toString(LocationConverter.codeToCoords(postcode)));
    }
}