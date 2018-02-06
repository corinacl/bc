package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.http.HttpStatus;

import entities.Booking;
import entities.Bungalow;
import wrappers.DateRangeWrapper;

public class BungalowResourceFunctionalTesting {
	
	private static String token;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @BeforeClass
    public static void populate() {
        new RestService().populate();
        token = new RestService().loginAdmin();
    }
	
	@Test
    public void testGetAllBungalows() {
        List<Bungalow> response = Arrays.asList(new RestBuilder<Bungalow[]>(RestService.URL).path(Uris.BUNGALOWS).get()
                .clazz(Bungalow[].class).basicAuth(token, "").build());

        assertNotNull(response);
        assertTrue(response.size() > 0);
        assertEquals(14, response.size());
        assertEquals(14, response.get(13).getNumber());
    }
	
	@Test
	public void testSearchAvailabilityIncompleteFields (){
		thrown.expect(new HttpMatcher(HttpStatus.BAD_REQUEST));
		DateRangeWrapper dateRange = new DateRangeWrapper("01/04/2017",null);
		new RestBuilder<Booking>(RestService.URL).path(Uris.BUNGALOWS+Uris.SEARCH).body(dateRange).post().clazz(Booking.class).basicAuth(token, "").build();
	}

    @AfterClass
    public static void deleteAll() {
        new RestService().deleteAll();
    }
}
