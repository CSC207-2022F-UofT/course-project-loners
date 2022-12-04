package Controllers_Presenters;

import Use_Cases.FetchData;
import Use_Cases.SendData;

import java.util.ArrayList;

public class DataController {
    public static Object[] fetchFromId(int id){
        return FetchData.fetchFromId(id);
    }
    public static int fetchFromEmail(String email){
        return FetchData.fetchIdFromEmail(email);
    }
    public static int fetchLastId(){
        return FetchData.fetchLastID();
    }
    public static ArrayList<String> fetchEmails(){
        return FetchData.fetchEmails();
    }
    public static String fetchPassword(String email){
        return FetchData.fetchPassword(email);
    }
    public static double[] fetchAddressFromId(int id){
        return FetchData.fetchAddressFromId(id);
    }
    public static void sendToId(int id, Object[] data){
        SendData.getInstance().sendToId(id, data);
    }
}
