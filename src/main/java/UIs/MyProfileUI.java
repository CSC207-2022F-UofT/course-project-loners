package UIs;

import Controllers_Presenters.DataFetchControl;
import Entities.Preferences;
import Entities.Profile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

import static javax.imageio.ImageIO.read;

public class MyProfileUI{
    JFrame f = new JFrame();
    JButton b=new JButton("Upload Image");
    DataFetchControl data_manager = new DataFetchControl();

    public static Profile profile = new Profile("Rick", 21, "male",
            "straight", null, null, "This is Rick", null, null);
    static Preferences preferences = new Preferences(20, "male",null, 5, 2);

    public MyProfileUI(){
        Object[] profile_data = data_manager.fetch_fromid(2);
        try {
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(Base64.getDecoder().decode((String)profile_data[9])));
            JLabel label = new JLabel("", new ImageIcon(image), 0);
            f.add(label);
        } catch (IOException e){
            System.out.println(e);
        }

        System.out.println(profile_data[9]);



        f.setLayout(new FlowLayout());


        b.setBounds(130,100,100, 40);
        f.add(b);

        //f.setSize(400,500);
        f.pack();
        f.setLayout(null);
        f.setVisible(true);




    }
    public static void main(String[] args) {
        DataFetchControl data_manager = new DataFetchControl();
        Object[] profile_data = data_manager.fetch_fromid(2);
        System.out.println(profile_data[9]);
        new MyProfileUI();
    }
}
