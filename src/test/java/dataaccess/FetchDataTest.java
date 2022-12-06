package dataaccess;


import controllers.DataController;
import org.junit.Test;

/*
* Test cases for FetchData
 */
public class FetchDataTest {

    @Test
    public void fetchFromId() {
        assert "Rick".equals(((Object[])FetchData.fetchFromId(0)[0])[1]);
    }

    @Test
    public void fetchIdFromEmail() {
        assert 0 == FetchData.fetchIdFromEmail("email@");
    }

    @Test
    public void fetchLastID() {
        assert 7 == FetchData.fetchLastID();
    }
}