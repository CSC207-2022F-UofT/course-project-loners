package controllers;

import dataaccess.FetchData; // implements a Use Case interface
import dataaccess.SendData; // implements a Use Case interface
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for ensuring that preferences are properly stored in the database.
 */
public class EditPreferencesControlTest {
    /** A mapping of preference labels to their corresponding text input */
    HashMap<String, String> preferenceMap;

    /** The user data stored in the database before the test runs */
    Object[] originalData;

    /**
     * Initialize an empty HashMap, and store the first user's data into originalData.
     */
    @Before
    public void setUp() {
        preferenceMap = new HashMap<>();
        originalData = (Object[]) FetchData.fetchFromID(0)[0];
    }

    /**
     * Restore the original data to the database, to overwrite any changes made during the test.
     */
    @After
    public void tearDown() {
        List<Object> tempOriginalData = new ArrayList<>(List.of(originalData));
        tempOriginalData.remove(0);
        Object[] originalDataNoID = tempOriginalData.toArray();
        SendData.getInstance().sendToID(0, originalDataNoID);
    }

    /**
     * Test the case where all three preferences inputted are within the range of valid inputs and thus the database
     * is updated.
     */
    @Test
    public void testPassPreferencesWithinRange() {
        preferenceMap.put("preferred age", "20");
        preferenceMap.put("preferred gender", "male");
        preferenceMap.put("preferred location range", "5");
        EditPreferencesControl editPreferencesControl = new EditPreferencesControl(preferenceMap, 0);
        editPreferencesControl.passPreferences();

        Object[] userData = FetchData.fetchFromID(0);
        userData = (Object[]) userData[0];
        assertEquals("20", userData[12]);
        assertEquals("male", userData[13]);
        assertEquals("5.0", userData[14]);
        assertEquals("0", userData[0]);
    }

    /**
     * Test the case where age and location range are both at the lower bound of valid inputs and thus the database
     * is still updated.
     */
    @Test
    public void testPassPreferencesZeroes() {
        preferenceMap.put("preferred age", "0");
        preferenceMap.put("preferred gender", "other");
        preferenceMap.put("preferred location range", "0");
        EditPreferencesControl editPreferencesControl = new EditPreferencesControl(preferenceMap, 0);
        editPreferencesControl.passPreferences();

        Object[] userData = FetchData.fetchFromID(0);
        userData = (Object[]) userData[0];
        assertEquals("0", userData[12]);
        assertEquals("other", userData[13]);
        assertEquals("0.0", userData[14]);
        assertEquals("0", userData[0]);
    }
}