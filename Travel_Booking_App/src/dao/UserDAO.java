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
 * @author rowan (mariah), max
 */
public class UserDAO {
//    private User user;
    
    public UserDAO(){}

    public User addNewUser(User user) {
        String query = "INSERT INTO users (username, password, first_name, last_name, email, role, phone) VALUES (?,?,?,?,?,?,?);";
        
        try ( Connection link = DBConnection.getConnection(); 
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
            
            System.out.println("Rows inserted: " + row);
            System.out.println(link.getMetaData().getURL());
            System.out.println("Database: " + link.getCatalog());
            
            if ( row > 0 ) { 
                ResultSet rs = p.getGeneratedKeys();
                if (rs.next()) {
                    int idGen = rs.getInt(1); //get int in column 1 of DB table
                    user.setuserID(idGen);
                    
                    System.out.println("Generated ID: " + user.getUserID() );
                    return user; 
                }
            }
        }
        catch ( SQLException e ) { e.printStackTrace(); }
        catch ( Exception e ) { e.printStackTrace(); }
        return null;
    }
    
    public User updateUser(User user) {
        // prepare statement
        String query = "UPDATE users "
                + "SET username = ?,"
                + "password = ?,"
                + "first_name = ?,"
                + "last_name = ?,"
                + "email = ?,"
                + "role = ?,"
                + "phone = ?,"
                + "street_number = ?,"
                + "street_name = ?,"
                + "city = ?,"
                + "province = ?,"
                + "postal_code = ?,"
                + "country = ?,"
                + "account_status = ?"
                //+ "WHERE username = ? AND password = BINARY ?;";
                + " WHERE user_id = ?;";
        
        // connect to database
        try (Connection connection = DBConnection.getConnnection();
                PreparedStatement p = connection.prepareStatement(query);) {
            
            // prepare query
            p.setString(1, user.getUsername());
            p.setString(2, user.getPassword());
            p.setString(3, user.getFirstName());
            p.setString(4, user.getLastName());
            p.setString(5, user.getEmail());
            p.setString(6, user.getRole());
            p.setString(7, user.getPhone());
            p.setString(8, user.getStreetNumber());
            p.setString(9, user.getStreetName());
            p.setString(10, user.getCity());
            p.setString(11, user.getProvince());
            p.setString(12, user.getPostalCode());
            p.setString(13, user.getCountry());
            p.setString(14, user.getAccountStatus());
            p.setInt(15, user.getUserID());
            
            
            // execute statement
            int row = p.executeUpdate();
            
            if (row > 0) {
                return user; // success
            }
        } catch (SQLException e) {
            System.out.println("Error when connecting to database: " + e);
        } catch (Exception e) {
            System.out.println("Error when connecting to Database: " + e);
        } finally {
            return null;
        }
    }
    
//    activeUser = UserDAO.getUserFromUsername(username, password);\
    
    public User getUserFromUsername(String username, String password){ //rename to CHECK LOGIN 
        String query = "SELECT user_id, password, username, first_name, last_name," + 
                "role, email, phone, street_number, street_name, city, province," +
                "postal_code, country, account_status FROM users " +
                "WHERE username = ? AND password = BINARY ? ;" ;
        
        try ( Connection link = DBConnection.getConnection(); 
            PreparedStatement p = link.prepareStatement(query); ) 
        {
            p.setString(1, username);
            p.setString(2, password);
            
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                User user = makeUserObj(rs);
                return user;
            }
        }
        catch ( SQLException e ) { e.printStackTrace(); }
        catch ( Exception e ) { e.printStackTrace(); }
        return null; 
    }
    
        public User getUserFromUsername(String username){
        String query = "SELECT user_id, password, username, first_name, last_name," + 
                "role, email, phone, street_number, street_name, city, province," +
                "postal_code, country, account_status FROM users " +
                "WHERE username = ? ;";
        
        try ( Connection link = DBConnection.getConnnection(); 
            PreparedStatement p = link.prepareStatement(query); ) 
        {
            p.setString(1, username);
            
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                User user = makeUserObj(rs);
                return user;
            }
        }
        catch ( SQLException e ) { e.printStackTrace(); }
        catch ( Exception e ) { e.printStackTrace(); }
        return null; 
    }
    
    public User makeUserObj(ResultSet rs) throws SQLException{
        User user = new User();
        user.setuserID( rs.getInt("user_id" ));
        user.setUsername( rs.getString("username") );
        user.setPassword( rs.getString("password") );
        user.setFirstName( rs.getString("first_name") );
        user.setLastName( rs.getString("last_name") );
        user.setRole( rs.getString("role") );
        user.setEmail( rs.getString("email") );
        user.setPhone( rs.getString("phone") );
        user.setAccountStatus( rs.getString( "account_status") );

        user.setStreetNumber( rs.getString("street_number") );
        user.setStreetName( rs.getString("street_name") );
        user.setCity( rs.getString("city") );
        user.setProvince( rs.getString("province") );
        user.setPostalCode( rs.getString("postal_code") );
        user.setCountry( rs.getString("country") );
        return user;
    }
}



     
