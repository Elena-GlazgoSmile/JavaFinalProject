package com.example.db;

import java.nio.file.Path;
import java.sql.*;
import java.util.HashMap;
import java.util.List;


public class DBRepository {
    private static Connection CONNECTION = null;
    private static final String URL = "jdbc:sqlite:C:\\Users\\712\\Desktop\\sqlite\\test.mdb";

    public static void connect() {
        try {
            CONNECTION = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if (CONNECTION != null)
                    CONNECTION.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void createTableStudents() {
        String sql = "CREATE TABLE IF NOT EXISTS students (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	group text NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(URL);
            Statement stat = conn.createStatement())  {
            stat.execute(sql);
            System.out.println("Table 'students' created.");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
