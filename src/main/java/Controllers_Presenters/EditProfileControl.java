package Controllers_Presenters;

import Use_Cases.DataFetchSend;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

public class EditProfileControl implements DataFetchSend {
    public String name;
    public Set<Object> objects = new HashSet<>();
    public EditProfileControl(String name){
        this.name = name;
        this.objects.add(this.name);
    }

    @Override
    public boolean send() {
        /*String.format("User1: %s", this.name))*/
        try {
            FileWriter myWriter = new FileWriter("database.txt", StandardCharsets.UTF_8, true);
            myWriter.write("\n");
            myWriter.write(this.name+", ");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");

            return true;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args){
        EditProfileControl edit = new EditProfileControl("Rick");
        edit.send();
    }

}
