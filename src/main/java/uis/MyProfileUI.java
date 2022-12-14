package uis;

import dataaccess.FetchData;
import controllers.UIController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*
 * MyProfileUI class the user's own profile page.
 */
public class MyProfileUI{
    /* Frame of the UI */
    JFrame f = new JFrame();
    /* id of the user  */
    int id;
    /* back button on MyProfileUI */
    JButton back = new JButton("Back to Menu");


    /*
     * Constructor for MyProfileUI.
     * The UI of the user associated to the given id will be presented.
     */
    public MyProfileUI(int id){
        this.id = id;
        // Define components that will be presented in this profile page
        Object[] profileData = FetchData.fetchFromID(id);
        JLabel greeting = new JLabel(String.format("Hi, %s! Welcome to your profile page!", ((Object[]) profileData[0])[1]));
        JLabel age = new JLabel(String.format("Your age: %s", ((Object[]) profileData[0])[4]));
        JLabel bio = new JLabel(String.format("Your bio: \n %s", ((Object[]) profileData[0])[5]));
        JButton toEditProfile = new JButton("Edit this Profile");
        JLabel socialMedia = new JLabel(String.format("Your social media choice is %s", ((Object[]) profileData[0])[10]));
        JLabel preferredAge = new JLabel(String.format("Your preferred age is %s", ((Object[]) profileData[0])[12]));
        JLabel preferredGender = new JLabel(String.format("Your preferred gender is %s", ((Object[]) profileData[0])[13]));
        JLabel preferredLocation = new JLabel(String.format("Your preferred location range is %s km", ((Object[]) profileData[0])[14]));

        f.setLayout(new GridLayout(5,1));
        f.add(greeting);
        // Load the profile image of the user.
        try {
            BufferedImage image = ImageIO.read(new File(String.format("saved_images/%s.jpg", id)));
            ImageIcon imageIcon = new ImageIcon(image); // load the image to a imageIcon
            Image image1 = imageIcon.getImage(); // transform it
            Image newImg = image1.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);
            JLabel label = new JLabel("", new ImageIcon(newImg), SwingConstants.CENTER);
            f.add(label);
        } catch (IOException e){
            return;
        }
//        f.setSize(400,600);
        UIController.setFrameSize(f);
        //Each component is added to the frame
        f.add(age);
        f.add(bio);
        f.add(socialMedia);
        f.add(preferredAge);
        f.add(preferredGender);
        f.add(preferredLocation);
        f.add(toEditProfile);
        f.add(back);

        f.pack();
        f.setVisible(true);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // terminate the program when you closed the window
        //The user will be redirected to EditProfileUI when toEditProfile button is clicked
        toEditProfile.addActionListener((e) -> {
                    f.setVisible(false);
                    new UIController(id).launchEditProfileUI();
                });
        //The user will be redirected to MainUI when the back button is clicked
        back.addActionListener((e) -> {
                f.setVisible(false);
                new UIController(id).launchMainUI();

        });
    }

    public void setVisible() {
        f.setVisible(true);
    }
}
