package controllers;

import dataaccess.FetchData; // implements a Use Case interface
import dataaccess.SendData; // implements a Use Case interface
import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

/**
 * Testing the LikesController
 */
public class LikesControllerTest {
    /**
     * Test a non-null likes list
     */
    @Test
    public void modifyLikesTestNotNull(){
        Object[] profDemo = {"7", "Taka", "taka@dmail.com", "dknnsoi92ld", "20", "Taka is me", "male", "straight", "43.670437: -79.401003", "dance", "instagram taka_taka", "3: ", "20", "female", "5.0"};
        String myLikes = "3: ";
        int otherId = 2;
        LikesController.modifyLikes(myLikes, otherId, profDemo);
        Object[] profNow = {"7", "Taka", "taka@dmail.com", "dknnsoi92ld", "20", "Taka is me", "male", "straight", "43.670437: -79.401003", "dance", "instagram taka_taka", "3: 2: ", "20", "female", "5.0"};
        Object[] profData = (Object[]) Objects.requireNonNull(FetchData.fetchFromID(7))[0];
        Assert.assertArrayEquals(profData, profNow);
        profDemo = new Object[]{"Taka", "taka@dmail.com", "dknnsoi92ld", "20", "Taka is me", "male", "straight", "43.670437: -79.401003", "dance", "instagram taka_taka", "3: ", "20", "female", "5.0"};
        SendData.getInstance().sendToID(7, profDemo);
    }

    /**
     * Modify the likes list when it is null (nobody has been liked yet)
     */
    @Test
    public void modifyLikesTestNull(){
        Object[] profDemo = {"7", "Taka", "taka@dmail.com", "dknnsoi92ld", "20", "Taka is me", "male", "straight", "43.670437: -79.401003", "dance", "instagram taka_taka", "null", "20", "female", "5.0"};
        String myLikes = "null";
        int otherId = 3;
        LikesController.modifyLikes(myLikes, otherId, profDemo);
        Object[] profNow = {"7", "Taka", "taka@dmail.com", "dknnsoi92ld", "20", "Taka is me", "male", "straight", "43.670437: -79.401003", "dance", "instagram taka_taka", "3: ", "20", "female", "5.0"};
        Object[] profData = (Object[]) Objects.requireNonNull(FetchData.fetchFromID(7))[0];
        Assert.assertArrayEquals(profData, profNow);
        profDemo = new Object[]{"Taka", "taka@dmail.com", "dknnsoi92ld", "20", "Taka is me", "male", "straight", "43.670437: -79.401003", "dance", "instagram taka_taka", "null", "20", "female", "5.0"};
        SendData.getInstance().sendToID(7, profDemo);
    }
}
