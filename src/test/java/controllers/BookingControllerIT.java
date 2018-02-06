package controllers;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import api.exceptions.IncompleteBookingException;
import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsPersistenceConfig;
import daos.BookingDao;
import entities.Booking;
import wrappers.BookingCreateWrapper;
import wrappers.BookingModifyWrapper;
import wrappers.DateRangeWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class BookingControllerIT {

    @Autowired
    private BookingController bookingController;
    
    @Autowired
    private BookingDao bookingDao;

    @Test
    public void testGetAll() {
        assertEquals(10, bookingController.getAll().size());
    }

    @Test
    public void testCreateArrivalDate() {
    	Calendar date = Calendar.getInstance();
		date.set(2017, 11, 12);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.HOUR_OF_DAY, 14);
		
    	assertEquals(bookingController.createArrivalDate("12/12/2017"), date);  
    }
    
    @Test
    public void testCreateBooking() throws IncompleteBookingException {
    	BookingCreateWrapper bookingCreateWrapper = new BookingCreateWrapper(2, 2, "02/02/2017", "06/02/2017", new BigDecimal (0));
    	Booking newBooking = bookingController.createBooking(bookingCreateWrapper);
    	assertEquals(11, bookingController.getAll().size());
    	bookingDao.delete(newBooking);
    }
    
    @Test
    public void testGetBookingById() {
    	BookingModifyWrapper booking = bookingController.getBookingById(1);
    	assertNotNull(booking);
    } 
    
    @Test
    public void testGetBookingByDateRange() throws IncompleteBookingException {
    	DateRangeWrapper dateRangeWrapper =  new DateRangeWrapper("01/06/2017", "20/06/2017");
    	List<Booking> bookings = bookingController.getBookingByDateRange(dateRangeWrapper);
    	assertEquals(3, bookings.size());
    } 
}
