package entities;

import java.math.BigDecimal;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class BookingTest {

	@Autowired
	private Client client;
	
	@Autowired
	private Bungalow bungalow;

	@Test
	public void testBooking() {
		Calendar date = Calendar.getInstance();
		BigDecimal totalPrice = new BigDecimal (135);
		Booking booking = new Booking(bungalow, client, date, date, totalPrice);
		Assert.assertEquals(booking.getClient(), client);
		Assert.assertEquals(booking.getTotalPrice(), totalPrice);
	}

}
