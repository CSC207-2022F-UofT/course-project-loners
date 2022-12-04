package tests.usecases;

import usecases.LocationConverter;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test the LocationConverter
 */
public class LocationConverterTest {
    /**
     * Check a working postal code in the location converter
     * Keep in mind that the precondition must be met
     */
    @Test
    public void codeToCoordsTestWorking(){
        String myCode = "L9M 0E5";
        double[] expected = LocationConverter.codeToCoords(myCode);
        double[] myCoords = {44.7968279, -79.9448889};
        Assert.assertArrayEquals(new double[][]{myCoords}, new double[][]{expected});
    }
}
