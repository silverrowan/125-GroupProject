
package view.Person;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.naming.directory.InvalidAttributeValueException;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rowan
 */
public class PersonControl {
    private PersonDAO dao;
    
    private PersonFindMany findManyView;
    private PersonFind findView;
    private PersonView view;
    
    //-------------------------------------------------------------------------
    //--------ADD--------------------------------------------------------------
    //-------------------------------------------------------------------------    
    //ADD and VIEW Person
    public PersonControl(PersonDAO dao, PersonView view) {
        this.view = view;
        this.dao = dao;

        view.addUserBtnListener(new AddPersonListener());
    }
    class AddPersonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Read values from the view, includes keeping nullable fields 
                // null as relevant

                String name = view.getTxtName().getText().trim();
                if (name.isEmpty() ){ name = null; } //isBlank treaks pure whitespace like empty

                Double weight = null;
                if ( !view.getTxtWeight().getText().trim().isEmpty() ){
                    weight = Double.parseDouble( view.getTxtWeight().getText() );
                }
                
                String accountNumberText = view.getTxtAcct().getText().trim();

                
                //convert to correct types
                int accountNumber = Integer.parseInt( accountNumberText );
                
                //validate
                if ( !validateName( name ) ) { 
                    throw new InvalidAttributeValueException( "Must have a name" );
                }
                if ( !validateAccountNumber( accountNumber ) ) {
                    throw new InvalidAttributeValueException( "Account Number must be a positive number" );
                }
                if ( !validateWeight( weight ) ) {
                    throw new InvalidAttributeValueException( "Weight must be a positive number" );
                }
                
                //Create obj
                PersonModel person = new PersonModel( name, accountNumber, weight );

                // Save using DAO
                PersonModel DBperson = dao.addNewPerson(person);

                if (DBperson == null) {
                    JOptionPane.showMessageDialog(view, "Did not create person");
                    return;
                } else {
                    JOptionPane.showMessageDialog(view, "Successfully created person");
                    person = DBperson;
                    view.clearFields();
                }
            } catch (InvalidAttributeValueException ex) {
                JOptionPane.showMessageDialog( null , ex.getMessage() + " invalidAttribExcept" );
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, ex.getMessage() + " general Exception" );
            }
        }
    }

    //-------------------------------------------------------------------------
    //--------FIND-------------------------------------------------------------
    //-------------------------------------------------------------------------
    //FIND and VIEW Person
    public PersonControl(PersonDAO dao, PersonFind findView) {
        this.findView = findView;
        this.dao = dao;

        findView.findPersonBtnListener(new FindPersonListener());
        findView.deletePersonBtnListener(new DeletePersonListener());
        
    }
    class FindPersonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //read accountnumber from view
            String accountNumberText = findView.getTxtID().getText().trim();
            int accountNumber = Integer.parseInt( accountNumberText );
            //validate
            
            PersonModel person = dao.getPersonFromID( accountNumber );
            
            if ( person == null ) { JOptionPane.showMessageDialog( null , "no person was found with that id"); }
            else {
//                JTextField nameTxtbox = new JTextField();
//                findView.getTxtName().setText( person.getName() );
                findView.setNameValue( person.getName() );

                String acctNum = String.valueOf( person.getAccountNumber() );
                findView.getTxtAcct().setText( acctNum );

//                String weight = String.valueOf( person.getWeight() );
//                findView.getTxtWeight().setText( weight );
                findView.setWeightValue( person.getWeight() );
            }
        }
    
    }
     
    //-------------------------------------------------------------------------
    //--------DELETE-----------------------------------------------------------
    //-------------------------------------------------------------------------
    //DELETE from FIND Person
    class DeletePersonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //read accountnumber from view
            String accountNumberText = findView.getTxtID().getText().trim();
            int accountNumber = Integer.parseInt( accountNumberText );
            //validate
            
            boolean delSuccess = dao.deletePerson( accountNumber );
            
            if ( delSuccess == true ) { 
                JOptionPane.showMessageDialog( null , "Successfully deleted person entry");
            } else {
                JOptionPane.showMessageDialog( null , "There was a problem deleting the entry"); 
            }
        }
    }
    
    //-------------------------------------------------------------------------
    //--------FIND MANY--------------------------------------------------------int
    //-------------------------------------------------------------------------
    //FIND and VIEW MULTIPLE People
    public PersonControl(PersonDAO dao, PersonFindMany findManyView) {
        this.findManyView = findManyView;
        this.dao = dao;

        findManyView.findPeopleBtnListener(new FindPeopleListener());
    }
    class FindPeopleListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                //read accountnumber from view
                String accountNumberText = findManyView.getTxtID().getText().trim();
                int accountNumber = Integer.parseInt( accountNumberText );
                //validate

                ArrayList<PersonModel> peopleList = dao.getPeopleFromAccountNumber( accountNumber );
                DefaultTableModel model = (DefaultTableModel) findManyView.getTblPeople().getModel();

                model.setRowCount( 0 ); //clear prev search results

                if ( peopleList.isEmpty() ){
                    JOptionPane.showMessageDialog( null , "No people found for account number " + accountNumber);
                    return;
                } else {
                    for ( PersonModel person : peopleList) {
                        model.addRow( new Object[] {
                            person.getId(), 
                            person.getAccountNumber(), 
                            person.getName(), 
                            person.getWeight()
                        });
                    }
                }
            } catch ( NumberFormatException ex ){
                JOptionPane.showMessageDialog( null, "Not a valid account number");
            } catch ( Exception ex ){
                JOptionPane.showMessageDialog( null, "There was a problem getting people for acct number: " + ex.getMessage());
            }

        }
    }
    
     

     
    
    
    
    //Helpers: validation
    private Boolean validateName(String name){
//        if (name.isBlank()) { //name is nullable here lol
//            JOptionPane.showMessageDialog(view, "Name is required.");
//            return false;
//        }
        return true;
    }
    private Boolean validateAccountNumber ( int accountNumber ){
        if (accountNumber < 0) {
            JOptionPane.showMessageDialog(view, "Account number must be greater than zero.");
            return false;
        }
        return true;
    }
    private Boolean validateWeight(Double weight){
        if ( weight == null ){ return true; }
        if (weight < 0) {
            JOptionPane.showMessageDialog(view, "Weight must be greater than zero.int");
            return false;
        }
        return true; 
    }
}    