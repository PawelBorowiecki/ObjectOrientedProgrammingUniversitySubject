package pl.oop.umcs.databasecreator;


import java.io.File;
import java.sql.*;
import java.sql.Connection;

public class Creator {

    public static void main(String[] args) {
        String url = "jdbc:sqlite:C:\\Users\\pavlo\\OneDrive\\Pulpit\\Foldery\\Nauka\\Informatyka\\Studia\\2Semestr\\ProgramowanieObiektowe\\EEGusereeg.db";
        Creator creator = new Creator();
        creator.create(url);

    }
    public void create(String url){
        String createTableSQL = "CREATE TABLE IF NOT EXISTS user_eeg ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "username TEXT NOT NULL,"
                + "electrode_number INTEGER NOT NULL,"
                + "image TEXT NOT NULL"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
            System.out.println("Ok");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(String url) {
        String filepath = url.substring(url.indexOf("\\"));
        File dbFile = new File(filepath);
        if (dbFile.exists()) {
            if (!dbFile.delete()){
                System.out.println("Error during delete database");
            }
        }else{
            System.out.println("Error database dosent exist");
        }
    }
}