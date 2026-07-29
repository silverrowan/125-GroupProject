package dao;

//import com.mysql.cj.xdevapi.Statement;
import model.User;
import utility.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author rowan (mariah), max
 */
public class UserDAO {
//    private User user;
    
    public UserDAO(){}

    public User addNewUser(User user) {
        String query = """
                INSERT INTO users (
                       username, password, first_name, last_name, email, 
                       role, phone
                       ) 
                    VALUES (?,?,?,?,?,?,?);
                """;
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
                + " WHERE user_id = ?;";
        
        // connect to database
        try (Connection connection = DBConnection.getConnection();
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
            throw new SQLException("Update failed");
        } catch (SQLException e) {
            System.out.println("Update failed: " + e);
        } catch (Exception e) {
            System.out.println("Update failed: " + e);
        }
        
        return null;
    }
    
    public boolean deleteUser(int userID) {
        
        // statement
        String statement = """
                           DELETE FROM users
                           WHERE user_id = ?;
                           """;
        
        // open connection
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement p = connection.prepareStatement(statement);) {
            
            p.setInt(1, userID); // set userID
            
            int rows = p.executeUpdate();
            
            if (rows != 1) {
                throw new SQLException("Fatal: something went wrong with delete!");
            }
            
            return true; // success
        } catch (SQLException e) {
            System.out.println("Error when connecting to database: " + e);
        } catch (Exception e) {
            System.out.println("Error when connecting to Database: " + e);
        }
        
        return false;
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
        
