package wrappers;

public class DateRangeAndBungalowNrWrapper {
	
	private String arrival;
	
	private String departure;
	
	private int bungalow;
	
	public DateRangeAndBungalowNrWrapper() {
	}

	public DateRangeAndBungalowNrWrapper(String arrival, String departure, int bungalow) {
		this.arrival = arrival;
		this.departure = departure;
		this.bungalow = bungalow;
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

	public int getBungalow() {
		return bungalow;
	}

	public void setBungalow(int bungalow) {
		this.bungalow = bungalow;
	}

	@Override
	public String toString() {
		return "DateRangeAndBungalowNrWrapper [arrival=" + arrival + ", departure=" + departure + ", bungalow="
				+ bungalow + "]";
	}

}
