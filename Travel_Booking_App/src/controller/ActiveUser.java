package controller;

import model.User;

/**
 *
 * @author Mariah Malczewska
 */
public class ActiveUser {
    private int activeUserID;
    private int activeCustomerID;

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



