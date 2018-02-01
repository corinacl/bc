package wrappers;

import java.math.BigDecimal;

public class BookingSaveModifiedWrapper {
	
	private int idBungalow; 
	
	private int idClient;
	
	private String arrival;
	
	private String departure;
	
	private int id;
	
	private BigDecimal deposit;
	
	public BookingSaveModifiedWrapper() {
	
	}

	public BookingSaveModifiedWrapper(int id, int idBungalow, int idClient, String arrival, String departure, BigDecimal deposit) {
		this.id = id;
		this.idBungalow = idBungalow;
		this.idClient = idClient;
		this.arrival = arrival;
		this.departure = departure;
		this.deposit = deposit;
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

	public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    @Override
    public String toString() {
        return "BookingSaveModifiedWrapper [idBungalow=" + idBungalow + ", idClient=" + idClient + ", arrival=" + arrival + ", departure="
                + departure + ", id=" + id + ", deposit=" + deposit + "]";
    }
}
