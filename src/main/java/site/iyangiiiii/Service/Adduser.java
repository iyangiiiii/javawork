package site.iyangiiiii.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Adduser {

	public static Boolean adduser(String user, String password) {

		Connection con = ConnectDatabase.connectDB();

		PreparedStatement preSql;

		String sqlStr=null;
		
		if(userlist()) {
			sqlStr = "insert into users(username, password) values (?,?)";
		}else {
			sqlStr = "insert into users(username, password) values (?,?)";
		}

		try {

			preSql = con.prepareStatement(sqlStr);
			preSql.setString(1, user);
			preSql.setString(2, password);
			int ok = preSql.executeUpdate();
			con.close();
			return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "用户名已存在", "警告", JOptionPane.WARNING_MESSAGE);
			return false;
		}
	}

	//判断用户是否存在
	public static boolean userlist() {
		Connection con = ConnectDatabase.connectDB();

		PreparedStatement preSql;

		ResultSet rs;

		String sqlStr = "select * from user";
		try {
			preSql = con.prepareStatement(sqlStr);
			rs = preSql.executeQuery();
			boolean flag = false;
			while(rs.next()) {
				flag = true;
				return true;
			}
			if(!flag) {
				return false;
			}
			con.close();
			return false;
		} catch (SQLException e) {
			return false;
		}
	}
}
