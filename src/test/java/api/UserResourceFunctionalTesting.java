package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.apache.logging.log4j.LogManager;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import api.Uris;
import entities.Role;
import wrappers.UserCreateWrapper;
import wrappers.UserWrapper;

public class UserResourceFunctionalTesting {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testCreateManager() {
        String token = new RestService().loginAdmin();
        for (int i = 0; i < 4; i++) {
            new RestBuilder<Object>(RestService.URL).path(Uris.USERS).body(new UserCreateWrapper("user" + i, "pass", Role.MANAGER))
                    .basicAuth(token, "").post().build();
        }
    }

    @Test
    public void testCreateManagerUnauthorized2() {
        try {
            new RestBuilder<Object>(RestService.URL).path(Uris.USERS).body(new UserCreateWrapper("userManager", "pass", Role.MANAGER)).post().build();
            fail();
        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.UNAUTHORIZED, httpError.getStatusCode());
            LogManager.getLogger(this.getClass())
                    .info("testCreateManagerUnauthorized (" + httpError.getMessage() + "):\n     " + httpError.getResponseBodyAsString());
        }
    }

    @Test
    public void testCreateManagerUnauthorized() {
        thrown.expect(new HttpMatcher(HttpStatus.UNAUTHORIZED));
        new RestBuilder<Object>(RestService.URL).path(Uris.USERS).body(new UserCreateWrapper("userManager", "pass", Role.MANAGER)).post().build();
    }
    
    
    @Test
    public void testBadRequestCreate() {
        String token = new RestService().loginAdmin();
        try {
            UserWrapper userWrapper = new UserWrapper("", "pass");
            new RestBuilder<Object>(RestService.URL).path(Uris.USERS).body(userWrapper).basicAuth(token, "").post().build();
            fail();
        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.BAD_REQUEST, httpError.getStatusCode());
            LogManager.getLogger(this.getClass())
                    .info("testBadRequestCreate (" + httpError.getMessage() + "):\n    " + httpError.getResponseBodyAsString());
        }
    }

    @Test
    public void testRepeatingFieldCreate() {
        String token = new RestService().loginAdmin();
        UserCreateWrapper userWrapper = new UserCreateWrapper("usuario", "pass", Role.AUTHENTICATED);
        new RestBuilder<Object>(RestService.URL).path(Uris.USERS).body(userWrapper).basicAuth(token, "").post().build();
        try {
            new RestBuilder<Object>(RestService.URL).path(Uris.USERS).body(userWrapper).basicAuth(token, "").post().build();
            fail();
        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.CONFLICT, httpError.getStatusCode());
            LogManager.getLogger(this.getClass())
                    .info("testRepeatingFieldCreate (" + httpError.getMessage() + "):\n    " + httpError.getResponseBodyAsString());
        }
    }

    @After
    public void deleteAll() {
        new RestService().deleteAll();
    }
}
