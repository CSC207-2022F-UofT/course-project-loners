package usecases;

import org.junit.Test;

import java.util.HashMap;

/*
* This test case tests EditProfile. The edit method is tested here.
* The test is about whether we can successfully edit the profile or not depending on the authenticator
 */
public class EditProfileTest {
    @Test
    public void testEdit() {
        HashMap<String, Object> info = new HashMap<>();
        info.put("name", "Rick");
        info.put("email", "email@");
        info.put("password", "password");
        info.put("age", 32);
        info.put("bio", "I'm Rick");
        info.put("gender", "male");
        info.put("orientation", "straight");
        info.put("location", "M5S 2E2");
        info.put("hobbies", "play soccer");
        info.put("socialMedia", "instagram: rick");
        info.put("likes", "");
        info.put("preferredAge", 20);
        info.put("preferredGender", "female");
        info.put("preferredLocation", "M5S 2E2");
        assert EditProfile.getInstance().edit(info);
    }
}