package entities;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Bungalow {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(unique = true)
	private int number;
	
	@ManyToOne
    @JoinColumn
	private BungalowType type;
	
	public Bungalow (){
	
	}
	
	public Bungalow(int number, BungalowType type) {
		this.number = number;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public BungalowType getType() {
		return type;
	}

	public void setType(BungalowType type) {
		this.type = type;
	}

	@Override
    public int hashCode() {
        return id;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return id == ((Bungalow) obj).id;
    }

	@Override
	public String toString() {
		return "Bungalow [id=" + id + ", number=" + number + ", type=" + type + "]";
	}
    
}
