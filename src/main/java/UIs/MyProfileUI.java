package UIs;

import Controllers_Presenters.DataFetchControl;
import Entities.Preferences;
import Entities.Profile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

import static javax.imageio.ImageIO.read;

public class MyProfileUI implements ActionListener {
    public static Profile profile = new Profile("Rick", 21, "male",
            "straight", null, null, "This is Rick", null, null);
    static Preferences preferences = new Preferences(20, "male",null, 5, 2);

    JFrame f = new JFrame();
    int id;






    public MyProfileUI(int id){
        this.id = id;
        DataFetchControl data_manager = new DataFetchControl();
        Object[] profile_data = data_manager.fetch_fromid(id);
        JLabel greeting = new JLabel(String.format("Hi, %s! Welcome to your profile page!", ((Object[]) profile_data[0])[1]));
        JLabel age = new JLabel(String.format("Your age: %s", ((Object[]) profile_data[0])[4]));
        JLabel bio = new JLabel(String.format("Your bio: \n %s", ((Object[]) profile_data[0])[5]));
        JButton toEditProfile = new JButton("Edit this Profile");
        f.setLayout(new FlowLayout());
        f.add(greeting);
        try {
            BufferedImage image = ImageIO.read(new File(String.format("saved_images/%s.jpg", Integer.toString(id))));
            JLabel label = new JLabel("", new ImageIcon(image), 0);
            f.add(label);
        } catch (IOException e){
            System.out.println(e);
        }

        toEditProfile.addActionListener(this);
        f.add(age);
        f.add(bio);
        f.add(toEditProfile);

        f.pack();

        f.setLayout(null);
        f.setVisible(true);




    }
    @Override
    public void actionPerformed(ActionEvent e){
        new EditProfileUI();
    }
    public static void main(String[] args) {
        new MyProfileUI(2);
    }
}
