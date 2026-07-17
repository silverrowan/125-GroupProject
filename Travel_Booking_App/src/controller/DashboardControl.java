package controller;

import utility.AppContext;
import utility.DuplicateTargetException;
import java.awt.Window;
import java.nio.file.AccessDeniedException;
import java.util.LinkedList;
import javax.swing.JFrame;
import model.User;
import view.models.Card;
import view.Login;
import view.ProductsGUI;
import view.ViewCustomerGUI;
import view.components.GradientPanel;
import view.components.ListMenu;
import view.models.ImgCard;

/**
 *
 * @author Mariah Malczewska
 */
public class DashboardControl<T> {
//    private menuDAO menuDao;
//    private ListMenu listMenu;
    private AppContext context;
    private JFrame dashWindow;
    private GradientPanel dashDisplay;
    private GradientPanel dashList;

    private ListMenu menuA;
    private ListMenu menuB;
    private LinkedList<Card> menuList = new LinkedList<Card>();
    
//    public DashboardControl(){}
//    public DashboardControl( AppContext context, DashboardMenu menu ){
    public DashboardControl( AppContext context, JFrame dashWindow ){
        this.context = context;
        this.dashWindow = dashWindow;
//        this.menuA = dashWindow.getDashboardList().getMenuListA();
//        this.menuB = dashWindow.getDashboardList().getMenuListB();
}
    
    public void initialize() { 
//        menuA.addListSelectionListener( new MenuSelect() ); //menu listener
//        menuB.addListSelectionListener( new MenuSelect() ); //menu listener
        buildMenu(); 
    }
        
    private void buildMenu(){
        User currentUser = context.getCurrentSession().getCurrentUser();
                addToMenuList(new Card("Welcome " + currentUser.getUsername(), Card.CardType.HEADER, ""));
        switch ( currentUser.getRole() ) {
            case "Admin":
                
                addToMenuList(new ImgCard("id-card", "My Profile", Card.CardType.MENU, "ViewAdminProfile"));
                
                addToMenuList(new Card( "", Card.CardType.SEPARATOR, ""));
                
                addToMenuList(new ImgCard("add_location_alt", "Search Products", Card.CardType.MENU, "SearchProducts"));
                addToMenuList(new ImgCard("add_location_alt", "Search Packages", Card.CardType.MENU, "SearchPackages"));
                addToMenuList(new ImgCard("add_location_alt", "Search Destinations", Card.CardType.MENU, "SearchDestinations"));
                
                addToMenuList(new Card( "", Card.CardType.SEPARATOR, ""));
                
                addToMenuList(new ImgCard("user-search-line", "Search Users", Card.CardType.MENU, "SearchForUser"));
                addToMenuList(new ImgCard("user-search-line", "Search Bookings", Card.CardType.MENU, "SearchForBooking"));
                try { addAdminTargetItems(); }
                catch ( Exception e ) { System.out.println("exception " + e ); }
                
                addToMenuList(new Card( "", Card.CardType.SEPARATOR, ""));
                
                buildSharedMenu();
                break;
            case "Travel Agent":
        //        Agent Name Header
                addToMenuList(new ImgCard("id-card", "My Profile", Card.CardType.MENU, "ViewEmployeeProfile"));
                addToMenuList(new ImgCard("user-search-line", "Search Customers", Card.CardType.MENU, "SearchForCustomer"));
        //        Active Customer Name Header
                addToMenuList(new ImgCard("id-card", "Customer Profile", Card.CardType.MENU, "ViewCustomerProfile"));
//                addToMenuList(new Card("id-card", "Customer Profile", Card.CardType.MENU, "ViewCustomerProfile"));
                buildSharedMenu();
                break;
//            case "Tour Guide":                
            case "Customer":
                addToMenuList(new ImgCard("id-card", "My Profile", Card.CardType.MENU, "ViewCustomerProfile"));
                buildSharedMenu();
                break;
            }
            System.out.println("menulist" + menuList.getFirst().getName() );
            System.out.println("menulist" + menuList.getFirst().getName() );
            
//            splitMenuLists();
            
        //refresh menu
    }
    
