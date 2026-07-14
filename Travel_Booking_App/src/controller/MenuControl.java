package controller;

import javax.naming.InvalidNameException;
import model.User;
import model.gui.Model_MenuItem;
import view.components.ListMenu;
import view.components.MenuCustomer;

/**
 *
 * @author Mariah Malczewska
 */
public class MenuControl {
//    private menuDAO menuDao;
    private ListMenu menuGui;
    private AppContext context;
    private MenuCustomer menu;
    
    public MenuControl(){}
    public MenuControl( AppContext context, MenuCustomer menu ){
        this.context = context;
        this.menu = menu;
    }
    
//    public static ListMenu generateMainMenu(){}
        
//    private ListMenu buildMenu(){
    private void buildMenu(){
        User currentUser = context.getCurrentSession().getCurrentUser();
        switch ( currentUser.getRole() ) {
            case "Admin":
                menu.getListMenuCustomer().addItem(new Model_MenuItem("id-card", "Customer Profile", Model_MenuItem.MenuType.MENU, "ViewCustomerGUI"));
                buildSharedMenu();
                break;
            case "Travel Agent":
        //        Agent Name Header
                menu.getListMenuCustomer().addItem(new Model_MenuItem("id-card", "Profile", Model_MenuItem.MenuType.MENU, "ViewEmployee"));
                menu.getListMenuCustomer().addItem(new Model_MenuItem("user-search-line", "Search Customers", Model_MenuItem.MenuType.MENU, "SearchForCustomer"));
        //        Active Customer Name Header
                menu.getListMenuCustomer().addItem(new Model_MenuItem("id-card", "Customer Profile", Model_MenuItem.MenuType.MENU, "ViewCustomerGUI"));
                buildSharedMenu();
                break;
//            case "Tour Guide":                
            case "Customer":
                menu.getListMenuCustomer().addItem(new Model_MenuItem("id-card", "Profile", Model_MenuItem.MenuType.MENU, "ViewCustomerGUI"));
                buildSharedMenu();
                break;
        
        //refresh menu
    }
    }
    
    private void buildSharedMenu(){
        menu.getListMenuCustomer().addItem(new Model_MenuItem("world-search", "Destinations", Model_MenuItem.MenuType.MENU, "SearchDestinations/Products"));
        menu.getListMenuCustomer().addItem(new Model_MenuItem("calendar_plus", "Latest", Model_MenuItem.MenuType.MENU, "ViewMostRecentBooking"));
        menu.getListMenuCustomer().addItem(new Model_MenuItem("plans", "Plans", Model_MenuItem.MenuType.MENU, "ViewFutureBookingsOrItinerary"));
        menu.getListMenuCustomer().addItem(new Model_MenuItem("calendar_clock", "History", Model_MenuItem.MenuType.MENU, "ViewBookings"));
        menu.getListMenuCustomer().addItem(new Model_MenuItem("logout-box", "Logout", Model_MenuItem.MenuType.MENU, "LoginFunction"));
        menu.repaint();
    }
}