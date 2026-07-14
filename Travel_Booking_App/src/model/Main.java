
package model;

import controller.AppContext;
import controller.LoginControl;
import model.gui.Session;
import view.Login;
import view.components.AppWindow;

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
        
        LoginControl loginControl = new LoginControl(context, loginView);
        loginView.setVisible(true);

//        AppWindow app = new AppWindow( context );
//        AppControl appControl = new AppControl();
        // instance of login when the real version. For now, new AppWindow        // instance of login when the real version. For now, new AppWindow
            }

}
