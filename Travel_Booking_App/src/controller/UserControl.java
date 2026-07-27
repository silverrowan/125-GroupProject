package controller;

import utility.AppContext;
import view.AddUserGUIPage1;
import model.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import dao.UserDAO;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import view.FilterUsersGUI;
import view.profile.EditCustomerGUI;
import view.profile.EditEmployeeGUI;


/**
 *
 * @author rowan (Mariah Malczewska), Max Zhang
 */
public class UserControl {
    private AppContext context;
    private UserDAO userDAO;
    private AddUserGUIPage1 userView;
    private FilterUsersGUI usersView;
   
    public UserControl( AppContext context, AddUserGUIPage1 userView ) {
        this.context = context;
        this.userView = userView;
        userDAO = context.getUserDao();
        
        this.userView.addNextBtnListener( new AddUserRecord() );
    }

    public UserControl( AppContext context, FilterUsersGUI usersView ) {
        this.context = context;
        this.usersView = usersView;
        userDAO = context.getUserDao();
        
        this.usersView.addNewUserBtnListener( new AddUserRecord() );
        this.usersView.addSearchBtnListener( new SearchUsers() );
        //****** IMPORTANT****** if active user is customer or agent only show/ allow customers option
        //****** IMPORTANT****** if active user is admin, show/allow role drop-down
        if (context.getCurrentUser().getRole().equals("Customer")) {
            return; // customers cannot search other users!
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
            AddUserGUIPage1 addView = new AddUserGUIPage1();
            addView.getComboRole().setSelectedItem("Customer");
            addView.getComboRole().setEnabled(context.getCurrentUser().getRole().equals("Admin")); // only admin can make any user, agents can only make customers
            addView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            UserControl uc = new UserControl(context, addView);
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
                context.setCurrentFocusUser(userDAO.getUsersFromID(userID).get(0));
                System.out.println("Focus user set to " + context.getCurrentFocusUser().getUsername());
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
            user = userDAO.addNewUser(user);

            if ( !(user == null) ) { JOptionPane.showMessageDialog(null, "User created successfully"); }
            else { JOptionPane.showMessageDialog(null, "User was not created"); }
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
