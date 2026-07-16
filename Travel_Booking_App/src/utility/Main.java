
package utility;

import controller.DashboardControl;
import controller.LoginControl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.User;
import utility.AppWindow;
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

        AppWindow view = new AppWindow( context ); 
        DashboardControl dash = new DashboardControl( context, view ); 
        dash.initialize();
        view.setVisible(true);
    }
}


