package Use_Cases;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoadFile implements ActionListener {
    private JFrame f;
    private JButton b;
    public Image image;
    public int id;
    public LoadFile(JFrame f,JButton b, int id){
        this.f = f;
        this.b = b;
        this.image = null;
        this.id = id;
    }
    public void setLoader(){
        this.f.add(b);
        this.b.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // When the button is clicked, the image will be loaded from saved_images folder.
        FileDialog fd = new FileDialog(this.f, "Open", FileDialog.LOAD);
        fd.setVisible(true);
        String filename = fd.getFile();
        try{
            BufferedImage image = ImageIO.read(new File(fd.getDirectory(), filename));
            this.image = image;
            File myObj = new File(String.format("saved_images/%s.jpg", this.id));
            if(myObj.exists()){
                boolean deletion = myObj.delete();
                if(deletion){
                    System.out.println("Success!");
                } else {
                    System.out.println("Something went wrong. Try again");
                }
            } else {
                System.out.println("No default image");
            }
        } catch (IOException error){
            System.out.println("Error!");
            System.out.println(error);
        }

    }
}