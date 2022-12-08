package dataaccess;

import org.junit.Test;

/*
* Test cases for FetchData
 */
public class FetchDataTest {

    @Test
    public void testFetchFromID() {
        assert "Rick".equals(((Object[])FetchData.fetchFromID(0)[0])[1]);
    }

    @Test
    public void testFetchIDFromEmail() {
        assert 0 == FetchData.fetchIDFromEmail("email@");
    }

    @Test
    public void testFetchLastID() {
        assert 7 == FetchData.fetchLastID();
    }
}