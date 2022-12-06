package dataaccess;

import org.junit.Test;

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
}