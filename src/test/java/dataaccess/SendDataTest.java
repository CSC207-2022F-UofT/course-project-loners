package dataaccess;
import org.junit.Test;

/*
* Test cases for SendData
 */
public class SendDataTest {
    @Test
    public void testSendToID(){

        String str_data = "Kelly, kelly@mail, pw, 21, This is Kelly, female, straight, 43.670437: -79.401003, sing, " +
                "Instagram: kelly, 3: , 21, male, 20.0";
        SendData.getInstance().sendToID(0, str_data.split(", "));
        assert "Kelly".equals(((Object[])FetchData.fetchFromID(0)[0])[1]);
    }
}
