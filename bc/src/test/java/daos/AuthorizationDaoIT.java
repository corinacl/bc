package daos;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import daos.AuthorizationDao;
import daos.UserDao;
import entities.Role;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class AuthorizationDaoIT {

    @Autowired
    private AuthorizationDao authorizationDao;

    @Autowired
    private UserDao userDao;

    @Test
    public void testFindRoleByUser() {
        List<Role> roles = authorizationDao.findRoleByUser(userDao.findByUsername("manager"));
        assertEquals(1, roles.size());
        assertEquals(Role.MANAGER, roles.get(0));
    }

}
