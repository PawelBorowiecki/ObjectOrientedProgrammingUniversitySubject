package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private Connection connection;

    public Connection getConnection(){
        return this.connection;
    }

    public void connect(String filePath){
        try{
           connection = DriverManager.getConnection("jdbc:sqlite:" + filePath);
           System.out.println("connected : " + filePath);
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
