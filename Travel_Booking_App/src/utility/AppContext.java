
package utility;

import view.dashboard.AppWindowCust;
import dao.CustomerDAO;
import dao.EmployeeDAO;
import dao.UserDAO;
import model.User;
import view.ViewCustomerGUI;

/**
 *
 * @author rowan
 * Database states
 */
public class AppContext {
    // Current User and Customer inside
    Session currentSession = new Session();
    
    // DAOs
    UserDAO userDao = new UserDAO();
    EmployeeDAO employeeDao = new EmployeeDAO(); 
    CustomerDAO customerDao = new CustomerDAO();
    
    //Open Windows
    private AppWindowCust appWindow;
    private ViewCustomerGUI customerView;
//    private ViewBookingsGUI bookingsView;
//    private SearchProductsGUI searchView; 

//Constructor    
    public AppContext() {
        this.currentSession = new Session();
        this.userDao = new UserDAO();
        this.employeeDao = new EmployeeDAO();
        this.customerDao = new CustomerDAO();
    }

    // DAO Getters & Setters
    //========================================================================    
    public UserDAO getUserDao() { return userDao; }
    public void setUserDao(UserDAO userDao) {
        this.userDao = userDao;
    }

    public EmployeeDAO getEmployeeDao() { return employeeDao; }
    public void setEmployeeDao(EmployeeDAO employeeDao) {
        this.employeeDao = employeeDao;
    }

    public CustomerDAO getCustomerDao() { return customerDao; }
    public void setCustomerDao(CustomerDAO customerDao) {
        this.customerDao = customerDao;
    }
    
    // Session Getter & Setter Helpers
    //========================================================================    
    public Session getCurrentSession() { return currentSession; }
    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }
    
    public User getCurrentUser() { //make the string shorter helpers :) they're got more than set
        return getCurrentSession().getCurrentUser();
    }
    
    public User getCurrentCustomerUser() { //make the string shorter helpers
        return getCurrentSession().getCurrentCustomer();
    }
    
    public User getCurrentFocusUser() {
        return getCurrentSession().getFocusUser();
    }
}
