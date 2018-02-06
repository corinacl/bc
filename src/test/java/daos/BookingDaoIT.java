package daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import entities.Booking;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class BookingDaoIT {

    @Autowired
    private BookingDao bookingDao;

    @Autowired
    private ClientDao clientDao;
    
    @Autowired
    private BungalowDao bungalowDao;

    @Test
    public void testCreateBooking() {
        assertEquals(10, bookingDao.count());
    }

    @Test
    public void testFindByIdBooking() {
        assertNotNull(bookingDao.findOne(2));
    }

    @Test
    public void testFindBookingByClient() {
    	List<Booking> bookings = bookingDao.findByClient(clientDao.findByDni("98765432b"));
    	assertNotNull(bookings);
    }
    
    @Test
    public void testFindBookingByDateRange() {
    	Calendar arrival = Calendar.getInstance();
    	arrival.set(2017, 6, 1);
    	arrival.set(Calendar.SECOND, 0);
    	arrival.set(Calendar.MINUTE, 0);
    	arrival.set(Calendar.HOUR_OF_DAY, 14);
    	Calendar departure = Calendar.getInstance();
    	departure.set(2017, 6, 29);
    	departure.set(Calendar.SECOND, 0);
    	departure.set(Calendar.MINUTE, 0);
    	departure.set(Calendar.HOUR_OF_DAY, 12);
    	List<Booking> bookings = bookingDao.findByBookingsBetween(arrival, departure);
    	assertNotNull(bookings);
    }
    
    @Test
    public void testFindBookingByDateRangeAndBungalow() {
    	Calendar arrival = Calendar.getInstance();
    	arrival.set(2017, 6, 1);
    	arrival.set(Calendar.SECOND, 0);
    	arrival.set(Calendar.MINUTE, 0);
    	arrival.set(Calendar.HOUR_OF_DAY, 14);
    	Calendar departure = Calendar.getInstance();
    	departure.set(2017, 6, 31);
    	departure.set(Calendar.SECOND, 0);
    	departure.set(Calendar.MINUTE, 0);
    	departure.set(Calendar.HOUR_OF_DAY, 12);
    	List<Booking> bookings = bookingDao.findByBookingsByBungalowBetween(arrival, departure, bungalowDao.findOne(1));
    	assertEquals(3, bookings.size());
    }
}
