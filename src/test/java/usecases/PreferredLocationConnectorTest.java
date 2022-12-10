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
        List<Integer> actual = PreferredLocationConnector.withinPreferredLocation(idOfUser, preferredLocationRange);
        List<Integer> expected = new ArrayList<>();
        expected.add(0);
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);
        expected.add(6);
        expected.add(9);
        expected.add(10);
        expected.add(11);
        expected.add(12);
        expected.add(13);
        expected.add(14);
        expected.add(15);
        expected.add(16);
        expected.add(17);
        Assert.assertEquals(expected, actual);
    }
}



