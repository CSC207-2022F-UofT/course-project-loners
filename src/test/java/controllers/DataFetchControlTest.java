package controllers;

import org.junit.Test;

import java.util.ArrayList;

public class DataFetchControlTest {
    @Test
    public void testFetchFromid() {
        Object[] expected = (Object[]) DataFetchControl.fetchFromid(0)[0];
        Object[] actual = {"0", "Name", "email@", "null", "20", "bio", "male", "straight", "20.0: 10.0",
                "play soccer: play violin", "null", "null", "20", "male", "2.0"};

        for(int i=0; i < expected.length;i++) {
            System.out.println(expected[i] == actual[i]);
            System.out.println(actual[i]);
        }
    }

    @Test
    public void testFetchIdFromEmail() {
        int expected = DataFetchControl.fetchIdFromEmail("email@");
        assert expected == 0;
    }

    @Test
    public void testFetchLastID() {
        DataFetchControl dataFetchControl = new DataFetchControl();
        int expected = dataFetchControl.fetchLastID();
        assert expected == 7;
    }

    @Test
    public void testFetchEmails() {
        DataFetchControl dataFetchControl = new DataFetchControl();
        ArrayList<String> emails = dataFetchControl.fetchEmails();
        assert emails.size() == 8;
    }

    @Test
    public void testFetchPassword() {
        String expected = DataFetchControl.fetchPassword("email@");
        assert expected.equals("null");
    }
    @Test
    public void testFetchAddressFromId() {
//        double[] expected = DataFetchControl.fetch_address_from_id(0);
//        assert expected.equals(new double[] {20.0, 10.0});
    }
}