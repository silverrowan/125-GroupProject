
package dao;

import model.User;
import utility.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Customer;

/**
 *
 * @author Mariah Malczewska
 */
public class CustomerDAO {
    
    public CustomerDAO(){}
    
    public static Customer addNewCustomer(User user) {
//        user = userDAO.addNewUser(user);

        String query = "INSERT INTO customers ( user_id ) VALUES (?,?);";
        
        try ( Connection link = DBConnection.getConnection(); 
            PreparedStatement p = link.prepareStatement(query, Statement.RETURN_GENERATED_KEYS); )
        {
            p.setInt(1, user.getUserID() );
            
            int row = p.executeUpdate();
            if ( row > 0 ) {
                ResultSet rs = p.getGeneratedKeys();
                if (rs.next()) {
                    int idGen = rs.getInt(1); // get int in column 1 of DB table
                    
                    Customer cust = new Customer(idGen);
                    return cust;
                }
            }
        }
        catch ( SQLException e ) { e.printStackTrace(); }
        catch ( Exception e ) { e.printStackTrace(); }
        return null;        
    }
}