        try ( Connection link = DBConnection.getConnection(); 
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
    
    /**
     * fetches all users with a certain role from database
     * @param role Customer, Admin, Travel Agent
     * @return ArrayList of all users
     */
    public ArrayList<User> getUsersByRole(String role) {
        if (role.equals("All roles")) {
            // searching for all roles
            return this.getAllUsers();
        }
        
        String query = """
                       SELECT * FROM users
                       WHERE role = ?;
                       """;
        
        return getUsersByRole(query, role);
    }
    
    private ArrayList<User> getUsersByRole(String query, String role) {
        // connect to database
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement p = connection.prepareStatement(query);) {

            p.setString(1, role);
            
            ResultSet rs = p.executeQuery(); // execute query
            
            ArrayList<User> list = new ArrayList<>(); // create list
            
            // add each user to list
            while (rs.next()) {
                User user = makeUserObj(rs);
                list.add(user);
            }
            
            return list; // return list
        } catch (SQLException e) {
            System.out.println("Error when connecting to database: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        
        return null;
    }
    
    /**
     * fetches all users regardless of role
     * @return ArrayList of all users
     */
    public ArrayList<User> getAllUsers() {
        String query = """
                       SELECT * FROM users
                       ;
                       """;
        
        return getUsers(query);
    }
    
    /**
     * gets a user from a role and a user id
     * @param role Admin, Travel Agent, Customer
     * @param userID id of user
     * @return ArrayList of user (there should just be one)
     */
    public ArrayList<User> getUsersByRoleAndID(String role, int userID) {
        if (role.equals("All roles")) {
            // searching for all roles
            return this.getUsersFromID(userID);
        }
        
        String query = """
                       SELECT * FROM users
                       WHERE role = ?
                       AND user_id = ?;
                       """;
        
        return getUsersByRoleAndID(query, role, userID);
    }
    
    private ArrayList<User> getUsersByRoleAndID(String query, String role, int userID) {
        // connect to database
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement p = connection.prepareStatement(query);) {

            p.setString(1, role);
            p.setInt(2, userID);
            
            ResultSet rs = p.executeQuery(); // execute query
            
            ArrayList<User> list = new ArrayList<>(); // create list
            
            // add each user to list
            while (rs.next()) {
                User user = makeUserObj(rs);
                list.add(user);
            }
            
            // if size is not 1, something went wrong
            if (list.size() > 1) {
                throw new SQLException("Multiple users returned from one userID");
            }
            
            return list; // return list
        } catch (SQLException e) {
            System.out.println("Error when connecting to database: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        
        return null;
    }
    
    /**
     * gets a user from a user id
     * @param userID
     * @return ArrayList of user (there should just be one)
     */
    public ArrayList<User> getUsersFromID(int userID) {
        String query = """
                       SELECT * FROM users
                       WHERE user_id = ?;
                       """;
        
        return getUserFromID(query, userID);
    }
    
    /**
     * returns all users with a certain role who have a username similar to the one provided
     * @param role Admin, Travel Agent, Customer
     * @param username the complete or partial username
     * @return ArrayList of all matching users
     */
    public ArrayList<User> getUsersByRoleAndUsername(String role, String username) {
        if (role.equals("All roles")) {
            // searching for all roles
            return this.getAllUsersFromUsername(username);
        }
        
        String query = """
                       SELECT * FROM users
                       WHERE role = ?
                       AND username LIKE ?;
                       """;
        
        return getUsersByRoleAndUsername(query, role, username);
    }
    
    private ArrayList<User> getUsersByRoleAndUsername(String query, String role, String username) {
        // connect to database
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement p = connection.prepareStatement(query);) {
            
            StringBuilder sb = new StringBuilder("%");
            for (int c = 0; c < username.length(); c++) {
                sb.append(username.charAt(c));
                sb.append("%");
            }
            
            p.setString(1, role);
            p.setString(2, sb.toString());
            
            ResultSet rs = p.executeQuery(); // execute query
            
            ArrayList<User> list = new ArrayList<>(); // create list
            
            // add each user to list
            while (rs.next()) {
                User user = makeUserObj(rs);
                list.add(user);
            }
            
            return list; // return list
        } catch (SQLException e) {
            System.out.println("Error when connecting to database: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        
        return null;
    }
    
    /**
     * returns all users who have a username similar to the one provided
     * @param username the complete or partial username
     * @return ArrayList of all matching users
     */
    public ArrayList<User> getAllUsersFromUsername(String username) {
        String query = """
                       SELECT * FROM users
                       WHERE username LIKE ?;
                       """;
        
        return getUsersFromUsername(query, username);
    }
    
    private ArrayList<User> getUsers(String query) {
        // connect to database
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement p = connection.prepareStatement(query);) {
            
            ResultSet rs = p.executeQuery(); // execute query
            
            ArrayList<User> list = new ArrayList<>(); // create list
            
            // add each user to list
            while (rs.next()) {
                User user = makeUserObj(rs);
                list.add(user);
            }
            
            return list; // return list
        } catch (SQLException e) {
            System.out.println("Error when connecting to database: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        
        return null;
    }
    
    private ArrayList<User> getUserFromID(String query, int userID) {
        // connect to database
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement p = connection.prepareStatement(query);) {

            p.setInt(1, userID);
            
            ResultSet rs = p.executeQuery(); // execute query
            
            ArrayList<User> list = new ArrayList<>(); // create list
            
            // add each user to list
            while (rs.next()) {
                User user = makeUserObj(rs);
                list.add(user);
            }
            
            // if size is not 1, something went wrong
            if (list.size() > 1) {
                throw new SQLException("Multiple users returned from one userID");
            }
            
            return list; // return list
        } catch (SQLException e) {
            System.out.println("Error when connecting to database: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        
        return null;
    }
    
    private ArrayList<User> getUsersFromUsername(String query, String username) {
        // connect to database
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement p = connection.prepareStatement(query);) {
            
            StringBuilder sb = new StringBuilder("%");
            for (int c = 0; c < username.length(); c++) {
                sb.append(username.charAt(c));
                sb.append("%");
            }
            p.setString(1, sb.toString());
            
            ResultSet rs = p.executeQuery(); // execute query
            
            ArrayList<User> list = new ArrayList<>(); // create list
            
            // add each user to list
            while (rs.next()) {
                User user = makeUserObj(rs);
                list.add(user);
            }
            
            return list; // return list
        } catch (SQLException e) {
            System.out.println("Error when connecting to database: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        
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



     
