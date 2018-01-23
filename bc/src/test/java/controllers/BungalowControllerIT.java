package controllers;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import api.exceptions.IncompleteModifyBookingException;
import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsPersistenceConfig;
import entities.Bungalow;
import wrappers.DateRangeWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class BungalowControllerIT {

    @Autowired
    private BungalowController bungalowController;

    @Test
    public void testGetAll() {
        assertEquals(14, bungalowController.getAll().size());
    }

    @Test
    public void testGetAvailability() throws IncompleteModifyBookingException {
    	List<Bungalow> bungalows = bungalowController.getAvailabilityInDates(new DateRangeWrapper("01/04/2017","29/04/2017"));
        assertEquals(14, bungalows.size());
    }
}
