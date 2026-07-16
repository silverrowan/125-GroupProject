
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
    private String role;
    private String accountStatus;

    // full constructor
    public User(String username, String password, String firstName, String lastName, String email, String phone, String streetNumber, String streetName, String city, String province, String postalCode, String country, String role, String accountStatus) {
        this(username, password, firstName, lastName, email, role);
        this.phone = phone;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.country = country;
        this.accountStatus = accountStatus;
    }

    // constructor with requred fields only
    public User(String username, String password, String firstName, String lastName, String email, String role) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;        
    }

    public User(String username, String password, String firstName, String lastName, String email, String role, String phone) {
        this(username, password, firstName, lastName, email, role);
        this.phone = phone;
    }
    
    public User(){
       this("placeholder", "placeholder", "placeholder", "placeholder", "placeholder", "placeholder");
    }
    
    // getters and setters
    public int getUserID() {
        return userID;
    }
    
    public void setuserID(int userID) {
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }
    
    public void setUserAddress( String streetNumber, String streetName, String 
            city, String province, String postalCode, String country) {
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.country = country;
    }            
}
