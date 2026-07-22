package controller;

import java.awt.Button;
import java.awt.Font;
import utility.AppContext;
import utility.DuplicateTargetException;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.AccessDeniedException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import model.User;
import view.dashboard.AppWindowAdmin;
import view.dashboard.AppWindowAgent;
import utility.GenericView;
import view.dashboard.AppWindowCust;
import utility.GenericView.Crud;
import view.AddBookingGUI;
import view.FilterUsersFrameGUIExperiment;
import view.Login;
import view.ProductsGUI;
import view.ViewAllTripsGUI;
import view.components.GradientPanel;
import view.models.ButtonBase;
import view.profile.EditCustomerGUI;
import view.profile.EditEmployeeGUI;

/**
 *
 * @author Mariah Malczewska
 */
public class DashboardControl extends GenericControl{
//    private AppContext context;

    private AppWindowAdmin dashAdmin;
    private AppWindowAgent dashAgent;
    private AppWindowCust dashCust;
    private GenericView dash;

    private GradientPanel dashDisplay;
    private GradientPanel dashList;

//    private Button btnAdminProfile;
//    private Button btnSearchCust;
//    private Button btnSearchDestAdmin;
//    private Button btnSearchProduct;
//    private Button btnSearchPackage;
//    private Button btnSearchTrip;
//    private Button btnSearchBooking;
//
//    private Button btnAgentProfile;
//    private Button btnClearUser;
//
//    private Button btnCustProfile;
//    private Button btnSearchDest;
//    private Button btnLatestBooking;
//    private Button btnAllBooking;
//
//    private Button btnLogout;
//    
    private User user;
    private User cust;
    private User focus;

    public DashboardControl( AppContext context, GenericView dash, int never ){
        super( context, dash );
        this.user = context.getCurrentUser();
        this.cust = context.getCurrentCustomerUser();
        this.focus = context.getCurrentFocusUser();
        
    }
    
//    logged in user = Customer
    public DashboardControl( AppContext context, AppWindowCust dashCust ){
        this( context, dashCust, 99 );
        this.dashCust = (AppWindowCust) getView();        
        this.dashCust.addListenerToBtnCustProfile( new Profile( true ) );
        this.dashAgent.addListenerToBtnSearchCustFocus( new SearchUser( true ) );
        this.dashCust.addListenerToBtnSearchDest( new SearchDest() );
        this.dashCust.addListenerToBtnAllBooking( new AllBooking() );
        this.dashCust.addListenerToLogoutCust( new Logout() );
        
//        JLabel userLabel = dashAgent.getPnlDashAgent().getDashPanelAgent().getLblUser();
        JLabel focusLabel = dashAgent.getPnlDashAgent().getDashPanelAgent().getPnlDashCust().getLblUser();
        JButton focusBtn = dashAgent.getPnlDashAgent().getDashPanelAgent().getPnlDashCust().getBtnCustProfile();
        JButton findFocusBtn = dashAdmin.getPnlDashAdmin().getPnlDashAgent().getPnlDashCust().getBtnCustSearch();
        
        Font standardFont = focusLabel.getFont();
        Font headerFont = standardFont.deriveFont(Font.BOLD, 24);

        changeSessionFocusObjects( focusBtn, findFocusBtn, focusLabel, headerFont );
    }
    
