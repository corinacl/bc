package services;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import config.ResourceNames;
import daos.AuthorizationDao;
import daos.BookingDao;
import daos.BungalowDao;
import daos.BungalowTypeDao;
import daos.ClientDao;
import daos.UserDao;
import entities.Authorization;
import entities.Booking;
import entities.Bungalow;
import entities.BungalowType;
import entities.Client;
import entities.Role;
import entities.User;

@Service
@Transactional
@PropertySource(ResourceNames.PROPERTIES)
public class Populate {

    private String adminUsername;

    private String adminPassword;

    @Autowired
    private Environment environment;

    @Autowired
    private UserDao userDao;

    @Autowired
    private AuthorizationDao authorizationDao;
    
    @Autowired
    private ClientDao clientDao;
    
    @Autowired
    private BookingDao bookingDao;
    
    @Autowired
    private BungalowDao bungalowDao;
    
    @Autowired
    private BungalowTypeDao bungalowTypeDao;
    
    @PostConstruct
    public void readAdmin() {
        adminUsername = environment.getProperty("admin.username");
        adminPassword = environment.getProperty("admin.password");
        createDefaultAdmin();
        populate(); //COMENTAR PARA TESTEAR
    }
    
    public void createDefaultAdmin() {
        User adminSaved = userDao.findByUsername(adminUsername);
        if (adminSaved == null) {
            User admin = new User(adminUsername, adminPassword);
            userDao.save(admin);
            authorizationDao.save(new Authorization(admin, Role.ADMIN));
        }
    }
    
