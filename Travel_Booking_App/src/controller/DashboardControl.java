package controller;

import java.awt.Window;
import javax.naming.InvalidNameException;
import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.User;
import model.gui.Model_MenuItem;
import view.AddUserGUIPage1;
import view.Login;
import view.ProductsGUI;
import view.ViewCustomerGUI;
import view.components.AppWindow;
import view.components.ListMenu;
import view.DashboardMenu;

/**
 *
 * @author Mariah Malczewska
 */
public class DashboardControl<T> {
//    private menuDAO menuDao;
//    private ListMenu listMenu;
    private AppContext context;
    private DashboardMenu menu;
    private AppWindow dashWindow;
    
//    public DashboardControl(){}
//    public DashboardControl( AppContext context, DashboardMenu menu ){
    public DashboardControl( AppContext context, AppWindow dashWindow ){
        this.context = context;
        this.dashWindow = dashWindow;
        this.menu = dashWindow.getDashboardList();
}
    
    public void initialize() { 
        buildMenu(); 
        menu.getMenuList().addListSelectionListener( new MenuSelect() ); //menu listener
    }
        
    private void buildMenu(){
        User currentUser = context.getCurrentSession().getCurrentUser();
        switch ( currentUser.getRole() ) {
            case "Admin":
                menu.getMenuList().addItem(new Model_MenuItem("id-card", "My Profile", Model_MenuItem.MenuType.MENU, "ViewEmployeeProfile"));
                menu.getMenuList().addItem(new Model_MenuItem("user-search-line", "Search Customers", Model_MenuItem.MenuType.MENU, "SearchForCustomer"));
                //        Active Customer Name Header
                menu.getMenuList().addItem(new Model_MenuItem("id-card", "Customer Profile", Model_MenuItem.MenuType.MENU, "ViewCustomerProfile"));
                buildSharedMenu();
                break;
            case "Travel Agent":
        //        Agent Name Header
                menu.getMenuList().addItem(new Model_MenuItem("id-card", "Profile", Model_MenuItem.MenuType.MENU, "ViewEmployeeProfile"));
                menu.getMenuList().addItem(new Model_MenuItem("user-search-line", "Search Customers", Model_MenuItem.MenuType.MENU, "SearchForCustomer"));
        //        Active Customer Name Header
                menu.getMenuList().addItem(new Model_MenuItem("id-card", "Customer Profile", Model_MenuItem.MenuType.MENU, "ViewCustomerProfile"));
//                menu.getMenuList().addItem(new Model_MenuItem("id-card", "Customer Profile", Model_MenuItem.MenuType.MENU, "ViewCustomerProfile"));
                buildSharedMenu();
                break;
//            case "Tour Guide":                
            case "Customer":
                menu.getMenuList().addItem(new Model_MenuItem("id-card", "Profile", Model_MenuItem.MenuType.MENU, "ViewCustomerProfile"));
                buildSharedMenu();
                break;
        
        //refresh menu
    }
    }
    
    private void buildSharedMenu(){
        menu.getMenuList().addItem(new Model_MenuItem("world-search", "Packages", Model_MenuItem.MenuType.MENU, "SearchPackages"));
        menu.getMenuList().addItem(new Model_MenuItem("calendar_plus", "Latest", Model_MenuItem.MenuType.MENU, "LastBooking"));
//        menu.getMenuList().addItem(new Model_MenuItem("plans", "Plans", Model_MenuItem.MenuType.MENU, "ViewFutureBookingsOrItinerary"));
        menu.getMenuList().addItem(new Model_MenuItem("calendar_clock", "History", Model_MenuItem.MenuType.MENU, "ViewBookings"));
        menu.getMenuList().addItem(new Model_MenuItem("logout-box", "Logout", Model_MenuItem.MenuType.MENU, "Logout"));
        
        menu.repaint();
    }
    
    class MenuSelect implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting()) { return; }                
            
            Model_MenuItem item = menu.getMenuList().getSelectedValue();
            
            if (item == null) { return; }

            switch ( item.getLinkAction() ) {
                case "Logout":
                    logoutUser( context );
                    break;
                case "ViewBookings":
                    makeViewCustomerBookings( context );
                    break;
                case "LastBooking":
                    makeViewCustomerLastBooking( context );
                    break;
                case "SearchPackages":
                    makeViewPackageSearch( context );
                    break;
                case "ViewCustomerProfile":
                    makeViewProfile( context, false ); 
                    break;
                case "ViewEmployeeProfile":
                    makeViewProfile( context, true ); 
                    break;
                default:
                    System.out.println("Selection " + item.getLinkAction() + " has no valid target");
                    break;
            }
            
            menu.getMenuList().clearSelection(); // clears selection after completing - makes act like a button instead of a selection list
        }
    }
    
// ===========================================================================
// ===========================================================================
//  TEMPLATE
// ===========================================================================
// ===========================================================================
//    private void makeViewTEMPLATE( AppContext context ) {
//        // Make new view & set up
//        //==========================
//        //get the data & apply it? not sure if before or after creating the view/control
//        ViewGUIClassName view = new ViewGUIClassName(); //new view
//        //set to NOT close app when this window closes
//        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
//        //associated controller
//        ViewControlClassName userControl = new ViewControlClassName( context, view ); 
//        view.setVisible(true); //make it visible
//        
//        // Close preceeding window & clean up
//        //==========================
//        clear any data that needs to be cleared/reset
//        //dispose of opening window UNLESS DASHBOARD; 
//        //if it is dashboard, dont dispose. *logout is a special case*
//        this.dispose();  
//    }

    private void logoutUser( AppContext context ) {
        System.out.println("logout");
        context.getCurrentSession().clearCurrentUserAndCustomer();
        for ( Window window : Window.getWindows() ) {
            window.dispose();
        }
        Login loginView = new Login();
        LoginControl loginControl = new LoginControl(context, loginView);
        loginView.setVisible(true);
    }
    
    private void makeViewCustomerBookings( AppContext context ) {
        System.out.println("view bookings");
        // open/create multi-booking view for current customer
    }

    private void makeViewCustomerLastBooking( AppContext context ) {
        System.out.println("view a booking");
        // open/create single-booking view for current customer's last booking
    }
    
    private void makeViewPackageSearch( AppContext context ) {
        System.out.println("search products");
        //get the data & apply it? not sure if before or after creating the view/control
        ProductsGUI view = new ProductsGUI(); //new view
        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 

        //associated controller
//        DestinationsController packageControl = new DestinationsController( context, view ); 
        view.setVisible(true); //make it visible
    }

        
    private void makeViewProfile( AppContext context, boolean isEmployeeProfile ) {
        System.out.println("view profile; for employee? " + isEmployeeProfile );
        
        // Make new view & set up
        //==========================
        //get the data & apply it? not sure if before or after creating the view/control
        ViewCustomerGUI view = new ViewCustomerGUI(); //new view
        //set to NOT close app when this window closes
        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        //associated controller
//        AddUserGUIPage1 userControl = new AddUserGUIPage1( context, view ); 
        view.setVisible(true); //make it visible
        // Close preceeding window & clean up
        //==========================
//        clear any data that needs to be cleared/reset
//dispose of opening window UNLESS DASHBOARD; otherwise leave it along *logout is a special case*
//      this.dispose();  
    }


    
    

}