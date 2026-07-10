
package model;

import java.util.Date;

/**
 *
 * @author Max Zhang
 */
public class Employee {
    // fields
    private int employeeID;
    private int userID;
    private String jobTitle;
    private Date hireDate;
    private statusType employeeStatus;

    // full constructor
    public Employee(int employeeID, int userID, String jobTitle, Date hireDate, statusType employeeStatus) {
        this(employeeID, userID);
        this.jobTitle = jobTitle;
        this.hireDate = hireDate;
        this.employeeStatus = employeeStatus;
    }

    // required constructor
    public Employee(int employeeID, int userID) {
        this.employeeID = employeeID;
        this.userID = userID;
    }
    
    //emum field options
    public static enum statusType { Active, Inactive }    
    
    // getters and setters
    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public statusType getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(statusType employeeStatus) {
        this.employeeStatus = employeeStatus;
    }
}
