package usecases;

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
        actual.add(0);
        actual.add(1);
        actual.add(2);
        actual.add(3);
        actual.add(4);
        actual.add(6);
        actual.add(9);
        actual.add(10);
        actual.add(11);
        actual.add(12);
        actual.add(13);
        actual.add(14);
        Assert.assertEquals(actual, expected);
    }
}



