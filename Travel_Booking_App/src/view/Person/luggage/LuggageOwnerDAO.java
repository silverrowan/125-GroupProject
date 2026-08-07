package view.Person.luggage;

import view.Person.PersonModel;
import utility.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.Types;


/**
 *
 * @author rowan
 */
public class LuggageOwnerDAO {
    public LuggageOwnerDAO(){}
    
    public ArrayList<LuggageOwnerModel> getLuggageOwnerFromID( int personID ) {
        String query = """
                       SELECT l.id AS luggage_id, 
                            l.description, p.name, p.accountNumber, p.id AS owner_id
                       FROM luggage l LEFT JOIN people p ON l.owner_id = p.id
                       WHERE p.id = ?
                       ORDER BY p.accountNumber, p.name, l.id;
                       """;
        try ( Connection link = DBConnection.getConnection(); 
            PreparedStatement p = link.prepareStatement(query); )
        {
            p.setInt( 1, personID );
            
            ArrayList<LuggageOwnerModel> luggageList = new ArrayList<>();
            ResultSet rs = p.executeQuery();
            while ( rs.next() ){
                LuggageOwnerModel luggage = makeLuggageObj(rs);
                luggageList.add(luggage);
            }
            return luggageList;
        } catch ( SQLException e ){ 
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "there was an SQL problem getting the luggage entry");
        } catch ( Exception e ){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "there was a problem getting the luggage entry");
        }
        return null;
    }


    //helper: make Luggage Result
    public LuggageOwnerModel makeLuggageObj( ResultSet rs ) throws SQLException { 
        LuggageOwnerModel  luggage = new LuggageOwnerModel();
        luggage.setOwnerId( rs.getInt("owner_id") );
        luggage.setLuggageId( rs.getInt("luggage_id") );
        luggage.setAccountNumber(rs.getInt("accountNumber") );
        //nullable string needs no extra treatment
        luggage.setName(rs.getString("name") );
        luggage.setDescription(rs.getString("description") );       
        return luggage;
    }
}
