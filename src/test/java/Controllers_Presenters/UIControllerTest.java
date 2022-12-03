package Controllers_Presenters;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UIControllerTest {
    UIController controller;
    @Before
    public void setup(){
        // TODO: virtual database??
        controller = new UIController();
    }

    @Test(timeout = 50)
    public void testCheckHasPreference(){
        controller.checkHasPreference();
    }

}