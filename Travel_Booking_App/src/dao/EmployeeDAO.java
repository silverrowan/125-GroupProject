
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Employee;
import model.User;
import utility.DBConnection;

/**
 *
 * @author Mariah Malczewska
 */
public class EmployeeDAO {

    public static Employee addNewEmployee(User user, int userID) {
        user = UserDAO.addNewUser(user);

        String query = "INSERT INTO customers ( user_id ) VALUES (?,?);";
        
        try ( Connection link = DBConnection.getConnnection(); 
            PreparedStatement p = link.prepareStatement(query, Statement.RETURN_GENERATED_KEYS); )
        {
            p.setInt(1, user.getUserID() );
            
            int row = p.executeUpdate();
            if ( row > 0 ) {
                ResultSet rs = p.getGeneratedKeys();
                if (rs.next()) {
                    int idGen = rs.getInt(1); // get int in column 1 of DB table
                    
                    Employee emp = new Employee(idGen);
                    return emp;
                }
            }
        }
        catch ( SQLException e ) { e.printStackTrace(); }
        catch ( Exception e ) { e.printStackTrace(); }
        return null;        
    }
}    
