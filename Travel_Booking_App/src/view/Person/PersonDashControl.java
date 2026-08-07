
package view.Person;

import view.Person.*;
import view.Person.PersonDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import dao.UserDAO;
import javax.naming.directory.InvalidAttributeValueException;
import javax.swing.JFrame;
import javax.swing.JTextField;
import view.Person.PersonModel;
import view.Person.PersonFind;

import view.Person.PersonView;
import view.Person.luggage.LuggageControl;
import view.Person.luggage.LuggageMany;
import view.Person.luggage.LuggageOwnerDAO;
/**
 *
 * @author rowan
 */
public class PersonDashControl {
    private PersonDash dash;
    private PersonDAO dao;
    private LuggageOwnerDAO lDao;
    
    //-------------------------------------------------------------------------
    //--------DASH DIRECT------------------------------------------------------
    //-------------------------------------------------------------------------    
    public PersonDashControl(PersonDash dash, PersonDAO dao) {
        this.dash = dash;
        this.dao = dao;
        this.lDao = new LuggageOwnerDAO();

        dash.addPersonBtnListener(new openAddPerson());
        dash.findPersonBtnListener(new openFindPerson());
        dash.findPeopleBtnListener(new openFindPeople());
        dash.findLuggageBtnListener(new openLuggage());
    }
    
    class openAddPerson implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        PersonView view = new PersonView();
        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        PersonControl pc = new PersonControl( dao, view );
        view.setVisible(true);
        }
    }
    
    class openFindPerson implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        PersonFind view = new PersonFind();
        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        PersonControl pc = new PersonControl( dao, view );
        view.setVisible(true);
        }
    }
    
    class openFindPeople implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        PersonFindMany view = new PersonFindMany();
        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        PersonControl pc = new PersonControl( dao, view );
        view.setVisible(true);
        }
    }
    
    class openLuggage implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        LuggageMany view = new LuggageMany();
        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        LuggageControl pc = new LuggageControl( lDao, view );
        view.setVisible(true);
        }
    }
}    