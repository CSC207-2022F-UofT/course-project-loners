package tests.Controllers_Presenters;

import controllers.DataFetchControl;
import controllers.DataSendControl;
import junit.framework.TestCase;
import org.junit.Test;

public class DataSendControlTest extends TestCase {
    @Test
    public void testSend_toid() {
        DataSendControl dataSendControl = new DataSendControl();
        Object[] info = (Object[]) DataFetchControl.fetch_fromid(0)[0];
        dataSendControl.send_toid(0, info);
        for(int i=0; i< info.length; i++){
            System.out.println(info[i]);
            System.out.println(((Object[]) DataFetchControl.fetch_fromid(0)[0])[i]);
            assert info[i].equals(((Object[]) DataFetchControl.fetch_fromid(0)[0])[i]);
        }
    }
    @Test
    public void testProfileConvertStr() {

    }
}