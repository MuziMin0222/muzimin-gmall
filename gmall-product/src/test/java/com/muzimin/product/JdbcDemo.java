package com.muzimin.product;

import java.sql.*;

/**
 * @author: 李煌民
 * @date: 2024-06-06 21:01
 **/
public class JdbcDemo {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.56.10:3306/gmall_pms?useSSL=false", "root", "root");
        PreparedStatement showTables = connection.prepareStatement("select count(*) as value from pms_category");
        ResultSet rs = showTables.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getLong("value"));
        }
    }
}
