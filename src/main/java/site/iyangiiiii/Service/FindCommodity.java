package site.iyangiiiii.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FindCommodity {

	FindCommodity() {
	}

	// 显示所有商品
	public static void allcommodity(DefaultTableModel model) {

		Connection con = ConnectDatabase.connectDB();
		PreparedStatement preSql;
		ResultSet rs;
		String sqlStr = "select * from commodity";
		try {
			preSql = con.prepareStatement(sqlStr);
			rs = preSql.executeQuery();
			while (rs.next()) {
				int comid = rs.getInt(1);
				String type = rs.getString(2);
				String name = rs.getString(3);
				String manufacturer = rs.getString(4);
				String status = rs.getString(5);
				model.addRow(new Vector<>(Arrays.asList(comid, type, name, manufacturer, status)));
			}
			con.close();
		} catch (SQLException e) {
		}
	}

	// 按类别查找商品
	public static void findcomid(DefaultTableModel model, Integer id) {
		Connection con = ConnectDatabase.connectDB();
		PreparedStatement preSql;
		ResultSet rs;
//		id = "%" + id + "%";
		String sqlStr = "select * from commodity where comid like ?";
		try {
			preSql = con.prepareStatement(sqlStr);
			preSql.setInt(1, id);
			rs = preSql.executeQuery();
			boolean flag = false;
			while (rs.next()) {
				flag = true;
				int comid = rs.getInt(1);
				String type = rs.getString(2);
				String name = rs.getString(3);
				String manufacturer = rs.getString(4);
				String status = rs.getString(5);
				model.addRow(new Vector<>(Arrays.asList(comid, type, name, manufacturer, status)));
			}
			if (!flag) {
				JOptionPane.showMessageDialog(null, "商品不存在", "警告", JOptionPane.WARNING_MESSAGE);
			}
			con.close();
		} catch (SQLException e) {
		}
	}

	// 按书名查找商品
	public static void findcommodityname(DefaultTableModel model, String commodityname) {
		Connection con = ConnectDatabase.connectDB();
		PreparedStatement preSql;
		ResultSet rs;
		commodityname = "%" + commodityname + "%";
		String sqlStr = "select * from commodity where name like ?";
		try {
			preSql = con.prepareStatement(sqlStr);
			preSql.setString(1, commodityname);
			rs = preSql.executeQuery();
			boolean flag = false;
			while (rs.next()) {
				flag = true;
				int comid = rs.getInt(1);
				String type = rs.getString(2);
				String name = rs.getString(3);
				String manufacturer = rs.getString(4);
				String status = rs.getString(5);
				model.addRow(new Vector<>(Arrays.asList(comid, type, name, manufacturer, status)));
			}
			if (!flag) {
				JOptionPane.showMessageDialog(null, "商品不存在", "警告", JOptionPane.WARNING_MESSAGE);
			}
			con.close();
		} catch (SQLException e) {
		}
	}

	// 按作者查找商品
	public static void findmanufacturer(DefaultTableModel model, String Manufacturer) {
		Connection con = ConnectDatabase.connectDB();
		PreparedStatement preSql;
		ResultSet rs;
		Manufacturer = "%" + Manufacturer + "%";
		String sqlStr = "select * from commodity where manufacturer like ?";
		try {
			preSql = con.prepareStatement(sqlStr);
			preSql.setString(1, Manufacturer);
			rs = preSql.executeQuery();
			boolean flag = false;
			while (rs.next()) {
				flag = true;
				int comid = rs.getInt(1);
				String type = rs.getString(2);
				String name = rs.getString(3);
				String manufacturer = rs.getString(4);
				String status = rs.getString(5);
				model.addRow(new Vector<>(Arrays.asList(comid, type, name, manufacturer, status)));
			}
			if (!flag) {
				JOptionPane.showMessageDialog(null, "商品不存在", "警告", JOptionPane.WARNING_MESSAGE);
			}
			con.close();
		} catch (SQLException e) {
		}
	}

	// 按书号查找商品
	public static void findstatus(DefaultTableModel model, String Status) {
		Connection con = ConnectDatabase.connectDB();
		PreparedStatement preSql;
		ResultSet rs;
		String sqlStr = "select * from commodity where status = ?";
		try {
			preSql = con.prepareStatement(sqlStr);
			preSql.setString(1, Status);
			rs = preSql.executeQuery();
			boolean flag = false;
			while (rs.next()) {
				flag = true;
				int comid = rs.getInt(1);
				String type = rs.getString(2);
				String commodityname = rs.getString(3);
				String manufacturer = rs.getString(4);
				String status = rs.getString(5);
				model.addRow(new Vector<>(Arrays.asList(comid, type, commodityname, manufacturer, status)));
			}
			if (!flag) {
				JOptionPane.showMessageDialog(null, "商品不存在", "警告", JOptionPane.WARNING_MESSAGE);
			}
			con.close();
		} catch (SQLException e) {
		}
	}
	public static void findtype(DefaultTableModel model, String Type) {
		Connection con = ConnectDatabase.connectDB();
		PreparedStatement preSql;
		ResultSet rs;
		String sqlStr = "select * from commodity where type = ?";
		try {
			preSql = con.prepareStatement(sqlStr);
			preSql.setString(1, Type);
			rs = preSql.executeQuery();
			boolean flag = false;
			while (rs.next()) {
				flag = true;
				int comid = rs.getInt(1);
				String type = rs.getString(2);
				String name = rs.getString(3);
				String manufacturer = rs.getString(4);
				String status = rs.getString(5);
				model.addRow(new Vector<>(Arrays.asList(comid, type, name, manufacturer, status)));
			}
			if (!flag) {
				JOptionPane.showMessageDialog(null, "商品不存在", "警告", JOptionPane.WARNING_MESSAGE);
			}
			con.close();
		} catch (SQLException e) {
		}
	}
}