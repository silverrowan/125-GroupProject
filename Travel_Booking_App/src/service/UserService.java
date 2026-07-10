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
    private final UserDAO userDAO;
    
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    
    public boolean addNewUser(String username, String password, String firstName, String lastName, String email, String role){
        if ( username == null | username.isEmpty() ) { throw new IllegalArgumentException("a username is required"); }
        if ( password == null | password.isEmpty() ) { throw new IllegalArgumentException("a password is required"); }
        if ( firstName == null | firstName.isEmpty() ) { throw new IllegalArgumentException("a first name is required"); }
        if ( lastName == null | lastName.isEmpty() ) { throw new IllegalArgumentException("a last name is required"); }
        if ( email == null | email.isEmpty() ) { throw new IllegalArgumentException("an email is required"); }
        if ( role == null | role.isEmpty() ) { throw new IllegalArgumentException("a role is required"); }
        
        if ( password.length() < 6 ) { throw new IllegalArgumentException("Password must be at least 6 characters"); }
        boolean validRole = false;
        if (role.equals("Admin") || role.equals("Travel Agent") || 
                role.equals("Tour Guide") || role.equals("Customer") ) { 
            validRole = true; 
        }
        if ( !validRole ) { throw new IllegalArgumentException("Role must be one of Admin, Travel Agent, Tour Guide, or Customer"); }
//        if ( !"Active".equals(__) && !"Inactive".equals(__) ) { throw new IllegalArgumentException("Status must be Active or Inactive"); }
//        if ( email == LETTERS@LETTERS.LETTERS -- note where 'letters' incl _-.) { throw new IllegalArgumentException("Please enter a valid email"); }

        User user = new User(username, password, firstName, lastName, email, role);
        return userDAO.createNewUser(user);
    }
}
