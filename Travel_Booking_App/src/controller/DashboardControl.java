package controller;

import java.awt.Button;
import utility.AppContext;
import utility.DuplicateTargetException;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.AccessDeniedException;
import java.util.LinkedList;
import model.User;
import utility.AppWindowAdmin;
import utility.AppWindowAgent;
import utility.GenericView;
import utility.AppWindowCust;
import view.models.Card;
import view.Login;
import view.ProductsGUI;
import view.ViewCustomerGUI;
import view.components.GradientPanel;

/**
 *
 * @author Mariah Malczewska
 */
public class DashboardControl extends GenericControl{
//    private menuDAO menuDao;
//    private ListMenu listMenu;
    private AppContext context;

    private AppWindowAdmin dashAdmin;
    private AppWindowAgent dashAgent;
    private AppWindowCust dashCust;
    private GenericView dash;

    private GradientPanel dashDisplay;
    private GradientPanel dashList;

    private Button btnAdminProfile;
    private Button btnSearchCust;
    private Button btnSearchDestAdmin;
    private Button btnSearchProduct;
    private Button btnSearchPackage;
    private Button btnSearchTrip;
    private Button btnSearchBooking;

    private Button btnAgentProfile;
    private Button btnClearUser;

    private Button btnCustProfile;
    private Button btnSearchDest;
    private Button btnLatestBooking;
    private Button btnAllBooking;

    private Button btnLogout;


//    private ListMenu menuB;
//    private LinkedList<Card> menuList = new LinkedList<Card>();
    
//    public DashboardControl(){}
//    public DashboardControl( AppContext context, DashboardMenu menu ){
    public DashboardControl( AppContext context, GenericView dash, int never ){
        super( context, dash );
    }

    public DashboardControl( AppContext context, AppWindowCust dashCust ){
        this( context, dashCust, 99 );
        this.dashCust = (AppWindowCust) getView();        
        this.dashCust.addListenerToBtnCustProfile( new CustProfile() );
        this.dashCust.addListenerToBtnSearchDest( new SearchDest() );
        this.dashCust.addListenerToBtnLatestBooking( new LatestBooking() );
        this.dashCust.addListenerToBtnAllBooking( new AllBooking() );
        this.dashCust.addListenerToLogoutCust( new Logout() );
    }
    
    public DashboardControl( AppContext context, AppWindowAgent dashAgent ){
        this( context, dashAgent, 99 );
        this.dashAgent = (AppWindowAgent) getView();        
        //--cust section--
        this.dashAgent.addListenerToBtnCustProfile( new CustProfile() );
        this.dashAgent.addListenerToBtnSearchDest( new SearchDest() );
        this.dashAgent.addListenerToBtnLatestBooking( new LatestBooking() );
        this.dashAgent.addListenerToBtnAllBooking( new AllBooking() );
        //--agent section--
        this.dashAgent.addListenerToBtnAgentProfile( new AgentProfile() );        
        this.dashAgent.addListenerToBtnSearchCust( new SearchCust() );        
        this.dashAgent.addListenerToBtnLogoutAgent( new Logout() );        
        this.dashAgent.addListenerToBtnClearCust( new ClearCust() );        
    }
    
    public DashboardControl( AppContext contextOuter, AppWindowAdmin dashAdminOuter ){
        this( contextOuter, dashAdminOuter, 99 );
        this.dashAdmin = (AppWindowAdmin) getView();
        //--cust section--
        this.dashAdmin.addListenerToBtnCustProfile( new CustProfile() );
        this.dashAdmin.addListenerToBtnSearchDest( new SearchDest() );
        this.dashAdmin.addListenerToBtnLatestBooking( new LatestBooking() );
        this.dashAdmin.addListenerToBtnAllBooking( new AllBooking() );
        //--agent section--
        this.dashAdmin.addListenerToBtnAdminProfile( new AdminProfile() );        
        this.dashAdmin.addListenerToBtnSearchCust( new SearchCust() );        
        this.dashAdmin.addListenerToBtnLogoutAgent( new Logout() );        
        this.dashAdmin.addListenerToBtnClearCust( new ClearCust() );
        //--admin section--
        this.dashAdmin.addListenerToBtnSearchProduct( new SearchProduct() );    
        this.dashAdmin.addListenerToBtnSearchPackage( new SearchPackage() );    
        this.dashAdmin.addListenerToBtnSearchDestAdmin( new SearchDestAdmin() );    
        this.dashAdmin.addListenerToBtnSearchTrips( new SearchTrip() );    
        this.dashAdmin.addListenerToBtnSearchBook( new SearchBooking() );    
    }
    
