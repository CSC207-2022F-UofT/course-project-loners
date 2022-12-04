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

/*
* DataFetchControl class has methods that fetches the data from the database in a way the user wants to
 */
public class DataFetchControl {
    /**
     * This method fetch the profile data associated to id.
     @param id of the user
     @return Array Object that contains profile data
     */
    public static Object[] fetchFromId(int id){
        try {
            BufferedReader myReader = new BufferedReader(new FileReader("database.txt"));
            String line = myReader.readLine();
            int lineId = Integer.parseInt(line.split(", ")[0]);
            while(lineId != id){
                line = myReader.readLine();
                lineId = Integer.parseInt(line.split(", ")[0]);
            }
            List<String> profileData = Arrays.asList(line.split(", "));
            BufferedImage image = ImageIO.read(new File(String.format("saved_images/%s.jpg", id)));

            Object[] data = profileData.toArray();

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
    public static int fetchIdFromEmail(String email){
        try {
            BufferedReader myReader = new BufferedReader(new FileReader("database.txt"));
            String line = myReader.readLine();
            if (line == null){ return -1;} // if file is empty
            else{
                String lineEmail = Arrays.asList(line.split(", ")).get(2);
                while(!Objects.equals(lineEmail, email)) {
                    line = myReader.readLine();
                    lineEmail = Arrays.asList(line.split(", ")).get(2);
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
    public int fetchLastID(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("database.txt"));
            String line = reader.readLine();
            if (line == null){ return -1; } // if the file is empty, id is -1
            else {
                String last = "";
                while(line != null){
                    last = line;
                    line = reader.readLine();}
                List<String> lastLst = Arrays.asList(last.split(", "));
                return Integer.parseInt(lastLst.get(0));
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
    public ArrayList<String> fetchEmails(){
        ArrayList<String> emails = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("database.txt"));
            String line = reader.readLine();
            String tmp;
            while(line != null) {
                tmp = line;
                List<String> lstLine = Arrays.asList(tmp.split(", "));
                emails.add(lstLine.get(2));
                line = reader.readLine();
            }
            return emails;
        } catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
            return emails;
        }
    }

    public static String fetchPassword(String email){
        // This method assumes the email exists in our database.
        try {
            BufferedReader myReader = new BufferedReader(new FileReader("database.txt"));
            String line = myReader.readLine();
            String[] wholeLine = line.split(", ");
            String lineEmail = wholeLine[2];
            String linePassword = wholeLine[3];
            while(!Objects.equals(lineEmail, email)){
                line = myReader.readLine();
                wholeLine = line.split(", ");
                lineEmail = wholeLine[2];
                linePassword = wholeLine[3];
            }
            return linePassword;
        } catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
    }
    public static double[] fetchAddressFromId(int input_id){
        // use id to find location
        // return -1.9 if file is empty
        // return -10 if it has error

        String address = ((String[])fetchFromId(input_id)[0])[8];
        if(address.matches(".*\\d.*") && address.contains(":")){
            String[] locationStr = address.split(":");
            double latitude = Double.parseDouble(locationStr[0]);
            double longitude = Double.parseDouble(locationStr[1]);
            return new double[] {latitude, longitude};
        }
        else{
            System.out.println("It only contains String.");
            return new double[]{0.0, 0.0};
        }
    }

}
