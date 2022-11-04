package Entity;
import Entity.UIs.EditProfileUI;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;

public class EditProfileControl implements DataFetchSend{
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
            FileWriter myWriter = new FileWriter("user_data.txt", StandardCharsets.UTF_8, true);
            myWriter.write("Hello");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");

            return true;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }
    }
    public void main(String[] args){
        this.send();
    }

}
