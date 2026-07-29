package controller;

import dao.CustomerDAO;
import dao.EmployeeDAO;
import dao.UserDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import javax.naming.directory.InvalidAttributeValueException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Customer;
import model.Employee;
import model.User;
import utility.AppContext;
import utility.PropertyValidator;
import view.FilterUsersGUI;
import view.profile.*;

/**
 *
 * @author Max Zhang, Mariah Malczewska
 */
public class ProfileControl {
    private AppContext context;
    private AbstractEditUserView editProfileView;
    private User currentUser;
    private UserDAO userDAO;
    
    private AddNewUser userView;
    private FilterUsersGUI usersView;
    
    /**
     * add new user view
     * @param context context
     * @param userView view
     */
    public ProfileControl ( AppContext context, AddNewUser userView ) { 
        this.context = context;
        this.userView = userView;
        userDAO = context.getUserDao();
        
        // add listeners
        
        // Cancel button
        this.userView.addCancelBtnListener((ActionEvent e) -> {
            this.userView.dispose();
        });
        
        // save
        this.userView.addSaveBtnListener(new AddUserRecord());
    }
    
    /**
     * Search for users
     * @param context the context
     * @param usersView the view
     */
    public ProfileControl( AppContext context, FilterUsersGUI usersView ) { //SearchView
        this.context = context;
        this.usersView = usersView;
        userDAO = context.getUserDao();
        
//        this.usersView.addNewUserBtnListener( new ProfileControl.AddUserRecord() );
        this.usersView.addSearchBtnListener( new SearchUsers() );
        if (context.getCurrentUser().getRole().equals("Customer")) {
            return;
        }
        if (!context.getCurrentUser().getRole().equals("Admin")) {
            usersView.getvSearchBarUsers().getComboUserRole().setSelectedItem("Customer"); // set customer search only
            usersView.getvSearchBarUsers().getComboUserRole().setEnabled(false); // disable it
        }
        
        SearchUsers su = new SearchUsers();
        
        // initial search
        su.initialSearch();
        
        // add listeners
        usersView.addSearchBtnListener(su);
        
        usersView.addNewUserBtnListener((ActionEvent e) -> {
            AddNewUser addView = new AddNewUser();
            addView.getSelectionRole().setSelectedItem("Customer");
            addView.getSelectionRole().setEnabled(context.getCurrentUser().getRole().equals("Admin")); // only admin can make any user, agents can only make customers
            addView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ProfileControl pc = new ProfileControl(context, addView);
            addView.setVisible(true);
        });
        
        // focus on a user
        usersView.addFocusBtnListener((ActionEvent e) -> {
            int selectedRow = usersView.getTblUsers().getSelectedRow(); // get selected row index
            int userID = -1;
            
            // if a row is selected
            if (selectedRow != -1) {
                userID = Integer.parseInt(usersView.getTblUsers().getValueAt(selectedRow, 0).toString());
            }
            
            // set the focus user
            if (userID != -1) {
                User focusUser = userDAO.getUsersFromID(userID).get(0);
                context.setCurrentFocusUser(focusUser);
                context.getCurrentSession().getDashControl().refreshDash();
                JOptionPane.showMessageDialog(null, "User focused: " + focusUser.getUsername());
            }
        });
        
        // open a user's profile
        usersView.addOpenBtnListener((ActionEvent e) -> {
            int selectedRow = usersView.getTblUsers().getSelectedRow(); // get selected row index
            int userID = -1;
            
            // if a row is selected
            if (selectedRow != -1) {
                userID = Integer.parseInt(usersView.getTblUsers().getValueAt(selectedRow, 0).toString());
            }
            
            // open the user's profile
            if (userID != -1) {
                User user = userDAO.getUsersFromID(userID).get(0);
                String role = user.getRole();
                
                if (role.equals("Customer")) {
                    EditCustomerGUI view = new EditCustomerGUI();
                    view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    ProfileControl pc = new ProfileControl(context, null, user, view);
                    view.setVisible(true);
                } else {
                    EditEmployeeGUI view = new EditEmployeeGUI();
                    view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    ProfileControl pc = new ProfileControl(context, null, user, view);
                    view.setVisible(true);
                }
            }
        });
    }
    
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
        Customer currentCustomer = customerDAO.getCustomerFromUserID(this.currentUser.getUserID());
        
        if (currentCustomer == null) {
            currentCustomer = new Customer(this.currentUser.getUserID()); // make employee object if it does not exist in the database
            customerDAO.addNewCustomer(this.currentUser); // add it to the database
        }
        
