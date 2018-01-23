package entities;

public enum Role {
    ADMIN, MANAGER, AUTHENTICATED;

    public String roleName() {
        return "ROLE_" + this.toString();
    }

}
