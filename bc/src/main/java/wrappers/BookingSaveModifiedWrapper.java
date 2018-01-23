package wrappers;


public class BookingSaveModifiedWrapper {
	
	private int idBungalow; 
	
	private int idClient;
	
	private String arrival;
	
	private String departure;
	
	private int id;
	
	public BookingSaveModifiedWrapper() {
	
	}

	public BookingSaveModifiedWrapper(int id, int idBungalow, int idClient, String arrival, String departure) {
		this.id = id;
		this.idBungalow = idBungalow;
		this.idClient = idClient;
		this.arrival = arrival;
		this.departure = departure;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getIdBungalow() {
		return idBungalow;
	}

	public void setIdBungalow(int idBungalow) {
		this.idBungalow = idBungalow;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
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
		return "BookingCreateWrapper [id=" + id + ", bungalow=" + idBungalow + ", idclient=" + idClient + ", arrival=" + arrival
				+ ", departure=" + departure + "]";
	}
}
