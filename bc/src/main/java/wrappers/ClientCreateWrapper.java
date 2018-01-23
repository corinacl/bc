package wrappers;

public class ClientCreateWrapper {
	
	private String name; 
	
	private String surname;
	
	private String phone;
	
	private String dni;
	
	private String email;
	
	public ClientCreateWrapper() {
	
	}

	public ClientCreateWrapper(String name, String surname, String phone, String dni, String email) {
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
	public String toString() {
		return "ClientCreateWrapper [name=" + name + ", surname=" + surname + ", phone=" + phone + ", dni=" + dni
				+ ", email=" + email + "]";
	}

}
