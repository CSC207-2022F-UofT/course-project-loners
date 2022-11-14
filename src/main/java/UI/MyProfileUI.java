package UI;

import javax.swing.*;
import java.awt.*;

public class MyProfileUI {
    JFrame f;
    Color red  = new Color(255, 0, 0);
    public MyProfileUI (){
        f=new JFrame();

        JButton b=new JButton("click");
        JTextField textField = new JTextField(20);
        String text = textField.getText();
        b.setBounds(130,100,100, 40);
        f.add(b);
        f.add(textField);
        f.setSize(400,500);
        f.setLayout(null);
        f.setVisible(true);
        System.out.println(text);
    }
    public static void main(String[] args) {
        new MyProfileUI();
    }
}

