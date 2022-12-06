package usecases;

import dataaccess.FetchData;
import entities.Profile;
import java.io.*;
import java.nio.charset.StandardCharsets;

/*
 * SendData class is responsible for sending the data to the Database.
 * In clean architecture, this works as Data Access Interface
 */
public class SendData {
    /*
     * This class sends the given data to database accordingly to how the user wants to.
     */
    public boolean status;
    private static final SendData d = new SendData();
    /**
     * A constructor for DataSendControl class.
     @param profile Profile instance whose information will be sent to the database
     */
    public SendData(Profile profile) {
        int lastID = FetchData.fetchLastID();
        if (lastID == -10) { // if lastID has error
            this.status = false;
        } else if (lastID == -1) { // if the file is empty
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
                myWriter.write((lastID + 1) +", "+ ProfileConvertStr(profile) + ", null, null, null, null");
                myWriter.close();
                this.status = true;
            } catch (IOException e) {
                // If the FileWriter fails to write to database.txt, IOException will be raised.
                e.printStackTrace();
                this.status = false;
            }
        }
    }

    public SendData() {}

    /**
     * Implements singleton design pattern for datasend control
     * @return instance of DataSendControl
     */
    public static SendData getInstance(){
        return d;
    }
    /**
     @param (id, data) id represents the id of the profile the use wants to edit. data represents the updated data
     of the profile
     */
    public void sendToId(int id, Object[] data){
        try{
            // BufferedReader reads database.txt line by line.
            BufferedReader myReader = new BufferedReader(new FileReader("database.txt"));
            // StringBuffer stores the updated data and will be inserted the database.txt
            StringBuilder inputBuffer = new StringBuilder();
            String line = myReader.readLine();
            int lineId = Integer.parseInt(line.split(", ")[0]);
            // iterate through lines until the line of database.txt refers to the data we want to modify
            while (lineId != id){
                inputBuffer.append(line);
                inputBuffer.append('\n');
                line = myReader.readLine();
                lineId = Integer.parseInt(line.split(", ")[0]);
            }
            String hobbies = (String) data[8];
            String likes = (String) data[10];
            String modifiedData = id+", "+ data[0]+", "+data[1]+", "+data[2] + ", " + data[3] + ", "+ data[4] + ", "+ data[5] +
                    ", "+ data[6] + ", "+ data[7] +  ", "+ hobbies + ", "+ data[9] + ", "+ likes +
                    ", "+ data[11] + ", "+ data[12] + ", "+data[13];


            inputBuffer.append(modifiedData);
            inputBuffer.append('\n');
            line = myReader.readLine();
            while (!(line==null)){
                inputBuffer.append(line);
                line = myReader.readLine();
                if(!(line==null)){inputBuffer.append('\n');}
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
