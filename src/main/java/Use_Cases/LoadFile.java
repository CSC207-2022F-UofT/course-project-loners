package Use_Cases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadFile implements ActionListener {
    JFrame f;
    JButton b;
    public LoadFile(JFrame f,JButton b){
        this.f = f;
        this.b = b;

    }
    public void setLoader(){
        this.f.add(b);
        this.b.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        FileDialog fd = new FileDialog(this.f, "Open", FileDialog.LOAD);
        fd.setVisible(true);
        String filename = fd.getFile();
        if (filename == null)
            System.out.println("You cancelled the choice");
        else
            System.out.println("You chose " + filename);

    }
}