        class CustProfile implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        }
//----------------------------Listener Implementation--------------------------
//-----------------------------------------------------------------------------
        //Cust section
        class SearchDest implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        }

        class LatestBooking implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        }

        class AllBooking implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        }

        class Logout implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        }

        //Agent section
        class AgentProfile implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        }

        class AdminProfile implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        }

        class SearchCust implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        }

        class ClearCust implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        }

        //Admin Section
        class SearchProduct implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        }

        class SearchPackage implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        }

        class SearchDestAdmin implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        }

        class SearchTrip implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        }

        class SearchBooking implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        }

    public void initialize() { 
//        menuA.addListSelectionListener( new MenuSelect() ); //menu listener
//        menuB.addListSelectionListener( new MenuSelect() ); //menu listener
//        buildMenu(); 
    }
        
//    public void startView( context, view ) {
//        GenericView view = new GenericView( context ); //make target window/dashboard: This is Menu AND beside contents.
//        GenericControl dash = new GenericControl( context, view ); 
////        dash.initialize();
//        view.setVisible(true);        
//    }


//            case "Admin":
//                
//                addToMenuList(new ImgCard("id-card", "My Profile", Card.CardType.MENU, "ViewAdminProfile"));
//                addToMenuList(new ImgCard("add_location_alt", "Search Products", Card.CardType.MENU, "SearchProducts"));
//                addToMenuList(new ImgCard("add_location_alt", "Search Packages", Card.CardType.MENU, "SearchPackages"));
//                addToMenuList(new ImgCard("add_location_alt", "Search Destinations", Card.CardType.MENU, "SearchDestinations"));
//                             
//                addToMenuList(new ImgCard("user-search-line", "Search Users", Card.CardType.MENU, "SearchForUser"));
//                addToMenuList(new ImgCard("user-search-line", "Search Bookings", Card.CardType.MENU, "SearchForBooking"));
//                try { addAdminTargetItems(); }
//                buildSharedMenu();
//                break;
    
//            case "Travel Agent":

//                addToMenuList(new ImgCard("id-card", "My Profile", Card.CardType.MENU, "ViewEmployeeProfile"));
//                addToMenuList(new ImgCard("user-search-line", "Search Customers", Card.CardType.MENU, "SearchForCustomer"));
//                addToMenuList(new ImgCard("id-card", "Customer Profile", Card.CardType.MENU, "ViewCustomerProfile"));
////                addToMenuList(new Card("id-card", "Customer Profile", Card.CardType.MENU, "ViewCustomerProfile"));
//                buildSharedMenu();
//                break;
    

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
//            addToMenuList(new ImgCard("id-card", "Employee Profile", Card.CardType.MENU, "ViewEmployeeProfile"));
        } 
        else { addAgentTargetItems( customer ); }

    }
    
    private void addAgentTargetItems( User customer ) {
        if ( customer != null ) { 
            //use customer option
//            addToMenuList( "", "Active Customer: " + customer.getUsername(), Card.CardType.HEADER, "" );
//            addToMenuList(new ImgCard("id-card", "Customer Profile", Card.CardType.MENU, "ViewCustomerProfile"));
        } else {
            // NEW user when cleared
//            addToMenuList( "", "No Active Customer", Card.CardType.HEADER, "" );
//            addToMenuList(new ImgCard("id-card", "New User", Card.CardType.MENU, "New User"));
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

    public void logoutUser( AppContext context ) {
        System.out.println("logout");
        context.getCurrentSession().clearSession();
        
        for ( Window window : Window.getWindows() ) {
            window.dispose();
        }
        Login loginView = new Login();
        GenericControl loginControl = new GenericControl(context, loginView);
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
        view.setDefaultCloseOperation(GenericView.DISPOSE_ON_CLOSE); 

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
        view.setDefaultCloseOperation(GenericView.DISPOSE_ON_CLOSE); 
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