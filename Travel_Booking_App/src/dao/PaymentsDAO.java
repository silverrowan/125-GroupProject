package dao;

/**
 *
 * @author mariah
 */

import utility.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import model.Payments;

public class PaymentsDAO {

    private Connection getConnection() throws SQLException {
        return DBConnection.getConnection();
    }

// MAKE NEW PAYMENTS
//----------------------------------------------------------------------
    /**
     * Adds a new payment and gets its generated ID.
     */
    public Payments addPayment(Payments pmt) {
        String query = """
            INSERT INTO payments (
                    booking_id, invoice_date, base_price, activity_fees, tax_amount,
                    total_amount, payment_date, amount_paid, payment_method, payment_status
                    )
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

        try ( Connection link = DBConnection.getConnection(); 
                    PreparedStatement p = link.prepareStatement(query, Statement.RETURN_GENERATED_KEYS); )
        {
            p.setInt( 1, pmt.getBookingID() );
            p.setString( 2, pmt.getInvoiceDate().toString() );
            p.setFloat(3, pmt.getBasePrice() );
            p.setFloat( 4, pmt.getActivityFees() );
            p.setFloat( 5, pmt.getTaxAmount() );
            p.setFloat( 6, pmt.getTotalAmount() );
            p.setString( 7, pmt.getPaymentDate().toString() );
            p.setFloat( 8, pmt.getAmountPaid() );
            p.setString( 9, pmt.getPaymentMethod().toString() );
            p.setString( 10, pmt.getPaymentStatus().toString() );

            int row = p.executeUpdate();

            if (row <= 0) { 
                JOptionPane.showMessageDialog( null , "Payment was not recorded");
                return null;
            }
            try ( ResultSet set = p.getGeneratedKeys() ) {
                if (set.next()) {
                    int id = set.getInt(1);
                    pmt.setPaymentID( id );
                    
                    System.out.println( "Generated Payment ID: " + pmt.getPaymentID() );
                    JOptionPane.showMessageDialog( null , "Payment was recorded successfully" ); 
                    return pmt;
                }
            }
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog( null , "Payment was not recorded. " + exception);
            return null;
        }
        return null;
    }
    
// GET/RETRIEVE PAYMENTS    
//----------------------------------------------------------------------
        /**
     * Gets every payment for a specific booking, regardless of method or status.
     */
//    public List<Trips> getAllTrips() {
//        List<Trips> trips = new ArrayList<>();
//
//        String sql = """
//            SELECT *
//            FROM trips
//            ORDER BY departure_date
//            """;
//
//        try (
//            Connection connection = getConnection();
//            PreparedStatement statement =
//                    connection.prepareStatement(sql);
//            ResultSet resultSet = statement.executeQuery()
//        ) {
//
//            while (resultSet.next()) {
//                trips.add(mapResultSetToTrip(resultSet));
//            }
//
//        } catch (SQLException exception) {
//            throw new RuntimeException(
//                    "Unable to load trips.",
//                    exception
//            );
//        }
//
//        return trips;
//    }
    
// EDIT PAYMENTS    
//----------------------------------------------------------------------
    /**
     * Updates editable values (via edit payment window).
     */
    public boolean updatePayment(Payments pmt) {
        String query = """
                UPDATE payments SET "
                    payment_date = ?,
                    amount_paid = ?,
                    payment_method = ?,
                    payment_status = ?,
                WHERE payment_id = ?
                """;

        try ( Connection link = DBConnection.getConnection(); 
                PreparedStatement p = link.prepareStatement(query, Statement.RETURN_GENERATED_KEYS); )
        {
            p.setString( 1, pmt.getPaymentDate().toString() );
            p.setFloat( 2, pmt.getAmountPaid() );
            p.setString( 3, pmt.getPaymentMethod().toString() );
            p.setString( 4, pmt.getPaymentStatus().toString() );

            return checkSuccessfulViaObj( p, pmt, "Successfully edited payment", "Payment was not edited" );
            
        } catch (SQLException exception) {
            throw new RuntimeException(
                    "Unable to update trip.",
                    exception
            );
        }
    }

// DELETE PAYMENTS    
//----------------------------------------------------------------------    
    /**
    * Deletes a payment using its Payment object.
    */
   public boolean deletePayment(Payments pmt) {
       String query = "DELETE FROM payments WHERE payment_id = ?";
        try ( Connection link = DBConnection.getConnection(); 
                    PreparedStatement p = link.prepareStatement(query, Statement.RETURN_GENERATED_KEYS); )
        {
        p.setInt( 1, pmt.getPaymentID() );
            int row = p.executeUpdate();
            if ( row > 0 ) {
                JOptionPane.showMessageDialog( null, "Successfully deleted Payment");
                return true;
            }
            JOptionPane.showMessageDialog( null, "Payment was not deleted");
            return false;
       } catch (SQLException exception) {
            JOptionPane.showMessageDialog( null, "Payment was not deleted");
            return false;
       }
   }

// HELPER FUNCTIONS
   public Boolean checkSuccessfulViaObj( PreparedStatement p, Payments pmt, String success, String failure  ) throws SQLException{
        p.setInt( 1, pmt.getPaymentID() );
        int row = p.executeUpdate();
        if ( row > 0 ) {
            JOptionPane.showMessageDialog( null, success);
            return true;
        }
        JOptionPane.showMessageDialog( null, failure);
        return false;     
   }

   public Boolean checkSuccessfulViaID( PreparedStatement p, int pmt, String success, String failure  ) throws SQLException{
        p.setInt( 1, pmt );
        int row = p.executeUpdate();
        if ( row > 0 ) {
            JOptionPane.showMessageDialog( null, success);
            return true;
        }
        JOptionPane.showMessageDialog( null, failure);
        return false;     
   }
}