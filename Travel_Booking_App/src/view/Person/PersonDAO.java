package view.Person;

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
public class PersonDAO {
    public PersonDAO(){}
    
    public PersonModel addNewPerson( PersonModel person ) {
        String query = """
                       INSERT INTO people ( accountNumber, name, weight )
                       VALUES ( ?, ?, ? );
                       """;
        try ( Connection link = DBConnection.getConnection(); 
            PreparedStatement p = link.prepareStatement(query, Statement.RETURN_GENERATED_KEYS); )
        {
            p.setInt( 1, person.getAccountNumber() );
            p.setString( 2, person.getName());
            
            Double weight = person.getWeight();
            if ( weight == null ){
                p.setNull(3, Types.DOUBLE );
            } else {
                p.setDouble( 3, weight);
            }
            
            int row = p.executeUpdate();
            
            if ( row > 0 ) {
                ResultSet rs = p.getGeneratedKeys();
                if ( rs.next() ){
                    int idGen = rs.getInt(1);
                    person.setId( idGen );
                    
                    return person;
                }
            }
        } catch ( SQLException e ){ 
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "there was an SQL problem creating the person entry");
        } catch ( Exception e ){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "there was a problem creating the person entry");
        }
        return null;
    }

    public PersonModel updatePerson( PersonModel person ) {
        String query = """
                       UPDATE people 
                       SET accountNumber = ?,
                            name = ?,
                            weight = ?
                       WHERE person_id = ?;
                       """;
        try ( Connection link = DBConnection.getConnection(); 
            PreparedStatement p = link.prepareStatement(query);)
        {
            p.setInt( 1, person.getAccountNumber() );
            p.setString( 2, person.getName());
            p.setDouble( 3, person.getWeight());
            p.setInt( 4, person.getId());
            
            int row = p.executeUpdate();
            
            if ( row > 0 ) { return person; }
            throw new SQLException("Update Failed");
        } catch ( SQLException e ){ 
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "there was an SQL problem updating the person entry");
        } catch ( Exception e ){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "there was a problem creating the person entry");
        }
        return null;
    }

    public boolean deletePerson( int personID ) {
        String query = """
                       DELETE FROM people 
                       WHERE id = ?;
                       """;
        try ( Connection link = DBConnection.getConnection(); 
            PreparedStatement p = link.prepareStatement(query);)
        {
            p.setInt( 1, personID);
            
            int row = p.executeUpdate();
            
            if ( row == 0 ) { throw new SQLException("Nothing was deleted"); }
            if ( row < 0 ) { throw new SQLException("Something went wrong"); }
            if ( row > 1 ) { throw new SQLException("Multiple entries were deleted!"); }
            
            return true;
            
        } catch ( SQLException e ){ 
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "There was a problem: " + e.getMessage() );
        } catch ( Exception e ){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "there was a problem deleting the person entry");
        }
        return false;
    }
    
    public PersonModel getPersonFromID( int personID ) {
        String query = """
                       SELECT id, accountNumber, name, weight
                       FROM people
                       WHERE id = ?;
                       """;
        try ( Connection link = DBConnection.getConnection(); 
            PreparedStatement p = link.prepareStatement(query, Statement.RETURN_GENERATED_KEYS); )
        {
            p.setInt( 1, personID );
            
            ResultSet rs = p.executeQuery();
            if ( rs.next() ){
                PersonModel person = makePersonObj(rs);
                return person;
            }
        } catch ( SQLException e ){ 
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "there was an SQL problem getting the person entry");
        } catch ( Exception e ){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "there was a problem getting the person entry");
        }
        return null;
    }
    
    public ArrayList<PersonModel> getPeopleFromAccountNumber( int accountNumber ) {
        String query = "";
        if ( accountNumber == 0 ){ //use for ALL accounts
            query = """
                    SELECT * FROM people;
                    """;
        } else {
            query = """
                    SELECT id, accountNumber, name, weight
                    FROM people 
                    WHERE accountNumber = ?;
                    """;
        }
        try ( Connection link = DBConnection.getConnection(); 
            PreparedStatement p = link.prepareStatement(query); )
        {
            if ( accountNumber != 0 ){ p.setInt( 1, accountNumber ); } //if NOT all accounts
            
            ArrayList<PersonModel> peopleList = new ArrayList<>();
            ResultSet rs = p.executeQuery();
            
            while ( rs.next() ){
                PersonModel person = makePersonObj(rs);
                peopleList.add(person);
            }
            return peopleList;
            
        } catch ( SQLException e ){ 
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "there was an SQL problem getting the person entry");
        } catch ( Exception e ){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "there was a problem getting the person entry");
        }
        return null;
    }


    //helper: make Person Result
    public PersonModel makePersonObj( ResultSet rs ) throws SQLException { 
        PersonModel  person = new PersonModel();
        person.setId( rs.getInt("id") );
        person.setAccountNumber(rs.getInt("accountNumber") );
        //nullable string needs no extra treatment
        person.setName(rs.getString("name") );
        //nullable double column
        double weight = rs.getDouble("weight") ;
        if (rs.wasNull() ){ person.setWeight( null ); } 
        else { person.setWeight( weight ); }
        //alt could also be:
        //person.setWeight(rs.getObject("weight", Double.class));
        //which will return null if its null in the DB
        
        return person;
    }
}
