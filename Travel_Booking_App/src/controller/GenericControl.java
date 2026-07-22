
package controller;

import javax.swing.JFrame;
import utility.AppContext;
import utility.GenericView;

/**
 *
 * @author Mariah Malczewska
 */
public class GenericControl {
    private AppContext appContext;
    private JFrame view;

    public GenericControl( AppContext appContext, JFrame view ) {
        this.appContext = appContext;
        this.view = view;
    }

    public AppContext getAppContext() { return appContext; }
    public void setAppContext(AppContext appContext) { this.appContext = appContext; }

    public JFrame getView() { return view; }
    public void setView(JFrame view) { this.view = view; }
    
    
}
