package site.iyangiiiii.Utils;

public enum UserType {
    TYPE_USER("Admin"),
    TYPE_ADMIN("User"),
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
