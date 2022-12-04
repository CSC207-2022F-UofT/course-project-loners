package Controllers_Presenters;

import Use_Cases.FetchData;
import Use_Cases.SendData;
import junit.framework.TestCase;
import org.junit.Test;

public class SendDataTest extends TestCase {
    @Test
    public void testSend_toid() {

        Object[] info = (Object[]) FetchData.fetchFromId(0)[0];
        SendData.getInstance().sendToId(0, info);
        for(int i=0; i< info.length; i++){
            System.out.println(info[i]);
            System.out.println(((Object[]) FetchData.fetchFromId(0)[0])[i]);
            assert info[i].equals(((Object[]) FetchData.fetchFromId(0)[0])[i]);
        }
    }
    @Test
    public void testProfileConvertStr() {

    }
}