package Controllers_Presenters;

import Use_Cases.EditProfile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class EditProfileControl{
    /*
    * EditProfileControl class
    * This class manages editing the profile.
     */
    public String name;
    public DataSendControl dataSend = new DataSendControl();
    private BufferedImage image;
    public  EditProfileControl(){
        this.image = null;
    }
    EditProfile editProfile = new EditProfile();
    public boolean withHoldImage(JFrame f){
        FileDialog fd = new FileDialog(f, "Open", FileDialog.LOAD);
        fd.setVisible(true);
        String filename = fd.getFile();
        try{
            BufferedImage image = ImageIO.read(new File(fd.getDirectory(), filename));
            this.image = image;
            return true;
        } catch (IOException error){
            JOptionPane.showMessageDialog(null, "Something went wrong when editing your profile!");
            return false;
        }
    }
    public boolean sendImage(int id){
        File myObj = new File(String.format("saved_images/%s.jpg", id));
        if(myObj.exists()){
            boolean deletion = myObj.delete();
            if(!deletion){
                return false;
            }
        } else {
            return false;
        }
        try{
            File outputfile = new File(String.format("saved_images/%s.jpg", id));
            ImageIO.write(this.image, "jpg", outputfile);
            return true;
        } catch(IOException error){
            return false;
        }
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

            String str_data = info.get("name") + ", " + info.get("email") + ", " + info.get("password") + ", " + info.get("age") + ", " +
                    info.get("bio") + ", " + info.get("gender") + ", " + info.get("orientation") + ", " +
                    info.get("location") + ", " + info.get("hobbies") + ", " +
                    info.get("socialMedia") + ", " + likes + ", " + info.get("preferredAge") + ", " +
                    info.get("preferredGender") + ", " + info.get("preferredLocation");
            dataSend.send_toid(id, str_data.split(", "));
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Something went wrong when editing your profile!");
            return false;
        }

    }

}