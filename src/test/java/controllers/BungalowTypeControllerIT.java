package controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsPersistenceConfig;
import entities.BungalowType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class BungalowTypeControllerIT {

    @Autowired
    private BungalowTypeController bungalowTypeController;
    
    @Test
    public void testGetAll() {
        assertEquals(3, bungalowTypeController.getAll().size());
    }

    @Test
    public void testGetTypeById() {
    	BungalowType bungalowType = bungalowTypeController.getBungalowTypeById(1);
    	assertEquals(bungalowType.getType(), "A");
    }
}
