package cypher_login.back_jwt.User;

public enum Role {
    ADMIN("admin"),
    USER("user");

    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public static Role fromString(String role) {
        for (Role r : Role.values()) {
            if (r.getRoleName().equalsIgnoreCase(role)) {
                return r;
            }
        }
        throw new IllegalArgumentException("Invalid role: " + role);
    }
}

