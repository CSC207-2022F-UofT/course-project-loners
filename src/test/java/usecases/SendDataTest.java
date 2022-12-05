package usecases;
import controllers.DataController;
import org.junit.Test;

/*
* Test case about SendData class in use cases.
 */
public class SendDataTest {
    @Test
    public void sendToID(){

        String str_data = "Rick, email@, password, 19, student, male, straight, 20.0: 30.0, play soccer, " +
                "instagram: rick, 3:, 20, female, 20.0";
        SendData.getInstance().sendToId(0, str_data.split(", "));
        assert "Rick".equals(((Object[])DataController.fetchFromId(0)[0])[1]);
    }
}
