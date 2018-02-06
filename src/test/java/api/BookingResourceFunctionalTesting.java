package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.http.HttpStatus;

import entities.Booking;
import wrappers.BookingCreateWrapper;
import wrappers.BookingModifyWrapper;
import wrappers.ClientIdWrapper;
import wrappers.DateRangeAndBungalowNrWrapper;

public class BookingResourceFunctionalTesting {
	
	private static String token;

    @Rule
    public ExpectedException thrown = ExpectedException.none();
	
	@BeforeClass
    public static void populate() {
        new RestService().populate();
        token = new RestService().loginAdmin();
    }
	
	@Test
    public void testGetAllBookings() {
        List<Booking> response = Arrays.asList(new RestBuilder<Booking[]>(RestService.URL).path(Uris.BOOKINGS).get()
                .clazz(Booking[].class).basicAuth(token, "").build());

        assertNotNull(response);
        assertTrue(response.size() > 0);
        assertEquals(10, response.size());
        assertEquals(1, response.get(1).getBungalow().getNumber());
    }
	
	@Test
    public void testCreateBookings() {
		BookingCreateWrapper bookingCreateWrapper = new BookingCreateWrapper(3, 3, "02/02/2017", "06/02/2017", new BigDecimal (0));    	
		Booking response = new RestBuilder<Booking>(RestService.URL).path(Uris.BOOKINGS).body(bookingCreateWrapper)
				.post().clazz(Booking.class).basicAuth(token, "").post().build();
		
        assertNotNull(response);
        assertEquals(3, response.getBungalow().getId());
        assertEquals(3, response.getClient().getId());
    }
	
	@Test
    public void testBookingsByClient() {
        List<Booking> response = Arrays.asList(new RestBuilder<Booking[]>(RestService.URL).path(Uris.BOOKINGS + Uris.CLIENTS)
        		.body(new ClientIdWrapper(6)).post().clazz(Booking[].class).basicAuth(token, "").build());

        assertNotNull(response);
        assertTrue(response.size() > 0);
    }
	
	@Test
    public void testSearchBookings() {
		DateRangeAndBungalowNrWrapper d = new DateRangeAndBungalowNrWrapper("01/06/2017", "06/08/2017", 2);
        List<Booking> response = Arrays.asList(new RestBuilder<Booking[]>(RestService.URL).path(Uris.BOOKINGS + Uris.SEARCH + Uris.BOOKINGS)
        		.body(d).post().clazz(Booking[].class).basicAuth(token, "").build());

        assertNotNull(response);
        assertTrue(response.size() > 0);
    }
	
	@Test
    public void testModifyBookingIncompleteFields() {
		thrown.expect(new HttpMatcher(HttpStatus.BAD_REQUEST));
		BookingModifyWrapper booking = new BookingModifyWrapper(2, null, null, null, null, null);    	
		new RestBuilder<Booking>(RestService.URL).path(Uris.BOOKINGS).body(booking).put()
		.clazz(Booking.class).basicAuth(token, "").build();
    }

    @AfterClass
    public static void deleteAll() {
        new RestService().deleteAll();
    }
}
