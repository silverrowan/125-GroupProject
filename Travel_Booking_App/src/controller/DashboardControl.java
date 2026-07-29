package controller;

import java.awt.Font;
import utility.AppContext;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import view.FilterUsersGUI;
import view.Login;
import view.ProductsGUI;
import view.ViewAllTripsGUI;
import view.profile.EditCustomerGUI;
import view.profile.EditEmployeeGUI;

/**
 *
 * @author Mariah Malczewska
 */
public class DashboardControl extends GenericControl{
    private AppWindowAdmin dashAdmin;
    private AppWindowAgent dashAgent;
    private AppWindowCust dashCust;

    private JButton focusBtn;
    private JButton findFocusBtn;
    private JButton clearUserBtn;
    private JButton focusBookBtn;
    
    private JLabel focusLabel;
    private JLabel userLabel;
    private JLabel searchLabel;
    
    private Font headerFont;
    
    private User user;


    public DashboardControl( AppContext context, GenericView dash, int never ){
        super( context, dash );
        this.user = context.getCurrentUser();
    }
    
//    logged in user = Customer
    public DashboardControl( AppContext context, AppWindowCust dashCust ){ 
        this( context, dashCust, 99 );

        this.userLabel = dashCust.getPnlDashCust().getPnlDashCust().getLblUser();
        this.focusLabel = dashCust.getPnlDashCust().getPnlDashCust().getLblUser();
        this.focusBtn = dashCust.getPnlDashCust().getPnlDashCust().getBtnCustProfile();
        this.findFocusBtn = dashCust.getPnlDashCust().getPnlDashCust().getBtnCustSearch();
        
        this.headerFont = focusLabel.getFont().deriveFont(Font.BOLD, 24);
        
        //listeners
        this.dashCust = (AppWindowCust) getView();        
        this.dashCust.addListenerToBtnCustProfile( new Profile( true ) );
        this.dashCust.addListenerToBtnSearchCustFocus( new SearchUser( true ) );
        this.dashCust.addListenerToBtnSearchDest( new SearchDest() );
        this.dashCust.addListenerToBtnAllBooking( new AllBooking() );
        this.dashCust.addListenerToLogoutCust( new Logout() );
        
        //adjust labels and buttons
        refreshDash();
    }
    
    //Logged in user == agent
    public DashboardControl( AppContext context, AppWindowAgent dashAgent ){
        this( context, dashAgent, 99 );
        this.dashAgent = (AppWindowAgent) getView();        

        //get labels and buttons that need adjusting
        this.userLabel = dashAgent.getPnlDashAgent().getDashPanelAgent().getLblUser();
        this.focusLabel = dashAgent.getPnlDashAgent().getDashPanelAgent().getPnlDashCust().getLblUser();
        this.focusBtn = dashAgent.getPnlDashAgent().getDashPanelAgent().getPnlDashCust().getBtnCustProfile();
        this.findFocusBtn = dashAgent.getPnlDashAgent().getDashPanelAgent().getPnlDashCust().getBtnCustSearch();
        this.clearUserBtn = dashAgent.getPnlDashAgent().getDashPanelAgent().getBtnClearCust();
        this.focusBookBtn = dashAgent.getPnlDashAgent().getDashPanelAgent().getPnlDashCust().getBtnViewBooking();

        this.headerFont = focusLabel.getFont().deriveFont(Font.BOLD, 24);

        //--cust section listeners--
        this.dashAgent.addListenerToBtnCustProfile( new Profile( false ) );
        this.dashAgent.addListenerToBtnSearchCustFocus( new SearchUser( true ) );
        this.dashAgent.addListenerToBtnSearchDest( new SearchDest() );
        this.dashAgent.addListenerToBtnAllBooking( new AllBooking() );
        //--agent section listeners--
        this.dashAgent.addListenerToBtnAgentProfile( new Profile( true ) );        
        this.dashAgent.addListenerToBtnSearchCust( new SearchUser( true ) );        
        this.dashAgent.addListenerToBtnLogoutAgent( new Logout() );        
        this.dashAgent.addListenerToBtnClearCust( new ClearCust( ) );        

        
        //adjust labels and buttons
        userLabel.setText( "Welcome " + user.getUsername() +"!" );
        refreshDash( );       
    }
   
//    Loggen in user == admin
    public DashboardControl( AppContext contextOuter, AppWindowAdmin dashAdminOuter ){
        this( contextOuter, dashAdminOuter, 99 );
        this.dashAdmin = (AppWindowAdmin) getView();
        
        //Get labels and buttons to adjust
        this.searchLabel = dashAdmin.getPnlDashAdmin().getPnlDashAdmin().getLblUser();
        this.userLabel = dashAdmin.getPnlDashAdmin().getPnlDashAgent().getLblUser();
        this.focusLabel = dashAdmin.getPnlDashAdmin().getPnlDashAgent().getPnlDashCust().getLblUser();
        this.focusBtn = dashAdmin.getPnlDashAdmin().getPnlDashAgent().getPnlDashCust().getBtnCustProfile();
        this.findFocusBtn = dashAdmin.getPnlDashAdmin().getPnlDashAgent().getPnlDashCust().getBtnCustSearch();
        this.clearUserBtn = dashAdmin.getPnlDashAdmin().getPnlDashAgent().getBtnClearCust();
        this.focusBookBtn = dashAdmin.getPnlDashAdmin().getPnlDashAgent().getPnlDashCust().getBtnViewBooking();

        this.headerFont = focusLabel.getFont().deriveFont(Font.BOLD, 24);
        
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

        //adjust labels and buttons
        changeLabelToHeader( userLabel, headerFont, "Welcome " + user.getUsername() +"!" );
        changeLabelToHeader( searchLabel, headerFont, "Search & Edit" );
        refreshDash( );

    }
//--------------------------Listener Helper------------------------
//-----------------------------------------------------------------------------
    public AppContext getContext(){ return super.getAppContext(); }
    
//--------------------------Listener Classes & Function------------------------
//-----------------------------------------------------------------------------
        
