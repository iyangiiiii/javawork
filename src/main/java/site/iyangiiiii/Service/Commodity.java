package site.iyangiiiii.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;

public class Commodity {
    //图书类别加入下拉框
    public static void findcategory(JComboBox<String> box) {
        Connection con = ConnectDatabase.connectDB();
        PreparedStatement preSql;
        ResultSet rs;
        String sqlStr = "select * from bookcategory";
        try {
            preSql = con.prepareStatement(sqlStr);
            rs = preSql.executeQuery();
            while (rs.next()) {
                String category = rs.getString(1);
                box.addItem(category);
            }
            con.close();
        } catch (SQLException e) {
        }
    }

    //添加图书
    public static void addcommodity(int comid, String type,String name,String manufacturer,String status,int inventory) {
        Connection con = ConnectDatabase.connectDB();

        PreparedStatement preSql;

        String sqlStr = "insert into commodity(type, name, manufacturer, status, inventory) values (?, ?, ?, ?, ?, ?)";

        try {
            preSql = con.prepareStatement(sqlStr);
            preSql.setInt(1, comid);
            preSql.setString(2, type);
            preSql.setString(3, name);

            preSql.setString(4, manufacturer);
            preSql.setString(5, status);
            preSql.setInt(6, inventory);
            int ok = preSql.executeUpdate();
            con.close();
        } catch (SQLException e) { System.out.println("错了 傻逼");
        }
    }

    //添加图书
    public static void modifycommodity(int comid, String type,String name,String manufacturer,String status,int inventory) {
        Connection con = ConnectDatabase.connectDB();

        PreparedStatement preSql;

        String sqlStr = "update commodity set type = ? ,name = ? ,manufacturer = ?,status = ? ,inventory = ? where comid = ?";

        try {
            preSql = con.prepareStatement(sqlStr);
            preSql.setInt(1, comid);
            preSql.setString(2, type);
            preSql.setString(3, name);
            preSql.setString(4, manufacturer);
            preSql.setString(5, status);
            preSql.setInt(6, inventory);
            int ok = preSql.executeUpdate();
            con.close();
        } catch (SQLException e) {
        }
    }
    //删除图书
    public static void deletecommodity(int comid) {
        Connection con = ConnectDatabase.connectDB();

        PreparedStatement preSql;

        String sqlStr = "delete from commodity where comid = ?";

        try {
            preSql = con.prepareStatement(sqlStr);
            preSql.setInt(1, comid);
            int ok = preSql.executeUpdate();
            con.close();
        } catch (SQLException e) {
        }
    }
}