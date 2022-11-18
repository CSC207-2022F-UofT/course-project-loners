package Controllers_Presenters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

            Object[] data = profile_data.toArray();
            return data;
        } catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
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

    public static void main(String[] args){
        Object[] data = new DataFetchControl().fetch_fromid(2);
        System.out.println(data[9].getClass());
    }
}