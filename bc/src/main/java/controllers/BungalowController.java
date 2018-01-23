package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import api.exceptions.IncompleteModifyBookingException;
import daos.BungalowDao;
import entities.Bungalow;
import wrappers.DateRangeAndIdBookingWrapper;
import wrappers.DateRangeWrapper;

@Controller
public class BungalowController {

	private BungalowDao bungalowDao;
	
	private BookingController bookingController;
		
	@Autowired
	public void setBungalowDao(BungalowDao bungalowDao) {
		this.bungalowDao = bungalowDao;
	}
	
	@Autowired
	public void setBookingController(BookingController bookingController) {
		this.bookingController = bookingController;
	}
	
	public List<Bungalow> getAll(){
		List<Bungalow> bungalows = bungalowDao.findAll();
		return bungalows;
	}
	
	public List<Bungalow> getAvailabilityInDates(DateRangeWrapper dateRangeWrapper) throws IncompleteModifyBookingException{
		if ((dateRangeWrapper.getArrival() == null) || (dateRangeWrapper.getDeparture() == null)){
			throw new IncompleteModifyBookingException("Fechas incompletas.");
		}else{
			return bungalowDao.findAvailability(
					bookingController.createArrivalDate(dateRangeWrapper.getArrival()), 
					bookingController.createDepartureDate(dateRangeWrapper.getDeparture()));
		}
	}
	
	public List<Bungalow> getAvailabilityInDatesForModify(DateRangeAndIdBookingWrapper dateRangeAndIdBookingWrapper) throws IncompleteModifyBookingException{
		if ((dateRangeAndIdBookingWrapper.getArrival() == null) || (dateRangeAndIdBookingWrapper.getDeparture() == null)){
			throw new IncompleteModifyBookingException("Fechas incompletas.");
		}else{
			return bungalowDao.findAvailabilityForModify(
					bookingController.createArrivalDate(dateRangeAndIdBookingWrapper.getArrival()), 
					bookingController.createDepartureDate(dateRangeAndIdBookingWrapper.getDeparture()),
					dateRangeAndIdBookingWrapper.getIdBooking());
		}
	}
}
