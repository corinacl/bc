package controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.AuthorizationDao;
import daos.TokenDao;
import daos.UserDao;
import entities.Token;
import entities.User;
import wrappers.TokenWrapper;

@Controller
@Transactional
public class TokenController {

    private TokenDao tokenDao;

    private UserDao userDao;

    private AuthorizationDao authorizationDao;

    @Autowired
    public void setTokenDao(TokenDao tokenDao) {
        this.tokenDao = tokenDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setAuthorizationDao(AuthorizationDao authorizationDao) {
        this.authorizationDao = authorizationDao;
    }

    public TokenWrapper login(String username) {
        User user = userDao.findByUsername(username);
        assert user != null;
        Token token = new Token(user);
        tokenDao.save(token);
        return new TokenWrapper(token.getValue(), authorizationDao.findRoleByUser(user).get(0).name());
    }

}
