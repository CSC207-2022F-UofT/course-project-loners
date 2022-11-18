package Controllers_Presenters;

import Entity.Preferences;
import Entity.Profile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class tmp_DataSend {
    public boolean status;
    public tmp_DataSend(Profile profile) {
        int last_id = new tmp_DataFetch().fetch_lastID();
        if (last_id == -10) { // if last_id has error
            this.status = false;
        } else if (last_id == -1) { // if the file is empty
            try {
                FileWriter myWriter = new FileWriter("database.txt", StandardCharsets.UTF_8, true);
                myWriter.write("\n");
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

    public tmp_DataSend(Profile profile, Preferences preferences) {
        int last_id = new tmp_DataFetch().fetch_lastID();
        if (last_id == -10) { // if last_id has error
            this.status = false;
        } else if (last_id == -1) { // if the file is empty
            try {
                FileWriter myWriter = new FileWriter("database.txt", StandardCharsets.UTF_8, true);
                myWriter.write("\n");
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

    public String ProfileConvertStr(Profile profile) {
        return profile.getName() + ", " + profile.getEmail() + ", " + profile.getPassword() + ", " + profile.getAge() + ", " +
                profile.getBio() + ", " + profile.getGender() + ", " + profile.getOrientation() + ", " +
                Arrays.toString(profile.getLocation()) + ", " + profile.getImage() + ", " + profile.getHobbies() + ", " +
                profile.getSocialMedia() + ", " + profile.getLikes();
    }

    public String PreferencesConvertStr(Preferences preferences) {
        return preferences.getPreferredAge() + ", "
                + preferences.getPreferredGender() + ", "
                + Arrays.toString(preferences.getPreferredLocation());
    }
}

