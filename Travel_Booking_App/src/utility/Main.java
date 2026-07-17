
package utility;

import controller.DashboardControl;
import controller.LoginControl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.User;
import utility.AppWindowCust;
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
        String username = "s";           
        String password = "123123123";

        activeUser = context.getUserDao().getUserFromUsername(username, password);
        loginUser = null;
        context.getCurrentSession().setCurrentUser(activeUser);
        System.out.println("Successful Login");

        String role = context.getCurrentUser().getRole();
        System.out.println("role: " + role);
        JFrame view;
        
        if ( role.equals( "Admin" ) ) {
            System.out.println("entered admin if");
            view = new AppWindowAdmin( context ); //make target window/dashboard: This is Menu AND beside contents.
        } else if ( role == "Travel Agent" ) {
            System.out.println("entered Agent if");
            view = new AppWindowAgent( context );
//        } else if ( role == "Travel Guide") {
//            //later
        } else {
            System.out.println("entered Cust/Other if");
            view = new AppWindowCust( context );
        }
        
        System.out.println("view: " + view);
        
        DashboardControl dash = new DashboardControl( context, view ); 
        dash.initialize();
        view.setVisible(true);
    }  
}


