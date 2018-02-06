package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import api.exceptions.IncompleteFieldException;
import daos.AuthorizationDao;
import daos.UserDao;
import entities.Authorization;
import entities.User;
import wrappers.UserCreateWrapper;

@Controller
public class UserController {

    private UserDao userDao;

    private AuthorizationDao authorizationDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setAuthorizationDao(AuthorizationDao authorizationDao) {
        this.authorizationDao = authorizationDao;
    }

    public boolean registration(UserCreateWrapper userCreateWrapper) throws IncompleteFieldException {
    	if(userCreateWrapper.getRole() == null || userCreateWrapper.getUsername() == null || userCreateWrapper.getPassword() == null){
    		throw new IncompleteFieldException();
    	}else{
	        if (null == userDao.findByUsername(userCreateWrapper.getUsername())) {
	            User user = new User(userCreateWrapper.getUsername(), userCreateWrapper.getPassword());
	            userDao.save(user);
	            authorizationDao.save(new Authorization(user, userCreateWrapper.getRole()));
	            return true;
	        } else {
	            return false;
	        }
    	}
    }

	public List<Authorization> getAll() {
		return authorizationDao.findAll();
	}
}
