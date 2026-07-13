package controller;

import javax.naming.InvalidNameException;
import model.gui.Model_MenuItem;
import view.components.ListMenu;

/**
 *
 * @author Mariah Malczewska
 */
public class MenuControl {
//    private menuDAO menuDao;
    private ListMenu menuGui;
    
    private Model_MenuItem[] customerMenuArray;
    private Model_MenuItem[] adminMenuArray;
    private Model_MenuItem[] agentMenuArray;
    private Model_MenuItem[] guideMenuArray;
    
    
    public static ListMenu generateMainMenu(){
        
        switch (role) {
            case "Admin":
            case "Agent":
                listMenu.addItem(new Model_MenuItem("search-line", "Search Customers", Model_MenuItem.MenuType.MENU, "ViewCustomerGUI"));
            case "Customer":
                listMenu.addItem(new Model_MenuItem("id-card", "Profile", Model_MenuItem.MenuType.MENU, "ViewCustomerGUI"));
                listMenu.addItem(new Model_MenuItem("world-search", "Destinations", Model_MenuItem.MenuType.MENU, "ViewDestinationsGUI"));
                listMenu.addItem(new Model_MenuItem("calendar_plus", "Latest", Model_MenuItem.MenuType.MENU, "ViewTripFrame"));
                listMenu.addItem(new Model_MenuItem("plans", "Plans", Model_MenuItem.MenuType.MENU, "ViewTripFrame"));
                listMenu.addItem(new Model_MenuItem("calendar_clock", "History", Model_MenuItem.MenuType.MENU, "ViewTripFrame"));
                listMenu.addItem(new Model_MenuItem("logout-box", "Logout", Model_MenuItem.MenuType.MENU, "ViewLogin"));
                break;
            case "Guide":
                break;
            default: 
                throw new InvalidNameException( "User role does not match available roles");
        }
        //if active user = customer
    }  
    
    
}
