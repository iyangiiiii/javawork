package site.iyangiiiii.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.iyangiiiii.DAO.UserRepository;
import site.iyangiiiii.Entities.User;
import site.iyangiiiii.Utils.UserType;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserService {
	public static Logger logger = Logger.getLogger("UserService");

	protected UserRepository userRepository;

	public static UserService userService;

	protected UserService(UserRepository repository) {
		this.userRepository = repository;

		userService = this;
	}
	/**
	 * 创建一个普通用户
	 * @param username 用户名
	 * @param password 密码
	 * @return 如果成功返回uid, 否则返回-1
	 */
	public static int createUser(String username, String password) {
		return createUser(username, password, UserType.TYPE_USER);
	}

	/**
	 * 创建一个新用户
	 * @param username 用户名
	 * @param password 密码
	 * @param userType 用户类型
	 * @return 如果成功返回uid, 否则返回-1
	 */
	public static int createUser(String username, String password, UserType userType) {
		try {
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setUserType(userType);

			user = userService.userRepository.save(user);

			return user.getUid();
		}
		catch (Exception e) {
			logger.log(Level.SEVERE, "adduser", e);
			return -1;
		}
	}

	/**
	 * 验证用户账号密码的正确性
	 * @param username 账号
	 * @param password 密码
	 * @return 成功返回0, 否则返回-1
	 */
	public static int verify(String username, String password) {
		try {
			User user = userService.userRepository.findUserByUsername(username);
			if(!user.getUsername().equals(username)) return -1;
			if(!user.getPassword().equals(password)) return -1;
			return 0;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "verify: ", e);
			return -1;
		}
	}

	/**
	 * 获取所有用户
	 * @return 成功返回用户列表, 失败返回null
	 */
	public static List<User> getAllUsers() {
		try{
            return userService.userRepository.findUsersByUsernameNotNull();
		}
		catch (Exception e) {
			logger.log(Level.SEVERE, "adduser", e);
			return null;
		}
	}
}
