package UIs;

import Use_Cases.ToBuffer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyProfileUI implements ActionListener {
    JFrame f;
    JButton b=new JButton("Upload Image");




    public MyProfileUI (){
        f=new JFrame();
        /*
        try{
            System.out.println("Here");
            Image image = ImageIO.read(new File(fd.getFile()));
            ToBuffer toBuffer = new ToBuffer();
            BufferedImage bimage = toBuffer.toBufferedImage(image);
            bimage.toString();
        } catch (IOException e){
            System.out.println("Something went wrong");
        }

         */

        b.setBounds(130,100,100, 40);
        f.add(b);

        f.setSize(400,500);
        f.setLayout(null);
        f.setVisible(true);
        b.addActionListener(this);



    }
    @Override
    public void actionPerformed(ActionEvent e) {
        FileDialog fd = new FileDialog(f, "Open", FileDialog.LOAD);
        fd.setVisible(true);
        String filename = fd.getFile();
        if (filename == null)
            System.out.println("You cancelled the choice");
        else
            System.out.println("You chose " + filename);

    }
    public static void main(String[] args) {
        new MyProfileUI();
    }
}
