package Controllers_Presenters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class tmp_DataFetchControl {
    public Object[] fetch_fromEmail(String email){
        try {
            BufferedReader myReader = new BufferedReader(new FileReader("database.txt"));
            String line = myReader.readLine();
            if (line == null){ return null;} // if file is empty
            else{
                String line_email = line.split(", ")[2];
                while(!Objects.equals(line_email, email)) {
                    line = myReader.readLine();
                    line_email = line.split(", ")[2];
                }
            }
            List<String> line_data = Arrays.asList(line.split(", "));
            return line_data.toArray();
        } catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
    }
}
