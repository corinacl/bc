package api;

import static org.junit.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.junit.After;
import org.junit.Test;

public class TokenResourceFunctionalTesting {

    @Test
    public void testLoginManager() {
        String token = new RestService().registerAndLoginManager();
        assertTrue(token.length() > 20);
        LogManager.getLogger(this.getClass()).info("testLoginPlayer (token:" + token + ")");
    }

    @After
    public void deleteAll() {
        new RestService().deleteAll();
    }
}
