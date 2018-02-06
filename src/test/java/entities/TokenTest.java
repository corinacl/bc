package entities;

import static org.junit.Assert.*;

import org.junit.Test;

import entities.Token;
import entities.User;

public class TokenTest {

    @Test
    public void testTokenUser() {
        User user = new User("u", "p");
        Token token = new Token(user);
        assertTrue(token.getValue().length() > 20);
        assertEquals(token.getUser(), user);
    }

}
