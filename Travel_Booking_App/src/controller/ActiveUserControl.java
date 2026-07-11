package controller;

import static controller.UserControl.validatePassword;
import static controller.UserControl.validateUsername;
import dao.UserDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.User;
import view.ViewLogin;
import view.components.AppWindow;

/**
 *
 * @author Mariah Malczewska
 */
public class ActiveUserControl {
//    private ActiveDAO activeDao; (if needed...)
    private UserDAO userDao;
    private ViewLogin loginView;
    
//    private int activeUserID;
//    private int activeCustomerID;

    public ActiveUserControl( UserDAO userDao, ViewLogin loginView ) {
        this.userDao = userDao;
        this.loginView = loginView;
        
        this.loginView.addLoginBtnListener( new LogInUser() );
        this.loginView.addNewCustomerBtnListener( new AddNewCustomer() );
    }
    
    class LogInUser implements ActionListener {
        private User activeUser;

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getTxtUserName().getText();
            char[] passChars = loginView.getTxtPassword().getPassword();
            
            String password = new String(passChars);
            clearPassArray(passChars);
            
            if ( !validateUsername(username) ) { 
                JOptionPane.showMessageDialog(null, "a username is required");
                throw new IllegalArgumentException("a username is required"); }
            if ( !validatePassword(password) ) { 
                JOptionPane.showMessageDialog(null, "a password is required, and must be at least 8 characters");
                throw new IllegalArgumentException("Password must be at least 8 characters"); }

            activeUser = UserDAO.getUserFromUsername(username, password);
            
            if ( activeUser == null ) { JOptionPane.showMessageDialog(null, "Username or password do not match, try again"); }
            else { 
                System.out.println("Successful Login");
                //open appropriate dashboard, assign user as activeUser }
                AppWindow view = new AppWindow();
                view.setVisible(true);
                //close login window
                //set active user
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        }
    }
    
    class AddNewCustomer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //open add new customer window

            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }

//    public int getActiveUserID() {
//        return activeUserID;
//    }
//
//    public void setActiveUserID(int activeUserID) {
//        this.activeUserID = activeUserID;
//    }
//
//    public int getActiveCustomerID() {
//        return activeCustomerID;
//    }
//
//    public void setActiveCustomerID(int activeCustomerID) {
//        this.activeCustomerID = activeCustomerID;
//    }
    
    // get related values from user by 100
    public String getActiveUserRole() {
//        return ( where userID == activeUserID) getRole();
        return "";
    }
    
    public String getActiveCustomerName(int activeCustomerID) {
//        return ( where userID == activeCustomerID ) getFirstName() + " " getLastName();
        return "";
    }
 
    public static void clearPassArray(char[] pass) {
        for (int i = pass.length -1 ; i >= 0 ; i--) {
            pass[i] = 0;
        }
    }
    
    public static boolean validateUsername(String username) { 
        return !( username == null || username.isEmpty() ); 
    }
    
    public static boolean validatePassword(String password) { 
        return !(password == null || password.isEmpty() || password.length() < 8);
    }
}



