package Controllers_Presenters;


import Entity.Preferences;
import Entity.Profile;
import Entity.Authenticator;
import Use_Case.DataFetchSend;
import Use_Case.EditProfile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class EditProfileControl implements DataFetchSend {
    public String name;
    public Set<Object> objects = new HashSet<>();
    public Authenticator authenticator = new Authenticator();
    public EditProfile editProfile = new EditProfile();

    static Profile profile = new Profile("Rick", 21, "male",
            "straight", null, null, "This is Rick", null, null);
    static Preferences preferences = new Preferences(21, "male", null);

    public  EditProfileControl(){

    }

    @Override
    public boolean send(HashMap<String, Object> info) {
        /*String.format("User1: %s", this.name))*/
        System.out.println("This part is successfully executed");
        if(editProfile.edit(info, profile)){
            System.out.println("This part is successfully executed2");
            try {

                char id1 = '0';
                char id2 = '0';
                char id3 = '0';

                FileReader myReader = new FileReader("database.txt");
                id1 = (char) myReader.read();
                id2 = (char) myReader.read();
                id3 = (char) myReader.read();
                int k;
                while((k=myReader.read())!=-1){}
                // On my Profile UI, there should be id that referes to the id pf the user in the database
                myReader.close();
                FileWriter myWriter = new FileWriter("database.txt", StandardCharsets.UTF_8, true);
                myWriter.write("\n");
                myWriter.write(id1+id2+id3 + profile.getName()+", "+profile.getEmail()+", "+ profile.getAge()+", "+
                        profile.getBio()+ ", "+profile.getGender()+", "+profile.getOrientation()+", "+
                        preferences.getPreferredAge()+", "+preferences.getPreferredGender()+", "+
                        preferences.getPreferredLocation());
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