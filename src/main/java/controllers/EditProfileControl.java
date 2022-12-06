package controllers;

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
    public String name;
    private BufferedImage image;
    /**
     * The constructor of EditProfileControl class sets default value of image to null.
     */
    public  EditProfileControl(){
        this.image = null;
    }

    /**
     * Using Singleton Design Pattern
     */
    public static final EditProfileControl e = new EditProfileControl();
    public static EditProfileControl getInstance(){
        return e;
    }

    /**
     *
     * @param f Frame of the UI where the local image folder will be searched
     * @return boolean value representing whether it has successfully set the image attribute of the instance to
     * the image that the user has selected.
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
     *
     * @param id represents the user who wants to modify their profile image
     *           This method is correlated to withHoldImage method above.
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
            DataController.sendToID(id, str_data.split(", "));
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Something went wrong when editing your profile!");
            return false;
        }

    }

}