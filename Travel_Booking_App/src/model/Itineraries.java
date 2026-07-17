/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

import java.time.Instant;
import java.util.Date;

/**
 *
 * @author Mariah Malczewska
 */
public class Itineraries {
    private int itineraryID;
    private int bookingID;
    private String itinerarySummary;
    private String hotelDetails;
    private String transportationDetails;
    private String activitySchedule;
    private String guideNotes;
    private activityStatusType activityStatus;
    private Date lastUpdated;   

    //CONSTRUCTORS
    public Itineraries(int bookingID) {
        this.bookingID = bookingID;
        this.activityStatus = activityStatusType.NotStarted;
        // gets current timestamp and converts it into correct date format
        this.lastUpdated = java.util.Date.from( Instant.now() );
    }

    public Itineraries(int bookingID, String itinerarySummary, 
            String hotelDetails, String transportationDetails, 
            String activitySchedule, String guideNotes, activityStatusType activityStatus, 
            Date lastUpdated) {
        this.bookingID = bookingID;
        this.itinerarySummary = itinerarySummary;
        this.hotelDetails = hotelDetails;
        this.transportationDetails = transportationDetails;
        this.activitySchedule = activitySchedule;
        this.guideNotes = guideNotes;
        this.activityStatus = activityStatus;
        // gets current timestamp and converts it into correct date format
        this.lastUpdated = java.util.Date.from( Instant.now() );
    }
    
    //emum field options
    public static enum activityStatusType { NotStarted, InProgress, Completed } // not exact database match    
    
    //GETTERS
    /**
     * @return the itineraryID
     */
    public int getItineraryID() {
        return itineraryID;
    }

    /**
     * @return the bookingID
     */
    public int getBookingID() {
        return bookingID;
    }

    /**
     * @return the itinerarySummary
     */
    public String getItinerarySummary() {
        return itinerarySummary;
    }

    /**
     * @return the hotelDetails
     */
    public String getHotelDetails() {
        return hotelDetails;
    }

    /**
     * @return the transportationDetails
     */
    public String getTransportationDetails() {
        return transportationDetails;
    }

    /**
     * @return the activitySchedule
     */
    public String getActivitySchedule() {
        return activitySchedule;
    }

    /**
     * @return the guideNotes
     */
    public String getGuideNotes() {
        return guideNotes;
    }

    /**
     * @return the activityStatus
     */
    public activityStatusType getActivityStatus() {
        return activityStatus;
    }

    /**
     * @return the lastUpdated
     */
    public Date getLastUpdated() {
        return lastUpdated;
    }

    //SETTERS
    /**
     * @param bookingID the itinerarySummary to set
     */
    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    /**
     * @param itinerarySummary the itinerarySummary to set
     */
    public void setItinerarySummary(String itinerarySummary) {
        this.itinerarySummary = itinerarySummary;
    }

    /**
     * @param hotelDetails the hotelDetails to set
     */
    public void setHotelDetails(String hotelDetails) {
        this.hotelDetails = hotelDetails;
    }

    /**
     * @param transportationDetails the transportationDetails to set
     */
    public void setTransportationDetails(String transportationDetails) {
        this.transportationDetails = transportationDetails;
    }

    /**
     * @param activitySchedule the activitySchedule to set
     */
    public void setActivitySchedule(String activitySchedule) {
        this.activitySchedule = activitySchedule;
    }

    /**
     * @param guideNotes the guideNotes to set
     */
    public void setGuideNotes(String guideNotes) {
        this.guideNotes = guideNotes;
    }

    /**
     * @param activityStatus the activityStatus to set
     */
    public void setActivityStatus(activityStatusType activityStatus) {
        this.activityStatus = activityStatus;
    }

    /**
     * @param activityStatus the activityStatus to set
     */
    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = java.util.Date.from( Instant.now() );
    }
}

