package api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.exceptions.IncompleteDataSearchException;
import api.exceptions.IncompleteBookingException;

import org.springframework.web.bind.annotation.RequestMethod;

import controllers.BookingController;
import entities.Booking;
import wrappers.BookingCreateWrapper;
import wrappers.BookingModifyWrapper;
import wrappers.BookingSaveModifiedWrapper;
import wrappers.ClientIdWrapper;
import wrappers.DateRangeAndBungalowNrWrapper;
import wrappers.DateRangeWrapper;

@RestController
@RequestMapping(Uris.BOOKINGS)
public class BookingResource {

	private BookingController bookingController;
	
	@Autowired
	public void setBookingController(BookingController bookingController){
		this.bookingController = bookingController;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Booking> listBookings(){
		return bookingController.getAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public Booking createBooking(@RequestBody BookingCreateWrapper bookingCreateWrapper) throws IncompleteBookingException{
    	return bookingController.createBooking(bookingCreateWrapper);
    }
	
	@RequestMapping(method = RequestMethod.PUT)
    public void modifyBooking (@RequestBody BookingSaveModifiedWrapper bookingWrapper) throws IncompleteBookingException {
    	this.bookingController.bookingModify(bookingWrapper);
    }
	
	@RequestMapping(value = Uris.ID, method = RequestMethod.GET)  
    public BookingModifyWrapper getBookingById(@PathVariable(value = "id") int id){
        return bookingController.getBookingById(id);
    }
	
	@RequestMapping(value = Uris.SEARCH, method = RequestMethod.POST)
    public List<Booking> search (@RequestBody DateRangeWrapper dateRangeWrapper) throws IncompleteBookingException{
    	return bookingController.getBookingByDateRange(dateRangeWrapper);
    }
	
	@RequestMapping(value = Uris.SEARCH + Uris.BOOKINGS, method = RequestMethod.POST)
    public List<Booking> searchBookings (@RequestBody DateRangeAndBungalowNrWrapper dateRangeAndBungalowNrWrapper) throws IncompleteDataSearchException {
    	return bookingController.searchBookings(dateRangeAndBungalowNrWrapper);
    }
	
	@RequestMapping(value = Uris.CLIENTS, method = RequestMethod.POST)
    public List<Booking> getBookingsByClient (@RequestBody ClientIdWrapper clientIdWrapper){
    	return bookingController.getBookingsByClient(clientIdWrapper);
    }
	
	@RequestMapping(value = Uris.ID, method = RequestMethod.DELETE)
    public void deleteBooking (@PathVariable(value = "id") int id){
    	this.bookingController.deleteBooking(id);
    }
}