        class Profile implements ActionListener {
            Boolean isSelf;
            
            public Profile( Boolean isSelf ){ this.isSelf = isSelf; }
            
            @Override
            public void actionPerformed(ActionEvent e) {
//                User user = getAppContext().getCurrentUser();
                User focusUser = getContext().getCurrentSession().getFocusUser();
                
                if ( isSelf ) { makeViewProfile( getAppContext(), user ); }
                else { makeViewProfile( getAppContext(), focusUser ); }
            }
        }
        
        //Cust section
        class SearchDest implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeViewDestSearch( getContext() );
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
            
            public SearchUser( Boolean customerOnly ){ this.onlyCustomers = customerOnly; }

            @Override
            public void actionPerformed(ActionEvent e) {
                makeFindUserView( getAppContext(), onlyCustomers );
            }
        }      

        class ClearCust implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {

                getAppContext().getCurrentSession().clearFoci();
                refreshDash( );
            }
        }

        //Admin Section
        class SearchTrip implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                //open searchTrip view
                //open controller
                makeViewTripSearch( getContext() );
            }
        }

        class SearchBooking implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        }
    
        
    public void refreshDash() {
        //focus objects
        changeSessionFocusObjects();
//        everything else that might need refreshing
    }
    
    private void changeSessionFocusObjects( ) {
        User loggedIn = getAppContext().getCurrentSession().getCurrentUser();
        user = loggedIn;
        
        switch (loggedIn.getRole()) {
            case "Customer":
                changeCustFocusObjects( );
                break;
            case "Travel Agent":
                changeAgentFocusObjects( );
                break;
            case "Admin":
                changeAdminFocusObjects( );
                break;        
            default:
                JOptionPane.showMessageDialog( null , "Invalid user role, contact the Administrator to fix");
                logoutUser();
                break;
        }
    }
    
    private void changeCustFocusObjects( ) {
            changeLabelToHeader( focusLabel, headerFont, "Welcome " + user.getUsername() );

            focusBtn.setVisible(true);
            focusBtn.setText( "My Profile" );
            findFocusBtn.setVisible(false);                  
    }
    
    private void changeAgentFocusObjects( ) {
        ensureValidFocus();
        AppContext context = getAppContext();
        User focus = context.getCurrentFocusUser();
          
        focusBookBtn.setText( "Customer Bookings" );
        if ( focus == null ) {
            changeLabelToHeader( focusLabel, headerFont, "No Customer set" );

            focusBtn.setVisible(false);
            findFocusBtn.setVisible(true);
            clearUserBtn.setEnabled( false );
            focusBookBtn.setEnabled( false );
        } else if ( !focus.getRole().equals("Customer") ){ 
            JOptionPane.showMessageDialog( null, "Selected user is not a customer, Can only act for Customers" );
            context.getCurrentSession().clearFoci();
        } else {
            changeLabelToHeader( focusLabel, headerFont, "Customer: " + focus.getUsername() );

            focusBtn.setVisible(true);
            focusBtn.setText( focus.getUsername() + "'s Profile" );
            findFocusBtn.setVisible(false);
            clearUserBtn.setEnabled( true );
            focusBookBtn.setEnabled( true );            
        }
    }
    
    private void changeAdminFocusObjects( ) {
        ensureValidFocus();
        User focus = getAppContext().getCurrentFocusUser();
        
        if ( focus == null ) {
            String focusLabelText = "No User set";
            changeLabelToHeader( focusLabel, headerFont, focusLabelText );

            focusBtn.setVisible(false);
            findFocusBtn.setText("Search for Users");
            findFocusBtn.setVisible(true);
            clearUserBtn.setEnabled( false );
            focusBookBtn.setText( "Focus User Bookings" );
            focusBookBtn.setEnabled( false );
        } else {
            String focusLabelText = focus.getRole() + ": " + focus.getUsername();
            changeLabelToHeader( focusLabel, headerFont, focusLabelText );

            focusBtn.setVisible(true);
            focusBtn.setText( focus.getUsername() + "'s Profile" );
            findFocusBtn.setVisible(false);
            focusBookBtn.setText( focus.getUsername() + "'s Bookings" );
            changeBtnText( clearUserBtn, "Clear Focus User" );
            clearUserBtn.setEnabled( true );
            focusBookBtn.setEnabled( true );
        }      
    }
    
    private void ensureValidFocus(){ //this should be moved to the session
        User customer = getAppContext().getCurrentSession().getCurrentCustomer();
        User employee = getAppContext().getCurrentSession().getCurrentEmployee();
        
        if ( !user.getRole().equals("Admin") && employee != null ) {
            getAppContext().getCurrentSession().clearCurrentEmployee();
        }
        if ( user.getRole().equals("Customer") && customer.getUserID() != user.getUserID() ) {
            getAppContext().getCurrentSession().setCurrentCustomer( user );
        }
        if ( customer != null && employee != null ) { 
            getAppContext().getCurrentSession().clearFoci();
            JOptionPane.showMessageDialog(null, "Cannot have two active focuss. Clearing both. Retry");
        }
    }
    
    private void changeBtnText( JButton btn, String btnLabel ){
        btn.setText( btnLabel );
    }
    
    private void changeLabelToHeader( JLabel label, Font font, String text){
        label.setText( text );
        label.setFont( font );
        label.setHorizontalAlignment( JLabel.CENTER );        
    }
    
    //------------------------------USER actions--------------------------------
    public void logoutUser() {
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
        FilterUsersGUI view = new FilterUsersGUI( ); // views *shouldnt* need context, controller should tell it everything it needs
        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        ProfileControl userControl = new ProfileControl( context, view ); 
        view.setVisible(true); //make it visible
    }
        
    private void makeViewProfile( AppContext context, User viewUser ) { // OR isEmployeeProfile
        if ( viewUser == null ) { 
            JOptionPane.showMessageDialog( null, "There is no user in focus to display" ); 
        }        
        User currUser = context.getCurrentUser();
        String currURole = currUser.getRole();
        
        String viewUserRole = viewUser.getRole();
        
        if ( viewUserRole.equals("Customer")) { 
            makeEditCustomerView( viewUser ); 
        } 
        else { makeEditEmployeeView( viewUser ); }
    }

    private void makeEditCustomerView( User viewUser ) {
        EditCustomerGUI viewEdit = new EditCustomerGUI();
        viewEdit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ProfileControl pc = new ProfileControl(getAppContext(), this, viewUser , viewEdit);
        viewEdit.setVisible(true);
    }

    private void makeEditEmployeeView( User viewUser ) {
        EditEmployeeGUI viewEdit = new EditEmployeeGUI();
        viewEdit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ProfileControl pc = new ProfileControl(getAppContext(), this, viewUser, viewEdit);
        viewEdit.setVisible(true);
    }

    //--------------------------BOOKING actions---------------------------------
    private void makeViewCustomerBookings( AppContext context ) {
        System.out.println("view bookings");
        // open/create multi-booking view for current customer
    }

