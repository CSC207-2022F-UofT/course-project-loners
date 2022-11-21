package Controllers_Presenters;

import Entities.Profile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class DataFetchControl {
    public Object[] fetch_fromid(int id){
        try {
            BufferedReader myReader = new BufferedReader(new FileReader("database.txt"));
            String line = myReader.readLine();
            int line_id = Integer.parseInt(line.split(", ")[0]);
            while(line_id != id){
                line = myReader.readLine();
                line_id = Integer.parseInt(line.split(", ")[0]);
            }
            List<String> profile_data = Arrays.asList(line.split(", "));
            BufferedImage image = ImageIO.read(new File(String.format("saved_images/%s.jpg", Integer.toString(id))));

            Object[] data = profile_data.toArray();
            Object[] data_with_image = {data, image};

            return data_with_image;
        } catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
    }

    public static int fetch_id_fromEmail(String email){
        // use email to find id belongs to that email
        // return -1 if file is empty
        // return -10 if it has error
        try {
            BufferedReader myReader = new BufferedReader(new FileReader("database.txt"));
            String line = myReader.readLine();
            if (line == null){ return -1;} // if file is empty
            else{
                String line_email = Arrays.asList(line.split(", ")).get(2);
                while(!Objects.equals(line_email, email)) {
                    line = myReader.readLine();
                    line_email = Arrays.asList(line.split(", ")).get(2);
                }
            }
            List<String> line_data = Arrays.asList(line.split(", "));
            return Integer.parseInt(line.split(", ")[0]);
        } catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
            return -10;
        }
    }

    public int fetch_lastID(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("database.txt"));
            String line = reader.readLine();
            if (line == null){ return -1; } // if the file is empty, id is -1
            else {
                String last = "";
                while(line != null){
                    last = line;
                    line = reader.readLine();}
                List<String> last_lst = Arrays.asList(last.split(", "));
                return Integer.parseInt(last_lst.get(0));
            }
        } catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
            return -10;
        }
    }

    public ArrayList<String> fetch_emails(){
        ArrayList<String> emails = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("database.txt"));
            String line = reader.readLine();
            if (line == null){ return emails; } // if the file is empty, id should be 1
            else { // if file is not empty, id = last line's id + 1
                String tmp = "";
                while(line != null) {
                    tmp = line;
                    List<String> lst_line = Arrays.asList(tmp.split(", "));
                    emails.add((String) ((List<String>)lst_line).get(2).toString());
                    line = reader.readLine();
                }
                return emails;
            }
        } catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
            return emails;
        }
    }

    public static String fetch_password(String email){
        // This method assumes the email exists in our database.
        try {
            BufferedReader myReader = new BufferedReader(new FileReader("database.txt"));
            String line = myReader.readLine();
            String[] whole_line = line.split(", ");
            String line_email = whole_line[2];
            String line_password = whole_line[3];
            while(!Objects.equals(line_email, email)){
                line = myReader.readLine();
                whole_line = line.split(", ");
                line_email = whole_line[2];
                line_password = whole_line[3];
            }
            return line_password;
        } catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args){
        Object[] data = new DataFetchControl().fetch_fromid(2);
        System.out.println(data[9].getClass());
        // Testing the method fetch_password
        // String test = DataFetchControl.fetch_password("email");
        // System.out.println(test);
    }

}
