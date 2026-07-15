
package model.gui;

import model.Customer;
import model.User;
import view.ViewCustomerGUI;
import view.components.AppWindow;

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

    public Session() { }
    
    public Session( User currentUser ) {
        this.currentUser = currentUser;
        currentUser.setPassword( null );
    }

    public User getCurrentUser() { return currentUser; }
    public void setCurrentUser(User currentUser) { 
        this.currentUser = currentUser;
        currentUser.setPassword( null );
    }

    public User getCurrentCustomer() { return currentCustomer; }
    public void setCurrentCustomer(User currentCustomer) {
        this.currentCustomer = currentCustomer;
        currentCustomer.setPassword( null );
    }
    
    public void clearCurrentUser() { this.currentUser = null; }
    public void clearCurrentCustomer() { this.currentCustomer = null; }
    public void clearCurrentUserAndCustomer() { 
        this.clearCurrentUser();
        this.clearCurrentCustomer();
    }
}

/* 

general implementation notes: 
    DAO should not know who the current user is. 


*/