package tests.Controllers_Presenters;

import controllers.EditProfileControl;
import org.junit.Test;

import javax.swing.*;
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
        JFrame f = new JFrame();
        EditProfileControl editProfileControl = new EditProfileControl();
        f.setVisible(true);
        assert editProfileControl.withHoldImage(f);
    }

    @Test
    public void sendImage() {
        JFrame f = new JFrame();
        EditProfileControl editProfileControl = new EditProfileControl();
        f.setVisible(true);
        editProfileControl.withHoldImage(f);
        editProfileControl.sendImage(0);
    }

    @Test
    public void convertLocation() {
        EditProfileControl editProfileControl = new EditProfileControl();
        assert editProfileControl.convertLocation("M5E 2S2").equals("");
    }

    @Test
    public void send() {

    }
}
