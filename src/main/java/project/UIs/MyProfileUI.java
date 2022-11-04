package project.UIs;
import project.EditProfileControl;

import javax.swing.*;
import java.awt.*;

public class MyProfileUI {
    JFrame f;
    Color red  = new Color(255, 0, 0);
    public MyProfileUI (){
        f=new JFrame();

        JButton b=new JButton("click");
        b.setBounds(130,100,100, 40);
        f.add(b);
        f.setSize(400,500);
        f.setLayout(null);
        f.setVisible(true);
    }
    public static void main(String[] args) {
        new MyProfileUI();
        EditProfileControl control = new EditProfileControl("Rick");
        control.send();
    }
}

