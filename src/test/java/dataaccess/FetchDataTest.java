package dataaccess;

import org.junit.Test;

import java.util.Arrays;

/*
* Test cases for FetchData
 */
public class FetchDataTest {

    @Test
    public void fetchFromID() {
        assert "Rick".equals(((Object[])FetchData.fetchFromID(0)[0])[1]);
    }

    @Test
    public void fetchIDFromEmail() {
        assert 0 == FetchData.fetchIDFromEmail("email@");
    }

    @Test
    public void fetchLastID() {
        assert 7 == FetchData.fetchLastID();
    }
    @Test
    public void fetchAddressFromID() {
        double[] location = {20.0, 30.0001};
        assert Arrays.equals(location, FetchData.fetchAddressFromID(7));
    }
}