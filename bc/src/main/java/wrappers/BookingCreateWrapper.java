package wrappers;

public class BookingCreateWrapper {
	
	private int idCliente;
	
	private int idBungalow; 
	
	private String arrival;
	
	private String departure;
	
	public BookingCreateWrapper() {
	}

	public BookingCreateWrapper(int idCliente, int idBungalow, String arrival, String departure) {
		this.idCliente = idCliente;
		this.idBungalow = idBungalow;
		this.arrival = arrival;
		this.departure = departure;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdBungalow() {
		return idBungalow;
	}

	public void setIdBungalow(int idBungalow) {
		this.idBungalow = idBungalow;
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
		return "BookingCreateWrapper [bungalow=" + idBungalow + ", idcliente=" + idCliente + ", arrival=" + arrival
				+ ", departure=" + departure + "]";
	}
}