    //Logged in user == agent
    public DashboardControl( AppContext context, AppWindowAgent dashAgent ){
        this( context, dashAgent, 99 );
        this.dashAgent = (AppWindowAgent) getView();        
        //--cust section listeners--
        this.dashAgent.addListenerToBtnCustProfile( new Profile( false ) );
        this.dashAgent.addListenerToBtnSearchCustFocus( new SearchUser( true ) );
        this.dashAgent.addListenerToBtnSearchDest( new SearchDest() );
        this.dashAgent.addListenerToBtnAllBooking( new AllBooking() );
        //--agent section listeners--
        this.dashAgent.addListenerToBtnAgentProfile( new Profile( true ) );        
        this.dashAgent.addListenerToBtnSearchCust( new SearchUser( true ) );        
        this.dashAgent.addListenerToBtnLogoutAgent( new Logout() );        
        this.dashAgent.addListenerToBtnClearCust( new ClearCust() );        

        JLabel userLabel = dashAgent.getPnlDashAgent().getDashPanelAgent().getLblUser();
        JLabel focusLabel = dashAgent.getPnlDashAgent().getDashPanelAgent().getPnlDashCust().getLblUser();
        JButton focusBtn = dashAgent.getPnlDashAgent().getDashPanelAgent().getPnlDashCust().getBtnCustProfile();
        JButton findFocusBtn = dashAdmin.getPnlDashAdmin().getPnlDashAgent().getPnlDashCust().getBtnCustSearch();
        
        //adjust them
        Font standardFont = focusLabel.getFont();
        Font headerFont = standardFont.deriveFont(Font.BOLD, 24);
        userLabel.setText( "Welcome " + user.getUsername() +"!" );
        changeSessionFocusObjects( focusBtn, findFocusBtn, focusLabel, headerFont );        
    }
    
//    Loggen in user == admin
    public DashboardControl( AppContext contextOuter, AppWindowAdmin dashAdminOuter ){
        this( contextOuter, dashAdminOuter, 99 );
        this.dashAdmin = (AppWindowAdmin) getView();
        //--cust section listeners--
        this.dashAdmin.addListenerToBtnCustProfile( new Profile( false ) );
        this.dashAdmin.addListenerToBtnSearchCustFocus( new SearchUser( false ));
        this.dashAdmin.addListenerToBtnSearchDest( new SearchDest() );
        this.dashAdmin.addListenerToBtnAllBooking( new AllBooking() );
        //--agent section listeners--
        this.dashAdmin.addListenerToBtnAdminProfile( new Profile( true ) );        
        this.dashAdmin.addListenerToBtnSearchCust( new SearchUser( true ));
        this.dashAdmin.addListenerToBtnLogoutAgent( new Logout() );        
        this.dashAdmin.addListenerToBtnClearCust( new ClearCust() );
        //--admin section listeners--
        this.dashAdmin.addListenerToBtnSearchDestAdmin( new SearchDest() );    
        this.dashAdmin.addListenerToBtnSearchTrips( new SearchTrip() );    
        this.dashAdmin.addListenerToBtnSearchBook( new SearchBooking() );
        //Get labels and buttons to adjust

        JLabel searchLabel = dashAdmin.getPnlDashAdmin().getPnlDashAdmin().getLblUser();
        JLabel userLabel = dashAdmin.getPnlDashAdmin().getPnlDashAgent().getLblUser();
        JLabel focusLabel = dashAdmin.getPnlDashAdmin().getPnlDashAgent().getPnlDashCust().getLblUser();
        JButton focusBtn = dashAdmin.getPnlDashAdmin().getPnlDashAgent().getPnlDashCust().getBtnCustProfile();
        JButton findFocusBtn = dashAdmin.getPnlDashAdmin().getPnlDashAgent().getPnlDashCust().getBtnCustSearch();
        //adjust them
        Font standardFont = searchLabel.getFont();
        Font headerFont = standardFont.deriveFont(Font.BOLD, 24);
        
        changeLabelToHeader( userLabel, headerFont, "Welcome " + user.getUsername() +"!" );
        changeLabelToHeader( searchLabel, headerFont, "Search & Edit" );
        changeSessionFocusObjects( focusBtn, findFocusBtn, focusLabel, headerFont );
    }
    
//--------------------------Listener Classes & Function------------------------
//-----------------------------------------------------------------------------
        
        class Profile implements ActionListener {
            Boolean isSelf;
            
            public Profile( Boolean isSelf ){ this.isSelf = isSelf; }
            
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = getAppContext().getCurrentUser();
                User focusUser = getAppContext().getCurrentSession().getFocusUser();
                
