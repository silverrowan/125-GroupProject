/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author kalei
 */

import model.Destinations;
import utility.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DestinationsDAO {

    private Connection getConnection() throws SQLException {
        return DBConnection.getConnection(); //check this
    }

    /**
     * Returns every destination, including inactive destinations.
     * Mainly used by the administrator destination-management GUI.
     */
    public List<Destinations> getAllDestinations() {

        List<Destinations> destinations = new ArrayList<>();

        String sql = """
            SELECT *
            FROM destinations
            ORDER BY destination_name
            """;

        try (
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {
                destinations.add(mapResultSetToDestination(resultSet));
            }

        } catch (SQLException exception) {
            throw new RuntimeException(
                "Unable to load destinations.",
                exception
            );
        }

        return destinations;
    }

    /**
     * Returns only destinations with Available status.
     * Used by ProductsGUI and Add/EditTripGUI.
     */
    public List<Destinations> getAvailableDestinations() {

        List<Destinations> destinations = new ArrayList<>();

        String sql = """
            SELECT *
            FROM destinations
            WHERE destination_status = 'Available'
            ORDER BY destination_name
            """;

        try (
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {
                destinations.add(mapResultSetToDestination(resultSet));
            }

        } catch (SQLException exception) {
            throw new RuntimeException(
                "Unable to load available destinations.",
                exception
            );
        }

        return destinations;
    }

    /**
     * Returns one destination using its primary key.
     */
    public Destinations getDestinationByID(int destinationID) {

        String sql = """
            SELECT *
            FROM destinations
            WHERE destination_id = ?
            """;

        try (
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, destinationID);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return mapResultSetToDestination(resultSet);
                }
            }

        } catch (SQLException exception) {
            throw new RuntimeException(
                "Unable to load destination with ID "
                    + destinationID + ".",
                exception
            );
        }

        return null;
    }

    /**
     * Searches available destinations by destination name,
     * country/region, hotel name, or hotel city.
     */
    public List<Destinations> searchDestinations(String searchTerm) {

        List<Destinations> destinations = new ArrayList<>();

        String sql = """
            SELECT *
            FROM destinations
            WHERE destination_status = 'Available'
              AND (
                    LOWER(destination_name) LIKE LOWER(?)
                 OR LOWER(country_region) LIKE LOWER(?)
                 OR LOWER(hotel_name) LIKE LOWER(?)
                 OR LOWER(hotel_city) LIKE LOWER(?)
              )
            ORDER BY destination_name
            """;

        String searchPattern = "%" + searchTerm.trim() + "%";

        try (
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, searchPattern);
            statement.setString(2, searchPattern);
            statement.setString(3, searchPattern);
            statement.setString(4, searchPattern);

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    destinations.add(
                        mapResultSetToDestination(resultSet)
                    );
                }
            }

        } catch (SQLException exception) {
            throw new RuntimeException(
                "Unable to search destinations.",
                exception
            );
        }

        return destinations;
    }

    /**
     * Adds a destination and sets the generated destination ID
     * on the model object.
     */
    public boolean addDestination(Destinations destination) {

        String sql = """
            INSERT INTO destinations (
                destination_name,
                country_region,
                notes,
                hotel_name,
                hotel_rating,
                hotel_street_number,
                hotel_street_name,
                hotel_city,
                hotel_province_region,
                hotel_postal_code,
                hotel_country,
                flight_info,
                bus_train_info,
                transfer_details,
                included_activities,
                optional_activities,
                duration_days,
                duration_nights,
                base_price,
                activity_fees,
                total_estimated_cost,
                destination_status
            )
            VALUES (
                ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
                ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
            )
            """;

        try (
            Connection connection = getConnection();
            PreparedStatement statement =
                connection.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
                )
        ) {

            setDestinationParameters(statement, destination);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                return false;
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {

                if (generatedKeys.next()) {
                    destination.setDestinationID(
                        generatedKeys.getInt(1)
                    );
                }
            }

            return true;

        } catch (SQLException exception) {
            throw new RuntimeException(
                "Unable to add destination.",
                exception
            );
        }
    }

    /**
     * Updates every editable field for an existing destination.
     */
    public boolean updateDestination(Destinations destination) {

        String sql = """
            UPDATE destinations
            SET destination_name = ?,
                country_region = ?,
                notes = ?,
                hotel_name = ?,
                hotel_rating = ?,
                hotel_street_number = ?,
                hotel_street_name = ?,
                hotel_city = ?,
                hotel_province_region = ?,
                hotel_postal_code = ?,
                hotel_country = ?,
                flight_info = ?,
                bus_train_info = ?,
                transfer_details = ?,
                included_activities = ?,
                optional_activities = ?,
                duration_days = ?,
                duration_nights = ?,
                base_price = ?,
                activity_fees = ?,
                total_estimated_cost = ?,
                destination_status = ?
            WHERE destination_id = ?
            """;

        try (
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            setDestinationParameters(statement, destination);

            statement.setInt(
                23,
                destination.getDestinationID()
            );

            return statement.executeUpdate() > 0;

        } catch (SQLException exception) {
            throw new RuntimeException(
                "Unable to update destination.",
                exception
            );
        }
    }

    /**
     * Changes only the Available/Inactive status.
     */
    public boolean updateDestinationStatus(
            int destinationID,
            String status) {

        String sql = """
            UPDATE destinations
            SET destination_status = ?
            WHERE destination_id = ?
            """;

        try (
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, status);
            statement.setInt(2, destinationID);

            return statement.executeUpdate() > 0;

        } catch (SQLException exception) {
            throw new RuntimeException(
                "Unable to update destination status.",
                exception
            );
        }
    }

    /**
     * Applies the 22 destination values shared by INSERT and UPDATE.
     */
    private void setDestinationParameters(
            PreparedStatement statement,
            Destinations destination) throws SQLException {

        statement.setString(
            1,
            destination.getDestinationName()
        );

        statement.setString(
            2,
            destination.getCountryRegion()
        );

        statement.setString(
            3,
            destination.getNotes()
        );

        statement.setString(
            4,
            destination.getHotelName()
        );

        statement.setFloat(
            5,
            destination.getHotel_rating()
        );

        statement.setString(
            6,
            destination.getHotelStreetNumber()
        );

        statement.setString(
            7,
            destination.getHotelStreetName()
        );

        statement.setString(
            8,
            destination.getHotelCity()
        );

        statement.setString(
            9,
            destination.getHotelProvinceRegion()
        );

        statement.setString(
            10,
            destination.getHotelPostalCode()
        );

        statement.setString(
            11,
            destination.getHotelCountry()
        );

        statement.setString(
            12,
            destination.getFlightInfo()
        );

        statement.setString(
            13,
            destination.getBusTrainInfo()
        );

        statement.setString(
            14,
            destination.getTransferDetails()
        );

        statement.setString(
            15,
            destination.getIncludedActivities()
        );

        statement.setString(
            16,
            destination.getOptionalActivities()
        );

        statement.setInt(
            17,
            destination.getDurationDays()
        );

        statement.setInt(
            18,
            destination.getDurationNights()
        );

        statement.setFloat(
            19,
            destination.getBasePrice()
        );

        statement.setFloat(
            20,
            destination.getActivityFees()
        );

        statement.setFloat(
            21,
            destination.getTotalEstimatedCost()
        );

        statement.setString(
            22,
            destination.getDestinationStatus().toString()
        );
    }

    /**
     * Converts one database row into a Destinations model object.
     */
    private Destinations mapResultSetToDestination(
            ResultSet resultSet) throws SQLException {

        Destinations destination = new Destinations(
            resultSet.getString("destination_name"),
            resultSet.getString("country_region"),
            resultSet.getString("notes"),
            resultSet.getString("hotel_name"),
            resultSet.getFloat("hotel_rating"),
            resultSet.getString("hotel_street_number"),
            resultSet.getString("hotel_street_name"),
            resultSet.getString("hotel_city"),
            resultSet.getString("hotel_province_region"),
            resultSet.getString("hotel_postal_code"),
            resultSet.getString("hotel_country"),
            resultSet.getString("flight_info"),
            resultSet.getString("bus_train_info"),
            resultSet.getString("transfer_details"),
            resultSet.getString("included_activities"),
            resultSet.getString("optional_activities"),
            resultSet.getInt("duration_days"),
            resultSet.getInt("duration_nights"),
            resultSet.getFloat("base_price"),
            resultSet.getFloat("activity_fees"),
            resultSet.getString("destination_status")
        );

        destination.setDestinationID(
            resultSet.getInt("destination_id")
        );

        destination.setTotalEstimatedCost(
            resultSet.getFloat("total_estimated_cost")
        );

        return destination;
    }
}
