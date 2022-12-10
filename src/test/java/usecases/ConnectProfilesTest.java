package usecases;

import entities.Preferences;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test ConnectProfiles.
 */
public class ConnectProfilesTest {
    /**
     * Check if compareTraits gets correct output.
     */
    @Test
    public void testCompareTraits(){
        Preferences p = new Preferences(19, "female", 20.0, 0);
        List<Integer> lst = new ArrayList<>();
        lst.add(1);
        lst.add(11);
        assertEquals(new ConnectProfiles(p).compareTraits(), lst);
    }

}