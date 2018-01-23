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

import entities.BungalowType;

public class BungalowTypeResourceFunctionalTesting {
	
	private static String token;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @BeforeClass
    public static void populate() {
        new RestService().populate();
        token = new RestService().loginAdmin();
    }
	
	@Test
    public void testGetAllBungalowType() {
        List<BungalowType> response = Arrays.asList(new RestBuilder<BungalowType[]>(RestService.URL).path(Uris.TYPE).get()
                .clazz(BungalowType[].class).basicAuth(token, "").build());

        assertNotNull(response);
        assertTrue(response.size() > 0);
        assertEquals("B", response.get(1).getType());
    }

    @AfterClass
    public static void deleteAll() {
        new RestService().deleteAll();
    }
}
