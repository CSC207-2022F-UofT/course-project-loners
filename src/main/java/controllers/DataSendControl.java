package controllers;

import entities.Profile;
import usecases.DataSendAccess;

import java.io.*;
import java.nio.charset.StandardCharsets;


public class DataSendControl implements DataSendAccess {
    /*
    * This class sends the given data to database accordingly to how the user wants to.
     */
    public boolean status;
    private static final DataSendControl d = new DataSendControl();
    /**
     * A constructor for DataSendControl class.
     @param profile Profile instance whose information will be sent to the database
     */
    public DataSendControl(Profile profile) {
        int last_id = new DataFetchControl().fetch_lastID();
        if (last_id == -10) { // if last_id has error
            this.status = false;
        } else if (last_id == -1) { // if the file is empty
            try {
                // FileWriter writes the given data to database.txt
                FileWriter myWriter = new FileWriter("database.txt", StandardCharsets.UTF_8, true);
                myWriter.write("0, "+ ProfileConvertStr(profile));
                myWriter.close();
                this.status = true;
            } catch (IOException e) {
                // If the FileWriter fails to write to database.txt, IOException will be raised.
                e.printStackTrace();
                this.status = false;
            }
        } else { // if the file is not empty
            try {
                // FileWriter writes the given data to database.txt
                FileWriter myWriter = new FileWriter("database.txt", StandardCharsets.UTF_8, true);
                myWriter.write("\n");
                myWriter.write((last_id + 1) +", "+ ProfileConvertStr(profile) + ", null, null, null, null");
                myWriter.close();
                this.status = true;
            } catch (IOException e) {
                // If the FileWriter fails to write to database.txt, IOException will be raised.
                e.printStackTrace();
                this.status = false;
            }
        }
    }


    public DataSendControl() {}

    /**
     * Implements singleton design pattern for datasend control
     * @return instance of DataSendControl
     */
    public static DataSendControl getInstance(){
        return d;
    }
    /**
     @param (id, data) id represents the id of the profile the use wants to edit. data represents the updated data
     of the profile
     */
    @Override
    public void send_toid(int id, Object[] data){
        try{
            // BufferedReader reads database.txt line by line.
            BufferedReader myReader = new BufferedReader(new FileReader("database.txt"));
            // StringBuffer stores the updated data and will be inserted the database.txt
            StringBuilder inputBuffer = new StringBuilder();
            String line = myReader.readLine();
            int line_id = Integer.parseInt(line.split(", ")[0]);
            // iterate through lines until the line of database.txt refers to the data we want to modify
            while (line_id != id){
                inputBuffer.append(line);
                inputBuffer.append('\n');
                line = myReader.readLine();
                line_id = Integer.parseInt(line.split(", ")[0]);
            }
            String hobbies = (String) data[8];
            String likes = (String) data[10];
            String modified_data = id+", "+ data[0]+", "+data[1]+", "+data[2] + ", " + data[3] + ", "+ data[4] + ", "+ data[5] +
                    ", "+ data[6] + ", "+ data[7] +  ", "+ hobbies + ", "+ data[9] + ", "+ likes +
                    ", "+ data[11] + ", "+ data[12] + ", "+data[13];


            inputBuffer.append(modified_data);
            inputBuffer.append('\n');
            line = myReader.readLine();
            while (!(line==null)){
                inputBuffer.append(line);
                inputBuffer.append('\n');
                line = myReader.readLine();
            }
            myReader.close();
            FileOutputStream fileOut = new FileOutputStream("database.txt");
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
        } catch (IOException e){
            // If it fails to read or write to database.txt, this Exception will be raised.
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * A helper method used in DataSendControl class.
     @param profile A Profile object
     @return String representation of the profile data.
     */
    @Override
    public String ProfileConvertStr(Profile profile) {
        String str = profile.getName() + ", " + profile.getEmail() + ", " + profile.getPassword() + ", " + profile.getAge() + ", " +
                    profile.getBio() + ", " + profile.getGender() + ", " + profile.getOrientation() + ", " +
                    profile.getLocation()[0] + ": " + profile.getLocation()[1] + ", ";

        if (profile.getHobbies() != null){str += String.join(": ", profile.getHobbies()) + ", ";}
        else {str += profile.getHobbies() + ", ";}

        str += profile.getSocialMedia() + ", " + profile.getLikes();

        return str;
    }

    public static void main(String[] args) {
    }
}

