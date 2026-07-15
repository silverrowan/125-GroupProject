package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Kalei Marchak
 */
public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/travel_booking_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Travel";
    
    public static Connection getConnection() throws SQLException {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found.", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
}
