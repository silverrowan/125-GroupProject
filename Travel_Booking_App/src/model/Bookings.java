package model;

import java.util.Date;

/**
 *
 * @author Mariah Malczewska
 */
public class Bookings {
    private int bookingID;
    private int customerID;
    private int tripID;
    private int createdByUserID;
    private Date bookingDate;
    private int numberOfTravelers;
    private bookingStatusType bookingStatus;
    private String specialRequests;
    private String bookingNotes;

    //CONSTRUCTORS

    public Bookings(int customerID, int tripID, Date bookingDate, int numberOfTravelers) {
        this.customerID = customerID;
        this.tripID = tripID;
        this.bookingDate = bookingDate;
        this.numberOfTravelers = numberOfTravelers;
        this.bookingStatus = bookingStatusType.Upcoming;
    }

    public Bookings(int customerID, int tripID, int createdByUserID, 
            Date bookingDate, int numberOfTravelers, bookingStatusType bookingStatus, 
            String specialRequests, String bookingNotes) {
        this.customerID = customerID;
        this.tripID = tripID;
        this.createdByUserID = createdByUserID;
        this.bookingDate = bookingDate;
        this.numberOfTravelers = numberOfTravelers;
        this.bookingStatus = bookingStatus;
        this.specialRequests = specialRequests;
        this.bookingNotes = bookingNotes;
    }  
    
    //emum field options
    public static enum bookingStatusType { Upcoming, Completed, Cancelled }
    
    //GETTERS
    /**
     * @return the bookingID
     */
    public int getBookingID() {
        return bookingID;
    }

    /**
     * @return the customerID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * @return the tripID
     */
    public int getTripID() {
        return tripID;
    }

    /**
     * @return the createdByUserID
     */
    public int getCreatedByUserID() {
        return createdByUserID;
    }

    /**
     * @return the bookingDate
     */
    public Date getBookingDate() {
        return bookingDate;
    }

    /**
     * @return the numberOfTravelers
     */
    public int getNumberOfTravelers() {
        return numberOfTravelers;
    }

    /**
     * @return the bookingStatus
     */
    public bookingStatusType getBookingStatus() {
        return bookingStatus;
    }

    /**
     * @return the specialRequests
     */
    public String getSpecialRequests() {
        return specialRequests;
    }

    /**
     * @return the bookingNotes
     */
    public String getBookingNotes() {
        return bookingNotes;
    }

    //SETTERS
    
    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }
    
    /**
     * @param customerID the customerID to set
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * @param tripID the tripID to set
     */
    public void setTripID(int tripID) {
        this.tripID = tripID;
    }

    /**
     * @param createdByUserID the createdByUserID to set
     */
    public void setCreatedByUserID(int createdByUserID) {
        this.createdByUserID = createdByUserID;
    }

    /**
     * @param bookingDate the bookingDate to set
     */
    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    /**
     * @param numberOfTravelers the numberOfTravelers to set
     */
    public void setNumberOfTravelers(int numberOfTravelers) {
        this.numberOfTravelers = numberOfTravelers;
    }

    /**
     * @param bookingStatus the bookingStatus to set
     */
    public void setBookingStatus(bookingStatusType bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    /**
     * @param specialRequests the specialRequests to set
     */
    public void setSpecialRequests(String specialRequests) {
        this.specialRequests = specialRequests;
    }

    /**
     * @param bookingNotes the bookingNotes to set
     */
    public void setBookingNotes(String bookingNotes) {
        this.bookingNotes = bookingNotes;
    }
}
