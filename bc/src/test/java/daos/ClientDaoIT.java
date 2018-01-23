package daos;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import entities.Client;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})

public class ClientDaoIT {

    @Autowired
    private ClientDao clientDao;

    @Test
    public void testCreate() {
        assertEquals(7, clientDao.count());
    }
    
    @Test
    public void testFindByName() {
    	List<Client> client = clientDao.findByName("joey");
        assertNotNull(client);
    }
    
    @Test
    public void testFindByDNI() {
    	Client client = clientDao.findByDni("123");
        assertNull(client);
    }
    
    @Test
    public void testFindByEmail() {
    	Client client = clientDao.findByEmail("gregory@house.com");
    	assertNotNull(client);
    }

}
