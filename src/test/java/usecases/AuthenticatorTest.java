package usecases;

import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

/**
 * Test the Authenticator
 */
public class AuthenticatorTest {
    /**
     * Check a working postal code in the location converter
     * Keep in mind that the precondition must be met
     */

    @Test(timeout = 50)
    public void isValidEmailTest(){
        String validEmail = "1234@gmail.com";
        String invalidEmail = "1234";
        assertTrue(Authenticator.isValidEmail(validEmail));
        assertFalse(Authenticator.isValidEmail(invalidEmail));
    }

    @Test(timeout = 50)
    public void isValidNameTest(){
        String validName = "John";
        String invalidName = "!";
        String invalidName1 = "12";
        assertTrue(Authenticator.isValidName(validName));
        assertFalse(Authenticator.isValidName(invalidName));
        assertFalse(Authenticator.isValidName(invalidName1));
    }

    @Test(timeout = 50)
    public void isValidAgeTest(){
        int validAge = 30;
        int invalidAge = 155;
        assertTrue(Authenticator.isValidAge(validAge));
        assertFalse(Authenticator.isValidAge(invalidAge));
    }

    @Test(timeout = 50)
    public void isValidAddressTest(){
        String validAddress =  "L9M0E5";
        String invalidAddress = "1M12M2";

        assertTrue(Authenticator.isValidAddress(validAddress));
        assertFalse(Authenticator.isValidAddress(invalidAddress));
    }

    @Test
    public void isValidBioTest(){
        String validBio = "HiIamJohn";
        String invalidBio = "121312131ye2131121312313141514131515132131108102381289312931873787591dd91912989d1991991" +
                "919d1999d91d91bb121b2db1bd19b1hd91989182981298192891d1981928d8f81f89189f89f88d989s98s8919889d8f18f8" +
                "f8819f89f88d881d988d189f98f89f891f898f189f8f18f89f89f8f8f981f898f898f89f89f89f89f8f891891d89wd91d398" +
                "38918d189d3189d81989189d1389d89189d3198d3198d91686d176167d61dv7yv8b8618dh18h19b18b339d1b91bd91bd317" +
                "9b3173bd91bd91b97db19b917db19bd971bd97b139dh23";

        assertTrue(Authenticator.isValidBio(validBio));
        assertFalse(Authenticator.isValidBio(invalidBio));
    }

    @Test(timeout = 50)
    public void isValidHobbiesTest(){
        ArrayList<String> validHobbiesList = new ArrayList<>();
        ArrayList<String> invalidHobbiesList = new ArrayList<>();
        String validHobbies = "playing soccer, watching TV, and partying";
        String invalidHobbies = "playing soccer, watching TV, and partying" +
                "playing soccer, watching TV, and partying" +
                "playing soccer, watching TV, and partying" +
                "playing soccer, watching TV, and partying" +
                "playing soccer, watching TV, and partying" +
                "playing soccer, watching TV, and partying" +
                "playing soccer, watching TV, and partying" +
                "playing soccer, watching TV, and partying" +
                "playing soccer, watching TV, and partying" +
                "playing soccer, watching TV, and partying" +
                "playing soccer, watching TV, and partying" +
                "playing soccer, watching TV, and partying" +
                "playing soccer, watching TV, and partying" +
                "playing soccer, watching TV, and partying" +
                "playing soccer, watching TV, and partying" +
                "playing soccer, watching TV, and partying";
        validHobbiesList.add(validHobbies);
        invalidHobbiesList.add(invalidHobbies);
        assertTrue(Authenticator.isValidHobbies(validHobbiesList));
        assertFalse(Authenticator.isValidHobbies(invalidHobbiesList));
    }

    @Test(timeout = 50)
    public void EmailExistTest(){
        String existingEmail = "email2";
        String nonExistingEmail = "email12211";

        assertTrue(Authenticator.emailExists(existingEmail));
        assertFalse(Authenticator.emailExists(nonExistingEmail));
    }

    @Test(timeout = 50)
    public void EmailMatchPasswordTest(){
        String existingEmail = "email2";
        String nonExistingEmail = "email12211";
        String existingPassword = "password";
        String nonExistingPassword = "null";

        assertTrue(Authenticator.emailMatchPassword(existingEmail, existingPassword));
        assertFalse(Authenticator.emailMatchPassword(existingEmail, nonExistingPassword));
        assertFalse(Authenticator.emailMatchPassword(nonExistingEmail, existingPassword));
        assertFalse(Authenticator.emailMatchPassword(nonExistingEmail, nonExistingPassword));
    }
}
