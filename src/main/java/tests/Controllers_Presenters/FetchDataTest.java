package Controllers_Presenters;

import Use_Cases.FetchData;
import junit.framework.TestCase;
import org.junit.Test;
import java.util.ArrayList;

public class FetchDataTest extends TestCase {
    @Test
    public void testFetch_fromid() {
        Object[] expected = (Object[]) FetchData.fetchFromId(0)[0];
        Object[] actual = {"0", "Name", "email@", "null", "20", "bio", "male", "straight", "20.0: 10.0",
                "play soccer: play violin", "null", "null", "20", "male", "2.0"};

        for(int i=0; i < expected.length;i++) {
            System.out.println(expected[i] == actual[i]);
            System.out.println(actual[i]);
        }
    }

    @Test
    public void testFetch_id_fromEmail() {
        int expected = FetchData.fetchIdFromEmail("email@");
        assert expected == 0;
    }

    @Test
    public void testFetch_lastID() {
        FetchData fetchData = new FetchData();
        int expected = fetchData.fetchLastID();
        assert expected == 7;
    }

    @Test
    public void testFetch_emails() {
        FetchData fetchData = new FetchData();
        ArrayList<String> emails = fetchData.fetchEmails();
        assert emails.size() == 8;
    }

    @Test
    public void testFetch_password() {
        String expected = FetchData.fetchPassword("email@");
        assert expected.equals("null");
    }
    @Test
    public void testFetch_address_from_id() {
        double[] expected = FetchData.fetchAddressFromId(0);
        assert expected.equals(new double[] {20.0, 10.0});
    }
}