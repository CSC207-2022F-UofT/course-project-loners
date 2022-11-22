package UIs;

import Controllers_Presenters.DataFetchControl;
import Controllers_Presenters.UIController;
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
    static Preferences preferences = new Preferences(20, "male", 5, 2);

    JFrame f = new JFrame();
    //BoxLayout layout = new BoxLayout(f,BoxLayout.PAGE_AXIS);
    int id;
    JButton back = new JButton("Back to Menu");


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
        f.add(back);

        f.pack();

        f.setLayout(new FlowLayout());
        f.setVisible(true);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // this line will end the execution when you closed the window
        UIController.makeFrameFullSize(f);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                new UIController(id).launchMainUI();
            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent e){
        new EditProfileUI();
    }

    public void setVisible(boolean b) {
        f.setVisible(true);
    }

    public static void main(String[] args) {new MyProfileUI(2);}
}
