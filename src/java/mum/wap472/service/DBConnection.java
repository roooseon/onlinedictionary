/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.wap472.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Rojan
 */
public class DBConnection {

    public static Connection getConnection() {

        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String connectionUrl = "jdbc:mysql://localhost:3306/entries?user=root&password=root";
            con = DriverManager.getConnection(connectionUrl);

//            String query = "SELECT word FROM entries where word=?;";
//            PreparedStatement st = con.prepareStatement(query);
//
//            String str1 = "apple";
//            st.setString(1, str1);
//            ResultSet rs = st.executeQuery();
//            
//            while (rs.next()) {
//                System.out.println(rs.getString(1));
//            }

        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.toString());
        } catch (ClassNotFoundException cE) {
            System.out.println("Class Not Found Exception: " + cE.toString());
        }
        return con;
    }

    public static void closeConnection(Connection con) {
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.toString());
        }
    }

    
}
