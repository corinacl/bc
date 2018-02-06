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

import entities.Client;
import wrappers.ClientCreateWrapper;

public class ClientResourceFunctionalTesting {
	
	private static String token;

    @Rule
    public ExpectedException thrown = ExpectedException.none();
	
	@BeforeClass
    public static void populate() {
        new RestService().populate();
        token = new RestService().loginAdmin();
    }
	
	@Test
    public void testGetAllClients() {
        List<Client> response = Arrays.asList(new RestBuilder<Client[]>(RestService.URL).path(Uris.CLIENTS).get()
                .clazz(Client[].class).basicAuth(token, "").build());

        assertNotNull(response);
        assertTrue(response.size() > 0);
        assertEquals(7, response.size());
        assertEquals("Cliente", response.get(1).getName());
    }
	
	@Test
    public void testCreateClient() {
		ClientCreateWrapper client = new ClientCreateWrapper("Cori", "Leon", null, null, null);
		Client response = new RestBuilder<Client>(RestService.URL).path(Uris.CLIENTS).post().body(client)
                .clazz(Client.class).basicAuth(token, "").build();

        assertNotNull(response);
        assertEquals("Cori", response.getName());
    }

    @AfterClass
    public static void deleteAll() {
        new RestService().deleteAll();
    }
}