//    private void makeViewSingleBooking( AppContext context, Crud crud ) {
//        AddBookingGUI bookView = new AddBookingGUI( ); // views *shouldnt* need context, controller should tell it everything it needs
//        bookView.setCrud( crud );
//        bookView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
//        BookingsController bookControl = new BookingsController( context, bookView ); 
//        bookView.setVisible(true); //make it visible
//        
    // view existing booking matching booking ID
    //            bookView.setCrud( GenericView.Crud.REQUEST );
    //            pass booking ID to dao to get matching booking object
//    }

    // ------------------Other Make & Set up Views------------------------------
    private void makeViewDestSearch( AppContext context ) {
        System.out.println("search products");
        ProductsGUI view = new ProductsGUI(); //new view
        view.setDefaultCloseOperation(GenericView.DISPOSE_ON_CLOSE); 
        DestinationsController destsControl = new DestinationsController(context, (GenericView) view ); 
        view.setVisible(true); //make it visible
    }
    
    private void makeViewTripSearch( AppContext context ){
        ViewAllTripsGUI view = new ViewAllTripsGUI(); //new view
        view.setDefaultCloseOperation(GenericView.DISPOSE_ON_CLOSE); 
        TripsController tripControl = new TripsController( context, view ); 
        view.setVisible(true); //make it visible
    }
}
