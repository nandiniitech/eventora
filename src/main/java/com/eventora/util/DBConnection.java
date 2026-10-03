package com.eventora.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {

        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/eventora_db",
                "root",
                "N@ndini"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }

    public static void close(AutoCloseable... resources) {
        for (AutoCloseable r : resources) {
            if (r != null) {
                try { r.close(); } catch (Exception e) { e.printStackTrace(); }
            }
        }
    }
}
