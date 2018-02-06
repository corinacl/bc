package controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.BookingDao;
import daos.BungalowDao;
import entities.Booking;
import entities.Bungalow;
import wrappers.PlanningWrapper;

@Controller
public class PlanningController {
	
	private BookingController bookingController;
	
	private BungalowDao bungalowDao;
	
	@Autowired
	public void setBungalowgDao(BungalowDao bungalowDao) {
		this.bungalowDao = bungalowDao;
	}
	
	private BookingDao bookingDao;
	
	@Autowired
	public void setBookingDao(BookingDao bookingDao) {
		this.bookingDao = bookingDao;
	}
		
	@Autowired
	public void setBookingController(BookingController bookingController) {
		this.bookingController = bookingController;
	}
	
	public Map<Integer, List<Integer>> getBookingsForPlanning(PlanningWrapper planningWrapper) {
		Calendar start = Calendar.getInstance();
		start.set(planningWrapper.getYear(), planningWrapper.getMonth()-1, start.getMinimum(Calendar.DAY_OF_MONTH));
		start.set(Calendar.SECOND, 0);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.HOUR_OF_DAY, 14);
		
		Calendar end = Calendar.getInstance();
		end.set(planningWrapper.getYear(), planningWrapper.getMonth()-1, start.getActualMaximum(Calendar.DAY_OF_MONTH));
		end.set(Calendar.SECOND, 0);
		end.set(Calendar.MINUTE, 0);
		end.set(Calendar.HOUR_OF_DAY, 12);
				
		List<Booking> listOfBookingInSelectedMonth = bookingDao.findByDatesBetween(start, end);

		return completePlanning(getDaysOfBookingsPerBungalow(listOfBookingInSelectedMonth, planningWrapper), start);
	}
	
	public Calendar isArrivalInSelectedMonth(Calendar arrival, PlanningWrapper planningWrapper){
		boolean isArrivalInSelectedMonth = false;
		
		while(!isArrivalInSelectedMonth){
			if( (arrival.get(Calendar.MONTH)+1) == planningWrapper.getMonth()){
				isArrivalInSelectedMonth = true;
			}else{
				arrival.add(Calendar.DAY_OF_MONTH, 1);
			}
		}
		return arrival;
	}
	
	public Calendar isDepartureInSelectedMonth(Calendar departure, PlanningWrapper planningWrapper){
		boolean isDepartureInSelectedMonth = false;
		
		while(!isDepartureInSelectedMonth){
			if( (departure.get(Calendar.MONTH)+1) == planningWrapper.getMonth()){
				isDepartureInSelectedMonth = true;
			}else{
				departure.add(Calendar.DAY_OF_MONTH, -1);
			}
		}
		return departure;
	}
	
	public BigDecimal getTotalNights(String arrivalDate, String departureDate){
		long arrivalMillis = bookingController.createArrivalDate(arrivalDate).getTimeInMillis();
		long departureMillis = bookingController.createArrivalDate(departureDate).getTimeInMillis();
		long diff = departureMillis - arrivalMillis;
		
		BigDecimal diffNights = new BigDecimal (diff / (24 * 60 * 60 * 1000));
		
		return diffNights;
}
	
	public List<Integer> getListOfDays (Booking booking, PlanningWrapper planningWrapper) {
		Calendar arrival = booking.getArrivalDate();
		Calendar departure = booking.getDepartureDate();
		List<Integer> bookingDays = new ArrayList<Integer>();
		List<Integer> fullMonthDays = new ArrayList<Integer>();
		
		arrival = isArrivalInSelectedMonth(arrival, planningWrapper);
		departure = isDepartureInSelectedMonth(departure, planningWrapper);

		String arrivalString = convertCalendarToString(arrival);
		String departureString = convertCalendarToString(departure);
		
		BigDecimal nights = getTotalNights(arrivalString, departureString);

		for (int i=0; i<=nights.intValue() ;i++){
			bookingDays.add(arrival.get(Calendar.DAY_OF_MONTH)+i);
		}
		
		for(int i=1; i<= arrival.getActualMaximum(Calendar.DAY_OF_MONTH); i++){
			if(bookingDays.contains(i)){
				fullMonthDays.add(1);
			}else{
				fullMonthDays.add(0);
			}
		}
		return fullMonthDays;
	}
	
	public String convertCalendarToString(Calendar date){
		String convertedDate= String.valueOf(date.get(Calendar.DAY_OF_MONTH)) 
				+ "/" + String.valueOf(date.get(Calendar.MONTH)+1)
				+ "/" + String.valueOf(date.get(Calendar.YEAR));
		
		return convertedDate;
	}
		
	
	public Map<Integer, List<Integer>> getDaysOfBookingsPerBungalow(List<Booking> listBookings, PlanningWrapper planningWrapper){
		Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		List<Bungalow> listBungalows = bungalowDao.findAll();
	
		for(Booking booking : listBookings){
			for(Bungalow bungalow : listBungalows){
				if (bungalow.getNumber() == booking.getBungalow().getNumber()){
					if(!map.containsKey(bungalow.getNumber())){
						map.put(bungalow.getNumber(), getListOfDays(booking, planningWrapper));
					}else{
						List<Integer> mixedList = new ArrayList<Integer>();
						List<Integer> existingList = map.get(bungalow.getNumber());
						List<Integer> newList = getListOfDays(booking, planningWrapper);
						for (int i=1; i<=existingList.size(); i++){
							if( (existingList.get(i-1) == 0) && (newList.get(i-1) == 0)){
								mixedList.add(0);
							}else if ((existingList.get(i-1) == 1) && (newList.get(i-1) == 1) || (existingList.get(i-1) == 2)){
								mixedList.add(2);
							}else{
								mixedList.add(1);
							}
						}
						map.put(bungalow.getNumber(), mixedList);
					}
				}
			}
		}
		return map;
	}
	
	public Map<Integer, List<Integer>> completePlanning (Map<Integer, List<Integer>> map, Calendar date){
		List<Bungalow> listBungalows = bungalowDao.findAll();
		List<Integer> newDayList = new ArrayList<Integer>();
		
		if (map.isEmpty()){
			for(Bungalow bungalow : listBungalows){
				newDayList.clear();
				for (int i=1; i<=date.getActualMaximum(Calendar.DAY_OF_MONTH); i++){
					newDayList.add(0);
				}
			map.put(bungalow.getNumber(), newDayList);
			}
		}else{
			for(Bungalow bungalow : listBungalows){
				if(!map.containsKey(bungalow.getNumber())){
					newDayList.clear();
					for (int i=1; i<=date.getActualMaximum(Calendar.DAY_OF_MONTH); i++){
						newDayList.add(0);
					}
					map.put(bungalow.getNumber(), newDayList);
				}
			}
		}
		return map;
	}
	
	public boolean validatePlanningWrapper(PlanningWrapper planningWrapper){
		if((planningWrapper.getMonth() == 0) || (planningWrapper.getYear() == 0)){
			return false;
		}else{
			return true;
		}
	}

}
