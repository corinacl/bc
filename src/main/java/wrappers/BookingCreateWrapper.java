package wrappers;

import java.math.BigDecimal;

public class BookingCreateWrapper {
	
	private int idCliente;
	
	private int idBungalow; 
	
	private String arrival;
	
	private String departure;
	
	private BigDecimal deposit;
	
	public BookingCreateWrapper() {
	}

	public BookingCreateWrapper(int idCliente, int idBungalow, String arrival, String departure, BigDecimal deposit) {
		this.idCliente = idCliente;
		this.idBungalow = idBungalow;
		this.arrival = arrival;
		this.departure = departure;
		this.deposit = deposit;
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

	public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    @Override
    public String toString() {
        return "BookingCreateWrapper [idCliente=" + idCliente + ", idBungalow=" + idBungalow + ", arrival=" + arrival + ", departure="
                + departure + ", deposit=" + deposit + "]";
    }
}
