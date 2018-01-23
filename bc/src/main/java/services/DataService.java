package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import daos.AuthorizationDao;
import daos.BookingDao;
import daos.BungalowDao;
import daos.BungalowTypeDao;
import daos.ClientDao;
import daos.TokenDao;
import daos.UserDao;

@Service
public class DataService {

    @Autowired
    private Populate populate;

    @Autowired
    private AuthorizationDao authorizationDao;

    @Autowired
    private TokenDao tokenDao;

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private ClientDao clientDao;
    
    @Autowired
    private BungalowDao bugalowDao;
    
    @Autowired
    private BungalowTypeDao bungalowTypeDao;
    
    @Autowired
    private BookingDao bookingDao;

    public void deleteAllExceptAdmin() {
    	authorizationDao.deleteAll();
        tokenDao.deleteAll();
        userDao.deleteAll();
        
        bookingDao.deleteAll();
        clientDao.deleteAll();
        bugalowDao.deleteAll();
    	bungalowTypeDao.deleteAll();

        populate.createDefaultAdmin();
    }
    
    public void populate() {
        populate.populate();
    }

}
