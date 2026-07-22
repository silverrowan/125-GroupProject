package controller;

import dao.CustomerDAO;
import dao.EmployeeDAO;
import dao.UserDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import model.Customer;
import model.Employee;
import model.User;
import utility.AppContext;
import utility.PropertyValidator;
import view.profile.*;



/**
 *
 * @author Max Zhang
 */
public class ProfileControl {
    private AppContext context;
    private final AbstractEditUserView editProfileView;
    private final User currentUser;
    private final UserDAO userDAO;

    /**
     * constructor for viewing customer profiles
     * @param context context of current user and session
     * @param dc DashboardControl used to log out
     * @param user the user whose profile is to be displayed
     * @param editCustomerView the view to display the profile
     */
    public ProfileControl(AppContext context, DashboardControl dc, User user, EditCustomerGUI editCustomerView) {
        this(editCustomerView, context, user); // update generic user information
        
        // get DAO
        CustomerDAO customerDAO = context.getCustomerDao();
        
        // get current customer
        Customer currentCustomer = customerDAO.getCustomerFromUserID(user.getUserID());
        
        // set fields on form
        editCustomerView.getInputEmergencyContactName().setText(currentCustomer.getEmergencyContactName()); // emergency contact
        editCustomerView.getInputEmergencyContactPhone().setText(currentCustomer.getEmergencyContactPhone()); // emergency contact
        
        // save button listener
        editCustomerView.addSaveBtnListener(new CustomerSaver(editCustomerView, currentCustomer, customerDAO));
        
        // delete account button listener
        editCustomerView.addDeleteAccountBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerDAO.deleteCustomer(currentCustomer.getCustomerID()); // delete customer first
                userDAO.deleteUser(currentUser.getUserID()); // then delete user
                
                // dispose view
                editCustomerView.dispose();
                
                // if user deleted is current user, log out
                if (user.getUserID() == context.getCurrentUser().getUserID()) {
                    dc.logoutUser();
                }
            }
        });
    }
    
    /**
     * constructor for viewing employee profiles
     * @param context context of current user and session
     * @param dc DashboardControl used to log out
     * @param user the user whose profile is to be displayed
     * @param editEmployeeView the view to display the profile
     */
    public ProfileControl(AppContext context, DashboardControl dc, User user, EditEmployeeGUI editEmployeeView) {
        this(editEmployeeView, context, user); // update generic user information
        
        // get DAO
        EmployeeDAO employeeDAO = context.getEmployeeDao();
        
        // get current employee
        Employee currentEmployee = employeeDAO.getEmployeeFromUserID(user.getUserID());
        
        // set fields on form
        editEmployeeView.getInputHireDate().setText((currentEmployee.getHireDate() == null) ? "" : currentEmployee.getHireDate().toString()); // hire date
        editEmployeeView.getInputJobTitle().setText(currentEmployee.getJobTitle()); // job title
        
        // save button listener
        editEmployeeView.addSaveBtnListener(new EmployeeSaver(editEmployeeView, currentEmployee, employeeDAO));
        
        // delete account button listener
        editEmployeeView.addDeleteAccountBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                employeeDAO.deleteEmployee(currentEmployee.getEmployeeID()); // delete employee first
                userDAO.deleteUser(currentUser.getUserID()); // then delete user
                
                // dispose view
                editEmployeeView.dispose();
                
                // if user deleted is current user, log out
                if (user.getUserID() == context.getCurrentUser().getUserID()) {
                    dc.logoutUser();
                }
            }
        });
    }
    private ProfileControl(AbstractEditUserView editProfileView, AppContext context, User user) {
        this.editProfileView = editProfileView;
        this.context = context;
        
        // get DAO
        userDAO = context.getUserDao();
        
        // get current user
        this.currentUser = user;
 
        // populate form with existing data
        
        // account & personal information
        editProfileView.getInputUsername().setText(currentUser.getUsername()); // username
        editProfileView.getInputFirstName().setText(currentUser.getFirstName()); // first name
        editProfileView.getInputLastName().setText(currentUser.getLastName()); // last name
        editProfileView.getInputEmail().setText(currentUser.getEmail()); // email
        editProfileView.getInputPhone().setText(currentUser.getPhone()); // phone
        editProfileView.getSelectionRole().setSelectedItem(currentUser.getRole()); // role
        editProfileView.getRadioStatus().setSelected(currentUser.getAccountStatus().equals("Active")); // status
        
        // address
        editProfileView.getInputCity().setText(currentUser.getCity()); // city
        editProfileView.getInputCountry().setText(currentUser.getCountry()); // country
        editProfileView.getInputPost().setText(currentUser.getPostalCode()); // postal code
        editProfileView.getInputProvince().setText(currentUser.getProvince()); // province
        editProfileView.getInputStreet().setText(currentUser.getStreetName()); // street
        editProfileView.getInputStreetNumber().setText(currentUser.getStreetNumber()); // street number
        
        
        // Cancel button
        this.editProfileView.addCancelBtnListener((ActionEvent e) -> {
            this.editProfileView.dispose();
        });
        
        // save button
        this.editProfileView.addSaveBtnListener(new UserSaver());
    }
    
    class UserSaver implements ActionListener {
        // account & personal info
        private String username;
        private String password;
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private String role;
        private String status;
        
        // address
        private String city;
        private String country;
        private String postalCode;
        private String province;
        private String streetName;
        private String streetNumber;
        
        @Override
        public void actionPerformed(ActionEvent e) {
            // account and personal info
            username = editProfileView.getInputUsername().getText();
            password = editProfileView.getInputPassword().getText();
            firstName = editProfileView.getInputFirstName().getText();
            lastName = editProfileView.getInputLastName().getText();
            email = editProfileView.getInputEmail().getText();
            phone = editProfileView.getInputPhone().getText();
            role = editProfileView.getSelectionRole().getSelectedItem().toString();
            status = (editProfileView.getRadioStatus().isSelected()) ? "Active" : "Inactive";
            
            // address
            city = editProfileView.getInputCity().getText();
            country = editProfileView.getInputCountry().getText();
            postalCode = editProfileView.getInputPost().getText();
            province = editProfileView.getInputProvince().getText();
            streetName = editProfileView.getInputStreet().getText();
            streetNumber = editProfileView.getInputStreetNumber().getText();
            
            if (this.password == null || this.password.isEmpty()) {
                this.password = currentUser.getPassword(); // no change
            }
            if (PropertyValidator.validateUsername(username)
                    && PropertyValidator.validatePassword(password)
                    && PropertyValidator.validateFirstName(firstName)
                    && PropertyValidator.validateLastName(lastName)
                    && PropertyValidator.validateEmail(email)) {
                // account & personal info
                currentUser.setUsername(username);
                currentUser.setPassword(password);
                currentUser.setFirstName(firstName);
                currentUser.setLastName(lastName);
                currentUser.setEmail(email);
                currentUser.setPhone(phone);
                currentUser.setAccountStatus(status);
                currentUser.setRole(role);
                
                // address
                currentUser.setUserAddress(streetNumber, streetName, city, province, postalCode, country);
                
                userDAO.updateUser(currentUser); // update database
                editProfileView.dispose(); // close window
            } else {
                // display error message?
            }
        }
        
    }
    
    class CustomerSaver implements ActionListener {
        
        // view
        private EditCustomerGUI editCustomerView;
        
        // customer
        private Customer currentCustomer;
        
        // DAO
        private CustomerDAO customerDAO;
        
        // fields to update
        private String emergencyContactName;
        private String emergencyContactPhone;
        
        // constructor
        public CustomerSaver(EditCustomerGUI editCustomerView, Customer currentCustomer, CustomerDAO customerDAO) {
            this.editCustomerView = editCustomerView;
            this.currentCustomer = currentCustomer;
            this.customerDAO = customerDAO;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            // get info from form
            this.emergencyContactName = this.editCustomerView.getInputEmergencyContactName().getText();
            this.emergencyContactPhone = this.editCustomerView.getInputEmergencyContactPhone().getText();
            
            // set customer info
            currentCustomer.setEmergencyContactName(emergencyContactName);
            currentCustomer.setEmergencyContactPhone(emergencyContactPhone);
            
            // update customer
            customerDAO.updateCustomer(currentCustomer);
        }
    }
    
    class EmployeeSaver implements ActionListener {
        
        // view
        private EditEmployeeGUI editEmployeeView;
        
        // employee
        private Employee currentEmployee;
        
        // DAO
        private EmployeeDAO employeeDAO;
        
        // fields to update
        private String jobTitle;
        private String hireDate;
        
        // constructor
        public EmployeeSaver(EditEmployeeGUI editEmployeeView, Employee currentEmployee, EmployeeDAO employeeDAO) {
            this.editEmployeeView = editEmployeeView;
            this.currentEmployee = currentEmployee;
            this.employeeDAO = employeeDAO;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            // get info from form
            this.jobTitle = this.editEmployeeView.getInputJobTitle().getText();
            this.hireDate = this.editEmployeeView.getInputHireDate().getText();
            
            // set employee info
            currentEmployee.setJobTitle(jobTitle);
            currentEmployee.setHireDate((hireDate.isEmpty() || hireDate.isBlank() || hireDate == null) ? null : Date.valueOf(hireDate));
            
            // update employee
            employeeDAO.updateEmployee(currentEmployee);
        }
    }
}
