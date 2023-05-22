/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.csit228g3.faburada_final_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author emman
 */
public class DBHelper {

    private static final String DB_URL = "jdbc:mysql://localhost/dbfaburada"; // replace with your database URL
    private static final String USER = "root"; // replace with your username
    private static final String PASS = ""; // replace with your password
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    public static Connection getConnection() throws SQLException {
        // Register JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        }

        // Open a connection
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        return conn;
    }

    public DBHelper() {
        try {

            conn = getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertCustomer(int id, String firstname, String lastname, String email_address, String shipping_address, String wishlist) {
        try {
            stmt = conn.createStatement();
            String sql = String.format("INSERT INTO tblcustomer (id, firstname, lastname, email_address, shipping_address, wishlist) "
                    + "VALUES (%d, '%s', '%s', '%s', '%s', '%s')", id, firstname, lastname, email_address, shipping_address, wishlist);
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteCustomerById(int id){
        try {
            stmt = conn.createStatement();
            String sql = String.format("DELETE FROM tblcustomer WHERE id = " + id);
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getCustomers() throws SQLException {
        stmt = conn.createStatement();
        String query = "SELECT * FROM tblcustomer";
        rs = stmt.executeQuery(query);
        return rs;
    }
    
}
