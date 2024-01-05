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

			// ����û����Ƿ���ڲ���֤����
			while (rs.next()) {
				String passwordFromDB = rs.getString("password");

				if (password.equals(passwordFromDB)) {
					userExists = true;
					break; // �ҵ�ƥ������룬����ѭ��
				}
			}

			// ����û����Ƿ����
			if (!userExists) {
				JOptionPane.showMessageDialog(null, "�û������������", "����", JOptionPane.WARNING_MESSAGE);
				return false;
			}

			con.close();
			return true; // �û�����������֤�ɹ�
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "���ݿ����Ӵ���", "����", JOptionPane.WARNING_MESSAGE);
			return false;
		}
	}

	//ȷ���Ƿ�Ϊ����Ա
	public static boolean sureadmin(String user) {
		Connection con = ConnectDatabase.connectDB();
		PreparedStatement preSql;
		ResultSet rs;
		String sqlStr = "select * from usertable where user = ?";
		try {
			preSql = con.prepareStatement(sqlStr);
			preSql.setString(1, user);
			rs = preSql.executeQuery();
			while(rs.next()) {
				int admin = rs.getInt(5);
				if (admin==1) {
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
