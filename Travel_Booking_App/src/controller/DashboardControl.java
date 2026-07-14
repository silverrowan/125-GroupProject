package controller;

import javax.naming.InvalidNameException;
import model.User;
import model.gui.Model_MenuItem;
import view.components.AppWindow;
import view.components.ListMenu;
import view.components.DashboardMenu;

/**
 *
 * @author Mariah Malczewska
 */
public class DashboardControl {
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
    
    public void initialize() { buildMenu(); }
        
    private void buildMenu(){
        User currentUser = context.getCurrentSession().getCurrentUser();
        switch ( currentUser.getRole() ) {
            case "Admin":
                menu.getMenuList().addItem(new Model_MenuItem("id-card", "Customer Profile", Model_MenuItem.MenuType.MENU, "ViewCustomerGUI"));
                buildSharedMenu();
                break;
            case "Travel Agent":
        //        Agent Name Header
                menu.getMenuList().addItem(new Model_MenuItem("id-card", "Profile", Model_MenuItem.MenuType.MENU, "ViewEmployee"));
                menu.getMenuList().addItem(new Model_MenuItem("user-search-line", "Search Customers", Model_MenuItem.MenuType.MENU, "SearchForCustomer"));
        //        Active Customer Name Header
                menu.getMenuList().addItem(new Model_MenuItem("id-card", "Customer Profile", Model_MenuItem.MenuType.MENU, "ViewCustomerGUI"));
                buildSharedMenu();
                break;
//            case "Tour Guide":                
            case "Customer":
                menu.getMenuList().addItem(new Model_MenuItem("id-card", "Profile", Model_MenuItem.MenuType.MENU, "ViewCustomerGUI"));
                buildSharedMenu();
                break;
        
        //refresh menu
    }
    }
    
    private void buildSharedMenu(){
        menu.getMenuList().addItem(new Model_MenuItem("world-search", "Destinations", Model_MenuItem.MenuType.MENU, "SearchDestinations/Products"));
        menu.getMenuList().addItem(new Model_MenuItem("calendar_plus", "Latest", Model_MenuItem.MenuType.MENU, "ViewMostRecentBooking"));
        menu.getMenuList().addItem(new Model_MenuItem("plans", "Plans", Model_MenuItem.MenuType.MENU, "ViewFutureBookingsOrItinerary"));
        menu.getMenuList().addItem(new Model_MenuItem("calendar_clock", "History", Model_MenuItem.MenuType.MENU, "ViewBookings"));
        menu.getMenuList().addItem(new Model_MenuItem("logout-box", "Logout", Model_MenuItem.MenuType.MENU, "LoginFunction"));
        
        menu.repaint();
    }
    
    
}