package controllers;

import entities.Profile;
import usecases.FetchData;
import usecases.SendData;


/*
* DataController class is responsible for managing the data fetching action that is invoked by UIs.
* To follow the clean architecture, we use this controller to do the data fetching and sending actions defined in
* FetchData and SendData classes in usecases folder.
 */
public class DataController {
    /*
    * For each of the following function, the description is written in FetchData and SendData classes
     */
    public static Object[] fetchFromId(int id){
        return FetchData.fetchFromId(id);
    }
    public static int fetchIdFromEmail(String email){
        return FetchData.fetchIdFromEmail(email);
    }
    public static int fetchLastID(){
        return FetchData.fetchLastID();
    }
    public static void sendData(Profile profile){
        new SendData(profile);
    }
    public static void sendToID(int id, Object[] data){
        SendData.getInstance().sendToId(id, data);
    }
}
