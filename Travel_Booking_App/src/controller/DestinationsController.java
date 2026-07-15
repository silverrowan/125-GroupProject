/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;



/**
 *
 * @author kalei
 */

import dao.DestinationsDAO;
import model.Destinations;

import java.util.List;

public class DestinationsController {

    private final DestinationsDAO destinationsDAO;

    public DestinationsController() {
        this.destinationsDAO = new DestinationsDAO();
    }

    public DestinationsController(DestinationsDAO destinationsDAO) {
        this.destinationsDAO = destinationsDAO;
    }

    public List<Destinations> getAllDestinations() {
        return destinationsDAO.getAllDestinations();
    }

    public List<Destinations> getAvailableDestinations() {
        return destinationsDAO.getAvailableDestinations();
    }

    public Destinations getDestinationByID(int destinationID) {
        if (destinationID <= 0) {
            throw new IllegalArgumentException(
                "A valid destination ID is required."
            );
        }

        Destinations destination =
            destinationsDAO.getDestinationByID(destinationID);

        if (destination == null) {
            throw new IllegalArgumentException(
                "Destination could not be found."
            );
        }

        return destination;
    }

    public List<Destinations> searchDestinations(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAvailableDestinations();
        }

        return destinationsDAO.searchDestinations(searchTerm.trim());
    }

    public boolean addDestination(Destinations destination) {
        validateDestination(destination);

        if (destination.getDestinationStatus() == null
                || destination.getDestinationStatus().isBlank()) {
            destination.setDestinationStatus("Available");
        }

        calculateTotalEstimatedCost(destination);

        return destinationsDAO.addDestination(destination);
    }

    public boolean updateDestination(Destinations destination) {
        if (destination == null) {
            throw new IllegalArgumentException(
                "Destination information is required."
            );
        }

        if (destination.getDestinationID() <= 0) {
            throw new IllegalArgumentException(
                "A valid destination ID is required."
            );
        }

        validateDestination(destination);
        calculateTotalEstimatedCost(destination);

        return destinationsDAO.updateDestination(destination);
    }

    public boolean deactivateDestination(int destinationID) {
        if (destinationID <= 0) {
            throw new IllegalArgumentException(
                "A valid destination ID is required."
            );
        }

        return destinationsDAO.updateDestinationStatus(
            destinationID,
            "Inactive"
        );
    }

    public boolean activateDestination(int destinationID) {
        if (destinationID <= 0) {
            throw new IllegalArgumentException(
                "A valid destination ID is required."
            );
        }

        return destinationsDAO.updateDestinationStatus(
            destinationID,
            "Available"
        );
    }

    public float calculateTotalEstimatedCost(
            float basePrice,
            float activityFees) {

        if (basePrice < 0) {
            throw new IllegalArgumentException(
                "Base price cannot be negative."
            );
        }

        if (activityFees < 0) {
            throw new IllegalArgumentException(
                "Activity fees cannot be negative."
            );
        }

        return basePrice + activityFees;
    }

    private void calculateTotalEstimatedCost(
            Destinations destination) {

        float total = calculateTotalEstimatedCost(
            destination.getBasePrice(),
            destination.getActivityFees()
        );

        destination.setTotalEstimatedCost(total);
    }

    private void validateDestination(Destinations destination) {
        if (destination == null) {
            throw new IllegalArgumentException(
                "Destination information is required."
            );
        }

        if (destination.getDestinationName() == null
                || destination.getDestinationName().isBlank()) {
            throw new IllegalArgumentException(
                "Destination name is required."
            );
        }

        if (destination.getCountryRegion() == null
                || destination.getCountryRegion().isBlank()) {
            throw new IllegalArgumentException(
                "Country or region is required."
            );
        }

        if (destination.getDurationDays() <= 0) {
            throw new IllegalArgumentException(
                "Duration days must be greater than zero."
            );
        }

        if (destination.getDurationNights() < 0) {
            throw new IllegalArgumentException(
                "Duration nights cannot be negative."
            );
        }

        if (destination.getBasePrice() < 0) {
            throw new IllegalArgumentException(
                "Base price cannot be negative."
            );
        }

        if (destination.getActivityFees() < 0) {
            throw new IllegalArgumentException(
                "Activity fees cannot be negative."
            );
        }

        if (destination.getHotel_rating() < 0
                || destination.getHotel_rating() > 5) {
            throw new IllegalArgumentException(
                "Hotel rating must be between 0 and 5."
            );
        }

        String status = destination.getDestinationStatus();

        if (status != null
                && !status.isBlank()
                && !status.equals("Available")
                && !status.equals("Inactive")) {
            throw new IllegalArgumentException(
                "Destination status must be Available or Inactive."
            );
        }
    }
}
