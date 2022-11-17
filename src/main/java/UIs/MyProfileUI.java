package UIs;

import Use_Cases.ToBuffer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyProfileUI {
    JFrame f;
    Color red  = new Color(255, 0, 0);
    FileDialog fd = new FileDialog(f, "Open", FileDialog.LOAD);


    public MyProfileUI (){
        f=new JFrame();

        try{
            Image image = ImageIO.read(new File(fd.getFile()));
            ToBuffer toBuffer = new ToBuffer();
            BufferedImage bimage = toBuffer.toBufferedImage(image);
            bimage.toString();
        } catch (IOException e){
            System.out.println("Something went wrong");
        }



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
