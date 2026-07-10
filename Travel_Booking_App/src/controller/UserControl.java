package controller;

import dao.UserDAO;
import service.UserService;
import view.AddUserGUIPage1;
import model.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author rowan
 */
public class UserControl {
//    private UserDAO userDao;
    private AddUserGUIPage1 userView;
//    private UserService userService;
    
    public UserControl( UserDAO userDao, AddUserGUIPage1 userView, UserService userService ) {
//        this.userDao = userDao;
        this.userView = userView;
//        this.userService = userService;
        
        this.userView.addNextBtnListener( new AddUserRecord() );
    }
    
    class AddUserRecord implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = userView.getTxtUsername().getText();
            String password = userView.getTxtPassword().getText();
            String firstName = userView.getTxtFirstName().getText();
            String lastName = userView.getTxtLastName().getText();
            String email = userView.getTxtEmail().getText();
            String phone = userView.getTxtPhone().getText();
            Object roleObj = userView.getComboRole().getSelectedItem();
            
            User user = new User(username, password, firstName, lastName, email, roleObj.toString(), phone);
//            UserService serv = new UserService(userDao);
            boolean isSuccess = UserService.addNewUser(username, password, firstName, lastName, email, roleObj, phone);
            
            if (isSuccess) { JOptionPane.showMessageDialog(null, "User created successfully"); }
            else { JOptionPane.showMessageDialog(null, "User was not created"); }
        }
    }
    
}
