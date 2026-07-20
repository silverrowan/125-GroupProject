package dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kalei
 */
import model.Trips;
import utility.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import java.util.ArrayList;
import java.util.List;

public class TripsDAO {

    private Connection getConnection() throws SQLException {
        return DBConnection.getConnection();
    }

    /**
     * Gets every trip, including completed and cancelled trips.
     * Used by the employee/admin ViewTripsGUI.
     */
    public List<Trips> getAllTrips() {
        List<Trips> trips = new ArrayList<>();

        String sql = """
            SELECT *
            FROM trips
            ORDER BY departure_date
            """;

        try (
            Connection connection = getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {
                trips.add(mapResultSetToTrip(resultSet));
            }

        } catch (SQLException exception) {
            throw new RuntimeException(
                    "Unable to load trips.",
                    exception
            );
        }

        return trips;
    }

    /**
     * Gets trips that customers can currently choose when booking.
     */
    public List<Trips> getAvailableTrips() {
        List<Trips> trips = new ArrayList<>();

        String sql = """
            SELECT *
            FROM trips
            WHERE trip_status = 'Upcoming'
            ORDER BY departure_date
            """;

        try (
            Connection connection = getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {
                trips.add(mapResultSetToTrip(resultSet));
            }

        } catch (SQLException exception) {
            throw new RuntimeException(
                    "Unable to load available trips.",
                    exception
            );
        }

        return trips;
    }

    /**
     * Gets upcoming trips belonging to one destination/package.
     * This is useful in AddBookingGUI.
     */
    public List<Trips> getTripsByDestination(int destinationID) {
        List<Trips> trips = new ArrayList<>();

        String sql = """
            SELECT *
            FROM trips
            WHERE destination_id = ?
              AND trip_status = 'Upcoming'
            ORDER BY departure_date
            """;

        try (
            Connection connection = getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(sql)
        ) {

            statement.setInt(1, destinationID);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    trips.add(mapResultSetToTrip(resultSet));
                }
            }

        } catch (SQLException exception) {
            throw new RuntimeException(
                    "Unable to load trips for destination ID "
                    + destinationID + ".",
                    exception
            );
        }

        return trips;
    }

    /**
     * Gets one trip using its primary key.
     */
    public Trips getTripByID(int tripID) {
        String sql = """
            SELECT *
            FROM trips
            WHERE trip_id = ?
            """;

        try (
            Connection connection = getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(sql)
        ) {

            statement.setInt(1, tripID);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToTrip(resultSet);
                }
            }

        } catch (SQLException exception) {
            throw new RuntimeException(
                    "Unable to load trip with ID " + tripID + ".",
                    exception
            );
        }

        return null;
    }

    /**
     * Adds a new trip and sets its generated trip ID.
     */
    public boolean addTrip(Trips trip) {
        String sql = """
            INSERT INTO trips (
                destination_id,
                assigned_guide_employee_id,
                trip_title,
                departure_date,
                return_date,
                max_travelers,
                trip_status
            )
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

        try (
            Connection connection = getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(
                            sql,
                            Statement.RETURN_GENERATED_KEYS
                    )
        ) {

            setTripParameters(statement, trip);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                return false;
            }

            try (ResultSet generatedKeys =
                         statement.getGeneratedKeys()) {

                if (generatedKeys.next()) {
                    trip.setTripID(generatedKeys.getInt(1));
                }
            }

            return true;

        } catch (SQLException exception) {
            throw new RuntimeException(
                    "Unable to add trip.",
                    exception
            );
        }
    }

    /**
     * Updates all editable values for an existing trip.
     */
    public boolean updateTrip(Trips trip) {
        String sql = """
            UPDATE trips
            SET destination_id = ?,
                assigned_guide_employee_id = ?,
                trip_title = ?,
                departure_date = ?,
                return_date = ?,
                max_travelers = ?,
                trip_status = ?
            WHERE trip_id = ?
            """;

        try (
            Connection connection = getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(sql)
        ) {

            setTripParameters(statement, trip);

            statement.setInt(8, trip.getTripID());

            return statement.executeUpdate() > 0;

        } catch (SQLException exception) {
            throw new RuntimeException(
                    "Unable to update trip.",
                    exception
            );
        }
    }

    /**
     * Updates only the trip status.
     */
    public boolean updateTripStatus(
            int tripID,
            String status) {

        String sql = """
            UPDATE trips
            SET trip_status = ?
            WHERE trip_id = ?
            """;

        try (
            Connection connection = getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(sql)
        ) {

            statement.setString(1, status);
            statement.setInt(2, tripID);

            return statement.executeUpdate() > 0;

        } catch (SQLException exception) {
            throw new RuntimeException(
                    "Unable to update trip status.",
                    exception
            );
        }
    }

    /**
     * Sets the seven values shared by INSERT and UPDATE.
     */
    private void setTripParameters(
            PreparedStatement statement,
            Trips trip) throws SQLException {

        statement.setInt(
                1,
                trip.getDestinationID()
        );

        /*
         * assigned_guide_employee_id is nullable in the database.
         * A value of 0 means that no guide has been assigned yet.
         */
        if (trip.getAssignedGuideEmployeeID() > 0) {
            statement.setInt(
                    2,
                    trip.getAssignedGuideEmployeeID()
            );
        } else {
            statement.setNull(
                    2,
                    Types.INTEGER
            );
        }

        statement.setString(
                3,
                trip.getTripTitle()
        );

        statement.setDate(
                4,
                new java.sql.Date(
                        trip.getDepartureDate().getTime()
                )
        );

        statement.setDate(
                5,
                new java.sql.Date(
                        trip.getReturnDate().getTime()
                )
        );

        statement.setInt(
                6,
                trip.getMaxTravelers()
        );

        statement.setString(
                7,
                trip.getTripStatus()
        );
    }

    /**
     * Converts one trips-table row into a Trips model.
     */
    private Trips mapResultSetToTrip(
            ResultSet resultSet) throws SQLException {

        int assignedGuideID =
                resultSet.getInt(
                        "assigned_guide_employee_id"
                );

        /*
         * ResultSet.getInt() returns 0 when the SQL value is NULL.
         * That works with your current int model field.
         */
        if (resultSet.wasNull()) {
            assignedGuideID = 0;
        }

        Trips trip = new Trips(
                resultSet.getInt("destination_id"),
                assignedGuideID,
                resultSet.getString("trip_title"),
                resultSet.getDate("departure_date"),
                resultSet.getDate("return_date"),
                resultSet.getInt("max_travelers"),
                resultSet.getString("trip_status")
        );

        trip.setTripID(
                resultSet.getInt("trip_id")
        );

        return trip;
    }
    
    /**
    * Deletes a trip using its primary key.
    */
   public boolean deleteTrip(int tripID) {
       String sql = """
           DELETE FROM trips
           WHERE trip_id = ?
           """;

       try (
           Connection connection = getConnection();
           PreparedStatement statement =
                   connection.prepareStatement(sql)
       ) {

           statement.setInt(1, tripID);

           return statement.executeUpdate() > 0;

       } catch (SQLException exception) {
           throw new RuntimeException(
                   "Unable to delete trip with ID " + tripID + ".",
                   exception
           );
       }
   }
}
