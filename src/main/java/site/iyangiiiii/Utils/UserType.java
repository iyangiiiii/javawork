package site.iyangiiiii.Utils;

public enum UserType {
    TYPE_USER("User"),
    TYPE_ADMIN("Admin"),
    TYPE_SERVICE("Service");
    private final String type;

    UserType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }
}
