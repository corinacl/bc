package wrappers;

public class ClientIdWrapper {
   
	private int id;
	
	public ClientIdWrapper() {

	}
	
	public ClientIdWrapper(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ClientIdWrapper [id=" + id + "]";
	}

}
