package dao;

import model.User;
import utility.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author rowan
 */
public class UserDAO {
//    private User user;
    
//    public UserDAO(){}
    
    public static boolean addNewUser(User user) {
        String query = "INSERT INTO users (username, password, first_name, last_name, email, role, phone) VALUES (?,?,?,?,?,?,?);";
        
        try ( Connection link = DBConnection.getConnnection(); 
            PreparedStatement p = link.prepareStatement(query) ) 
        {
            p.setString( 1, user.getUsername() );
            p.setString( 2, user.getPassword() );
            p.setString( 3, user.getFirstName() );
            p.setString( 4, user.getLastName() );
            p.setString( 5, user.getEmail() );
            p.setString( 6, user.getRole() );
            p.setString( 7, user.getPhone() );
            
            int row = p.executeUpdate();
            
            return row > 0; //equiv to if true return true.
        }
        catch ( SQLException e ) { e.printStackTrace(); }
        catch ( Exception e ) { e.printStackTrace(); }
            return false;            
    }
}
