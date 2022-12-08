package dataaccess;

import org.junit.Assert;
import org.junit.Test;

/*
* Test cases for FetchData
 */
public class FetchDataTest {

    @Test
    public void fetchFromID() {
        Assert.assertEquals("Kelly", ((Object[])FetchData.fetchFromID(0)[0])[1]);
        //assert "Kelly".equals(((Object[])FetchData.fetchFromID(0)[0])[1]);
    }

    @Test
    public void fetchIDFromEmail() {
        Assert.assertEquals(0, FetchData.fetchIDFromEmail("kelly@mail"));
        //assert 0 == FetchData.fetchIDFromEmail("kelly@mail");
    }

    @Test
    public void fetchLastID() {
        assert 14 == FetchData.fetchLastID();
    }
}