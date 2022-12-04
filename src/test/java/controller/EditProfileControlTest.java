package controller;

import controllers.EditProfileControl;
import org.junit.Test;

import java.util.HashMap;

public class EditProfileControlTest {
    @Test
    public void test_send(){
        HashMap<String, Object> info = new HashMap<>();
        info.put("name", "Name");
        info.put("email", "email@");
        info.put("password", "null");
        info.put("age", "20");
        info.put("bio", "bio");
        info.put("gender", "male");
        info.put("orientation", "straight");
        info.put("location", "20.0: 10.0");
        info.put("hobbies", "hobbies");
        info.put("socialMedia", "social media");
        info.put("likes", "null");
        info.put("preferredAge", "10");
        info.put("preferredGender", "female");
        info.put("preferredLocation", "2.0");
        EditProfileControl editProfileControl = new EditProfileControl();
        boolean expected = editProfileControl.send(info,0);
        assert expected;
    }

    @Test
    public void withHoldImage() {
    }

    @Test
    public void sendImage() {
    }

    @Test
    public void convertLocation() {
    }

    @Test
    public void send() {

    }
}
