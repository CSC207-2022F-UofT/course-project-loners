package dataaccess;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/*
* Test cases for FetchData
 */
public class FetchDataTest {

    @Test
    public void fetchFromID() {
        Assert.assertEquals("Nafisa", ((Object[])FetchData.fetchFromID(4)[0])[1]);
        //assert "Kelly".equals(((Object[])FetchData.fetchFromID(0)[0])[1]);
    }

    @Test
    public void fetchIDFromEmail() {
        Assert.assertEquals(4, FetchData.fetchIDFromEmail("nafi@mail"));
        //assert 0 == FetchData.fetchIDFromEmail("kelly@mail");
    }

    @Test
    public void fetchLastID() {
        assert 17 == FetchData.fetchLastID();
    }

    @Test
    public void fetchAddressFromID() {
        double[] location = {43.670437, -79.401003};
        assert Arrays.equals(location, FetchData.fetchAddressFromID(7));
    }
}
