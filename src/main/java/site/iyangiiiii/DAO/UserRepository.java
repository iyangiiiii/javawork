package site.iyangiiiii.DAO;

import site.iyangiiiii.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import site.iyangiiiii.Utils.UserType;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUid(int uid);
    User findUserByUsername(String username);
    List<User> findUsersByUserType(UserType userType);
    List<User> findUsersByUsernameNotNull();
}
