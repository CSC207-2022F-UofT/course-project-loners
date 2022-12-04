package tests.Use_Cases;

import Use_Cases.PreferredLocationConnector;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Test the PreferredLocationConnector
 */
public class PreferredLocationConnectorTest {
    /**
     * Check if it correctly outputs the list of ids' of those who are within the preferred location range from
     *      the preferred location of the user with a specific id.
     */
    @Test
    public void withinPreferredLocationTest(){
        int idOfUser = 7;
        double preferredLocationRange = 5.0;
        List<Integer> expected = PreferredLocationConnector.withinPreferredLocation(idOfUser, preferredLocationRange);
        List<Integer> actual = new ArrayList<>();
        actual.add(2);
        actual.add(3);
        actual.add(4);
        Assert.assertEquals(actual, expected);
    }



}
