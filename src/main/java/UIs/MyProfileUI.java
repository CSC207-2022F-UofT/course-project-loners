package UIs;

import Controllers_Presenters.DataFetchControl;
import Entities.Preferences;
import Entities.Profile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

import static javax.imageio.ImageIO.read;

public class MyProfileUI{
    public static Profile profile = new Profile("Rick", 21, "male",
            "straight", null, null, "This is Rick", null, null);
    static Preferences preferences = new Preferences(20, "male",null, 5, 2);

    JFrame f = new JFrame();
    DataFetchControl data_manager = new DataFetchControl();
    JLabel greeting = new JLabel(String.format("Hi, %s! Welcome to your profile page!", profile.getName()));
    JLabel age = new JLabel(String.format("Your age: %s", profile.getAge()));
    JLabel bio = new JLabel(String.format("Your bio: \n %s", profile.getBio()));





    public MyProfileUI(){
        Object[] profile_data = data_manager.fetch_fromid(2);
        f.setLayout(new FlowLayout());
        f.add(greeting);
        try {
            BufferedImage image = ImageIO.read(new File(String.format("saved_images/%s.jpg", Integer.toString(2))));
            JLabel label = new JLabel("", new ImageIcon(image), 0);
            f.add(label);
        } catch (IOException e){
            System.out.println(e);
        }


        f.add(age);
        f.add(bio);

        f.pack();

        f.setLayout(null);
        f.setVisible(true);




    }
    public static void main(String[] args) {
        new MyProfileUI();
    }
}
