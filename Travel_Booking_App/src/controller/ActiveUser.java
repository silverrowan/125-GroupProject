package controller;

import model.User;

/**
 *
 * @author Mariah Malczewska
 */
public class ActiveUser {
    private Object activeUser;
    private Object activeCustomer;
    private int activeUserID;
    private int activeCustomerID;

    
//    need:
//    User.java:
//        user id
//        username
//        firstname
//        lastname
//        role
//        status
//    Customer.java:
//        customer id
//    Employee.java:
//        employee id
//        employeeStatus
                                                    
                                                    
    public ActiveUser() {
    }

    public int getActiveUserID() {
        return activeUserID;
    }

    public void setActiveUserID(int activeUserID) {
        this.activeUserID = activeUserID;
    }

    public int getActiveCustomerID() {
        return activeCustomerID;
    }

    public void setActiveCustomerID(int activeCustomerID) {
        this.activeCustomerID = activeCustomerID;
    }
    
    // get related values from user by 100
    public String getActiveUserRole() {
//        return ( where userID == activeUserID) getRole();
        return "";
    }
    
    public String getActiveCustomerName(int activeCustomerID) {
//        return ( where userID == activeCustomerID ) getFirstName() + " " getLastName();
        return "";
    }
    
}



