package controllers;

import org.junit.Test;

import java.util.HashMap;

/*
 * Test case for EditProfileControl. We did not include withHoldImage and sendImage methods
 * because these two methods involve interaction with UIs
 */
public class EditProfileControlTest {

    @Test
    public void send() {
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
        assert EditProfileControl.getInstance().send(info,0);

    }
}