package daos;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import entities.Bungalow;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})

public class BungalowDaoIT {

    @Autowired
    private BungalowDao bungalowDao;
    
    @Test
    public void testCreate() {
        assertEquals(14, bungalowDao.count());
    }

    @Test
    public void testAvailabiltyByDates() {
    	Calendar arrival = Calendar.getInstance();
    	arrival.set(2017, 4, 1);
    	arrival.set(Calendar.SECOND, 0);
    	arrival.set(Calendar.MINUTE, 0);
    	arrival.set(Calendar.HOUR_OF_DAY, 14);
    	Calendar departure = Calendar.getInstance();
    	departure.set(2017, 4, 29);
    	departure.set(Calendar.SECOND, 0);
    	departure.set(Calendar.MINUTE, 0);
    	departure.set(Calendar.HOUR_OF_DAY, 12);
    	List<Bungalow> bungalows =  bungalowDao.findAvailability(arrival, departure);
        assertEquals(14, bungalows.size());
    }

}
