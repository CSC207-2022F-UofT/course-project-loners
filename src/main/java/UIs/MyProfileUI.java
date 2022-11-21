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
    static Preferences preferences = new Preferences(20, "male",null, 5, 2);

    JFrame f = new JFrame();
    //BoxLayout layout = new BoxLayout(f,BoxLayout.PAGE_AXIS);
    int id;
    public UIController uiController;
    public String email;
    public DataFetchControl dataFetchControl = new DataFetchControl();






    public MyProfileUI(String email){
        this.email = email;
        this.id = dataFetchControl.fetch_id_fromEmail(email);
        uiController = new UIController(email);
        DataFetchControl data_manager = new DataFetchControl();
        Object[] profile_data = data_manager.fetch_fromid(id);
        JLabel greeting = new JLabel(String.format("Hi, %s! Welcome to your profile page!", ((Object[]) profile_data[0])[1]));
        JLabel age = new JLabel(String.format("Your age: %s", ((Object[]) profile_data[0])[4]));
        JLabel bio = new JLabel(String.format("Your bio: \n %s", ((Object[]) profile_data[0])[5]));
        JButton toEditProfile = new JButton("Edit this Profile");
        JButton backToMain = new JButton("Back to Main Page");
        f.setLayout(new FlowLayout());
        f.add(greeting);
        try {
            BufferedImage image = ImageIO.read(new File(String.format("saved_images/%s.jpg", Integer.toString(id))));
            ImageIcon imageIcon = new ImageIcon(image); // load the image to a imageIcon
            Image image_1 = imageIcon.getImage(); // transform it
            Image new_img = image_1.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            JLabel label = new JLabel("", new ImageIcon(new_img), 0);
            f.add(label);
        } catch (IOException e){
            System.out.println(e);
        }

        toEditProfile.addActionListener(this);
        f.add(age);
        f.add(bio);
        f.add(toEditProfile);
        f.add(backToMain);



        f.pack();

        f.setLayout(new FlowLayout());
        f.setVisible(true);

        backToMain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uiController.backToMainUI();
                f.setVisible(false);
            }

        });




    }

    @Override
    public void actionPerformed(ActionEvent e){
        uiController.launchEditProfileUI();
        f.setVisible(false);
    }

    public void setVisible(boolean b) {
        f.setVisible(true);
    }
}
