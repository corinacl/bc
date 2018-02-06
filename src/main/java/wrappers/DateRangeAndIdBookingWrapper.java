package wrappers;

public class DateRangeAndIdBookingWrapper {

	private String arrival;
	
	private String departure;
	
	private int idBooking;
	
	public DateRangeAndIdBookingWrapper() {
	}

	public DateRangeAndIdBookingWrapper(String arrival, String departure, int idBooking) {
		this.arrival = arrival;
		this.departure = departure;
		this.idBooking = idBooking;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}
	
	public int getIdBooking() {
		return idBooking;
	}

	public void setIdBooking(int idBooking) {
		this.idBooking = idBooking;
	}
	
	@Override
	public String toString() {
		return "DateRangeAndIdBookingWrapper [arrival=" + arrival + ", departure=" + departure + ", idBooking="
				+ idBooking + "]";
	}
}
