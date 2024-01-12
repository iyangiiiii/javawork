package site.iyangiiiii.Service;

import org.springframework.stereotype.Service;
import site.iyangiiiii.DAO.UserRepository;
import site.iyangiiiii.Entities.User;
import site.iyangiiiii.Utils.HashUtils;
import site.iyangiiiii.Utils.UserType;

import java.util.List;
import java.util.UUID;
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
			user.setUserType(userType);

			// 密码加盐
			String salt = UUID.randomUUID().toString();
			String passwordSalt = password + salt;
			String passwordHash = HashUtils.calculateSHA256(passwordSalt);

			user.setPasswordSha256(passwordHash);
			user.setPasswordSalt(salt);

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
	 * @return 成功返回登录的用户实体, 否则返回null
	 */
	public static User verify(String username, String password) {
		try {
			User user = userService.userRepository.findUserByUsername(username);

			if(user == null) return null;

			String passwordSalt = password + user.getPasswordSalt();
			String passwordHash = HashUtils.calculateSHA256(passwordSalt);
			if(!user.getUsername().equals(username)) return null;
			if(!user.getPasswordSha256().equals(passwordHash)) return null;

			return user;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "verify: ", e);
			return null;
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
