package Controllers_Presenters;

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

    private static final DataFetchControl d =  new DataFetchControl();
    public static DataFetchControl getInstance(){
        return d;
    }

    /**
     * This method fetch the profile data associated to id.
     @param id of the user
     @return Array Object that contains profile data
     */
    public static Object[] fetch_fromid(int id){
        try {
            BufferedReader myReader = new BufferedReader(new FileReader("database.txt"));
            String line = myReader.readLine();
            int line_id = Integer.parseInt(line.split(", ")[0]);
            while(line_id != id){
                line = myReader.readLine();
                line_id = Integer.parseInt(line.split(", ")[0]);
            }
            List<String> profile_data = Arrays.asList(line.split(", "));
            BufferedImage image = ImageIO.read(new File(String.format("saved_images/%s.jpg", id)));

            Object[] data = profile_data.toArray();

            return new Object[] {data, image};

        } catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
            return new Object[0];
        }
    }

    /**
     * This method uses email to find id associated to that email
     * return -1 if file is empty
     * return -10 if it has error
     @param email of the profile
     @return id
     */
    public static int fetch_id_fromEmail(String email){
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
            return Integer.parseInt(line.split(", ")[0]);
        } catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
            return -10;
        }
    }

    /**
     * This method fetches the last id in the database.
     @return last id in the database
     */
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

    /**
     *
     @return ArrayList that contains all the emails
     */
    public ArrayList<String> fetch_emails(){
        ArrayList<String> emails = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("database.txt"));
            String line = reader.readLine();
            String tmp;
            while(line != null) {
                tmp = line;
                List<String> lst_line = Arrays.asList(tmp.split(", "));
                emails.add(lst_line.get(2));
                line = reader.readLine();
            }
            return emails;
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
    public static double[] fetch_address_from_id(int input_id){
        // use id to find location
        // return -1.9 if file is empty
        // return -10 if it has error
        try {
            BufferedReader myReader = new BufferedReader(new FileReader("database.txt"));
            String line = myReader.readLine();
            String[] whole_line = line.split(", ");
            String id = whole_line[0];
            String address = whole_line[8];
            String input_id_str = "" + input_id;
            while (!Objects.equals(id, input_id_str) ) {
                line = myReader.readLine();
                whole_line = line.split(", ");
                id = whole_line[0];
                address = whole_line[8];
            }
            if(address.matches(".*\\d.*") && address.contains(":")){
                String[] location_str = address.split(":");
                double latitude = Double.parseDouble(location_str[0]);
                double longitude = Double.parseDouble(location_str[1]);
                return new double[] {latitude, longitude};
            }
            else{
                System.out.println("It only contains String.");
                return new double[]{0.0, 0.0};
            }
        } catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
            return new double[]{0.0, 0.0};
        }
    }

}
