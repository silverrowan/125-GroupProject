/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import model.Bookings;
import model.Bookings.bookingStatusType;
import utility.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kalei
 */
public class BookingDAO {
    
     public List<Bookings> getAllBookings() {
        List<Bookings> bookings = new ArrayList<>();

        String sql = """
                SELECT booking_id,
                       customer_id,
                       trip_id,
                       created_by_user_id,
                       booking_date,
                       number_of_travelers,
                       booking_status,
                       special_requests,
                       booking_notes
                FROM bookings
                ORDER BY booking_date DESC
                """;

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {
                bookings.add(mapBooking(resultSet));
            }

        } catch (SQLException exception) {
            throw new RuntimeException(
                    "Error retrieving bookings: "
                    + exception.getMessage(),
                    exception
            );
        }

        return bookings;
    }

    public List<Bookings> getBookingsByCustomer(int customerID) {
        List<Bookings> bookings = new ArrayList<>();

        String sql = """
                SELECT booking_id,
                       customer_id,
                       trip_id,
                       created_by_user_id,
                       booking_date,
                       number_of_travelers,
                       booking_status,
                       special_requests,
                       booking_notes
                FROM bookings
                WHERE customer_id = ?
                ORDER BY booking_date DESC
                """;

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, customerID);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    bookings.add(mapBooking(resultSet));
                }
            }

        } catch (SQLException exception) {
            throw new RuntimeException(
                    "Error retrieving customer bookings: "
                    + exception.getMessage(),
                    exception
            );
        }

        return bookings;
    }

    public List<Bookings> getBookingsByTrip(int tripID) {
        List<Bookings> bookings = new ArrayList<>();

        String sql = """
                SELECT booking_id,
                       customer_id,
                       trip_id,
                       created_by_user_id,
                       booking_date,
                       number_of_travelers,
                       booking_status,
                       special_requests,
                       booking_notes
                FROM bookings
                WHERE trip_id = ?
                ORDER BY booking_date DESC
                """;

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, tripID);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    bookings.add(mapBooking(resultSet));
                }
            }

        } catch (SQLException exception) {
            throw new RuntimeException(
                    "Error retrieving trip bookings: "
                    + exception.getMessage(),
                    exception
            );
        }

        return bookings;
    }

    public Bookings getBookingByID(int bookingID) {
        String sql = """
                SELECT booking_id,
                       customer_id,
                       trip_id,
                       created_by_user_id,
                       booking_date,
                       number_of_travelers,
                       booking_status,
                       special_requests,
                       booking_notes
                FROM bookings
                WHERE booking_id = ?
                """;

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, bookingID);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapBooking(resultSet);
                }
            }

        } catch (SQLException exception) {
            throw new RuntimeException(
                    "Error retrieving booking: "
                    + exception.getMessage(),
                    exception
            );
        }

        return null;
    }

    public boolean addBooking(Bookings booking) {
        String sql = """
                INSERT INTO bookings (
                    customer_id,
                    trip_id,
                    created_by_user_id,
                    booking_date,
                    number_of_travelers,
                    booking_status,
                    special_requests,
                    booking_notes
                )
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
            )
        ) {

            statement.setInt(1, booking.getCustomerID());
            statement.setInt(2, booking.getTripID());

            if (booking.getCreatedByUserID() > 0) {
                statement.setInt(3, booking.getCreatedByUserID());
            } else {
                statement.setNull(3, Types.INTEGER);
            }

            statement.setDate(
                    4,
                    new java.sql.Date(
                            booking.getBookingDate().getTime()
                    )
            );

            statement.setInt(
                    5,
                    booking.getNumberOfTravelers()
            );

            statement.setString(
                    6,
                    booking.getBookingStatus().name()
            );

            statement.setString(
                    7,
                    booking.getSpecialRequests()
            );

            statement.setString(
                    8,
                    booking.getBookingNotes()
            );

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys =
                             statement.getGeneratedKeys()) {

                    if (generatedKeys.next()) {
                        booking.setBookingID(
                                generatedKeys.getInt(1)
                        );
                    }
                }

                return true;
            }

        } catch (SQLException exception) {
            throw new RuntimeException(
                    "Error adding booking: "
                    + exception.getMessage(),
                    exception
            );
        }

        return false;
    }

    public boolean updateBooking(Bookings booking) {
        String sql = """
                UPDATE bookings
                SET customer_id = ?,
                    trip_id = ?,
                    created_by_user_id = ?,
                    booking_date = ?,
                    number_of_travelers = ?,
                    booking_status = ?,
                    special_requests = ?,
                    booking_notes = ?
                WHERE booking_id = ?
                """;

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, booking.getCustomerID());
            statement.setInt(2, booking.getTripID());

            if (booking.getCreatedByUserID() > 0) {
                statement.setInt(3, booking.getCreatedByUserID());
            } else {
                statement.setNull(3, Types.INTEGER);
            }

            statement.setDate(
                    4,
                    new java.sql.Date(
                            booking.getBookingDate().getTime()
                    )
            );

            statement.setInt(
                    5,
                    booking.getNumberOfTravelers()
            );

            statement.setString(
                    6,
                    booking.getBookingStatus().name()
            );

            statement.setString(
                    7,
                    booking.getSpecialRequests()
            );

            statement.setString(
                    8,
                    booking.getBookingNotes()
            );

            statement.setInt(
                    9,
                    booking.getBookingID()
            );

            return statement.executeUpdate() > 0;

        } catch (SQLException exception) {
            throw new RuntimeException(
                    "Error updating booking: "
                    + exception.getMessage(),
                    exception
            );
        }
    }

    public boolean updateBookingStatus(
            int bookingID,
            bookingStatusType status
    ) {
        String sql = """
                UPDATE bookings
                SET booking_status = ?
                WHERE booking_id = ?
                """;

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, status.name());
            statement.setInt(2, bookingID);

            return statement.executeUpdate() > 0;

        } catch (SQLException exception) {
            throw new RuntimeException(
                    "Error updating booking status: "
                    + exception.getMessage(),
                    exception
            );
        }
    }

    private Bookings mapBooking(ResultSet resultSet)
            throws SQLException {

        int createdByUserID =
                resultSet.getInt("created_by_user_id");

        if (resultSet.wasNull()) {
            createdByUserID = 0;
        }

        Bookings booking = new Bookings(
                resultSet.getInt("customer_id"),
                resultSet.getInt("trip_id"),
                createdByUserID,
                resultSet.getDate("booking_date"),
                resultSet.getInt("number_of_travelers"),
                bookingStatusType.valueOf(
                        resultSet.getString("booking_status")
                ),
                resultSet.getString("special_requests"),
                resultSet.getString("booking_notes")
        );

        booking.setBookingID(
                resultSet.getInt("booking_id")
        );

        return booking;
    }
}
