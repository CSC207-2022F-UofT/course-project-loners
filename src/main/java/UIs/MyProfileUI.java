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

public class MyProfileUI{
    /*
    * MyProfileUI class the user's own profile page.
     */
    public static Profile profile = new Profile("Rick", 21, "male",
            "straight", null, null, "This is Rick", null, null);
    static Preferences preferences = new Preferences(20, "male", 5, 2);
    JFrame f = new JFrame();
    int id;
    JButton back = new JButton("Back to Menu");


    public MyProfileUI(int id){
        /*
        * Constructor for MyProfileUI.
        * The UI of the user associated to the given id will be presented.
         */
        this.id = id;
        // Define components that will be presented in this profile page
        DataFetchControl data_manager = new DataFetchControl();
        Object[] profile_data = data_manager.fetchFromId(id);
        JLabel greeting = new JLabel(String.format("Hi, %s! Welcome to your profile page!", ((Object[]) profile_data[0])[1]));
        JLabel age = new JLabel(String.format("Your age: %s", ((Object[]) profile_data[0])[4]));
        JLabel bio = new JLabel(String.format("Your bio: \n %s", ((Object[]) profile_data[0])[5]));
        JButton toEditProfile = new JButton("Edit this Profile");
        JLabel socialMedia = new JLabel(String.format("Your social media choice is %s", ((Object[]) profile_data[0])[10]));

        f.setLayout(new GridLayout(5,1));
        f.add(greeting);
        // Load the profile image of the user.
        try {
            BufferedImage image = ImageIO.read(new File(String.format("saved_images/%s.jpg", Integer.toString(id))));
            ImageIcon imageIcon = new ImageIcon(image); // load the image to a imageIcon
            Image image_1 = imageIcon.getImage(); // transform it
            Image new_img = image_1.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);
            JLabel label = new JLabel("", new ImageIcon(new_img), 0);
            f.add(label);
        } catch (IOException e){
            System.out.println(e);
        }
        f.setSize(400,600);
        f.add(age);
        f.add(bio);
        f.add(socialMedia);
        f.add(toEditProfile);
        f.add(back);

        f.pack();
        f.setVisible(true);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // terminate the program when you closed the window
        //The user will be redirected to EditProfileUI when toEditProfile button is clicked
        toEditProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                new UIController(id).launchEditProfileUI();
            }
        });
        //The user will be redirected to MainUI when the back button is clicked
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                new UIController(id).launchMainUI();
            }
        });
    }

    public void setVisible(boolean b) {
        f.setVisible(true);
    }
}
