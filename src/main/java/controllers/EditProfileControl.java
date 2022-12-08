package controllers;

import dataaccess.SendData; // implements a Use Case interface
import usecases.EditProfile;
import usecases.LocationConverter;

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

    /** The image uploaded by the user stored in an instance */
    private BufferedImage image;
    /** Default image is null */
    public  EditProfileControl(){
        this.image = null;
    }
    /** Using singleton Design Pattern */

    public static final EditProfileControl e = new EditProfileControl();
    /** Get instance of this class without defining it */
    public static EditProfileControl getInstance(){
        return e;
    }

    /**
     * This method enables users to upload images and withhold that image
     * @param f frame where the image uploading takes place
     * @return boolean value representing whether the user has successfully uploaded an image or not.
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
     * This method saves the uploaded image to saved_images folder
     * @param id of the user
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
     * This method sends the given updated profile data to the database
     @param info representing the updated data.
     */
    public boolean send(HashMap<String, Object> info, int id) {

        if(EditProfile.getInstance().edit(info)) {

            String likes;
            if (info.get("likes") == null) {
                likes = null;
            } else {
                likes = String.join(": ", (String) info.get("likes"));
            }

            double[] location = LocationConverter.codeToCoords((String) info.get("location"));
            System.out.println(info.get("location"));

            String str_data = info.get("name") + ", " + info.get("email") + ", " + info.get("password") + ", " + info.get("age") + ", " +
                    info.get("bio") + ", " + info.get("gender") + ", " + info.get("orientation") + ", " +
                    location[0] + ": "+location[1] + ", " + info.get("hobbies") + ", " +
                    info.get("socialMedia") + ", " + likes + ", " + info.get("preferredAge") + ", " +
                    info.get("preferredGender") + ", " + info.get("preferredLocation");
            SendData.getInstance().sendToID(id, str_data.split(", "));
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Something went wrong when editing your profile!");
            return false;
        }

    }

}