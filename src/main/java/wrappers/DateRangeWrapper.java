package wrappers;

public class DateRangeWrapper {
	
	private String arrival;
	
	private String departure;
	
	public DateRangeWrapper() {
	}

	public DateRangeWrapper(String arrival, String departure) {
		this.arrival = arrival;
		this.departure = departure;
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

	@Override
	public String toString() {
		return "BookingCreateWrapper [arrival=" + arrival + ", departure=" + departure + "]";
	}
}
