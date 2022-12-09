package usecases;

import dataaccess.FetchData;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testing RegCheckerTest
 */
public class RegCheckerTest {
    /**
     * The platform information of the user, in a correct format.
     */
    String platformInfo;
    /**
     * The email of the user, in a correct format.
     */
    String email;
    /**
     * The password of the user, in a correct format.
     */
    String pw;
    /**
     * The name of the user, in a correct format.
     */
    String name;
    /**
     * The age of the user, in a correct format.
     */
    String age;
    /**
     * The postcode of the user, in a correct format.
     */
    String postcode;

    @Before
    public void setUp(){
        // Define correct inputs
        platformInfo = "social media";
        email = "@email";
        pw = "pw";
        name = "name";
        age = "18";
        postcode = "M5S 1A4";
    }

    /**
     * Implicitly test checkIfMissing, checkValidate and checkDuplicate as they are private classes.
     * Test with all correct user inputs.
     */
    @Test
    public void testRegChecker(){
        // Test with all correct user inputs
        String platformInfo = "social media";
        String email = "@email";
        String pw = "pw";
        String name = "name";
        String age = "18";
        String postcode = "M5S 1A4";
        RegChecker checkerAllGood = new RegChecker(platformInfo, email, pw, name, age ,postcode, true);
        assertTrue(checkerAllGood.pass);
        assertEquals(checkerAllGood.diagnose,"");
    }

    /**
     * Implicitly test checkIfMissing using the constructor, as it is a private class.
     */
    @Test
    public void testCheckIfMissing(){
        // test when one of inputs is missing
        String emptyEmail = "";
        RegChecker checkerMiss = new RegChecker(platformInfo, emptyEmail, pw, name, age ,postcode, true);
        assertFalse(checkerMiss.pass);
        assertEquals(checkerMiss.diagnose, "Missing input(s). \n");
        // test when image upload platform did not receive image
        RegChecker checkerNoPic = new RegChecker(platformInfo, email, pw, name, age ,postcode, false);
        assertFalse(checkerNoPic.pass);
        assertEquals(checkerNoPic.diagnose, "You did not select an image to upload. \n");
    }

    /**
     * Implicitly test checkValidate using the constructor, as it is a private class.
     */
    @Test
    public void testCheckValidate(){
        // test when email is not valid
        String nonValidEmail = "email";
        RegChecker checkerNonValidEmail = new RegChecker(platformInfo, nonValidEmail, pw, name, age ,postcode, true);
        assertFalse(checkerNonValidEmail.pass);
        assertEquals(checkerNonValidEmail.diagnose, "- Email is not valid. \n");
        // test when postcode is not valid--case 1: without whitespace
        String postcodeNoSpace = "M5S1A4";
        RegChecker checkerCodeNoSpace = new RegChecker(platformInfo, email, pw, name, age ,postcodeNoSpace, true);
        assertFalse(checkerCodeNoSpace.pass);
        assertEquals(checkerCodeNoSpace.diagnose, "- Postal code is not valid (remember to enter it with a whitespace like the example provided)\n");
        // test when postcode is not valid--case 2: not Canadian postal code
        String postcodeNotCanadian = "123 456";
        RegChecker checkerCodeNotCanadian = new RegChecker(platformInfo, email, pw, name, age ,postcodeNotCanadian, true);
        assertFalse(checkerCodeNotCanadian.pass);
        assertEquals(checkerCodeNotCanadian.diagnose, "- Postal code is not a Canadian postal code\n");
        // test when age is valid--case 1: not in valid range
        String ageRangeFail = "200";
        RegChecker checkerAgeRangeFail = new RegChecker(platformInfo, email, pw, name, ageRangeFail ,postcode, true);
        assertFalse(checkerAgeRangeFail.pass);
        assertEquals(checkerAgeRangeFail.diagnose, "- Age is not in a valid range \n");
        // test when age is valid--case 1: not a number
        String ageNotNum = "a";
        RegChecker checkerAgeNotNum = new RegChecker(platformInfo, email, pw, name, ageNotNum ,postcode, true);
        assertFalse(checkerAgeNotNum.pass);
        assertEquals(checkerAgeNotNum.diagnose, "- Age is not a number \n");
    }

    /**
     * Implicitly test checkDuplicate using the constructor, as it is a private class.
     */
    @Test
    public void testCheckDuplicate(){
        Object[] data0 = (Object[]) FetchData.fetchFromID(0)[0];
        String newEmail = (String) data0[2];
        RegChecker checkerDup = new RegChecker(platformInfo, newEmail, pw, name, age ,postcode, true);
        assertFalse(checkerDup.pass);
        assertEquals(checkerDup.diagnose, "- This email has registered \n");
    }
}