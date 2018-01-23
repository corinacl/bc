package api;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.http.HttpStatus;

import entities.Booking;
import wrappers.PlanningWrapper;

public class PlanningResourceFunctionalTesting {
	
	private static String token;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @BeforeClass
    public static void populate() {
        new RestService().populate();
        token = new RestService().loginAdmin();
    }
	
	@Test
	public void testGetPlanningIncompleteFields (){
		thrown.expect(new HttpMatcher(HttpStatus.BAD_REQUEST));
		PlanningWrapper planningWrapper = new PlanningWrapper(0, 2017);
		new RestBuilder<Booking>(RestService.URL).path(Uris.BUNGALOWS+Uris.SEARCH).body(planningWrapper).post().clazz(Booking.class).basicAuth(token, "").build();
	}

    @AfterClass
    public static void deleteAll() {
        new RestService().deleteAll();
    }
}
