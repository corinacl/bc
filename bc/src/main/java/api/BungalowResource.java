package api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.exceptions.IncompleteModifyBookingException;

import org.springframework.web.bind.annotation.RequestMethod;

import controllers.BungalowController;
import entities.Bungalow;
import wrappers.DateRangeAndIdBookingWrapper;
import wrappers.DateRangeWrapper;

@RestController
@RequestMapping(Uris.BUNGALOWS)
public class BungalowResource {

	private BungalowController bungalowController;
	
	@Autowired
	public void setBungalowController(BungalowController bungalowController){
		this.bungalowController = bungalowController;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Bungalow> listBungalows(){
		return bungalowController.getAll();
	}
	
	@RequestMapping(value = Uris.SEARCH, method = RequestMethod.POST)
    public List<Bungalow> searchByBungalow (@RequestBody DateRangeWrapper dateRangeWrapper) throws IncompleteModifyBookingException{
    	return bungalowController.getAvailabilityInDates(dateRangeWrapper);
    }
	
	@RequestMapping(value = Uris.SEARCH + Uris.MODIFY, method = RequestMethod.POST)
    public List<Bungalow> searchByBungalowForModify (@RequestBody DateRangeAndIdBookingWrapper dateRangeAndIdBookingWrapper) throws IncompleteModifyBookingException{
    	return bungalowController.getAvailabilityInDatesForModify(dateRangeAndIdBookingWrapper);
    }

}
