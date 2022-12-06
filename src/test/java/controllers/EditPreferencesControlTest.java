package controllers;

import dataaccess.FetchData; // implements a Use Case interface
import org.junit.Before;
//import org.junit.After;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class EditPreferencesControlTest {
    HashMap<String, String> preferenceMap;

    @Before
    public void setUp() {
        preferenceMap = new HashMap<>();
    }

//    @After
//    public void tearDown() throws Exception {
//    }

    /**
     * Test the case where all three preferences inputted are valid and thus the database is updated.
     */
    @Test
    public void testPassPreferencesUpdate() {
        preferenceMap.put("preferred age", "20");
        preferenceMap.put("preferred gender", "male");
        preferenceMap.put("preferred location range", "5");
        EditPreferencesControl editPreferencesControl = new EditPreferencesControl(preferenceMap, 3);
        editPreferencesControl.passPreferences();

        Object[] userData = FetchData.fetchFromId(3);
        userData = (Object[]) userData[0];
        assertEquals("20", userData[12]);
        assertEquals("male", userData[13]);
        assertEquals("5.0", userData[14]);
        assertEquals("3", userData[0]);
    }
}