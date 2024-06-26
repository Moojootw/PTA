package projects.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import projects.exception.DbException;


public class DbConnection { //these are the credentials to log in to our database. These can be updated here as needed
    private static String HOST = "localhost";
    private static String PASSWORD = "projects";
    private static int PORT = 3306;
    private static String SCHEMA = "projects";
    private static String USER = "projects";
    
    public static Connection getConnection() { //constructor for the uri
        String uri = String.format("jdbc:mysql://%s:%d/%s?user=%s&password=%s", HOST, PORT, SCHEMA, USER, PASSWORD);
        
        try { //tests to see if we can establish a connection to the MySQL schema
            Connection conn = DriverManager.getConnection(uri);
            System.out.println("Connection to schema '"+ SCHEMA + "' is successful.");
            return conn;
        } catch (SQLException e) {
            System.out.println("Unable to get connection at " + uri);
            throw new DbException("Unable to get connection at \"+ uri");
            }
    }
}
	
