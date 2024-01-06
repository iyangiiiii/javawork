package site.iyangiiiii.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Landing {

	public static Boolean test(String user, String password) {

		Connection con = ConnectDatabase.connectDB();
		PreparedStatement preSql;
		ResultSet rs;

		String sqlStr = "select password FROM users where username = ?";
		try {
			preSql = con.prepareStatement(sqlStr);
			preSql.setString(1, user);
			rs = preSql.executeQuery();
			boolean userExists = false;

			// 检查用户名是否存在并验证密码
			while (rs.next()) {
				String passwordFromDB = rs.getString("password");

				if (password.equals(passwordFromDB)) {
					userExists = true;
					break; // 找到匹配的密码，跳出循环
				}
			}

			// 检查用户名是否存在
			if (!userExists) {
				JOptionPane.showMessageDialog(null, "用户名或密码错误", "警告", JOptionPane.WARNING_MESSAGE);
				return false;
			}

			con.close();
			return true; // 用户名和密码验证成功
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "数据库连接错误", "警告", JOptionPane.WARNING_MESSAGE);
			return false;
		}
	}

	//确定是否为管理员
	public static boolean sureadmin(String username) {
		Connection con = ConnectDatabase.connectDB();
		PreparedStatement preSql;
		ResultSet rs;
		String sqlStr = "select * from users where username = ?";
		try {
			preSql = con.prepareStatement(sqlStr);
			preSql.setString(1, username);
			rs = preSql.executeQuery();
			while(rs.next()) {
				int manager = rs.getInt(4);
				if (manager==1) {
					return true;
				}else {
					return false;
				}
			}
			con.close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
}
