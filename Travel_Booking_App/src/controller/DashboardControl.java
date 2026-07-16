package controller;

import utility.AppContext;
import utility.DuplicateTargetException;
import java.awt.Window;
import java.nio.file.AccessDeniedException;
import java.util.LinkedList;
import javax.naming.InvalidNameException;
import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.User;
import view.models.Model_MenuItem;
import utility.Session;
import view.AddUserGUIPage1;
import view.Login;
import view.ProductsGUI;
import view.ViewCustomerGUI;
import utility.AppWindow;
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
    private AppWindow dashWindow;
//    private DashboardMenu menu;
    private ListMenu menuA;
    private ListMenu menuB;
    private LinkedList<Model_MenuItem> menuList = new LinkedList<Model_MenuItem>();
    
//    public DashboardControl(){}
//    public DashboardControl( AppContext context, DashboardMenu menu ){
    public DashboardControl( AppContext context, AppWindow dashWindow ){
        this.context = context;
        this.dashWindow = dashWindow;
        this.menuA = dashWindow.getDashboardList().getMenuListA();
        this.menuB = dashWindow.getDashboardList().getMenuListB();
}
    
    public void initialize() { 
        menuA.addListSelectionListener( new MenuSelect() ); //menu listener
        menuB.addListSelectionListener( new MenuSelect() ); //menu listener
        buildMenu(); 
    }
        
    private void buildMenu(){
        User currentUser = context.getCurrentSession().getCurrentUser();
                addToMenuList(new Model_MenuItem("", "Welcome " + currentUser.getUsername(), Model_MenuItem.MenuType.HEADER, ""));
        switch ( currentUser.getRole() ) {
            case "Admin":
                
                addToMenuList(new Model_MenuItem("id-card", "My Profile", Model_MenuItem.MenuType.MENU, "ViewAdminProfile"));
                
                addToMenuList(new Model_MenuItem("", "", Model_MenuItem.MenuType.SEPARATOR, ""));
                
                addToMenuList(new Model_MenuItem("add_location_alt", "Search Products", Model_MenuItem.MenuType.MENU, "SearchProducts"));
                addToMenuList(new Model_MenuItem("add_location_alt", "Search Packages", Model_MenuItem.MenuType.MENU, "SearchPackages"));
                addToMenuList(new Model_MenuItem("add_location_alt", "Search Destinations", Model_MenuItem.MenuType.MENU, "SearchDestinations"));
                
                addToMenuList(new Model_MenuItem("", "", Model_MenuItem.MenuType.SEPARATOR, ""));
                
                addToMenuList(new Model_MenuItem("user-search-line", "Search Users", Model_MenuItem.MenuType.MENU, "SearchForUser"));
                addToMenuList(new Model_MenuItem("user-search-line", "Search Bookings", Model_MenuItem.MenuType.MENU, "SearchForBooking"));
                try { addAdminTargetItems(); }
                catch ( Exception e ) { System.out.println("exception " + e ); }
                
                addToMenuList(new Model_MenuItem("", "", Model_MenuItem.MenuType.SEPARATOR, ""));
                
                buildSharedMenu();
                break;
            case "Travel Agent":
        //        Agent Name Header
                addToMenuList(new Model_MenuItem("id-card", "My Profile", Model_MenuItem.MenuType.MENU, "ViewEmployeeProfile"));
                addToMenuList(new Model_MenuItem("user-search-line", "Search Customers", Model_MenuItem.MenuType.MENU, "SearchForCustomer"));
        //        Active Customer Name Header
                addToMenuList(new Model_MenuItem("id-card", "Customer Profile", Model_MenuItem.MenuType.MENU, "ViewCustomerProfile"));
//                addToMenuList(new Model_MenuItem("id-card", "Customer Profile", Model_MenuItem.MenuType.MENU, "ViewCustomerProfile"));
                buildSharedMenu();
                break;
//            case "Tour Guide":                
            case "Customer":
                addToMenuList(new Model_MenuItem("id-card", "My Profile", Model_MenuItem.MenuType.MENU, "ViewCustomerProfile"));
                buildSharedMenu();
                break;
            }
            System.out.println("menulist" + menuList.getFirst().getName() );
            System.out.println("menulist" + menuList.getFirst().getName() );
            
            splitMenuLists();
            
        //refresh menu
    }
    
    private void addToMenuList( Model_MenuItem item ){ menuList.addLast( item ); }

    private void addToMenuList( String icon, String title, Model_MenuItem.MenuType type, String action ){
        menuList.addLast( new Model_MenuItem(icon, title, type, action) );
    }
    
    private void splitMenuLists() {
        int listLength = menuList.size();
        int midpoint = ( listLength + 1) / 2 ;
        for ( int i = 0 ; i < midpoint ; i++ ) {
            menuA.addItem( menuList.removeFirst() );
        }
        for ( int i = midpoint ; i < listLength ; i++) {
            if ( menuList.size() == 0 ) { break; }
            menuB.addItem( menuList.removeFirst() );
        }
        menuA.repaint();
        menuB.repaint();
    }
    
    private void buildSharedMenu(){
        addToMenuList(new Model_MenuItem("world-search", "Packages", Model_MenuItem.MenuType.MENU, "SearchPackages"));
        addToMenuList(new Model_MenuItem("calendar_plus", "Latest", Model_MenuItem.MenuType.MENU, "LastBooking"));
//        addToMenuList(new Model_MenuItem("plans", "Plans", Model_MenuItem.MenuType.MENU, "ViewFutureBookingsOrItinerary"));
        addToMenuList(new Model_MenuItem("calendar_clock", "History", Model_MenuItem.MenuType.MENU, "ViewBookings"));
        addToMenuList(new Model_MenuItem("logout-box", "Logout", Model_MenuItem.MenuType.MENU, "Logout"));
    }
    
    class MenuSelect implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting()) { return; }                
            
            Model_MenuItem itemA = menuA.getSelectedValue();
            Model_MenuItem itemB = menuB.getSelectedValue();
            Model_MenuItem item;
            
            if (itemA == null && itemB == null) { return; }
            else if ( itemA != null ) { item = itemA; }
            else { item = itemB; }

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
            menuA.clearSelection(); // clears selection after completing - makes act like a button instead of a selection list
            menuB.clearSelection(); // clears selection after completing - makes act like a button instead of a selection list
        }
    }
    
    private void addAdminTargetItems() throws AccessDeniedException, DuplicateTargetException{
        User loggedIn = context.getCurrentSession().getCurrentUser();
        User customer = context.getCurrentSession().getCurrentCustomer();
        User employee = context.getCurrentSession().getCurrentEmployee();
        
        if ( !loggedIn.getRole().equals("Admin") ) { throw new AccessDeniedException("User cannot access Admin functions"); }
        if ( customer != null && employee != null ) { 
            context.getCurrentSession().clearTargets();
            throw new DuplicateTargetException("Cannot have two active targets. Clearing both. Retry");
        } 
        else if ( employee != null ) {
            addToMenuList( "", "Active Employee: " + employee.getUsername(), Model_MenuItem.MenuType.HEADER, "" );
            addToMenuList(new Model_MenuItem("id-card", "Employee Profile", Model_MenuItem.MenuType.MENU, "ViewEmployeeProfile"));
        } 
        else { addAgentTargetItems( customer ); }

    }
    
    private void addAgentTargetItems( User customer ) {
        if ( customer != null ) { 
            //use customer option
            addToMenuList( "", "Active Customer: " + customer.getUsername(), Model_MenuItem.MenuType.HEADER, "" );
            addToMenuList(new Model_MenuItem("id-card", "Customer Profile", Model_MenuItem.MenuType.MENU, "ViewCustomerProfile"));
        } else {
            // NEW user when cleared
            addToMenuList( "", "No Active Customer", Model_MenuItem.MenuType.HEADER, "" );
            addToMenuList(new Model_MenuItem("id-card", "New User", Model_MenuItem.MenuType.MENU, "New User"));
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
        context.getCurrentSession().clearSession();
        
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