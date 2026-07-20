
package utility;

import controller.DashboardControl;
import controller.LoginControl;
import controller.ProfileControl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.User;
import utility.AppWindowCust;
import view.profile.EditCustomerGUI;
import view.profile.EditEmployeeGUI;
import view.Login;

/**
 *
 * @author Mariah Malczewska
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        AppContext context = new AppContext();
        Login loginView = new Login();
        Session currentSession = new Session();
        
//        Bypassing Login & setting username manually
//------------------------------------------------------------------------------
//------------------------------------------------------------------------------
//        LoginControl loginControl = new LoginControl(context, loginView);
//        loginView.setVisible(true);
        directToDash(context);
    }  

    public static void directToDash(AppContext context) {
        User activeUser;
        User loginUser;
        String username = "mzhang";           
        String password = "12341234";
//        String username = "cchen";           
//        String password = "123123123";

        activeUser = context.getUserDao().getUserFromUsername(username, password);
        loginUser = null;
        context.getCurrentSession().setCurrentUser(activeUser);
        System.out.println("Successful Login");

        String role = context.getCurrentUser().getRole();
        System.out.println("role: " + role);
//        JFrame view;
        
        if ( role.equals( "Admin" ) ) {
            System.out.println("entered admin if");
            AppWindowAdmin view = new AppWindowAdmin( context ); //make target window/dashboard: This is Menu AND beside contents.
            DashboardControl dash = new DashboardControl( context, view ); 
            dash.initialize();
            view.setVisible(true); 
        } else if ( role.equals("Travel Agent") ) {
            System.out.println("entered Agent if");
            AppWindowAgent view = new AppWindowAgent( context );
            DashboardControl dash = new DashboardControl( context, view );
            dash.initialize();
            view.setVisible(true); 
//        } else if ( role.equals("Travel Guide") ) {
//            //later
        } else {
            System.out.println("entered Cust/Other if");
            AppWindowCust view = new AppWindowCust( context );
            DashboardControl dash = new DashboardControl( context, view );
            dash.initialize();
            view.setVisible(true);
        }
        
        // Edit customer & employees testing code (TEMPORARY)
        if (context.getCurrentUser().getRole().equals("Customer")) {
            EditCustomerGUI viewEdit = new EditCustomerGUI();
            viewEdit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ProfileControl pc = new ProfileControl(context, viewEdit);
            viewEdit.setVisible(true);
        } else {
            EditEmployeeGUI viewEdit = new EditEmployeeGUI();
            viewEdit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ProfileControl pc = new ProfileControl(context, viewEdit);
            viewEdit.setVisible(true);
        }
    }
}


