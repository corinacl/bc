package wrappers;

import java.math.BigDecimal;

public class BungalowTypeWrapper {
	
	private int id;
	
	private String type;
	
	private BigDecimal julToOctPrice;
	private BigDecimal octToDecPrice;
	private BigDecimal decToJanPrice;
	private BigDecimal janToAprPrice;
	private BigDecimal aprToJunPrice;
	
	public BungalowTypeWrapper() {
	
	}

	public BungalowTypeWrapper(int id, String type, BigDecimal julToOctPrice, BigDecimal octToDecPrice,
			BigDecimal decToJanPrice, BigDecimal janToAprPrice, BigDecimal aprToJunPrice) {
		this.id = id;
		this.type = type;
		this.julToOctPrice = julToOctPrice;
		this.octToDecPrice = octToDecPrice;
		this.decToJanPrice = decToJanPrice;
		this.janToAprPrice = janToAprPrice;
		this.aprToJunPrice = aprToJunPrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getJulToOctPrice() {
		return julToOctPrice;
	}

	public void setJulToOctPrice(BigDecimal julToOctPrice) {
		this.julToOctPrice = julToOctPrice;
	}

	public BigDecimal getOctToDecPrice() {
		return octToDecPrice;
	}

	public void setOctToDecPrice(BigDecimal octToDecPrice) {
		this.octToDecPrice = octToDecPrice;
	}

	public BigDecimal getDecToJanPrice() {
		return decToJanPrice;
	}

	public void setDecToJanPrice(BigDecimal decToJanPrice) {
		this.decToJanPrice = decToJanPrice;
	}

	public BigDecimal getJanToAprPrice() {
		return janToAprPrice;
	}

	public void setJanToAprPrice(BigDecimal janToAprPrice) {
		this.janToAprPrice = janToAprPrice;
	}

	public BigDecimal getAprToJunPrice() {
		return aprToJunPrice;
	}

	public void setAprToJunPrice(BigDecimal aprToJunPrice) {
		this.aprToJunPrice = aprToJunPrice;
	}

	@Override
	public String toString() {
		return "BungalowTypeWrapper [id=" + id + ", type=" + type + ", julToOctPrice=" + julToOctPrice
				+ ", octToDecPrice=" + octToDecPrice + ", decToJanPrice=" + decToJanPrice + ", janToAprPrice="
				+ janToAprPrice + ", aprToJunPrice=" + aprToJunPrice + "]";
	}
}