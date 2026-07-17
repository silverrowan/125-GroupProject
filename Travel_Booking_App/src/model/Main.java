
package model;

import utility.AppContext;
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
//        User placeholder = new User();
        AppWindow dash = new AppWindow( context );
        dash.setVisible(true);
    }

}
