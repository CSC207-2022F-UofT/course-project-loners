package controllers;

import entities.Profile;
import usecases.SendData;


/*
* DataController class is responsible for managing the data fetching action that is invoked by UIs.
* To follow the clean architecture, we use this controller to do the data fetching and sending actions defined in
* SendData class in usecases folder.
 */
public class DataController {
    /*
    * For each of the following function, the description is written in SendData class
     */
    public static SendData sendData(Profile profile){
        return new SendData(profile);
    }
    public static void sendToID(int id, Object[] data){
        SendData.getInstance().sendToId(id, data);
    }
}
