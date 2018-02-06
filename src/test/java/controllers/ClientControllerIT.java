package controllers;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import api.exceptions.DuplicatedEntryClientException;
import api.exceptions.IncompleteDataSearchException;
import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsPersistenceConfig;
import daos.ClientDao;
import entities.Client;
import wrappers.ClientCreateWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class ClientControllerIT {

    @Autowired
    private ClientController clientController;
    
    @Autowired
    private ClientDao clientDao;
    
/*    @Test
    public void testGetAll() {
        assertEquals(7, clientController.getAll().size());
    }*/

    @Test
    public void testGetClientById() {
    	Client client = clientController.getClientById(5);
    	assertEquals(client.getName(), "Sheldon");
    }
    
    @Test
    public void testCreateClient() throws DuplicatedEntryClientException {
    	ClientCreateWrapper createClient = new ClientCreateWrapper("Kate", "Beckett", null, "123321", null);
    	Client client = clientController.createClient(createClient);
    	assertNotNull(clientDao.findAllByDni("123321"));
    	clientDao.delete(client);
    }
    
    @Test
    public void searchClientBy() throws IncompleteDataSearchException{
    	List<Client> clients = clientController.searchClientBy("name", "sheldon");
    	assertNotNull(clients);
    }
}
