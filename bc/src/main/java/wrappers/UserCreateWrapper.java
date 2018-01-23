package wrappers;

import entities.Role;

public class UserCreateWrapper {

    private String username;

    private String password;
    
    private Role role;

    public UserCreateWrapper() {
    }

    public UserCreateWrapper(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
	public String toString() {
		return "UserCreateWrapper [username=" + username + ", password=" + password + ", role=" + role + "]";
	}

}
