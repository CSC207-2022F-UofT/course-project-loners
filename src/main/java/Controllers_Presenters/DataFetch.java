package Controllers_Presenters;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class DataFetch {
    public Object[] fetch_byid(String id){
        try {
            BufferedReader myReader = new BufferedReader(new FileReader("database.txt"));
            String line = myReader.readLine();
            while(line.charAt(0) != id.charAt(0) && line.charAt(1) != id.charAt(1) && line.charAt(2) != id.charAt(2) ){
                line = myReader.readLine();
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
    public boolean send_toid (String id, Object[] data){
        try{
            BufferedReader myReader = new BufferedReader(new FileReader("database.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            String line = myReader.readLine();
            inputBuffer.append(line);
            inputBuffer.append('\n');
            System.out.println(line.charAt(0));
            System.out.println(line.charAt(1));
            System.out.println(line.charAt(2));
            while (line.charAt(0) != "001".charAt(0) && line.charAt(1) != "001".charAt(1) && line.charAt(2) != "001".charAt(2) ){
                line = myReader.readLine();
                inputBuffer.append(line);
                inputBuffer.append('\n');

            }
            String modified_data = data[0]+", "+data[1]+", "+data[2] + ", " + data[3];

            inputBuffer.append(modified_data);
            inputBuffer.append('\n');
            myReader.readLine();
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

    public static void main(String[] args){

    }
}
