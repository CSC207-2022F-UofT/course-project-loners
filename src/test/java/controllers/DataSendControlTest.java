package controllers;

import org.junit.Test;

public class DataSendControlTest {
    @Test
    public void testSendToid() {
        DataSendControl dataSendControl = new DataSendControl();
        Object[] info = (Object[]) DataFetchControl.fetchFromid(0)[0];
        dataSendControl.sendToid(0, info);
        for(int i=0; i< info.length; i++){
            System.out.println(info[i]);
            System.out.println(((Object[]) DataFetchControl.fetchFromid(0)[0])[i]);
            assert info[i].equals(((Object[]) DataFetchControl.fetchFromid(0)[0])[i]);
        }
    }
    @Test
    public void testProfileConvertStr() {

    }
}