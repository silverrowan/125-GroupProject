package view.Person.luggage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import view.Person.PersonDAO;
import view.Person.PersonFindMany;
import view.Person.PersonModel;

/**
 *
 * @author rowan
 */
public class LuggageControl {
    private LuggageOwnerDAO dao;
    private LuggageMany view;  

    //-------------------------------------------------------------------------
    //--------FIND MANY--------------------------------------------------------int
    //-------------------------------------------------------------------------
    //FIND and VIEW MULTIPLE People
    public LuggageControl(LuggageOwnerDAO dao, LuggageMany view) {
        this.view = view;
        this.dao = dao;

        view.findLuggageBtnListener(new FindLuggageListener());
    }
    class FindLuggageListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                //read accountnumber from view
                String ownerIDText = view.getTxtID().getText().trim();
                int ownerID = Integer.parseInt( ownerIDText );
                //validate

                ArrayList<LuggageOwnerModel> luggageList = dao.getLuggageOwnerFromID( ownerID );
                DefaultTableModel model = (DefaultTableModel) view.getTblLuggage().getModel();

                model.setRowCount( 0 ); //clear prev search results

                if ( luggageList.isEmpty() ){
                    JOptionPane.showMessageDialog( null , "No luggage found for owner number " + ownerID );
                    return;
                } else {
                    for ( LuggageOwnerModel luggage : luggageList) {
                        model.addRow( new Object[] {
                            luggage.getLuggageId(), 
                            luggage.getAccountNumber(), 
                            luggage.getName(), 
                            luggage.getDescription()
                        });
                    }
                }
            } catch ( NumberFormatException ex ){
                JOptionPane.showMessageDialog( null, "Not a valid owner id");
            } catch ( Exception ex ){
                JOptionPane.showMessageDialog( null, "There was a problem getting luggage for ownerId: " + ex.getMessage());
            }

        }
    }
    
}