        // set fields on form
        editCustomerView.getInputEmergencyContactName().setText(currentCustomer.getEmergencyContactName()); // emergency contact
        editCustomerView.getInputEmergencyContactPhone().setText(currentCustomer.getEmergencyContactPhone()); // emergency contact
        
        // save button listener
        editCustomerView.addSaveBtnListener(new CustomerSaver(editCustomerView, currentCustomer, customerDAO));
        
        // delete account button listener
        editCustomerView.addDeleteAccountBtnListener(new DeleteCustomer(currentCustomer, customerDAO, editCustomerView, dc));

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
        Employee currentEmployee = employeeDAO.getEmployeeFromUserID(this.currentUser.getUserID());
        
        if (currentEmployee == null) {
            currentEmployee = new Employee(this.currentUser.getUserID()); // make employee object if it does not exist in the database
            employeeDAO.addNewEmployee(this.currentUser); // add it to the database
        }
        
        // set fields on form
        editEmployeeView.getInputHireDate().setText((currentEmployee.getHireDate() == null) ? "" : currentEmployee.getHireDate().toString()); // hire date
        editEmployeeView.getInputJobTitle().setText(currentEmployee.getJobTitle()); // job title
        
        // save button listener
        editEmployeeView.addSaveBtnListener(new EmployeeSaver(editEmployeeView, currentEmployee, employeeDAO));
        
