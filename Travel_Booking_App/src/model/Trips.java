
package model;
import java.util.Date;

/**
 *
 * @author Mariah Malczewska
 */
public class Trips {
    private int tripID;
    private int destinationID;
    private int assignedGuideEmployeeID;
    private String tripTitle;
    private Date departureDate;
    private Date returnDate;
    private int maxTravelers;
    private tripStatusType tripStatus;

    //CONSTRUCTORS

    public Trips(int destinationID, String tripTitle, Date departureDate, 
            Date returnDate, int maxTravelers) {
        this.destinationID = destinationID;
        this.tripTitle = tripTitle;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.maxTravelers = maxTravelers;
        this.tripStatus = tripStatusType.Upcoming;
    }

    public Trips(int destinationID, int assignedGuideEmployeeID, 
            String tripTitle, Date departureDate, Date returnDate, 
            int maxTravelers, tripStatusType tripStatus) {
        this.destinationID = destinationID;
        this.assignedGuideEmployeeID = assignedGuideEmployeeID;
        this.tripTitle = tripTitle;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.maxTravelers = maxTravelers;
        this.tripStatus = tripStatus;
    }
    
    //emum field options
    public static enum tripStatusType { Upcoming, Active, Completed, Cancelled }
    
    //GETTERS 
    /**
     * @return the tripID
     */
    public int getTripID() {
        return tripID;
    }

    /**
     * @return the destinationID
     */
    public int getDestinationID() {
        return destinationID;
    }

    /**
     * @return the assignedGuideEmployeeID
     */
    public int getAssignedGuideEmployeeID() {
        return assignedGuideEmployeeID;
    }

    /**
     * @return the tripTitle
     */
    public String getTripTitle() {
        return tripTitle;
    }

    /**
     * @return the departureDate
     */
    public Date getDepartureDate() {
        return departureDate;
    }

    /**
     * @return the returnDate
     */
    public Date getReturnDate() {
        return returnDate;
    }

    /**
     * @return the maxTravelers
     */
    public int getMaxTravelers() {
        return maxTravelers;
    }

    /**
     * @return the tripStatus
     */
    public tripStatusType getTripStatus() {
        return tripStatus;
    }

    //SETTERS
    /**
     * @param destinationID the destinationID to set
     */
    public void setDestinationID(int destinationID) {
        this.destinationID = destinationID;
    }

    /**
     * @param assignedGuideEmployeeID the assignedGuideEmployeeID to set
     */
    public void setAssignedGuideEmployeeID(int assignedGuideEmployeeID) {
        this.assignedGuideEmployeeID = assignedGuideEmployeeID;
    }

    /**
     * @param tripTitle the tripTitle to set
     */
    public void setTripTitle(String tripTitle) {
        this.tripTitle = tripTitle;
    }

    /**
     * @param departureDate the departureDate to set
     */
    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    /**
     * @param returnDate the returnDate to set
     */
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * @param maxTravelers the maxTravelers to set
     */
    public void setMaxTravelers(int maxTravelers) {
        this.maxTravelers = maxTravelers;
    }

    /**
     * @param tripStatus the tripStatus to set
     */
    public void setTripStatus(tripStatusType tripStatus) {
        this.tripStatus = tripStatus;
    }
}