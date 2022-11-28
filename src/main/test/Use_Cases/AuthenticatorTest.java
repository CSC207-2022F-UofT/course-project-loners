package Use_Cases;

import junit.framework.TestCase;
import org.junit.Test;

public class AuthenticatorTest extends TestCase {
    @Test
    public void test_is_valid_name(){
        Authenticator authenticator = new Authenticator();
        Boolean actual = authenticator.is_valid_name("John");
        Boolean expected = true;
        assert actual == expected;
    }

}