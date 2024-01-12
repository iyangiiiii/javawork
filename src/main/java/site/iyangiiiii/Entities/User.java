package site.iyangiiiii.Entities;


import jakarta.persistence.*;
import site.iyangiiiii.Utils.UserType;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int uid;

    @Column(length = 50)
    String username;

    @Column(length = 64)
    String passwordSha256;

    @Column(length = 50)
    String passwordSalt;

    UserType userType;

    public User(String username, String passwordSha256, String passwordSalt, UserType userType) {
        this.username = username;
        this.passwordSha256 = passwordSha256;
        this.passwordSalt = passwordSalt;
        this.userType = userType;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public User() {
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public int getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordSha256() {
        return passwordSha256;
    }

    public void setPasswordSha256(String passwordSha256) {
        this.passwordSha256 = passwordSha256;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public boolean isAdmin() {
        return getUserType() == UserType.TYPE_ADMIN;
    }
}
