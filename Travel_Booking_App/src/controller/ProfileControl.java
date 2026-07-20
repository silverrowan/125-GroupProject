package controller;

import dao.UserDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public ProfileControl(AppContext context, EditCustomerGUI editCustomerView) {
        this(editCustomerView, context);
    }
    
    public ProfileControl(AppContext context, EditEmployeeGUI editEmployeeView) {
        this(editEmployeeView, context);
    }
    private ProfileControl(AbstractEditUserView editProfileView, AppContext context) {
        this.editProfileView = editProfileView;
        this.context = context;
        
        // get DAO
        userDAO = context.getUserDao();
        
        // get current user
        this.currentUser = userDAO.getUserFromUsername(context.getCurrentUser().getUsername());
 
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
                
                userDAO.updateUser(currentUser);
            }
            
            editProfileView.dispose(); // close window
        }
        
    }
    
}
