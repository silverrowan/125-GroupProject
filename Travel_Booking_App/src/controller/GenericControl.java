
package controller;

import javax.swing.JFrame;
import utility.AppContext;
import utility.GenericView;

/**
 *
 * @author Mariah Malczewska
 */
public class GenericControl {
    private AppContext appcontext;
    private JFrame view;

    public GenericControl( AppContext appcontext, JFrame view ) {
        this.appcontext = appcontext;
        this.view = view;
    }

    public AppContext getAppcontext() { return appcontext; }
    public void setAppcontext(AppContext appcontext) { this.appcontext = appcontext; }

    public JFrame getView() { return view; }
    public void setView(JFrame view) { this.view = view; }
    
    
}
