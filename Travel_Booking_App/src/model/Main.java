
package model;

import controller.Session;
import utility.AppContext;
import view.ViewLogin;
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
//        ViewLogin login = new ViewLogin();
//        login.setVisible(true);
        // instance of login when the real version. For now, new AppWindow
        AppContext context = new AppContext();
//        User placeholder = new User();
        AppWindow dash = new AppWindow( context );
        dash.setVisible(true);
    }

}
