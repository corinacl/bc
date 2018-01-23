package entities;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Client {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(unique = true, nullable = true)
	private String dni;
	
	@Column(unique = true, nullable = true)
	private String email;
	
	private String name;
	
	private String surname;
	
	@Column(nullable = true)
	private String phone;
	
	
	public Client (){
	}
	
	public Client (String name, String surname, String phone, String dni, String email){
		this.dni = dni;
		this.name = name;
		this.surname = surname;
		this.phone = phone;
		this.email = email;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getId() {
        return id;
    }
	
	public String getDni() {
        return dni;
    }
    
    public void setDni(String dni) {
        this.dni = dni;
    }
    
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
        return id == ((Client) obj).id;
    }

	@Override
	public String toString() {
		return "Client [id=" + id + ", dni=" + dni + ", email=" + email + ", name=" + name + ", surname=" + surname
				+ ", phone=" + phone + "]";
	}
  
}
