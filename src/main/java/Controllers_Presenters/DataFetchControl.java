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
    /*
    * DataFetchControl class
    * The user can fetch data from the database in a way they want to.
     */
    public static Object[] fetchFromId(int id){
        /**
         * This method fetch the profile data associated to id.
         @param id
         @return Array Object that contains profile data
         @throws IOException
         */
        try {
            BufferedReader myReader = new BufferedReader(new FileReader("database.txt"));
            String line = myReader.readLine();
            int lineId = Integer.parseInt(line.split(", ")[0]);
            while(lineId != id){
                line = myReader.readLine();
                lineId = Integer.parseInt(line.split(", ")[0]);
            }
            List<String> profile_data = Arrays.asList(line.split(", "));
            BufferedImage image = ImageIO.read(new File(String.format("saved_images/%s.jpg", Integer.toString(id))));

            Object[] data = profile_data.toArray();
            Object[] dataWithImage = {data, image};

            return dataWithImage;
        } catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
    }

    public static int fetchIdFromEmail(String email){
        /**
         * This method uses email to find id associated to that email
         * return -1 if file is empty
         * return -10 if it has error
         @param email
         @return id
         @throws IOException
         */
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
            List<String> lineData = Arrays.asList(line.split(", "));
            return Integer.parseInt(line.split(", ")[0]);
        } catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
            return -10;
        }
    }

    public int fetchLastId(){
        /**
         * This method fetches the last id in the database.
         @param nothing
         @return last id in the database
         @throws IOException
         */
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

    public ArrayList<String> fetchEmails(){
        /**
         *
         @param nothing
         @return ArrayList that contains all the emails
         @throws IOException
         */
        ArrayList<String> emails = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("database.txt"));
            String line = reader.readLine();
            if (line == null){ return emails; } // if the file is empty, id should be 1
            else { // if file is not empty, id = last line's id + 1
                String tmp = "";
                while(line != null) {
                    tmp = line;
                    List<String> lstLine = Arrays.asList(tmp.split(", "));
                    emails.add(lstLine.get(2));
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
    public static double[] fetchAddressFromId(int inputId){
        // use id to find location
        // return -1.9 if file is empty
        // return -10 if it has error
        try {
            BufferedReader myReader = new BufferedReader(new FileReader("database.txt"));
            String line = myReader.readLine();
            String[] wholeLine = line.split(", ");
            String id = wholeLine[0];
            String address = wholeLine[8];
            String inputIdStr = "" + inputId;
            while (!Objects.equals(id, inputIdStr) ) {
                line = myReader.readLine();
                wholeLine = line.split(", ");
                id = wholeLine[0];
                address = wholeLine[8];
            }
            if(address.matches(".*\\d.*") && address.contains(":")){
                String locationStr[] = address.split(":");
                double latitude = Double.parseDouble(locationStr[0]);
                double longitude = Double.parseDouble(locationStr[1]);
                double[] location = {latitude, longitude};
                return location;
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

    public static void main(String[] args){
        double[] data1 = new DataFetchControl().fetchAddressFromId(1);
        // Testing the method fetch_password
        // String test = DataFetchControl.fetch_password("email");
        // System.out.println(test);
    }

}
