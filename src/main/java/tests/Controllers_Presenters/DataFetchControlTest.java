package tests.Controllers_Presenters;

import controllers.DataFetchControl;
import junit.framework.TestCase;
import org.junit.Test;
import java.util.ArrayList;

public class DataFetchControlTest extends TestCase {
    @Test
    public void testFetch_fromid() {
        Object[] expected = (Object[]) DataFetchControl.fetch_fromid(0)[0];
        Object[] actual = {"0", "Name", "email@", "null", "20", "bio", "male", "straight", "20.0: 10.0",
                "play soccer: play violin", "null", "null", "20", "male", "2.0"};

        for(int i=0; i < expected.length;i++) {
            System.out.println(expected[i] == actual[i]);
            System.out.println(actual[i]);
        }
    }

    @Test
    public void testFetch_id_fromEmail() {
        int expected = DataFetchControl.fetch_id_fromEmail("email@");
        assert expected == 0;
    }

    @Test
    public void testFetch_lastID() {
        DataFetchControl dataFetchControl = new DataFetchControl();
        int expected = dataFetchControl.fetch_lastID();
        assert expected == 7;
    }

    @Test
    public void testFetch_emails() {
        DataFetchControl dataFetchControl = new DataFetchControl();
        ArrayList<String> emails = dataFetchControl.fetch_emails();
        assert emails.size() == 8;
    }

    @Test
    public void testFetch_password() {
        String expected = DataFetchControl.fetch_password("email@");
        assert expected.equals("null");
    }
    @Test
    public void testFetch_address_from_id() {
        double[] expected = DataFetchControl.fetch_address_from_id(0);
        assert expected.equals(new double[] {20.0, 10.0});
    }
}