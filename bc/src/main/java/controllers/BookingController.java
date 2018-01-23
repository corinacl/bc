package controllers;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import api.exceptions.IncompleteDataSearchException;
import api.exceptions.IncompleteModifyBookingException;
import daos.BookingDao;
import daos.ClientDao;
import daos.BungalowDao;
import entities.Booking;
import entities.Bungalow;
import entities.Client;
import wrappers.BookingCreateWrapper;
import wrappers.BookingModifyWrapper;
import wrappers.BookingSaveModifiedWrapper;
import wrappers.ClientIdWrapper;
import wrappers.DateRangeAndBungalowNrWrapper;
import wrappers.DateRangeWrapper;

@Controller
public class BookingController {

	private BookingDao bookingDao;
	
	private ClientDao clientDao;
	
	private BungalowDao bungalowDao;
	
	@Autowired
	public void setBookingDao(BookingDao bookingDao) {
		this.bookingDao = bookingDao;
	}
	
	@Autowired
	public void setClientDao(ClientDao clientDao) {
		this.clientDao = clientDao;
	}
	
	@Autowired
	public void setBungalowDao(BungalowDao bungalowDao) {
		this.bungalowDao = bungalowDao;
	}
	
	public List<Booking> getAll(){
		List<Booking> bookings = bookingDao.findAll();
		return bookings;
	}
	
	public Calendar createArrivalDate (String createArrivalDate){
		String delims = "/";
		String[] tokens = createArrivalDate.split(delims);
		
		Calendar date = Calendar.getInstance();
		date.set(Integer.parseInt(tokens[2]), Integer.parseInt(tokens[1])-1, Integer.parseInt(tokens[0]));
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.HOUR_OF_DAY, 14);
 
