package wrappers;

import java.math.BigDecimal;

import entities.Bungalow;
import entities.Client;

public class BookingModifyWrapper {
	
	private Bungalow bungalow; 
	
	private Client client;
	
	private String arrival;
	
	private String departure;
	
	private int id;
	
	private BigDecimal deposit;
	
	public BookingModifyWrapper() {
	
	}

	public BookingModifyWrapper(int id, Bungalow bungalow, Client client, String arrival, String departure, BigDecimal deposit) {
		this.id = id;
		this.bungalow = bungalow;
		this.client = client;
		this.arrival = arrival;
		this.departure = departure;
		this.deposit = deposit;
	}
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Bungalow getBungalow() {
		return bungalow;
	}

	public void setBungalow(Bungalow bungalow) {
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

	public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    @Override
    public String toString() {
        return "BookingModifyWrapper [bungalow=" + bungalow + ", client=" + client + ", arrival=" + arrival + ", departure=" + departure
                + ", id=" + id + ", deposit=" + deposit + "]";
    }
}
