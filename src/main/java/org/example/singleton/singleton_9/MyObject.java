package org.example.singleton.singleton_9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyObject {
    public enum MyEnumSingleton{
        connectionFactory;
        private Connection connection;

        private MyEnumSingleton() {
            try {
                System.out.println("创建了 MyObject 对象");
                String url = "jdbc:mysql://localhost:3306/spring_boot";
                String username = "root";
                String password = "123456";
                String driverName = "com.mysql.cj.jdbc.Driver";
                Class.forName(driverName);
                connection  = DriverManager.getConnection(url,username,password);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        private Connection getConnection(){
            return connection;
        }
    }
    public static Connection getConnection(){
        return MyEnumSingleton.connectionFactory.getConnection();
    }

}