        // delete account button listener
        editEmployeeView.addDeleteAccountBtnListener(new DeleteEmployee(currentEmployee, employeeDAO, editEmployeeView, dc));
    }
    private ProfileControl(AbstractEditUserView editProfileView, AppContext context, User user) {
        this.editProfileView = editProfileView;
        this.context = context;
        
        // get DAO
        userDAO = context.getUserDao();
        
        // get current user
        this.currentUser = userDAO.getUserFromUsername(user.getUsername());
 
        // populate form with existing data
        
        // account & personal information
        editProfileView.getInputUsername().setText(currentUser.getUsername()); // username
        editProfileView.getInputFirstName().setText(currentUser.getFirstName()); // first name
        editProfileView.getInputLastName().setText(currentUser.getLastName()); // last name
        editProfileView.getInputEmail().setText(currentUser.getEmail()); // email
        editProfileView.getInputPhone().setText(currentUser.getPhone()); // phone
        editProfileView.getSelectionRole().setSelectedItem(currentUser.getRole()); // role
        editProfileView.getRadioStatus().setSelected(currentUser.getAccountStatus().equals("Active")); // status
        
        // only admins are allowed to edit role and status
        if (!context.getCurrentUser().getRole().equals("Admin")) {
            editProfileView.getSelectionRole().setEnabled(false);
            editProfileView.getRadioStatus().setEnabled(false);
        }
        
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
            char[] passwordChars = editProfileView.getInputPassword().getPassword();
            firstName = editProfileView.getInputFirstName().getText();
            lastName = editProfileView.getInputLastName().getText();
            email = editProfileView.getInputEmail().getText();
            phone = editProfileView.getInputPhone().getText();
            role = editProfileView.getSelectionRole().getSelectedItem().toString();
            status = (editProfileView.getRadioStatus().isSelected()) ? "Active" : "Inactive";
            
            // convert password to string
            StringBuilder sb = new StringBuilder();
            for (char c : passwordChars) {
                sb.append(c);
            }
            
            password = sb.toString();
            
            // address
            city = editProfileView.getInputCity().getText();
            country = editProfileView.getInputCountry().getText();
            postalCode = editProfileView.getInputPost().getText();
            province = editProfileView.getInputProvince().getText();
            streetName = editProfileView.getInputStreet().getText();
            streetNumber = editProfileView.getInputStreetNumber().getText();
            
            try {
                if (this.password == null || this.password.isEmpty()) {
                    this.password = currentUser.getPassword(); // no change
                }
                
                // validate username
                if (!PropertyValidator.validateUsername(username)) {
                    throw new InvalidAttributeValueException( "must have a valid username");
                }
                
                // validate password
                if ( !PropertyValidator.validatePassword(password) ) {
                    throw new InvalidAttributeValueException("must have a valid password, minimum 8 characters");
                }
                // validate email
                if ( !PropertyValidator.validateEmail(email) ) {
                    throw new InvalidAttributeValueException( "must have a valid email");
                }
                // validate first name
                if ( !PropertyValidator.validateFirstName(firstName) ) {
                    throw new InvalidAttributeValueException( "must have a valid first name");
                }
                // validate last name
                if ( !PropertyValidator.validateLastName(lastName) ) {
                    throw new InvalidAttributeValueException( "must have a valid last name");
                }
                
                
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

                User user = userDAO.updateUser(currentUser); // update database
                if (user == null) {
                    JOptionPane.showMessageDialog( null , "Changes were not saved for an unknown reason. Check that you have a unique username and email address.");
                    return;
                }
                JOptionPane.showMessageDialog( null , "Changes have been saved."); // show success message
                editProfileView.dispose(); // close window

            }
            catch ( InvalidAttributeValueException ex){
                JOptionPane.showMessageDialog( null , ex.getMessage() );
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
    
    class DeleteCustomer implements ActionListener {
        
        Customer customer;
        
        CustomerDAO customerDAO;
        
        EditCustomerGUI editCustomerView;
        
        DashboardControl dc;
        
        public DeleteCustomer(Customer customer, CustomerDAO customerDAO, EditCustomerGUI editCustomerView, DashboardControl dc) {
            this.customer = customer;
            this.customerDAO = customerDAO;
            this.editCustomerView = editCustomerView;
            this.dc = dc;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            // confirm before delete
            int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this account? This action cannot be undone.", "Delete Account", JOptionPane.YES_NO_OPTION);
            if (result != JOptionPane.YES_OPTION) {
                return;
            }

            customerDAO.deleteCustomer(this.customer.getCustomerID()); // delete customer first
            userDAO.deleteUser(currentUser.getUserID()); // then delete user

            JOptionPane.showMessageDialog(null, "Account deleted");

            // dispose view
            editCustomerView.dispose();

            // if user deleted is current user, log out
            if (currentUser.getUserID() == context.getCurrentUser().getUserID()) {
                dc.logoutUser();
            }
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
    
    class DeleteEmployee implements ActionListener {
        
        Employee employee;
        
        EmployeeDAO employeeDAO;
        
        EditEmployeeGUI editEmployeeView;
        
        DashboardControl dc;
        
        public DeleteEmployee(Employee employee, EmployeeDAO employeeDAO, EditEmployeeGUI editEmployeeView, DashboardControl dc) {
            this.employee = employee;
            this.employeeDAO = employeeDAO;
            this.editEmployeeView = editEmployeeView;
            this.dc = dc;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            // confirm before delete
            int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this account? This action cannot be undone.", "Delete Account", JOptionPane.YES_NO_OPTION);
            if (result != JOptionPane.YES_OPTION) {
                return;
            }

            employeeDAO.deleteEmployee(this.employee.getEmployeeID()); // delete employee first
            userDAO.deleteUser(currentUser.getUserID()); // then delete user

            JOptionPane.showMessageDialog(null, "Account deleted");

            // dispose view
            editEmployeeView.dispose();

            // if user deleted is current user, log out
            if (currentUser.getUserID() == context.getCurrentUser().getUserID()) {
                dc.logoutUser();
            }
        }
    }

    class AddUserRecord implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            User user = newDBUser();
            
            Boolean isSuccess = false;
            if ( user != null ) { 
                if ( user.getRole().equals( "Customer" ) ) {
                    isSuccess = newDBCustomer( user );
                    if (!isSuccess) {
                        userView.dispose();
                        JOptionPane.showMessageDialog(null, "There was a problem making the Customer, but User was successful");
                        return;
                    }
                } else {
                    isSuccess = newDBEmployee( user );
                    if (!isSuccess) {
                        userView.dispose();
                        JOptionPane.showMessageDialog(null, "There was a problem making the Employee, but User was successful");
                        return;
                    }
                }
                
                // success
                userView.dispose();
                JOptionPane.showMessageDialog(null, "User created successfully");
            } else { JOptionPane.showMessageDialog(null, "User was not created"); }    
        }
        
        public User newDBUser( ) {
            String username = userView.getInputUsername().getText();
            char[] passwordChar = userView.getInputPassword().getPassword();
            String firstName = userView.getInputFirstName().getText();
            String lastName = userView.getInputLastName().getText();
            String email = userView.getInputEmail().getText();
            String phone = userView.getInputPhone().getText();
            Object roleObj = userView.getSelectionRole().getSelectedItem();
            
            // convert password to string
            StringBuilder sb = new StringBuilder();
            for (char c : passwordChar) {
                sb.append(c);
            }
            
            String password = sb.toString();
            try {
                // validate username
                if (!PropertyValidator.validateUsername(username)) {
                    throw new InvalidAttributeValueException( "must have a valid username");
                }
                
                // validate password
                if ( !PropertyValidator.validatePassword(password) ) {
                    throw new InvalidAttributeValueException("must have a valid password, minimum 8 characters");
                }
                // validate email
                if ( !PropertyValidator.validateEmail(email) ) {
                    throw new InvalidAttributeValueException( "must have a valid email");
                }
                // validate first name
                if ( !PropertyValidator.validateFirstName(firstName) ) {
                    throw new InvalidAttributeValueException( "must have a valid first name");
                }
                // validate last name
                if ( !PropertyValidator.validateLastName(lastName) ) {
                    throw new InvalidAttributeValueException( "must have a valid last name");
                }
                
                String role = roleObj.toString();
                User user = new User(username, password, firstName, lastName, email, role, phone);
                user = userDAO.addNewUser(user);

                return user;
            } catch (InvalidAttributeValueException e) {
                JOptionPane.showMessageDialog( null , e.getMessage() );
            }

            return null;
        }
    }
    
    class SearchUsers implements ActionListener {
        

        @Override
        public void actionPerformed(ActionEvent e) {
            search();
        }
        
        public void initialSearch() {
            search();
        }
        
        private void search() {
            // get users
            
            ArrayList<User> users;
            
            String role = usersView.getvSearchBarUsers().getComboUserRole().getSelectedItem().toString();
            
            String username = null;
            int userID = -1;
            
            String searchTerm = usersView.getvSearchBarUsers().getTxtSearchField().getText().trim();
            
            // decide if searchTerm is ID or username
            if (isInteger(searchTerm)) {
                userID = Integer.parseInt(searchTerm);
            } else if (searchTerm != null && !searchTerm.isEmpty()) {
                username = searchTerm;
            }
            
            // if a valid userID is given, search based on userID
            if (userID >= 0) {
                users = userDAO.getUsersByRoleAndID(role, userID);
            } else if (username != null) {
                users = userDAO.getUsersByRoleAndUsername(role, username); // search based on username
            } else {
                users = userDAO.getUsersByRole(role); // search based on role
            }
            
            // get table
            DefaultTableModel model = (DefaultTableModel) usersView.getTblUsers().getModel();
            model.setRowCount(0);
            
            // populate table
            for (User user : users) {
                Object[] rowData = {
                    user.getUserID(),
                    user.getUsername(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getRole(),
                    user.getPhone(),
                    user.getAccountStatus()
                };

                model.addRow(rowData);
            }
            
        }
        
        private boolean isInteger(String s) {
            if (s.isBlank() || s.isEmpty()) {
                return false;
            }
            
            String digits = "0123456789";
            
            boolean isInt = true;
            
            for (int c = 0; c < s.length(); c++) {
                isInt = (digits.indexOf(s.charAt(c)) >= 0);
            }
            
            return isInt;
        }
        
    }
    
    public Boolean newDBCustomer( User user ){
        CustomerDAO custDao = new CustomerDAO();
        int userId = user.getUserID();

        if ( userId > -1 ) {
            Customer cust = custDao.addNewCustomer( user );
            if ( cust != null ) { return true; }
            else { return false; }
        }
        else {
            JOptionPane.showMessageDialog(null, "There was a problem making the customer account; could not get the related userID");
            return false;
        }
    }

    public Boolean newDBEmployee( User user ){
        EmployeeDAO empDao = new EmployeeDAO();
        int userId = user.getUserID();

        if ( userId > -1 ) {
            Employee emp = empDao.addNewEmployee( user );
            if ( emp != null ) { return true; }
            else { return false; }
        }
        else {
            JOptionPane.showMessageDialog(null, "There was a problem making the customer account; could not get the related userID");
            return false;
        }
    }
    
    // Validation Helper Functions
    public boolean validateRole(Object roleObj) {
        String role = null;
        boolean validRole = false;

        if ( roleObj == null ) { return false; }
        else { role = roleObj.toString(); }
        
        if ( role == null | role.isEmpty() ) { return false; }
        else if (role.equals("Admin") || role.equals("Travel Agent") || 
                role.equals("Tour Guide") || role.equals("Customer") ) { 
            return true; 
        } else { return false; }
    }
    public boolean validateUsername(String username) { 
        return !( username == null || username.isEmpty() ); 
    }
    public boolean validateFirstName(String firstName) { 
        return !( firstName == null || firstName.isEmpty() );
    }
    public boolean validateLastName(String lastName) { 
        return !( lastName == null || lastName.isEmpty() ); 
    }
    public boolean validateEmail(String email) { 
        return !( email == null || email.isEmpty() ); 
    }
    public boolean validatePassword(String password) { 
        return !(password == null || password.isEmpty() || password.length() < 8);
    }    
    public boolean validatePhone(String phone) { return true; } 
}
