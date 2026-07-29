package controller;

import utility.AppContext;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.User;
import view.dashboard.AppWindowAdmin;
import view.dashboard.AppWindowAgent;
import view.Login;
import view.dashboard.AppWindowCust;
import view.profile.NewCustomerGUI;

/**
 *
 * @author Mariah Malczewska
 * Handles the Logic and Validation for the Login Screen
 */
public class LoginControl {
    private AppContext context;
    private Login loginView;

    public LoginControl( AppContext context, Login loginView ) {
        this.context = context;
        this.loginView = loginView;
        
        this.loginView.addLoginBtnListener( new LogInUser() );
        this.loginView.addNewCustomerBtnListener( new addNewUser() );
    }
    
    class LogInUser implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            loginUser();
            loginView.dispose();           
        }
    }
    
    class addNewUser implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("entered new user");
            NewCustomerGUI view = new NewCustomerGUI();
            view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ProfileControl newUControl = new ProfileControl( context, view );
            view.setVisible(true);         
        }
    }
    
    public void loginUser(){
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

        User loginUser = context.getUserDao().getUserFromUsername(username, password);
        User activeUser;

        if ( loginUser == null ) { JOptionPane.showMessageDialog(null, "Username or password do not match, try again"); }
        else {
            activeUser = loginUser;
            loginUser = null;
            activeUser.setPassword( null );
            context.getCurrentSession().setCurrentUser(activeUser);
            System.out.println("Successful Login");
            getDashboard();
        }
    }

    public void getDashboard() {
        User user = context.getCurrentUser();
        if ( user == null ) {
            JOptionPane.showMessageDialog( null , "No current user set");
            return;
        }
        String role = user.getRole();
        System.out.println("role: " + role);

        if ( role.equals( "Admin" ) ) { //already checked for null role in calling function.
            System.out.println("entered admin if");
            AppWindowAdmin view = new AppWindowAdmin( context );
            DashboardControl dash = new DashboardControl( context, view );
            view.setVisible(true); 
            context.getCurrentSession().setDashControl( dash );
        } else if ( role.equals("Travel Agent") ) {
            System.out.println("entered Agent if");
            AppWindowAgent view = new AppWindowAgent( context );
            DashboardControl dash = new DashboardControl( context, view );
            view.setVisible(true); 
            context.getCurrentSession().setDashControl( dash );
//        } else if ( role.equals("Travel Guide") ) {
//            //later
        } else {
            System.out.println("entered Cust/Other if");
            context.getCurrentSession().setCurrentCustomer( user ); 
            AppWindowCust view = new AppWindowCust( context );
            DashboardControl dash = new DashboardControl( context, view );
            view.setVisible(true); 
            context.getCurrentSession().setDashControl( dash );
        }   
    }
    
    //helpers
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
