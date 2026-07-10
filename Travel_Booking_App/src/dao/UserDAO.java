package dao;

//import com.mysql.cj.xdevapi.Statement;
import model.User;
import utility.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author rowan
 */
public class UserDAO {
//    private User user;
    
    public UserDAO(){}

    public static User addNewUser(User user) {
        String query = "INSERT INTO users (username, password, first_name, last_name, email, role, phone) VALUES (?,?,?,?,?,?,?);";
        
        try ( Connection link = DBConnection.getConnnection(); 
            PreparedStatement p = link.prepareStatement(query, Statement.RETURN_GENERATED_KEYS); )
        {
            p.setString( 1, user.getUsername() );
            p.setString( 2, user.getPassword() );
            p.setString( 3, user.getFirstName() );
            p.setString( 4, user.getLastName() );
            p.setString( 5, user.getEmail() );
            p.setString( 6, user.getRole() );
            p.setString( 7, user.getPhone() );
            
            int row = p.executeUpdate();
            
            if ( row > 0 ) { 
                ResultSet rs = p.getGeneratedKeys();
                if (rs.next()) {
                    int idGen = rs.getInt(1); //get int in column 1 of DB table
                    user.setuserID(idGen);
                    return user; 
                }
            }
        }
        catch ( SQLException e ) { e.printStackTrace(); }
        catch ( Exception e ) { e.printStackTrace(); }
        return null;
    }
    
//    public boolean getUserID()
}
