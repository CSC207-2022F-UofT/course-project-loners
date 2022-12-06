package controllers;


import org.junit.Test;

/*
* Test cases for DataController
* The sendToId method will also be tested in SendData class.
 */
public class DataControllerTest {

    @Test
    public void fetchFromId() {
        assert "Rick".equals(((Object[])DataController.fetchFromId(0)[0])[1]);
    }

    @Test
    public void fetchIdFromEmail() {
        assert 0 == DataController.fetchIdFromEmail("email@");
    }

    @Test
    public void fetchLastID() {
        assert 7 == DataController.fetchLastID();
    }
}