
package controller;

import model.gui.Session;
import dao.CustomerDAO;
import dao.EmployeeDAO;
import dao.UserDAO;

/**
 *
 * @author rowan
 * Database states
 */
public class AppContext {
    Session currentSession = new Session();
    
    UserDAO userDao = new UserDAO();
    EmployeeDAO employeeDao = new EmployeeDAO();
    CustomerDAO customerDao = new CustomerDAO();


//Constructor    
    public AppContext() {
        this.currentSession = new Session();
        this.userDao = new UserDAO();
        this.employeeDao = new EmployeeDAO();
        this.customerDao = new CustomerDAO();
    }
    
    //Getters & Setters
    public Session getCurrentSession() { return currentSession; }
    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

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
    
    
}
