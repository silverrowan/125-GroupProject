
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Employee;
import model.User;
import utility.DBConnection;

/**
 *
 * @author Mariah Malczewska, Max Zhang
 */
public class EmployeeDAO {

    public Employee addNewEmployee(User user ) {
//        user = userDAO.addNewUser(user);

        String query = "INSERT INTO employees ( user_id ) VALUES (?,?);";
        
        try ( Connection link = DBConnection.getConnection(); 
            PreparedStatement p = link.prepareStatement(query, Statement.RETURN_GENERATED_KEYS); )
        {
            p.setInt(1, user.getUserID() );
            
            int row = p.executeUpdate();
            if ( row > 0 ) {
                ResultSet rs = p.getGeneratedKeys();
                if (rs.next()) {
                    int idGen = rs.getInt(1); // get int in column 1 of DB table
                    
                    Employee emp = new Employee(idGen, user.getUserID());
                    return emp;
                }
            }
        }
        catch ( SQLException e ) { e.printStackTrace(); }
        catch ( Exception e ) { e.printStackTrace(); }
        return null;        
    }
    
    public Employee updateEmployee(Employee employee) {
        
        // query
        String query = """
                       UPDATE employees
                       SET employee_status = ?,
                       job_title = ?,
                       hire_date = ?
                       WHERE employee_id = ?;
                       """;
        
        // connect to database
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement p = connection.prepareStatement(query);) {
            
            // prepare query
            p.setString(1, employee.getEmployeeStatus());
            p.setString(2, employee.getJobTitle());
            p.setDate(3, (Date) employee.getHireDate());
            p.setInt(4, employee.getEmployeeID());
            
            // execute statement
            int row = p.executeUpdate();
            
            if (row <= 0 ) throw new SQLException("Update failed"); // update failed
            
            return employee; // success
        } catch (SQLException e) {
            System.out.println("An error occured when connecting to database");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occured when connecting to database");
            System.out.println(e.getMessage());
        }
        
        return null;
    }
    
    public boolean deleteEmployee(int employeeID) {
        
        // SQL Statement
        String statement = """
                           DELETE FROM employees
                           WHERE employee_id = ?;
                           """;
        
        // open connection
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement p = connection.prepareStatement(statement);) {
            
            p.setInt(1, employeeID); // set parameter to employeeID
            
            int rows = p.executeUpdate();
            
            // supposed to delete only one row
            if (rows != 1) {
                throw new SQLException("Something went wrong with delete!");
            }
            
            return true; // success
            
        } catch (SQLException e) {
            System.out.println("An error occured when connecting to database");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return false;
    }
    
    public Employee getEmployeeFromUserID(int userID) {
        
        // query
        String query = "SELECT * FROM employees WHERE user_id = ?;";
        
        // open connection
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement p = connection.prepareStatement(query); ) {
            
            p.setInt(1, userID); // set first field to userID
            
            ResultSet rs = p.executeQuery(); // execute query
            
            if (rs.next()) {
                return addNewEmployee(rs); // make employee object and return it
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return null;
    }
    
    private Employee addNewEmployee(ResultSet rs) throws SQLException {
        // create new employee and set all attributes
        Employee employee = new Employee(rs.getInt("user_id"));
        employee.setEmployeeID(rs.getInt("employee_id"));
        employee.setEmployeeStatus(rs.getString("employee_status"));
        employee.setHireDate(rs.getDate("hire_date"));
        employee.setJobTitle(rs.getString("job_title"));
        
        // return employee
        return employee;
    }
}    

    