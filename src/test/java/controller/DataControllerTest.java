package controller;

import controllers.DataController;
import org.junit.jupiter.api.Test;
import usecases.FetchData;

import static org.junit.jupiter.api.Assertions.*;

class DataControllerTest {

    @Test
    void fetchFromId() {
        assert "Rick" == DataController.fetchFromId(0)[1];
    }

    @Test
    void fetchIdFromEmail() {
    }

    @Test
    void fetchLastID() {
    }

    @Test
    void sendData() {
    }

    @Test
    void sendToID() {
    }
}