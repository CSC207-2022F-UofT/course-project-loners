package Controllers_Presenters;

import Entities.Preferences;
import Entities.Profile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class DataSendControl {
    public boolean status;
    public DataSendControl(Profile profile) {
        int last_id = new DataFetchControl().fetch_lastID();
        if (last_id == -10) { // if last_id has error
            this.status = false;
        } else if (last_id == -1) { // if the file is empty
            try {
                FileWriter myWriter = new FileWriter("database.txt", StandardCharsets.UTF_8, true);
                myWriter.write("0, "+ ProfileConvertStr(profile));
                myWriter.close();
                this.status = true;
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
                this.status = false;
            }
        } else {
            try {
                FileWriter myWriter = new FileWriter("database.txt", StandardCharsets.UTF_8, true);
                myWriter.write("\n");
                myWriter.write((last_id + 1) +", "+ ProfileConvertStr(profile) + ", null, null, null");
                myWriter.close();
                this.status = true;
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
                this.status = false;
            }
        }
    }

    public DataSendControl(Profile profile, Preferences preferences) {
        int last_id = new DataFetchControl().fetch_lastID();
        if (last_id == -10) { // if last_id has error
            this.status = false;
        } else if (last_id == -1) { // if the file is empty
            try {
                FileWriter myWriter = new FileWriter("database.txt", StandardCharsets.UTF_8, true);
                myWriter.write("0, "+ ProfileConvertStr(profile)+ ", " + PreferencesConvertStr(preferences));
                myWriter.close();
                this.status = true;
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
                this.status = false;
            }
        } else {
            try {
                FileWriter myWriter = new FileWriter("database.txt", StandardCharsets.UTF_8, true);
                myWriter.write("\n");
                myWriter.write((last_id + 1) +", "+ ProfileConvertStr(profile) + PreferencesConvertStr(preferences));
                myWriter.close();
                this.status = true;
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
                this.status = false;
            }
        }
    }

    public DataSendControl() {}

    public boolean send_toid (int id, Object[] data){
        try{
            BufferedReader myReader = new BufferedReader(new FileReader("database.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            String line = myReader.readLine();
            System.out.println(line);
            int line_id = Integer.parseInt(line.split(", ")[0]);
            while (line_id != id){
                inputBuffer.append(line);
                inputBuffer.append('\n');
                line = myReader.readLine();
                line_id = Integer.parseInt(line.split(", ")[0]);
            }
            String modified_data = String.valueOf(id)+", "+ data[0]+", "+data[1]+", "+data[2] + ", " + data[3] + ", "+ data[4] + ", "+ data[5] +
                    ", "+ data[6] + ", "+ data[7] + ", "+ data[8] + ", "+ data[9] + ", "+ data[10] + ", "+ data[11] +
                    ", "+ data[12] + ", "+ data[13] + ", "+ data[14] + ", "+ data[15];

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
            return true;
        } catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }
    }

    public String ProfileConvertStr(Profile profile) {
        return profile.getName() + ", " + profile.getEmail() + ", " + profile.getPassword() + ", " + profile.getAge() + ", " +
                profile.getBio() + ", " + profile.getGender() + ", " + profile.getOrientation() + ", " +
                Arrays.toString(profile.getLocation()) + ", " + profile.getImage() + ", " + profile.getHobbies() + ", " +
                profile.getSocialMedia() + ", " + profile.getLikes();
    }

    public String PreferencesConvertStr(Preferences preferences) {
        return preferences.getPreferredAge() + ", "
                + preferences.getPreferredGender() + ", "
                + Arrays.toString(preferences.getPreferredLocation()) + ", "
                + preferences.getPreferredLocationRange();
    }
}

