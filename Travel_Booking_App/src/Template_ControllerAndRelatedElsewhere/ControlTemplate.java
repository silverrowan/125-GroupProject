package Template_ControllerAndRelatedElsewhere;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.SingleSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import view.models.Model_MenuItem;
import view.ProductsGUI;
import utility.AppWindow;

/**
 *
 * @author Mariah Malczewska
 */
public class ControlTemplate {
    private AppContextPlaceholder context;
    //private filds for each related view, format:
    //private ViewClassName view;  eg:
    private AppWindow dashWindow;
    // next line is for a helper - this is for the subview; 
    // don't pass into the constructer, pull out of the relevant view (dashWindow) 
    // once its parent is passed in
    private Object panelView; 
    private FrameGUITemplate frameView; 
    
    // EACH view that this interacts with will need its own Constructor
    public ControlTemplate( AppContextPlaceholder context, AppWindow dashWindow ) { 
        this.context = context;
        this.dashWindow = dashWindow;
        this.panelView = dashWindow.getDashboardList(); // helper - gets the subview
    }
    
    //may want one initialize per view, helper to keep things in the view clean and easier to swap out
    public void initializeMenu() {
        demoBuild(); // any build functions you define in here      
        
    // ATTACH the listener you will create in its own subclass - this one is for listening in a list
        frameView.getListMenuCustomer().addListSelectionListener( new MenuListSelect() ); 
        //attach a button listener
//        this.loginView.addLoginBtnListener( new LoginControl.LogInUser() );      
    }
    
    private void demoBuild() {
        // a demo function to select or build contents. Code Body goes here
//        repaint(); - not sure this one is needed if theres nothing changed in the main Frame not in Panels
        frameView.repaint(); // repaint panels/items edited
    }
    
    // Listener! 
    // Implemented as a class. there is another way, but we dont know the structure it uses. 
    // Will need one for each thing to watch/action. Eg. if three 
    // buttons on the page, each doing something different, the each need 
    // thier own listener
    // THIS ONE IS A LIST LISTENER
    class MenuListSelect implements ListSelectionListener {
        // click the warning and choose "implement all abstract methods" 
            // - remove the unsuported throw & enter your code
            // you pretty much just need the one function per listener unless you implement helper functions
//                  throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        // there may be variables you want to deine here, for global use - honestly 
        // not quite sure how that is working the times I'm using them, they 
        // could probably go inside the function but im not sure

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if ( e.getValueIsAdjusting() ) { return; } // this stops it from doing stuff if its part of a multi-select. dont think its ever relevant for us, but just in case
            
            SingleSelectionModel itemList = frameView.getJMenuBar().getSelectionModel();
            int item = itemList.getSelectedIndex(); // this pair version makes clearing later easier if long chain
            
//            or ...
            String item2 = frameView.getListMenuCustomer().getSelectedValue(); 
                // "Model_MenuItem" is the Class used for the frameView items - gets the item clicked on
                // getMenuList() must be defined in the view frameView references (FrameGUITemplate)
            
            if ( item == -1 ) { return; } // normally use null for objects. -> if nothing was selected do nothing.
            
            //commented b/c function doesnt actually exist
//            switch ( item.getAttributeToSwitchOn() ) { // getAttributeToSwitchOn() should be defined in your item's Model Class, -- see Dashboard Controller and Model_MenuItem for a good example
//                case 1:
//                    //code
//                    break;
//                case 2:
//                    // code
//                    break;
//                default:
//                    //code
//            }
            
            itemList.clearSelection(); // clears selection after completing - for LIST listeners makes act like a button; dont use if dont want that
            frameView.getListMenuCustomer().clearSelection(); 

        }
        
    }
    
    // BUTTON LISTENER
    class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

    }
    
    // Make View - you'll need this for any views that the related views could lead to
    private void makeViewTEMPLATE( AppContextPlaceholder context ) { // make and go to view template
//        // Make new view & set up
//        //==========================
//        //get the data & apply it? not sure if before or after creating the view/control, still figuring that one out. will update

//        ViewGUIClassName view = new ViewGUIClassName(); //new view, eg.
        ProductsGUI productView = new ProductsGUI( ); // views *shouldnt* need context, controller should tell it everything it needs
//        //set to NOT close app when this window closes - important
        productView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
//        //make associated controller: format:
//        ViewControlClassName userControl = new ViewControlClassName( context, view ); eg;
        ProductControlDemo userControl = new ProductControlDemo( context, productView ); 
        productView.setVisible(true); //make it visible
//        
//        // Close preceeding window & clean up // IMPORTANT
//        //==========================
//        clear any data that needs to be cleared/reset
//        //dispose of opening window UNLESS DASHBOARD; 
//        //if it is dashboard, dont dispose. *logout is a special case*
//        callingView.dispose();
        dashWindow.dispose(); // DONT ACTUALLY DISPOSE THE DASH unless logout - dash should stay open
    }
    
    // Any other functions your controller uses, eg helpers, validation, buis logic & rules
}


// //===========================================================================
// //===========================================================================
// // STUFF THAT GOES IN THE VIEW RELATED TO THIS CONTROLLER
// //===========================================================================
// //===========================================================================
// //IN view constructor: eg:
//public class Login extends JFrame {
//    public Login() {
//        initComponents(); // auto added. Leave alone, leave first
//        
//        //some common options:
//        this.setSize(xInt,yInt);
//        this.setLocationRelativeTo( null ); // centers the window on opening
//        this.setResizable( false ); // makes non-resizable
//    }
//    //getters and setters
//    // Make getters and setters for ALL components - INCLUDING subViews
//    //Listeners!
//    //Add listeners
//    //eg
//    public void addItemLIsOnListener( ActionListener loginListener ) {
//        itemListenerIsOn.addActionListener( loginListener );
//    }
//    public void addItemL2IsOnListener( ListSelectionListener listListener ) {
//        itemListener2IsOn.addActionListener( listListener );
//    }
//    
//    // IMPORTANT remove the MAIN that netbeans generated - only The one 'Main' Class should have one.