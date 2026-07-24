
package utility;

import controller.DashboardControl;
import javax.swing.JOptionPane;
import model.Customer;
import model.User;

/**
 *
 * @author Mariah Malczewska
 * keeps session information - currently just active user and customer 
 * empty state, User & customer are null, Employee not representing a customer
 * user is employee customer is null. Employee representing a customer user 
 * is employee, customer is customer. Customer representing self, both 
 * user and customer are customer
 */
public class Session {
    private User currentUser;
    private User currentCustomer; 
    private User currentEmployee; // only used by Admin 
    private DashboardControl dashControl;

    public Session() { }
    
    public Session( User currentUser ) {
        this.currentUser = currentUser;
    }

    public User getCurrentUser() { return currentUser; }
    public void setCurrentUser(User currentUser) { 
        this.currentUser = currentUser;
    }

    public User getCurrentCustomer() { return currentCustomer; }
    public void setCurrentCustomer(User currentCustomer) {
        this.currentEmployee = null;
        this.currentCustomer = currentCustomer;
    }
    
    public User getCurrentEmployee() { return currentEmployee; }
    public void setCurrentEmployee(User currentEmployee) {
        this.currentCustomer = null;
        this.currentEmployee = currentEmployee;
    }

    public DashboardControl getDashControl() { return dashControl; }
    public void setDashControl(DashboardControl dashControl) { 
        this.dashControl = dashControl; 
    }
    
    
    
    public User getFocusUser() {
        User user = getCurrentUser();
        User custUser = getCurrentCustomer();
        User empUser = getCurrentEmployee();
        
        if ( !user.getRole().equals("Admin") && empUser != null ) { 
            clearCurrentEmployee(); 
        }
        
        User focusUser;
        if ( custUser != null ) { focusUser = custUser; } 
        else { focusUser = empUser; }
        
        return focusUser;
    }
        public void setFocusUser( User user) {
            if ( user.getRole().equals( "Admin") ||
                    user.getRole().equals( "Travel Agent") ||
                    user.getRole().equals( "Tour Guide") ) {
                setCurrentEmployee( user );
            } else if ( user.getRole().equals( "Customer") ) {
                setCurrentCustomer( user );
            } else {
                JOptionPane.showMessageDialog( null , user.getUsername() + " does not have a vaild role." );
            }
    }
    
    public void clearCurrentUser() { this.currentUser = null; }
    public void clearCurrentCustomer() { this.currentCustomer = null; }
    public void clearCurrentEmployee() { this.currentEmployee = null; }
    public void clearFoci() {
        clearCurrentCustomer();
        clearCurrentEmployee();
    }
    public void clearSession() { 
        clearCurrentCustomer();
        clearCurrentEmployee();
        clearCurrentUser();
    }
}

/* 

general implementation notes: 
    DAO should not know who the current user is. 


*/