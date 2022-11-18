package Controllers_Presenters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class tmp_DataFetch {
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
                    emails.add(lst_line.get(2));
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

    // Not sure if this is needed
    // public ArrayList<Profile> load_data(){}
}
