
package model;

/**
 *
 * @author Max Zhang
 */
public class User {
    // fields
    private int userID;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String streetNumber;
    private String streetName;
    private String city;
    private String province;
    private String postalCode;
    private String country;
    private roleType role;
    private statusType accountStatus;

    // full constructor
    public User(int userID, String username, String password, String firstName, String lastName, String email, String phone, String streetNumber, String streetName, String city, String province, String postalCode, String country, roleType role, statusType accountStatus) {
        this(userID, username, password, firstName, lastName, email, role, accountStatus);
        this.phone = phone;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.country = country;
    }

    // constructor with requred fields only
    public User(int userID, String username, String password, String firstName, String lastName, String email, roleType role, statusType accountStatus) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;        
        this.accountStatus = accountStatus;
    }

    //emum field options
    public static enum roleType { CUSTOMER, ADMIN, AGENT, GUIDE }// NOTE do not match database names exactly
    public static enum statusType { Active, Inactive }    
    
    // getters and setters
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public roleType getRole() {
        return role;
    }

    public void setRole(roleType role) {
        this.role = role;
    }

    public statusType getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(statusType accountStatus) {
        this.accountStatus = accountStatus;
    }
}