    private void addToMenuList( Card item ){ menuList.addLast( item ); }

    private void addToMenuList( String icon, String title, Card.CardType type, String action ){
        menuList.addLast( new ImgCard(icon, title, type, action) );
    }
    
//    private void splitMenuLists() {
//        int listLength = menuList.size();
//        int midpoint = ( listLength + 1) / 2 ;
//        for ( int i = 0 ; i < midpoint ; i++ ) {
//            menuA.addItem( menuList.removeFirst() );
//        }
//        for ( int i = midpoint ; i < listLength ; i++) {
//            if ( menuList.size() == 0 ) { break; }
//            menuB.addItem( menuList.removeFirst() );
//        }
//        menuA.repaint();
//        menuB.repaint();
//    }
    
    private void buildSharedMenu(){
        addToMenuList(new ImgCard("world-search", "Packages", Card.CardType.MENU, "SearchPackages"));
        addToMenuList(new ImgCard("calendar_plus", "Latest", Card.CardType.MENU, "LastBooking"));
//        addToMenuList(new Card("plans", "Plans", Card.CardType.MENU, "ViewFutureBookingsOrItinerary"));
        addToMenuList(new ImgCard("calendar_clock", "History", Card.CardType.MENU, "ViewBookings"));
        addToMenuList(new ImgCard("logout-box", "Logout", Card.CardType.MENU, "Logout"));
    }
    
//    class MenuSelect implements ListSelectionListener {

//        @Override
//        public void valueChanged(ListSelectionEvent e) {
//            if (e.getValueIsAdjusting()) { return; }                
//            
//            Card itemA = menuA.getSelectedValue();
//            Card itemB = menuB.getSelectedValue();
//            Card item;
//            
//            if (itemA == null && itemB == null) { return; }
//            else if ( itemA != null ) { item = itemA; }
//            else { item = itemB; }
//
//            switch ( item.getTag()) {
//                case "Logout":
//                    logoutUser( context );
//                    break;
//                case "ViewBookings":
//                    makeViewCustomerBookings( context );
//                    break;
//                case "LastBooking":
//                    makeViewCustomerLastBooking( context );
//                    break;
//                case "SearchPackages":
//                    makeViewPackageSearch( context );
//                    break;
//                case "ViewCustomerProfile":
//                    makeViewProfile( context, false ); 
//                    break;
//                case "ViewEmployeeProfile":
//                    makeViewProfile( context, true ); 
//                    break;
//                default:
//                    System.out.println("Selection " + item.getTag()+ " has no valid target");
//                    break;
//            }
//            menuA.clearSelection(); // clears selection after completing - makes act like a button instead of a selection list
//            menuB.clearSelection(); // clears selection after completing - makes act like a button instead of a selection list
//        }
//    }
    
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
//            addToMenuList( new JLabel( "Active Employee: " + employee.getUsername(), Card.CardType.HEADER, "" ));
            addToMenuList(new ImgCard("id-card", "Employee Profile", Card.CardType.MENU, "ViewEmployeeProfile"));
        } 
        else { addAgentTargetItems( customer ); }

    }
    
    private void addAgentTargetItems( User customer ) {
        if ( customer != null ) { 
            //use customer option
//            addToMenuList( "", "Active Customer: " + customer.getUsername(), Card.CardType.HEADER, "" );
            addToMenuList(new ImgCard("id-card", "Customer Profile", Card.CardType.MENU, "ViewCustomerProfile"));
        } else {
            // NEW user when cleared
//            addToMenuList( "", "No Active Customer", Card.CardType.HEADER, "" );
            addToMenuList(new ImgCard("id-card", "New User", Card.CardType.MENU, "New User"));
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