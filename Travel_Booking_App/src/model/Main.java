package model;

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
        // instance of login when the real version. For now, new AppWindow
        new AppWindow().setVisible(true);
    }

}
