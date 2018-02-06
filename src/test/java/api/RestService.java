package api;

import wrappers.TokenWrapper;
import wrappers.UserCreateWrapper;
import entities.Role;

public class RestService {

    public static final String URL = "http://localhost:8080/manager/api";

    public void populate() {
        new RestBuilder<TokenWrapper>(RestService.URL).path(Uris.ADMINS).basicAuth(this.loginAdmin(), "").post().build();
    }

    public void deleteAll() {
        new RestBuilder<TokenWrapper>(RestService.URL).path(Uris.ADMINS).basicAuth(this.loginAdmin(), "").delete().build();
    }

    public String loginAdmin() {
        TokenWrapper token = new RestBuilder<TokenWrapper>(URL).path(Uris.TOKENS).basicAuth("admin", "admin").clazz(TokenWrapper.class)
                .post().build();
        return token.getToken();
    }

    public String registerAndLoginManager() {
        UserCreateWrapper testUser = new UserCreateWrapper("testUser", "1234", Role.MANAGER);
        new RestBuilder<Object>(URL).path(Uris.USERS).body(testUser).basicAuth(this.loginAdmin(), "").post().build();
        TokenWrapper token = new RestBuilder<TokenWrapper>(URL).path(Uris.TOKENS)
                .basicAuth((testUser.getUsername()), testUser.getPassword()).clazz(TokenWrapper.class).post().build();
        return token.getToken();
    }

    public String loginManager() {
        TokenWrapper token = new RestBuilder<TokenWrapper>(URL).path(Uris.TOKENS).basicAuth("manager", "manager").clazz(TokenWrapper.class)
                .post().build();
        return token.getToken();
    }
    
    public String loginAuth() {
        TokenWrapper token = new RestBuilder<TokenWrapper>(URL).path(Uris.TOKENS).basicAuth("authenticated", "authenticated").clazz(TokenWrapper.class)
                .post().build();
        return token.getToken();
    }

}
