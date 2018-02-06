package wrappers;

public class ClientSearchWrapper {
   
    private String searchBy;
    
    private String searchData;
    
    public ClientSearchWrapper(){
        
    }

	public ClientSearchWrapper(String searchBy, String searchData) {
		this.searchBy = searchBy;
		this.searchData = searchData;
	}

	public String getSearchBy() {
		return searchBy;
	}

	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}

	public String getSearchData() {
		return searchData;
	}

	public void setSearchData(String searchData) {
		this.searchData = searchData;
	}

	@Override
	public String toString() {
		return "ClientSearchWrapper [searchBy=" + searchBy + ", searchData=" + searchData + "]";
	}
           
}