    public void populate(){
    	//USUARIO
    	User manager = new User("manager", "manager");
    	userDao.save(manager);
    	authorizationDao.save(new Authorization(manager, Role.MANAGER));
    	
    	User authenticated = new User("auth", "auth");
    	userDao.save(authenticated);
    	authorizationDao.save(new Authorization(authenticated, Role.AUTHENTICATED));
    	
    	//CLIENTE
    	Client client = new Client("Cliente", "Genérico", "-", "-", null);
    	clientDao.save(client);
    	Client invitado = new Client("Cliente", "Invitado", "--", "--", null);
    	clientDao.save(invitado);
    	Client client1 = new Client("Richard", "Castle", "678123456", "12345678a", "richard@castle.com");
    	clientDao.save(client1);
    	Client client2 = new Client("Joey", "Triviani", "689000000", "98765432b", "joey@triviani.com");
    	clientDao.save(client2);
    	Client client3 = new Client("Sheldon", "Cooper", "612398765", "11111111c", "sheldon@cooper.com");
    	clientDao.save(client3);
    	Client client4 = new Client("Gregory", "House", "699000999", "00011122d", "gregory@house.com");
    	clientDao.save(client4);
    	Client client5 = new Client("Walter", "White", "611223344", "44334433d", "cuantos@gramos.com");
    	clientDao.save(client5);
    	
    	//BUNGALOW TYPE
    	BungalowType tipoA = new BungalowType("A", new BigDecimal(105.00),new BigDecimal(120.00),new BigDecimal(140.00),new BigDecimal(120.00),new BigDecimal(95.00));
    	bungalowTypeDao.save(tipoA);
    	BungalowType tipoB = new BungalowType("B", new BigDecimal(80.00),new BigDecimal(95.00),new BigDecimal(130.00),new BigDecimal(95.00),new BigDecimal(70.00));
    	bungalowTypeDao.save(tipoB);
    	BungalowType tipoC = new BungalowType("C", new BigDecimal(70.00),new BigDecimal(85.00),new BigDecimal(120.00),new BigDecimal(85.00),new BigDecimal(60.00));
    	bungalowTypeDao.save(tipoC);
    	
    	//BUNGALOW
    	Bungalow bungalow = new Bungalow(2, tipoB);
    	bungalowDao.save(bungalow);
    	Bungalow bungalow2 = new Bungalow(4, tipoB);
    	bungalowDao.save(bungalow2);
    	Bungalow bungalow3 = new Bungalow(5, tipoB);
    	bungalowDao.save(bungalow3);
    	Bungalow bungalow4 = new Bungalow(7, tipoB);
    	bungalowDao.save(bungalow4);
    	Bungalow bungalow5 = new Bungalow(8, tipoC);
    	bungalowDao.save(bungalow5);
    	Bungalow bungalow6 = new Bungalow(11, tipoB);
    	bungalowDao.save(bungalow6);
    	Bungalow bungalow7 = new Bungalow(14, tipoA);
    	bungalowDao.save(bungalow7);
    	Bungalow bungalow8 = new Bungalow(15, tipoA);
    	bungalowDao.save(bungalow8);
    	Bungalow bungalow9 = new Bungalow(16, tipoB);
    	bungalowDao.save(bungalow9);
    	Bungalow bungalow10 = new Bungalow(18, tipoA);
    	bungalowDao.save(bungalow10);
    	Bungalow bungalow11 = new Bungalow(19, tipoA);
    	bungalowDao.save(bungalow11);
    	Bungalow bungalow12 = new Bungalow(20, tipoA);
    	bungalowDao.save(bungalow12);
    	Bungalow bungalow13 = new Bungalow(24, tipoA);
    	bungalowDao.save(bungalow13);
    	Bungalow bungalow14 = new Bungalow(25, tipoA);
    	bungalowDao.save(bungalow14);
    	Bungalow bungalow15 = new Bungalow(26, tipoA);
    	bungalowDao.save(bungalow15);
    	Bungalow bungalow16 = new Bungalow(27, tipoC);
    	bungalowDao.save(bungalow16);
    	Bungalow bungalow17 = new Bungalow(28, tipoB);
    	bungalowDao.save(bungalow17);
    	Bungalow bungalow18 = new Bungalow(30, tipoC);
    	bungalowDao.save(bungalow18);
    	Bungalow bungalow19 = new Bungalow(31, tipoA);
    	bungalowDao.save(bungalow19);
    	Bungalow bungalow20 = new Bungalow(34, tipoB);
    	bungalowDao.save(bungalow20);
    	Bungalow bungalow21 = new Bungalow(35, tipoB);
    	bungalowDao.save(bungalow21);

    	Calendar arrival = Calendar.getInstance();
    	arrival.set(2018, 6, 3);
    	arrival.set(Calendar.SECOND, 0);
    	arrival.set(Calendar.MINUTE, 0);
    	arrival.set(Calendar.HOUR_OF_DAY, 14);
    	Calendar departure = Calendar.getInstance();
    	departure.set(2018, 6, 7);
    	departure.set(Calendar.SECOND, 0);
    	departure.set(Calendar.MINUTE, 0);
    	departure.set(Calendar.HOUR_OF_DAY, 12);
    	Calendar arrival2 = Calendar.getInstance();
    	arrival2.set(2018, 6, 6);
    	arrival2.set(Calendar.SECOND, 0);
    	arrival2.set(Calendar.MINUTE, 0);
    	arrival2.set(Calendar.HOUR_OF_DAY, 14);
    	Calendar departure2 = Calendar.getInstance();
    	departure2.set(2018, 6, 14);
    	departure2.set(Calendar.SECOND, 0);
    	departure2.set(Calendar.MINUTE, 0);
    	departure2.set(Calendar.HOUR_OF_DAY, 12);
    	Calendar arrival3 = Calendar.getInstance();
    	arrival3.set(2018, 5, 24);
    	arrival3.set(Calendar.SECOND, 0);
    	arrival3.set(Calendar.MINUTE, 0);
    	arrival3.set(Calendar.HOUR_OF_DAY, 14);
    	Calendar departure3 = Calendar.getInstance();
    	departure3.set(2018, 6, 3);
    	departure3.set(Calendar.SECOND, 0);
    	departure3.set(Calendar.MINUTE, 0);
    	departure3.set(Calendar.HOUR_OF_DAY, 12);
    	Calendar arrival4 = Calendar.getInstance();
    	arrival4.set(2018, 5, 8);
    	arrival4.set(Calendar.SECOND, 0);
    	arrival4.set(Calendar.MINUTE, 0);
    	arrival4.set(Calendar.HOUR_OF_DAY, 14);
    	Calendar departure4 = Calendar.getInstance();
    	departure4.set(2018, 5, 12);
    	departure4.set(Calendar.SECOND, 0);
    	departure4.set(Calendar.MINUTE, 0);
    	departure4.set(Calendar.HOUR_OF_DAY, 12);
    	Calendar arrival5 = Calendar.getInstance();
    	arrival5.set(2018, 6, 7);
    	arrival5.set(Calendar.SECOND, 0);
    	arrival5.set(Calendar.MINUTE, 0);
    	arrival5.set(Calendar.HOUR_OF_DAY, 14);
    	Calendar departure5 = Calendar.getInstance();
    	departure5.set(2018, 6, 12);
    	departure5.set(Calendar.SECOND, 0);
    	departure5.set(Calendar.MINUTE, 0);
    	departure5.set(Calendar.HOUR_OF_DAY, 12);
    	Calendar arrival6 = Calendar.getInstance();
    	arrival6.set(2018, 5, 2);
    	arrival6.set(Calendar.SECOND, 0);
    	arrival6.set(Calendar.MINUTE, 0);
    	arrival6.set(Calendar.HOUR_OF_DAY, 14);
    	Calendar departure6 = Calendar.getInstance();
    	departure6.set(2018, 5, 8);
    	departure6.set(Calendar.SECOND, 0);
    	departure6.set(Calendar.MINUTE, 0);
    	departure6.set(Calendar.HOUR_OF_DAY, 12);

    	
    	//BOOKING
    	Booking booking1 = new Booking(bungalow, client4, arrival5, departure5, new BigDecimal(135.00), new BigDecimal(0));
    	bookingDao.save(booking1);
    	Booking booking = new Booking(bungalow, client1, arrival, departure, new BigDecimal(267.00), new BigDecimal(0));
    	bookingDao.save(booking);
    	Booking booking2 = new Booking(bungalow2, client2, arrival2, departure2, new BigDecimal(586.00), new BigDecimal(150.00));
    	bookingDao.save(booking2);
    	Booking booking3 = new Booking(bungalow12, client3, arrival3, departure3, new BigDecimal(85.00), new BigDecimal(0));
    	bookingDao.save(booking3);
    	Booking booking4 = new Booking(bungalow16, client2, arrival2, departure5, new BigDecimal(185.00), new BigDecimal(0));
    	bookingDao.save(booking4);
    	Booking booking5 = new Booking(bungalow6, client3, arrival, departure2, new BigDecimal(95.00), new BigDecimal(0));
    	bookingDao.save(booking5);
    	Booking booking6 = new Booking(bungalow8, client2, arrival4, departure3, new BigDecimal(555.00), new BigDecimal(200.00));
    	bookingDao.save(booking6);
    	Booking booking7 = new Booking(bungalow, client2, arrival3, departure3, new BigDecimal(125.00), new BigDecimal(0));
    	bookingDao.save(booking7);
    	Booking booking8 = new Booking(bungalow3, client2, arrival4, departure4, new BigDecimal(85.00), new BigDecimal(25.00));
    	bookingDao.save(booking8);
    	Booking booking9 = new Booking(bungalow3, client4, arrival6, departure6, new BigDecimal(85.00), new BigDecimal(0));
    	bookingDao.save(booking9);
    	Booking booking10 = new Booking(bungalow19, client2, arrival3, departure3, new BigDecimal(125.00), new BigDecimal(0));
    	bookingDao.save(booking10);
    	Booking booking11 = new Booking(bungalow17, client2, arrival4, departure4, new BigDecimal(320.00), new BigDecimal(105.00));
    	bookingDao.save(booking11);
    	Booking booking12 = new Booking(bungalow14, client4, arrival6, departure6, new BigDecimal(185.00), new BigDecimal(50.00));
    	bookingDao.save(booking12);
    }

}
