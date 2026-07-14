package controller;

import dao.UserDAO;
import view.AddUserGUIPage1;
import model.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Customer;
import model.Employee;


/**
 *
 * @author rowan
 */
public class UserControl {
    private AppContext userDao;
    private AddUserGUIPage1 userView;
//    private UserService userService;
    
//    public UserControl( UserDAO userDao, AddUserGUIPage1 userView, UserService userService ) {
//        this.userDao = userDao;
//        this.userView = userView;
////        this.userService = userService;
//        
//        this.userView.addNextBtnListener( new AddUserRecord() );
//    }
   
    public UserControl( AppContext userDao, AddUserGUIPage1 userView ) {
        this.userDao = userDao;
        this.userView = userView;
        
        this.userView.addNextBtnListener( new AddUserRecord() );
    }
   
    class AddUserRecord implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = userView.getTxtUsername().getText();
            String password = userView.getTxtPassword().getText();
            String firstName = userView.getTxtFirstName().getText();
            String lastName = userView.getTxtLastName().getText();
            String email = userView.getTxtEmail().getText();
            String phone = userView.getTxtPhone().getText();
            Object roleObj = userView.getComboRole().getSelectedItem();
            
            if ( !validateUsername(username) ) { throw new IllegalArgumentException("a username is required"); }
            if ( !validatePassword(password) ) { throw new IllegalArgumentException("Password must be at least 8 characters"); }
            if ( !validateFirstName(firstName) ) { throw new IllegalArgumentException("a first name is required"); }
            if ( !validateLastName(lastName) ) { throw new IllegalArgumentException("a last name is required"); }
            if ( !validateEmail(email) ) { throw new IllegalArgumentException("Please provide a valid email number"); }
            if ( !validatePhone(phone) ) { throw new IllegalArgumentException("Please provide a valid phone number"); }
            if ( !validateRole(roleObj) ) { throw new IllegalArgumentException("Role must be one of Admin, Travel Agent, Tour Guide, or Customer"); }
            
            String role = roleObj.toString();
            User user = new User(username, password, firstName, lastName, email, role, phone);
//            if ( role == "Customer") { 
//                Customer cust = new Customer(user, getID(user)); 
//            }
//            else { 
//                Employee emp = new Employee(user, getID(user)); 
//            }
//            boolean isSuccess = UserDAO.addNewUser(user);
//            user = userDao.addNewUser(user);

            if ( !(user == null) ) { JOptionPane.showMessageDialog(null, "User created successfully"); }
            else { JOptionPane.showMessageDialog(null, "User was not created"); }
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
    
       //        if ( !"Active".equals(__) && !"Inactive".equals(__) ) { throw new IllegalArgumentException("Status must be Active or Inactive"); }
    //        if ( email == LETTERS@LETTERS.LETTERS -- note where 'letters' incl _-.) { throw new IllegalArgumentException("Please enter a valid email"); }

    
    public boolean validatePhone(String phone) { return true; }
    
}
