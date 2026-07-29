/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.BookingsDAO;
import java.util.List;
import model.Bookings;
import model.Bookings.bookingStatusType;

/**
 *
 * @author kalei
 */
public class BookingsReworkedController {
     private final BookingsDAO bookingsDAO;

    public BookingsReworkedController() {
        bookingsDAO = new BookingsDAO();
    }

    public List<Bookings> getAllBookings() {
        return bookingsDAO.getAllBookings();
    }

    public List<Bookings> getBookingsByCustomer(int customerID) {
        if (customerID <= 0) {
            throw new IllegalArgumentException(
                    "Customer ID must be valid."
            );
        }

        return bookingsDAO.getBookingsByCustomer(customerID);
    }

    public List<Bookings> getBookingsByTrip(int tripID) {
        if (tripID <= 0) {
            throw new IllegalArgumentException(
                    "Trip ID must be valid."
            );
        }

        return bookingsDAO.getBookingsByTrip(tripID);
    }

    public Bookings getBookingByID(int bookingID) {
        if (bookingID <= 0) {
            throw new IllegalArgumentException(
                    "Booking ID must be valid."
            );
        }

        return bookingsDAO.getBookingByID(bookingID);
    }

    public boolean addBooking(Bookings booking) {
        validateBooking(booking);

        if (booking.getBookingStatus() == null) {
            booking.setBookingStatus(
                    bookingStatusType.Upcoming
            );
        }

        return bookingsDAO.addBooking(booking);
    }

    public boolean updateBooking(Bookings booking) {
        if (booking == null) {
            throw new IllegalArgumentException(
                    "Booking cannot be null."
            );
        }

        if (booking.getBookingID() <= 0) {
            throw new IllegalArgumentException(
                    "Booking ID must be valid."
            );
        }

        validateBooking(booking);

        return bookingsDAO.updateBooking(booking);
    }

    public boolean updateBookingStatus(
            int bookingID,
            bookingStatusType status
    ) {
        if (bookingID <= 0) {
            throw new IllegalArgumentException(
                    "Booking ID must be valid."
            );
        }

        if (status == null) {
            throw new IllegalArgumentException(
                    "Booking status is required."
            );
        }

        return bookingsDAO.updateBookingStatus(
                bookingID,
                status
        );
    }

    private void validateBooking(Bookings booking) {
        if (booking == null) {
            throw new IllegalArgumentException(
                    "Booking cannot be null."
            );
        }

        if (booking.getCustomerID() <= 0) {
            throw new IllegalArgumentException(
                    "Customer ID must be valid."
            );
        }

        if (booking.getTripID() <= 0) {
            throw new IllegalArgumentException(
                    "Trip ID must be valid."
            );
        }

        if (booking.getBookingDate() == null) {
            throw new IllegalArgumentException(
                    "Booking date is required."
            );
        }

        if (booking.getNumberOfTravelers() <= 0) {
            throw new IllegalArgumentException(
                    "Number of travelers must be at least 1."
            );
        }

        if (booking.getSpecialRequests() != null
                && booking.getSpecialRequests().length() > 255) {
            throw new IllegalArgumentException(
                    "Special requests cannot exceed 255 characters."
            );
        }

        if (booking.getBookingNotes() != null
                && booking.getBookingNotes().length() > 255) {
            throw new IllegalArgumentException(
                    "Booking notes cannot exceed 255 characters."
            );
        }
    }
}
