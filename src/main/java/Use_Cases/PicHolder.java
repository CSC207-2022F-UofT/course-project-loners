package Use_Cases;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PicHolder implements ActionListener {
    private JFrame f;
    private JButton b;
    public Image image;
    public boolean saved;

    public PicHolder(JFrame f, JButton b){
        this.f = f;
        this.b = b;
        this.image = null;
        this.saved = false;
    }

    public void setLoader(){
        this.f.add(b);
        this.b.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        FileDialog fd = new FileDialog(this.f, "Open", FileDialog.LOAD);
        fd.setVisible(true);
        System.out.println(fd.getDirectory());
        String filename = fd.getFile();
        try{
            this.image = ImageIO.read(new File(fd.getDirectory(), filename));
            this.saved = true;
        } catch (IOException error){
            System.out.println("error!");
        }

    }
}
