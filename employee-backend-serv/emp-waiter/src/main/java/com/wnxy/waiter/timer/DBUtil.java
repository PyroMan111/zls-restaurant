//package com.wnxy.waiter.Timer;
//
//import java.sql.*;
//import java.time.LocalDateTime;
//
//public class DBUtil {
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database";
//    private static final String USER = "username";
//    private static final String PASS = "password";
//
//    public static String queryStatus() {
//        String status = null;
//        try {
//            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT status FROM table WHERE ..."); // 替换为您的查询条件
//            if (rs.next()) {
//                status = rs.getString("status");
//            }
//            rs.close();
//            stmt.close();
//            conn.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return status;
//    }
//}