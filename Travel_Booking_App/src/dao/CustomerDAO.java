
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
 * @author Mariah Malczewska, Max Zhang
 */
public class CustomerDAO {
    
    public CustomerDAO(){}
    
    public Customer addNewCustomer(User user) {
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
    
    public Customer updateCustomer(Customer customer) {
        
        // query
        String query = """
                       UPDATE customers
                       SET emergency_contact_name = ?,
                       emergency_contact_phone = ?
                       WHERE customer_id = ?;
                       """;
        
        // connect to database
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement p = connection.prepareStatement(query);) {
            
            // prepare query
            p.setString(1, customer.getEmergencyContactName());
            p.setString(2, customer.getEmergencyContactPhone());
            p.setInt(3, customer.getCustomerID());
            
            // execute statement
            int row = p.executeUpdate();
            
            if (row <= 0 ) throw new SQLException("Update failed"); // update failed
            
            return customer; // success
        } catch (SQLException e) {
            System.out.println("An error occured when connecting to database");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occured when connecting to database");
            System.out.println(e.getMessage());
        } finally {
            return null;
        }
    }
    
    public Customer getCustomerFromUserID(int userID) {
        
        // query
        String query = "SELECT * FROM customers WHERE user_id = ?;";
        
        // open connection
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement p = connection.prepareStatement(query); ) {
            
            p.setInt(1, userID); // set first field to userID
            
            ResultSet rs = p.executeQuery(); // execute query
            
            if (rs.next()) {
                return makeCustomerObj(rs); // make customer object and return it
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return null;
    }
    
    private Customer makeCustomerObj(ResultSet rs) throws SQLException {
        // create new customer and set all attributes
        Customer customer = new Customer(rs.getInt("user_id"));
        customer.setCustomerID(rs.getInt("customer_id"));
        customer.setCustomerNotes(rs.getString("customer_notes"));
        customer.setEmergencyContactName(rs.getString("emergency_contact_name"));
        customer.setEmergencyContactPhone(rs.getString("emergency_contact_phone"));
        
        // return customer
        return customer;
    }
}
