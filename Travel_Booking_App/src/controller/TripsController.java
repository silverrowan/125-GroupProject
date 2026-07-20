package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kalei
 */

import dao.TripsDAO;
import model.Trips;

import java.util.Date;
import java.util.List;

public class TripsController {

    private final TripsDAO tripsDAO;

    public TripsController() {
        this.tripsDAO = new TripsDAO();
    }

    public TripsController(TripsDAO tripsDAO) {
        this.tripsDAO = tripsDAO;
    }

    public List<Trips> getAllTrips() {
        return tripsDAO.getAllTrips();
    }

    public List<Trips> getAvailableTrips() {
        return tripsDAO.getAvailableTrips();
    }

    public List<Trips> getTripsByDestination(int destinationID) {
        if (destinationID <= 0) {
            throw new IllegalArgumentException(
                    "A valid destination ID is required."
            );
        }

        return tripsDAO.getTripsByDestination(destinationID);
    }

    public Trips getTripByID(int tripID) {
        if (tripID <= 0) {
            throw new IllegalArgumentException(
                    "A valid trip ID is required."
            );
        }

        Trips trip = tripsDAO.getTripByID(tripID);

        if (trip == null) {
            throw new IllegalArgumentException(
                    "Trip could not be found."
            );
        }

        return trip;
    }

    public boolean addTrip(Trips trip) {
        validateTrip(trip);

        if (trip.getTripStatus() == null
                || trip.getTripStatus().isBlank()) {
            trip.setTripStatus("Upcoming");
        }

        return tripsDAO.addTrip(trip);
    }

    public boolean updateTrip(Trips trip) {
        if (trip == null) {
            throw new IllegalArgumentException(
                    "Trip information is required."
            );
        }

        if (trip.getTripID() <= 0) {
            throw new IllegalArgumentException(
                    "A valid trip ID is required."
            );
        }

        validateTrip(trip);

        return tripsDAO.updateTrip(trip);
    }

    public boolean updateTripStatus(int tripID, String status) {
        if (tripID <= 0) {
            throw new IllegalArgumentException(
                    "A valid trip ID is required."
            );
        }

        validateStatus(status);

        return tripsDAO.updateTripStatus(tripID, status);
    }

    private void validateTrip(Trips trip) {
        if (trip == null) {
            throw new IllegalArgumentException(
                    "Trip information is required."
            );
        }

        if (trip.getDestinationID() <= 0) {
            throw new IllegalArgumentException(
                    "A destination is required."
            );
        }

        if (trip.getTripTitle() == null
                || trip.getTripTitle().isBlank()) {
            throw new IllegalArgumentException(
                    "Trip title is required."
            );
        }

        Date departureDate = trip.getDepartureDate();
        Date returnDate = trip.getReturnDate();

        if (departureDate == null) {
            throw new IllegalArgumentException(
                    "Departure date is required."
            );
        }

        if (returnDate == null) {
            throw new IllegalArgumentException(
                    "Return date is required."
            );
        }

        if (returnDate.before(departureDate)) {
            throw new IllegalArgumentException(
                    "Return date cannot be before departure date."
            );
        }

        if (trip.getMaxTravelers() <= 0) {
            throw new IllegalArgumentException(
                    "Maximum travelers must be greater than zero."
            );
        }

        if (trip.getTripStatus() != null
                && !trip.getTripStatus().isBlank()) {
            validateStatus(trip.getTripStatus());
        }
    }

    private void validateStatus(String status) {
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException(
                    "Trip status is required."
            );
        }

        boolean validStatus =
                status.equals("Upcoming")
                || status.equals("Active")
                || status.equals("Completed")
                || status.equals("Cancelled");

        if (!validStatus) {
            throw new IllegalArgumentException(
                    "Trip status must be Upcoming, Active, "
                    + "Completed, or Cancelled."
            );
        }
    }
    
    public boolean deleteTrip(int tripID) {
        if (tripID <= 0) {
            throw new IllegalArgumentException(
                    "A valid trip ID is required."
            );
        }

        return tripsDAO.deleteTrip(tripID);
    }
}
