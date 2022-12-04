package Controllers_Presenters;

import Use_Cases.EditProfile;
import Use_Cases.LocationConverter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

/*
 * EditProfileControl class
 * This class manages editing the profile.
 */
public class EditProfileControl{
    private BufferedImage image;
    public  EditProfileControl(){
        this.image = null;
    }
    EditProfile editProfile = new EditProfile();

    /**
     *
     * @param f of the JFrame
     * @return boolean value representing whether it successfully holds the image or not
     */
    public boolean withHoldImage(JFrame f){
        FileDialog fd = new FileDialog(f, "Open", FileDialog.LOAD);
        fd.setVisible(true);
        String filename = fd.getFile();
        try{
            this.image = ImageIO.read(new File(fd.getDirectory(), filename));
            return true;
        } catch (IOException error){
            JOptionPane.showMessageDialog(null, "Something went wrong when editing your profile!");
            return false;
        }
    }

    /**
     * This method sends the image the user chosen from their local folder to our save_images folder.
     * @param id of the use we want to send the image to
     */
    public void sendImage(int id){
        File myObj = new File(String.format("saved_images/%s.jpg", id));
        if(myObj.exists()) {
            boolean result= myObj.delete();
            if(result){
                try{
                    File outputfile = new File(String.format("saved_images/%s.jpg", id));
                    ImageIO.write(this.image, "jpg", outputfile);
                } catch(IOException error){
                    JOptionPane.showMessageDialog(null, "Something went wrong with uploading your image!");
                }
                }
        }
    }

    /**
     *
     * @param code representing the postal code
     * @return double[] representing the location in two doubles
     */
    public double[] convertLocation(String code){
        return LocationConverter.codeToCoords(code);
    }
    /**
     * This method sends the given updated profile data to the database
     @param info representing the updated data.
     */
    public boolean send(HashMap<String, Object> info, int id) {

        if(editProfile.edit(info)) {

            String likes;
            if (info.get("likes") == null) {
                likes = null;
            } else {
                likes = String.join(": ", (String) info.get("likes"));
            }
            String location = (info.get("location")).toString();

            String str_data = info.get("name") + ", " + info.get("email") + ", " + info.get("password") + ", " + info.get("age") + ", " +
                    info.get("bio") + ", " + info.get("gender") + ", " + info.get("orientation") + ", " +
                    location + ", " + info.get("hobbies") + ", " +
                    info.get("socialMedia") + ", " + likes + ", " + info.get("preferredAge") + ", " +
                    info.get("preferredGender") + ", " + info.get("preferredLocation");
            DataSendControl.getInstance().sendToId(id, str_data.split(", "));
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Something went wrong when editing your profile!");
            return false;
        }

    }

}