
package model;

/**
 *
 * @author Max Zhang
 */
public class Customer {
    private int customerID;
    private int userID;
    private String emergencyContactName;
    private String emergencyContactPhone;
    private String customerNotes;
    private String emergencyBlock;

    // full constructor
    public Customer(int customerID, int userID, String emergencyContactName, String emergencyContactPhone, String customerNotes) {
        this(customerID, userID);
        this.emergencyContactName = emergencyContactName;
        this.emergencyContactPhone = emergencyContactPhone;
        this.customerNotes = customerNotes;
    }

    // required constructor
    public Customer(int customerID, int userID) {
        this.customerID = customerID;
        this.userID = userID;
    }
    
    // getters and setters
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getEmergencyContactPhone() {
        return emergencyContactPhone;
    }

    public void setEmergencyContactPhone(String emergencyContactPhone) {
        this.emergencyContactPhone = emergencyContactPhone;
    }
    
    public void setEmergencyContactBlock(){
        this.emergencyBlock = this.emergencyContactName + ": " + this.emergencyContactPhone;
    }
    
    public String getEmergencyContactBlack(){
        return emergencyBlock;
    }

    public String getCustomerNotes() {
        return customerNotes;
    }

    public void setCustomerNotes(String customerNotes) {
        this.customerNotes = customerNotes;
    }

}
