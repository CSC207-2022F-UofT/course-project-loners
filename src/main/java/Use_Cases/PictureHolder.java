package Use_Cases;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


/**
 * Create a space for temporarily saving the picture that user uploaded.
 */
public class PictureHolder implements ActionListener {
    private final JFrame frame;
    private final JButton button;
    public Image image;
    public boolean saved; // true if the picture has saved into the folder "saved_images"

    /**
     * The constructor of PictureHolder,
     * initializing frame and button from RegUI (frame and pic_button), image to be null, and saved to be false.
     * @param frame a frame
     * @param button a button
     */
    public PictureHolder(JFrame frame, JButton button){
        this.frame = frame;
        this.button = button;
        this.image = null;
        this.saved = false;
    }

    /**
     * add button to frame and set the button reaction.
     */
    public void setLoader(){
        this.frame.add(button);
        this.button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // a window will show to the user to select the picture that they want to upload from their computer
        FileDialog selectFile = new FileDialog(this.frame, "Open", FileDialog.LOAD);
        selectFile.setVisible(true);
        // get the directory and name of the selected file
        String fileDirectory = selectFile.getDirectory();
        String fileName = selectFile.getFile();
        try{
            // save the image to the folder "saved_images"
            this.image = ImageIO.read(new File(fileDirectory, fileName));
            this.saved = true;
        } catch (IOException error){
            System.out.println("error when running PictureHolder");
        }
    }
}