		return date;
	}
	
	public Calendar createDepartureDate (String createDepartureDate){
		String delims = "/";
		String[] tokens = createDepartureDate.split(delims);
		
		Calendar date = Calendar.getInstance();
		date.set(Integer.parseInt(tokens[2]), Integer.parseInt(tokens[1])-1, Integer.parseInt(tokens[0]));
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.HOUR_OF_DAY, 12);
 
		return date;
	}
		
	public Calendar createArrivalDateToCompare(Calendar cal, String month){
		Calendar date = Calendar.getInstance();
		switch (month){
		case "janToApr":
			date.set(cal.get(Calendar.YEAR), 0, 6);
			date.set(Calendar.SECOND, 0);
			date.set(Calendar.MINUTE, 0);
			date.set(Calendar.HOUR_OF_DAY, 11);
			break;
		case "aprToJun":
			date.set(cal.get(Calendar.YEAR), 3, 15);
			date.set(Calendar.SECOND, 0);
			date.set(Calendar.MINUTE, 0);
			date.set(Calendar.HOUR_OF_DAY, 11);
			break;
		case "julToOct":
			date.set(cal.get(Calendar.YEAR), 6, 1);
			date.set(Calendar.SECOND, 0);
			date.set(Calendar.MINUTE, 0);
			date.set(Calendar.HOUR_OF_DAY, 11);
			break;
		case "octToDec":
			date.set(cal.get(Calendar.YEAR), 9, 15);
			date.set(Calendar.SECOND, 0);
			date.set(Calendar.MINUTE, 0);
			date.set(Calendar.HOUR_OF_DAY, 11);
			break;
		case "decToJan":
			date.set(cal.get(Calendar.YEAR), 11, 22);
			date.set(Calendar.SECOND, 0);
			date.set(Calendar.MINUTE, 0);
			date.set(Calendar.HOUR_OF_DAY, 11);
			break;
		default:
			break;
		}
		return date;
	}
	
	public Calendar createDepartureDateToCompare(Calendar cal, String month){
		Calendar date = Calendar.getInstance();
		switch (month){
			case "janToApr":
				date.set(cal.get(Calendar.YEAR), 3, 14);
				date.set(Calendar.SECOND, 0);
				date.set(Calendar.MINUTE, 0);
				date.set(Calendar.HOUR_OF_DAY, 15);
				break;
			case "aprToJun":
				date.set(cal.get(Calendar.YEAR), 5, 30);
				date.set(Calendar.SECOND, 0);
				date.set(Calendar.MINUTE, 0);
				date.set(Calendar.HOUR_OF_DAY, 15);
				break;
			case "julToOct":
				date.set(cal.get(Calendar.YEAR), 9, 14);
				date.set(Calendar.SECOND, 0);
				date.set(Calendar.MINUTE, 0);
				date.set(Calendar.HOUR_OF_DAY, 15);
				break;
			case "octToDec":
				date.set(cal.get(Calendar.YEAR), 11, 21);
				date.set(Calendar.SECOND, 0);
				date.set(Calendar.MINUTE, 0);
				date.set(Calendar.HOUR_OF_DAY, 15);
				break;
			case "decToJan":
				date.set(cal.get(Calendar.YEAR), 0, 5);
				date.set(Calendar.SECOND, 0);
				date.set(Calendar.MINUTE, 0);
				date.set(Calendar.HOUR_OF_DAY, 15);
				break;
			default:
				break;
		}
		return date;
	}
	
	public BigDecimal calculateTotalPrice(String arrivalDate, String departureDate, int idBungalow, Client client){
		Calendar arrival = createArrivalDate(arrivalDate);
		Calendar departure = createDepartureDate(departureDate);
		Bungalow bungalow = bungalowDao.findOne(idBungalow);
		BigDecimal totalPrice = new BigDecimal(0);
		boolean endOfDate = false;
		
		if (!client.getSurname().equals("Invitado")){
			while(!endOfDate){			
				if(!arrival.before(createArrivalDateToCompare(arrival, "janToApr")) && !arrival.after(createDepartureDateToCompare(arrival, "janToApr"))){
					totalPrice = totalPrice.add(bungalow.getType().getJanToAprPrice());
				} else if (!arrival.before(createArrivalDateToCompare(arrival, "octToDec")) && !arrival.after(createDepartureDateToCompare(arrival, "octToDec"))){
					totalPrice = totalPrice.add(bungalow.getType().getOctToDecPrice());
				} else if (!arrival.before(createArrivalDateToCompare(arrival, "decToJan")) && !arrival.after(createDepartureDateToCompare(arrival, "decToJan"))){
					totalPrice = totalPrice.add(bungalow.getType().getDecToJanPrice());
				} else if (!arrival.before(createArrivalDateToCompare(arrival, "aprToJun")) && !arrival.after(createDepartureDateToCompare(arrival, "aprToJun"))){
					totalPrice = totalPrice.add(bungalow.getType().getAprToJunPrice());
				} else if (!arrival.before(createArrivalDateToCompare(arrival, "julToOct")) && !arrival.after(createDepartureDateToCompare(arrival, "julToOct"))){
					totalPrice = totalPrice.add(bungalow.getType().getJulToOctPrice());
				}
	
				if (arrival.after(departure)){
					endOfDate = true;
				}else{
					arrival.add(Calendar.DAY_OF_MONTH, 1);
				}
			}
			return totalPrice;
		}else{
			return totalPrice;
		}
	}
	
	public Booking createBooking(BookingCreateWrapper bookingCreateWrapper) {
		Bungalow bungalow = bungalowDao.findOne(bookingCreateWrapper.getIdBungalow());
		Client client = clientDao.findOne(bookingCreateWrapper.getIdCliente());

		Booking booking = new Booking (bungalow, client, createArrivalDate(bookingCreateWrapper.getArrival()), 
				createDepartureDate(bookingCreateWrapper.getDeparture()), 
				calculateTotalPrice(bookingCreateWrapper.getArrival(), bookingCreateWrapper.getDeparture(), bookingCreateWrapper.getIdBungalow(), client));

		return bookingDao.save(booking);
	}
	
	public BookingModifyWrapper getBookingById(int id){
		Booking b = bookingDao.findOne(id);
		String arrival= String.valueOf(b.getArrivalDate().get(Calendar.DAY_OF_MONTH)) 
				+ "/" + String.valueOf(b.getArrivalDate().get(Calendar.MONTH)+1)
				+ "/" + String.valueOf(b.getArrivalDate().get(Calendar.YEAR));
		
		String departure= String.valueOf(b.getDepartureDate().get(Calendar.DAY_OF_MONTH)) 
				+ "/" + String.valueOf(b.getDepartureDate().get(Calendar.MONTH)+1)
				+ "/" + String.valueOf(b.getDepartureDate().get(Calendar.YEAR));
		
		BookingModifyWrapper booking = new BookingModifyWrapper(b.getId(), b.getBungalow(), b.getClient(), arrival, departure);
		
		return booking;
	}
	
	public void bookingModify (BookingSaveModifiedWrapper bookingSaveModifiedWrapper) throws IncompleteModifyBookingException {
		Booking booking = bookingDao.findOne(bookingSaveModifiedWrapper.getId());
		Bungalow bungalow = bungalowDao.findOne(bookingSaveModifiedWrapper.getIdBungalow());
		Client client = clientDao.findOne(bookingSaveModifiedWrapper.getIdClient());
		
		if((bungalow==null) || (bookingSaveModifiedWrapper.getArrival() == null) || (bookingSaveModifiedWrapper.getDeparture() == null)){
			throw new IncompleteModifyBookingException();
		}else{
			booking.setBungalow(bungalow);
			booking.setClient(client);
			booking.setArrivalDate(createArrivalDate(bookingSaveModifiedWrapper.getArrival()));
			booking.setDepartureDate(createDepartureDate(bookingSaveModifiedWrapper.getDeparture()));
			booking.setTotalPrice(calculateTotalPrice(bookingSaveModifiedWrapper.getArrival(), bookingSaveModifiedWrapper.getDeparture(), bookingSaveModifiedWrapper.getIdBungalow(), client));
			
			this.bookingDao.save(booking); 
		}
	}
	
	public List<Booking> getBookingByDateRange(DateRangeWrapper dateRangeWrapper) throws IncompleteModifyBookingException {
		if ((dateRangeWrapper.getArrival() == null) || (dateRangeWrapper.getDeparture() == null)){
			throw new IncompleteModifyBookingException();
		}else{
			return bookingDao.findByDatesBetween(
					createArrivalDate(dateRangeWrapper.getArrival()), 
					createDepartureDate(dateRangeWrapper.getDeparture()));
		}
	}

	public List<Booking> getBookingsByClient(ClientIdWrapper clientIdWrapper) {
		return bookingDao.findByClient(clientDao.findById(clientIdWrapper.getId()));
	}

	public void deleteBooking (int id) {
		Booking booking = bookingDao.findOne(id);
		bookingDao.delete(booking);
	}

	public List<Booking> searchBookings(DateRangeAndBungalowNrWrapper dateRangeAndBungalowNrWrapper) throws IncompleteDataSearchException {
		if (dateRangeAndBungalowNrWrapper.getArrival() == null || dateRangeAndBungalowNrWrapper.getDeparture() == null){
			throw new IncompleteDataSearchException ();
		}else{
			Bungalow bungalow = bungalowDao.findOne(dateRangeAndBungalowNrWrapper.getBungalow());
			if (bungalow == null){
				return bookingDao.findByBookingsBetween(createArrivalDate(dateRangeAndBungalowNrWrapper.getArrival()), createDepartureDate(dateRangeAndBungalowNrWrapper.getDeparture()));
			}else{
				return bookingDao.findByBookingsByBungalowBetween(
						createArrivalDate(dateRangeAndBungalowNrWrapper.getArrival()), 
						createDepartureDate(dateRangeAndBungalowNrWrapper.getDeparture()), 
						bungalow);
			}
		}
	}
}
