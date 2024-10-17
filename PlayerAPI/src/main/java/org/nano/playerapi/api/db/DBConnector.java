package org.nano.playerapi.api.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    public static boolean sw = true;
    public static String URL = "jdbc:mysql://localhost:3306/playerAPI?createDatabaseIfNotExist=true" ;
    public static String USER = "root";
    public static String PASSWORD = "0000";
    public static Connection connection = null;

    public DBConnector() {
        connection = getConnection();
    }

    public Connection getConnection() {
        if ( sw ) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println(" 성공적으로 데이터베이스에 연결 되었습니다! ");
            } catch (SQLException e) {
                System.out.println(" 데이터베이스에 연결하지 못했습니다. ");
                System.out.println(" 1. 데이터베이스 서버가 켜져있는지 확인해주세요. ");
                System.out.println(" 2. URL, ID, PASSWORD 가 맞는지 확인해주세요. ");
            }
            return connection;
        }else{
            System.out.println(" 데이터베이스에 연결하지 못했습니다. ");
            System.out.println(" 1. Config/database 에 DB Connect 가 true 인지 확인해주세요. ");
        }
        return null;
    }
}
