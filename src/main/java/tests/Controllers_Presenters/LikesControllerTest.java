package tests.Controllers_Presenters;

import Controllers_Presenters.DataFetchControl;
import Controllers_Presenters.DataSendControl;
import Controllers_Presenters.LikesController;
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
        Object[] profDemo = {"7", "Name8", "email8", "password", "19", "bio", "male", "orientation", "20.0: 30.0001", "hobbies", "socialMedia", "2:" , "19", "male", "5.0"};
        String myLikes = "2: ";
        int otherId = 3;
        LikesController.modifyLikes(myLikes, otherId, profDemo);
        Object[] profNow = {"7", "Name8", "email8", "password", "19", "bio", "male", "orientation", "20.0: 30.0001", "hobbies", "socialMedia", "2: 3: " , "19", "male", "5.0"};
        Object[] profData = (Object[]) Objects.requireNonNull(DataFetchControl.fetch_fromid(7))[0];
        Assert.assertArrayEquals(profData, profNow);
        profDemo = new Object[]{"7", "Name8", "email8", "password", "19", "bio", "male", "orientation", "20.0: 30.0001", "hobbies", "socialMedia", "2", "19", "male", "5.0" };
        DataSendControl d = new DataSendControl();
        d.send_toid(7, profDemo);
    }

    /**
     * Modify the likes list when it is null (nobody has been liked yet)
     */
    @Test
    public void modifyLikesTestNull(){
        Object[] profDemo = {"7", "Name8", "email8", "password", "19", "bio", "male", "orientation", "20.0: 30.0001", "hobbies", "socialMedia", "null" , "19", "male", "5.0"};
        String myLikes = "null";
        int otherId = 3;
        LikesController.modifyLikes(myLikes, otherId, profDemo);
        Object[] profNow = {"7", "Name8", "email8", "password", "19", "bio", "male", "orientation", "20.0: 30.0001", "hobbies", "socialMedia", "3: " , "19", "male", "5.0"};
        Object[] profData = (Object[]) Objects.requireNonNull(DataFetchControl.fetch_fromid(7))[0];
        Assert.assertArrayEquals(profData, profNow);
        profDemo = new Object[]{"7", "Name8", "email8", "password", "19", "bio", "male", "orientation", "20.0: 30.0001", "hobbies", "socialMedia", "null", "19", "male", "5.0" };
        DataSendControl d = new DataSendControl();
        d.send_toid(7, profDemo);
    }
}