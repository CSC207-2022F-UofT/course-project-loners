package Controllers_Presenters;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class DataFetchSendControl {
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
                    ", "+ data[12] + ", "+ data[13];

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

    public static void main(String[] args){
        Object[] data = new DataFetchSendControl().fetch_fromid(2);
        System.out.println(data[9].getClass());
    }
}
