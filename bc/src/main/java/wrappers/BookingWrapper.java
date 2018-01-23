package wrappers;

import java.util.Calendar;

public class BookingWrapper {
	
	private String bungalow; 
	
	private int idcliente;
	
	private Calendar arrival;
	
	private Calendar departure;
	
	private int id;
	
	public BookingWrapper() {
	
	}

	public BookingWrapper(int id, String bungalow, int idcliente, Calendar arrival, Calendar departure) {
		this.id = id;
		this.bungalow = bungalow;
		this.idcliente = idcliente;
		this.arrival = arrival;
		this.departure = departure;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getBungalow() {
		return bungalow;
	}

	public void setBungalow(String bungalow) {
		this.bungalow = bungalow;
	}
	
	public int getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}

	public Calendar getArrival() {
		return arrival;
	}

	public void setArrival(Calendar arrival) {
		this.arrival = arrival;
	}

	public Calendar getDeparture() {
		return departure;
	}

	public void setDeparture(Calendar departure) {
		this.departure = departure;
	}

	@Override
	public String toString() {
		return "BookingCreateWrapper [id=" + id + ", bungalow=" + bungalow + ", idcliente=" + idcliente + ", arrival=" + arrival
				+ ", departure=" + departure + "]";
	}
}
