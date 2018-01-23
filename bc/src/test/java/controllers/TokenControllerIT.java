package controllers;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsPersistenceConfig;
import wrappers.TokenWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class TokenControllerIT {

    @Autowired
    private TokenController tokenController;

    @Test
    public void testLogin() {
        TokenWrapper tokenWrapper = tokenController.login("manager");
        assertTrue(tokenWrapper.getToken().length() > 20);
        assertTrue(tokenWrapper.getRol().equals("MANAGER"));
    }

}
