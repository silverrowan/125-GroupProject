package service;

import dao.UserDAO;
import model.User;

/**
 *
 * @author rowan
 * service class to use buis logic and validations - if not using service layer
 * where do these go?
 */
public class UserService {
//    private static final UserDAO userDAO;
//    private final User user;
    
//    public UserService(UserDAO userDAO) {
////        this.userDAO = userDAO;
//    }
    
    public static boolean addNewUser(String username, String password, String firstName, String lastName, String email, Object roleObj, String phone) throws IllegalArgumentException {
        if ( !validateUsername(username) ) { throw new IllegalArgumentException("a username is required"); }
        if ( !validatePassword(password) ) { throw new IllegalArgumentException("Password must be at least 8 characters"); }
        if ( !validateFirstName(firstName) ) { throw new IllegalArgumentException("a first name is required"); }
        if ( !validateLastName(lastName) ) { throw new IllegalArgumentException("a last name is required"); }
        if ( !validateEmail(email) ) { throw new IllegalArgumentException("Please provide a valid email number"); }
        if ( !validatePhone(phone) ) { throw new IllegalArgumentException("Please provide a valid phone number"); }
        
        if ( !validateRole(roleObj) ) { throw new IllegalArgumentException("Role must be one of Admin, Travel Agent, Tour Guide, or Customer"); }
//        if ( !"Active".equals(__) && !"Inactive".equals(__) ) { throw new IllegalArgumentException("Status must be Active or Inactive"); }
//        if ( email == LETTERS@LETTERS.LETTERS -- note where 'letters' incl _-.) { throw new IllegalArgumentException("Please enter a valid email"); }

        User user = new User(username, password, firstName, lastName, email, roleObj.toString(), phone);
        boolean isSuccess = UserDAO.createNewUser(user);
        return isSuccess;
//        return userDAO.createNewUser(user);
    }
    
    public static boolean validateRole(Object roleObj) {
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
    
    public static boolean validateUsername(String username) { 
        return !( username == null || username.isEmpty() ); 
    }
    public static boolean validateFirstName(String firstName) { 
        return !( firstName == null || firstName.isEmpty() );
    }
    public static boolean validateLastName(String lastName) { 
        return !( lastName == null || lastName.isEmpty() ); 
    }
    public static boolean validateEmail(String email) { 
        return !( email == null || email.isEmpty() ); 
    }

    public static boolean validatePassword(String password) { 
        return !(password == null || password.isEmpty() || password.length() < 8);
    }
    
    public static boolean validatePhone(String phone) { return false; }
}
