package dataaccess;
import org.junit.Test;

/*
* Test cases for SendData
 */
public class SendDataTest {
    @Test
    public void sendToID(){

        String str_data = "Rick, email@, password, 19, student, male, straight, 20.0: 30.0, play soccer, " +
                "instagram: rick, 3:, 20, female, 20.0";
        SendData.getInstance().sendToID(0, str_data.split(", "));
        assert "Rick".equals(((Object[])FetchData.fetchFromID(0)[0])[1]);
    }
}
