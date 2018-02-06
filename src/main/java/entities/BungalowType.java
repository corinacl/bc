package entities;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class BungalowType {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(unique = true)
	private String type;
	
	private BigDecimal julToOctPrice;
	private BigDecimal octToDecPrice;
	private BigDecimal decToJanPrice;
	private BigDecimal janToAprPrice;
	private BigDecimal aprToJunPrice;
	
	public BungalowType (){
	}
	
	public BungalowType(String type, BigDecimal julToOctPrice, BigDecimal octToDecPrice, BigDecimal decToJanPrice, BigDecimal janToAprPrice, BigDecimal aprToJunPrice) {
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aprToJunPrice == null) ? 0 : aprToJunPrice.hashCode());
		result = prime * result + ((decToJanPrice == null) ? 0 : decToJanPrice.hashCode());
		result = prime * result + id;
		result = prime * result + ((janToAprPrice == null) ? 0 : janToAprPrice.hashCode());
		result = prime * result + ((julToOctPrice == null) ? 0 : julToOctPrice.hashCode());
		result = prime * result + ((octToDecPrice == null) ? 0 : octToDecPrice.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BungalowType other = (BungalowType) obj;
		if (aprToJunPrice == null) {
			if (other.aprToJunPrice != null)
				return false;
		} else if (!aprToJunPrice.equals(other.aprToJunPrice))
			return false;
		if (decToJanPrice == null) {
			if (other.decToJanPrice != null)
				return false;
		} else if (!decToJanPrice.equals(other.decToJanPrice))
			return false;
		if (id != other.id)
			return false;
		if (janToAprPrice == null) {
			if (other.janToAprPrice != null)
				return false;
		} else if (!janToAprPrice.equals(other.janToAprPrice))
			return false;
		if (julToOctPrice == null) {
			if (other.julToOctPrice != null)
				return false;
		} else if (!julToOctPrice.equals(other.julToOctPrice))
			return false;
		if (octToDecPrice == null) {
			if (other.octToDecPrice != null)
				return false;
		} else if (!octToDecPrice.equals(other.octToDecPrice))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BungalowType [id=" + id + ", type=" + type + ", julToOctPrice=" + julToOctPrice + ", octToDecPrice="
				+ octToDecPrice + ", decToJanPrice=" + decToJanPrice + ", janToAprPrice=" + janToAprPrice
				+ ", aprToJunPrice=" + aprToJunPrice + "]";
	}
}