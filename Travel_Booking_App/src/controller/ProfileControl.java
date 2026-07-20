package controller;

import dao.UserDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.User;
import utility.AppContext;
import view.EditUserGUIPage1;



/**
 *
 * @author Max Zhang
 */
public class ProfileControl {
    private final AppContext context;
    private final EditUserGUIPage1 editProfileView;
    private final User currentUser;
    private final UserDAO userDAO;

    public ProfileControl(AppContext context, EditUserGUIPage1 editProfileView) {
        this.context = context;
        this.editProfileView = editProfileView;
        
        
        // get DAO
        userDAO = context.getUserDao();
        
        // get current user
        this.currentUser = userDAO.getUserFromUsername(context.getCurrentUser().getUsername());
 
        // populate form with existing data
        editProfileView.getInputUsername().setText(currentUser.getUsername()); // username
        editProfileView.getInputFirstName().setText(currentUser.getFirstName()); // username
        editProfileView.getInputLastName().setText(currentUser.getLastName()); // username
        editProfileView.getInputEmail().setText(currentUser.getEmail()); // username
        editProfileView.getInputPhone().setText(currentUser.getPhone()); // username
        
        
        // Cancel button
        this.editProfileView.addCancelBtnListener((ActionEvent e) -> {
            this.editProfileView.dispose();
        });
        
        // save button
        this.editProfileView.addNextBtnListener(new EditUserSaver());
    }
    
    class EditUserSaver implements ActionListener {
        private String username;
        private String password;
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        @Override
        public void actionPerformed(ActionEvent e) {
            username = editProfileView.getInputUsername().getText();
            password = editProfileView.getInputPassword().getText();
            firstName = editProfileView.getInputFirstName().getText();            firstName = editProfileView.getInputFirstName().getText();
            lastName = editProfileView.getInputLastName().getText();
            email = editProfileView.getInputEmail().getText();
            phone = editProfileView.getInputPhone().getText();

            if (this.password == null || this.password.isEmpty()) {
                this.password = currentUser.getPassword(); // no change
            }
            if (this.validateUsername(username)
                    && this.validatePassword(password)
                    && this.validateFirstName(firstName)
                    && this.validateLastName(lastName)
                    && this.validateEmail(email)) {
                currentUser.setUsername(username);
                currentUser.setPassword(password);
                currentUser.setFirstName(firstName);
                currentUser.setLastName(lastName);
                currentUser.setEmail(email);
                currentUser.setPhone(phone);
                userDAO.updateUser(currentUser);
            }
            
            editProfileView.dispose(); // close window
        }
        
        public boolean validateUsername(String username) { 
            return !( username == null || username.isEmpty() ); 
        }

        public boolean validatePassword(String password) { 
            return !(password == null || password.isEmpty() || password.length() < 8);
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
    }
    
}
