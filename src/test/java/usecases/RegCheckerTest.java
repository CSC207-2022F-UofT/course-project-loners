package usecases;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing RegCheckerTest
 */
public class RegCheckerTest {

    /**
     * Implicitly test checkIfMissing, checkValidate and checkDuplicate as they are private classes.
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

        // Implicitly test checkIfMissing:
        // test when one of inputs is missing
        String emptyEmail = "";
        RegChecker checkerMiss = new RegChecker(platformInfo, emptyEmail, pw, name, age ,postcode, true);
        assertFalse(checkerMiss.pass);
        assertEquals(checkerMiss.diagnose, "missing input(s), \n");
        // test when image upload platform did not receive image
        RegChecker checkerNoPic = new RegChecker(platformInfo, email, pw, name, age ,postcode, false);
        assertFalse(checkerNoPic.pass);
        assertEquals(checkerNoPic.diagnose, "you did not select any image to upload, \n");

        // Implicitly test checkValidate:
        // test when email is not valid
        String nonValidEmail = "email";
        RegChecker checkerNonValidEmail = new RegChecker(platformInfo, nonValidEmail, pw, name, age ,postcode, true);
        assertFalse(checkerNonValidEmail.pass);
        assertEquals(checkerNonValidEmail.diagnose, "email is not valid; \n");
        // test when postcode is not valid--case 1: without whitespace
        String postcodeNoSpace = "M5S1A4";
        RegChecker checkerCodeNoSpace = new RegChecker(platformInfo, email, pw, name, age ,postcodeNoSpace, true);
        assertFalse(checkerCodeNoSpace.pass);
        assertEquals(checkerCodeNoSpace.diagnose, "postal code is not valid, remember to enter it with a whitespace like the example provided;\n");
        // test when postcode is not valid--case 2: not Canadian postal code
        String postcodeNotCanadian = "123 456";
        RegChecker checkerCodeNotCanadian = new RegChecker(platformInfo, email, pw, name, age ,postcodeNotCanadian, true);
        assertFalse(checkerCodeNotCanadian.pass);
        assertEquals(checkerCodeNotCanadian.diagnose, "postal code is not a valid Canadian postal code;\n");
        // test when age is valid--case 1: not in valid range
        String ageRangeFail = "200";
        RegChecker checkerAgeRangeFail = new RegChecker(platformInfo, email, pw, name, ageRangeFail ,postcode, true);
        assertFalse(checkerAgeRangeFail.pass);
        assertEquals(checkerAgeRangeFail.diagnose, "age is not in a valid range; \n");
        // test when age is valid--case 1: not a number
        String ageNotNum = "a";
        RegChecker checkerAgeNotNum = new RegChecker(platformInfo, email, pw, name, ageNotNum ,postcode, true);
        assertFalse(checkerAgeNotNum.pass);
        assertEquals(checkerAgeNotNum.diagnose, "age is not a valid input; \n");

        // Implicitly test checkDuplicate:
        // TODO: virtual database???
    }
}