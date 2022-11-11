package Entity;

import org.junit.Test;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class EditProfileControl implements DataFetchSend{
    public String name;
    public Set<Object> objects = new HashSet<>();

    static Profile profile = new Profile("Rick", 21, "male",
            "straight", null, null, "This is Rick", null, null);

    public  EditProfileControl(){

    }

    public boolean edit(Map<String, Object> info){
        Object[] keys = info.keySet().toArray();
        Object[] values = info.values().toArray();
        if (info.keySet().size() == 0){
            System.out.println("No change is made");
            return false;
        }
        for (int k = 0; k<info.keySet().size();k++){
            if(keys[k] == "name"){
                profile.name = (String) values[k];
            } else if (keys[k] == "email") {
                profile.email = (String) values[k];
            } else if (keys[k] == "age") {
                profile.age = (int) values[k];
            } else if (keys[k] == "gender") {
                profile.gender = (String) values[k];
            } else if (keys[k] == "location") {
                profile.location = (double[]) values[k];
            } else if (keys[k] == "image") {
                profile.image = (BufferedImage) values[k];
            } else if (keys[k] == "bio") {
                profile.bio = (String) values[k];
            } else if (keys[k] == "hobbies") {
                profile.hobbies = (List<String>) values[k];
            } else if (keys[k] == "socialMedia") {
                profile.socialMedia = (String) values[k];
            } else if (keys[k] == "likes") {
                profile.likes = (List<String>) values[k];
            } else if (keys[k] == "orientation") {
                profile.orientation = (String) values[k];
            }
        }
        return true;
    }

    @Override
    public boolean send(Map<String, Object> info) {
        /*String.format("User1: %s", this.name))*/
        System.out.println("This part is successfully executed");
        if(this.edit(info)){
            System.out.println("This part is successfully executed2");
            try {
                FileWriter myWriter = new FileWriter("database.txt", StandardCharsets.UTF_8, true);
                myWriter.write("\n");
                myWriter.write(profile.name+", "+profile.email+", "+ profile.age+", "+profile.bio+ ", "+profile.gender+", "+profile.orientation+",");
                myWriter.close();
                System.out.println("Successfully wrote to the file.");

                return true;
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
                return false;
            }
        }else{
            System.out.println("Couldn't successfully edited");
            return false;
        }

    }

    //public static void main(String[] args){
        //EditProfileControl edit = new EditProfileControl("Rick");
        //edit.send();
    //}

}
