package api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.exceptions.AlreadyExistUserFieldException;
import api.exceptions.IncompleteFieldException;
import api.exceptions.InvalidUserFieldException;
import controllers.UserController;
import entities.Authorization;
import wrappers.UserCreateWrapper;

@RestController
@RequestMapping(Uris.USERS)
public class UserResource {

    private UserController userController;

    @Autowired
    public void setUserController(UserController userController) {
        this.userController = userController;
    }
    
    @RequestMapping(method = RequestMethod.GET)
	public List<Authorization> listAuthorization(){
		return userController.getAll();
	}

    @RequestMapping(method = RequestMethod.POST)
    public void userRegistration(@RequestBody UserCreateWrapper userCreateWrapper) throws InvalidUserFieldException, AlreadyExistUserFieldException, IncompleteFieldException {
    	validateField(userCreateWrapper.getUsername(), "username");
        if (!this.userController.registration(userCreateWrapper)) {
            throw new AlreadyExistUserFieldException();
        }
    }
    
    private void validateField(String field, String msg) throws InvalidUserFieldException {
        if (field == null || field.isEmpty()) {
            throw new InvalidUserFieldException(".");
        }
    }

}