                if ( isSelf ) { makeViewProfile( getAppContext(), user ); }
                else { makeViewProfile( getAppContext(), focusUser ); }
            }
        }
        
        //Cust section
        class SearchDest implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeViewDestSearch(getAppContext());
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
                logoutUser();
            }
        }

        //Agent section
        class SearchUser implements ActionListener {
            Boolean onlyCustomers;
            
            public SearchUser( Boolean customerOnly ){ this.onlyCustomers = onlyCustomers; }

            @Override
            public void actionPerformed(ActionEvent e) {
                makeFindUserView( getAppContext(), onlyCustomers );
            }
        }      

        class ClearCust implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                getAppContext().getCurrentSession().clearCurrentCustomer();
                refresh();
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

    private void changeSessionFocusObjects( JButton focusBtn, JButton findFocusBtn, JLabel focusLabel, Font headerFont ) {
        User loggedIn = getAppContext().getCurrentSession().getCurrentUser();
        User customer = getAppContext().getCurrentSession().getCurrentCustomer();
        User employee = getAppContext().getCurrentSession().getCurrentEmployee();
        User focus = getAppContext().getCurrentFocusUser();
        
        if ( customer != null && employee != null ) { 
            getAppContext().getCurrentSession().clearFoci();
            JOptionPane.showMessageDialog(null, "Cannot have two active focuss. Clearing both. Retry");
        } 
        else if ( loggedIn.getRole().equals("Customer") ) { 
            String focusLabelText = "Welcome " + loggedIn.getUsername() + "!";
            changeLabelToHeader( focusLabel, headerFont, focusLabelText ); 
            
            focusBtn.setVisible(true);
            findFocusBtn.setVisible(false);              
        }
        else if ( loggedIn.getRole().equals("Agent") ) {
            if ( customer == null ) {
                String focusLabelText = "No Customer set";
                changeLabelToHeader( focusLabel, headerFont, focusLabelText );

                focusBtn.setVisible(false);
                findFocusBtn.setVisible(true);    
            } else {
                String focusLabelText = "Focus Customer: " + customer.getUsername();
                changeLabelToHeader( focusLabel, headerFont, focusLabelText );

                focusBtn.setVisible(true);
                findFocusBtn.setVisible(false);                  
            }
        } else if ( loggedIn.getRole().equals("Admin") ) {
            if ( focus == null ) {
                String focusLabelText = "No User set";
                changeLabelToHeader( focusLabel, headerFont, focusLabelText );

                focusBtn.setVisible(false);
                findFocusBtn.setText("Search for Users");
                findFocusBtn.setVisible(true);                 
            } else {
                String focusLabelText = "Focus " + focus.getRole() + " " + focus.getUsername();
                changeLabelToHeader( focusLabel, headerFont, focusLabelText );
                
                focusBtn.setVisible(true);
                findFocusBtn.setVisible(false);                  
            }
        } else {
            JOptionPane.showMessageDialog( null , "Invalid user role, contact the Administrator to fix");
            logoutUser();
        }        
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

    
    //------USER actions-------
    private void logoutUser() {
        System.out.println("logout");
        getAppContext().getCurrentSession().clearSession();
        
        for ( Window window : Window.getWindows() ) { //closes all open windows
            window.dispose();
        }
        Login loginView = new Login();
        LoginControl loginControl = new LoginControl(getAppContext(), loginView);
        loginView.setVisible(true);
    }
    
    private void makeFindUserView( AppContext context, Boolean onlyCustomers ) {
        FilterUsersFrameGUIExperiment view = new FilterUsersFrameGUIExperiment( ); // views *shouldnt* need context, controller should tell it everything it needs
        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        UserControl userControl = new UserControl( context, view ); 
        //Figure out how to filter
        //Apply customers only filter
        view.setVisible(true); //make it visible
    }
        
    private void makeViewProfile( AppContext context, User viewUser ) { // OR isEmployeeProfile
        if ( viewUser == null ) { 
            JOptionPane.showMessageDialog( null, "There is no user in focus to display" ); 
        }        
        User currUser = context.getCurrentUser();
        String currURole = currUser.getRole();
        
        String viewUserRole = viewUser.getRole();
        
        if ( currURole.equals("Customer") || viewUserRole.equals("Customer")) { 
            makeEditCustomerView(); 
        } 
        else { makeEditEmployeeView(); }
    }

    private void makeEditCustomerView() {
        EditCustomerGUI viewEdit = new EditCustomerGUI();
        viewEdit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ProfileControl pc = new ProfileControl(getAppContext(), viewEdit);
        viewEdit.setVisible(true);
    }

    private void makeEditEmployeeView() {
        EditEmployeeGUI viewEdit = new EditEmployeeGUI();
        viewEdit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ProfileControl pc = new ProfileControl(getAppContext(), viewEdit);
        viewEdit.setVisible(true);
    }

    //-------BOOKING actions-------
    private void makeViewCustomerBookings( AppContext context ) {
        System.out.println("view bookings");
        // open/create multi-booking view for current customer
    }

    private void makeViewSingleBooking( AppContext context, Crud crud ) {
        AddBookingGUI bookView = new AddBookingGUI( ); // views *shouldnt* need context, controller should tell it everything it needs
        bookView.setCrud( crud );
        bookView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        BookingsController bookControl = new BookingsController( context, bookView ); 
        bookView.setVisible(true); //make it visible
        
    // view existing booking matching booking ID
    //            bookView.setCrud( GenericView.Crud.REQUEST );
    //            pass booking ID to dao to get matching booking object
    }
    
    // Any other functions your controller uses, eg helpers, validation, buis logic & rules

    
    private void makeViewDestSearch( AppContext context ) {
        System.out.println("search products");
        System.out.println("NEEDS REAL CONTROLLER");
        ProductsGUI view = new ProductsGUI(); //new view
        view.setDefaultCloseOperation(GenericView.DISPOSE_ON_CLOSE); 
        ProductsControlPLACEHOLDER destsControl = new ProductsControlPLACEHOLDER( context, view ); 
        view.setVisible(true); //make it visible
    }
    
    private void makeViewTripSearch( AppContext context ){
        ViewAllTripsGUI view = new ViewAllTripsGUI(); //new view
        view.setDefaultCloseOperation(GenericView.DISPOSE_ON_CLOSE); 
        TripsController tripControl = new TripsController( context, view ); 
        view.setVisible(true); //make it visible
        
    }
    
    private void changeLabelToHeader( JLabel label, Font font, String text){
        label.setText( text );
        label.setFont( font );
        label.setHorizontalAlignment( JLabel.CENTER );        
    }
    

    private void refresh(){} //update dashboard view
}