
package utility;

import dao.CustomerDAO;
import dao.DestinationsDAO;
import dao.EmployeeDAO;
import dao.PaymentsDAO;
import dao.TripsDAO;
import dao.UserDAO;
import model.User;

/**
 *
 * @author rowan
 * Database states
 */
public class AppContext {
    // Session information inside: Current DashboardControl, User, Customer, Employee
    Session currentSession = new Session();  
    // DAOs
    UserDAO userDao = new UserDAO();
    EmployeeDAO employeeDao = new EmployeeDAO(); 
    CustomerDAO customerDao = new CustomerDAO();
    DestinationsDAO destDao = new DestinationsDAO();
    PaymentsDAO pmtDao = new PaymentsDAO();
    TripsDAO tripsDao = new TripsDAO();


//Constructor    
    public AppContext() {}

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

    public DestinationsDAO getDestDao() { return destDao; }
    public void setDestDao(DestinationsDAO destDao) { this.destDao = destDao; }

    public PaymentsDAO getPmtDao() { return pmtDao; }
    public void setPmtDao(PaymentsDAO pmtDao) { this.pmtDao = pmtDao; }

    public TripsDAO getTripsDao() { return tripsDao; }
    public void setTripsDao(TripsDAO tripsDao) { this.tripsDao = tripsDao; }
    
    
    
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
    public void setCurrentFocusUser( User user ){
        getCurrentSession().setFocusUser(user);
    }
}
