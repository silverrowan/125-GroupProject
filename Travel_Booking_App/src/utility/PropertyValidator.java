
package utility;

/**
 * A collection of all validator methods for all properties
 * @author Max Zhang
 */
public class PropertyValidator {
    public static boolean validateUsername(String username) { 
        return !( username == null || username.isEmpty() ); 
    }

    public static boolean validatePassword(String password) { 
        return !(password == null || password.isEmpty() || password.length() < 8);
    }

    public static boolean validateFirstName(String firstName) { 
        return !( firstName == null || firstName.isEmpty() ); 
    }

    public static boolean validateLastName(String lastName) { 
        return !( lastName == null || lastName.isEmpty() ); 
    }

    public static boolean validateEmail(String email) {
        return !( email == null || email.isEmpty() || !(email.indexOf('@') > 0)); 
    }
}
