package controller;

import controller.AppContext;
import dao.UserDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.User;
import view.AddUserGUIPage1;
import view.Login;
import view.components.AppWindow;
import view.components.DashboardMenu;

/**
 *
 * @author Mariah Malczewska
 * Handles the Logic and Validation for the Login Screen
 */
public class LoginControl {
    private final AppContext context;
    private final Login loginView;

    public LoginControl( AppContext context, Login loginView ) {
        this.context = context;
        this.loginView = loginView;
        
        this.loginView.addLoginBtnListener( new LogInUser() );
        this.loginView.addNewCustomerBtnListener( new AddNewCustomer() );
    }
    
    class LogInUser implements ActionListener {
        private User activeUser;
        private User loginUser;

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

            loginUser = context.getUserDao().getUserFromUsername(username, password);

            
            if ( loginUser == null ) { JOptionPane.showMessageDialog(null, "Username or password do not match, try again"); }
            else {
                activeUser = loginUser;
                loginUser = null;
                context.getCurrentSession().setCurrentUser(activeUser);
                System.out.println("Successful Login");
                
                AppWindow view = new AppWindow( context ); //make target window/dashboard: This is Menu AND beside contents.
                DashboardControl dash = new DashboardControl( context, view );
                dash.initialize();
                view.setVisible(true);
                
                AddUserGUIPage1 view2 = new AddUserGUIPage1( ); //make target window/dashboard: This is Menu AND beside contents.
                UserControl userControl = new UserControl( context, view2 );
//                userControl.initialize();
                view2.setVisible(true);
//                DashboardMenu dashboard = new DashboardMenu(); // can't use this one - MENU is an x of gradient which is of JPanel not Frame
//                dashboard.setVisible(true);
                loginView.dispose();
    // make it visible
//                TO DO 
                //close login window
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
 
    public void clearPassArray(char[] pass) {
        for (int i = pass.length -1 ; i >= 0 ; i--) {
            pass[i] = 0;
        }
    }
    
    public boolean validateUsername(String username) { 
        return !( username == null || username.isEmpty() ); 
    }
    
    public boolean validatePassword(String password) { 
        return !(password == null || password.isEmpty() || password.length() < 8);
    }
}