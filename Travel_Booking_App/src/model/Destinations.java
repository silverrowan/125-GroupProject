package model;

/**
 *
 * @author Mariah Malczewska
 */
public class Destinations {
    private int DestinationID;
    private String DestinationName;
    private String countryRegion;
    private String notes;
    private String hotelName;
    private float hotel_rating;
    private String hotelStreetNumber;
    private String hotelStreetName;
    private String hotelCity;
    private String hotelProvinceRegion;
    private String hotelPostalCode;
    private String hotelCountry;
    private String flightInfo;
    private String busTrainInfo;
    private String transferDetails;
    private String includedActivities;
    private String optionalActivities;
    private int durationDays;
    private int durationNights;
    private float basePrice;
    private float activityFees;
    private float totalEstimatedCost;
    private String destinationStatus;
    
    private String fullAddress;

    //CONSTRUCTORS
    public Destinations(String DestinationName, String countryRegion, 
            String notes, String hotelName, float hotel_rating, 
            String hotelStreetNumber, String hotelStreetName, String hotelCity, 
            String hotelProvinceRegion, String hotelPostalCode, 
            String hotelCountry, String flightInfo, String busTrainInfo, 
            String transferDetails, String includedActivities, 
            String optionalActivities, int durationDays, int durationNights, 
            float basePrice, float activityFees, String destinationStatus) {
        this.DestinationName = DestinationName;
        this.countryRegion = countryRegion;
        this.notes = notes;
        this.hotelName = hotelName;
        this.hotel_rating = hotel_rating;
        this.hotelStreetNumber = hotelStreetNumber;
        this.hotelStreetName = hotelStreetName;
        this.hotelCity = hotelCity;
        this.hotelProvinceRegion = hotelProvinceRegion;
        this.hotelPostalCode = hotelPostalCode;
        this.hotelCountry = hotelCountry;
        this.flightInfo = flightInfo;
        this.busTrainInfo = busTrainInfo;
        this.transferDetails = transferDetails;
        this.includedActivities = includedActivities;
        this.optionalActivities = optionalActivities;
        this.durationDays = durationDays;
        this.durationNights = durationNights;
        this.basePrice = basePrice;
        this.activityFees = activityFees;
        this.totalEstimatedCost = basePrice + activityFees;
        this.destinationStatus = destinationStatus;
    }

    public Destinations(String DestinationName, String countryRegion, 
            int durationDays, int durationNights, float basePrice, 
            String destinationStatus) {
        this.DestinationName = DestinationName;
        this.countryRegion = countryRegion;
        this.durationDays = durationDays;
        this.durationNights = durationNights;
        this.basePrice = basePrice;
        this.activityFees = 0;
        this.totalEstimatedCost = basePrice + activityFees;
        this.destinationStatus = destinationStatus;
    }

    //GETTERS     
    /**
     * @return the DestinationID
     */
    public int getDestinationID() {
        return DestinationID;
    }

    /**
     * @return the DestinationName
     */
    public String getDestinationName() {
        return DestinationName;
    }

    /**
     * @return the countryRegion
     */
    public String getCountryRegion() {
        return countryRegion;
    }

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @return the hotelName
     */
    public String getHotelName() {
        return hotelName;
    }

    /**
     * @return the hotel_rating
     */
    public float getHotel_rating() {
        return hotel_rating;
    }

    /**
     * @return the hotelStreetNumber
     */
    public String getHotelStreetNumber() {
        return hotelStreetNumber;
    }

    /**
     * @return the hotelStreetName
     */
    public String getHotelStreetName() {
        return hotelStreetName;
    }

    /**
     * @return the hotelCity
     */
    public String getHotelCity() {
        return hotelCity;
    }

    /**
     * @return the hotelProvinceRegion
     */
    public String getHotelProvinceRegion() {
        return hotelProvinceRegion;
    }

    /**
     * @return the hotelPostalCode
     */
    public String getHotelPostalCode() {
        return hotelPostalCode;
    }

    /**
     * @return the hotelCountry
     */
    public String getHotelCountry() {
        return hotelCountry;
    }

    /**
     * @return the flightInfo
     */
    public String getFlightInfo() {
        return flightInfo;
    }

    /**
     * @return the busTrainInfo
     */
    public String getBusTrainInfo() {
        return busTrainInfo;
    }

    /**
     * @return the transferDetails
     */
    public String getTransferDetails() {
        return transferDetails;
    }

    /**
     * @return the includedActivities
     */
    public String getIncludedActivities() {
        return includedActivities;
    }

    /**
     * @return the optionalActivities
     */
    public String getOptionalActivities() {
        return optionalActivities;
    }

    /**
     * @return the durationDays
     */
    public int getDurationDays() {
        return durationDays;
    }

    /**
     * @return the durationNights
     */
    public int getDurationNights() {
        return durationNights;
    }

    /**
     * @return the basePrice
     */
    public float getBasePrice() {
        return basePrice;
    }

    /**
     * @return the activityFees
     */
    public float getActivityFees() {
        return activityFees;
    }

    /**
     * @return the totalEstimatedCost
     */
    public float getTotalEstimatedCost() {
        return totalEstimatedCost;
    }

    /**
     * @return the destinationStatus
     */
    public String getDestinationStatus() {
        return destinationStatus;
    }
    
    /**
     * @return the fullAddress (of hotel at destination)
     */
        public String getFullAddress() {
        String addressLine1 = getHotelStreetNumber() + " " + getHotelStreetName();
        String addressLine2 = getHotelCity()+ " " + getHotelProvinceRegion() + " " + getHotelPostalCode();
        String addressLine3 = getHotelCountry();
        return addressLine1 + "\n" + addressLine2 + "\n" + addressLine3;
    }

    //SETTERS
    /**
     * @param DestinationID the DestinationID to set
     */
    public void setDestinationID(int DestinationID) {
        this.DestinationID = DestinationID;
    }

    /**
     * @param DestinationName the DestinationName to set
     */
    public void setDestinationName(String DestinationName) {
        this.DestinationName = DestinationName;
    }

    /**
     * @param countryRegion the countryRegion to set
     */
    public void setCountryRegion(String countryRegion) {
        this.countryRegion = countryRegion;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * @param hotelName the hotelName to set
     */
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    /**
     * @param hotel_rating the hotel_rating to set
     */
    public void setHotel_rating(float hotel_rating) {
        this.hotel_rating = hotel_rating;
    }

    /**
     * @param hotelStreetNumber the hotelStreetNumber to set
     */
    public void setHotelStreetNumber(String hotelStreetNumber) {
        this.hotelStreetNumber = hotelStreetNumber;
    }

    /**
     * @param hotelStreetName the hotelStreetName to set
     */
    public void setHotelStreetName(String hotelStreetName) {
        this.hotelStreetName = hotelStreetName;
    }

    /**
     * @param hotelCity the hotelCity to set
     */
    public void setHotelCity(String hotelCity) {
        this.hotelCity = hotelCity;
    }

    /**
     * @param hotelProvinceRegion the hotelProvinceRegion to set
     */
    public void setHotelProvinceRegion(String hotelProvinceRegion) {
        this.hotelProvinceRegion = hotelProvinceRegion;
    }

    /**
     * @param hotelPostalCode the hotelPostalCode to set
     */
    public void setHotelPostalCode(String hotelPostalCode) {
        this.hotelPostalCode = hotelPostalCode;
    }

    /**
     * @param hotelCountry the hotelCountry to set
     */
    public void setHotelCountry(String hotelCountry) {
        this.hotelCountry = hotelCountry;
    }

    /**
     * @param flightInfo the flightInfo to set
     */
    public void setFlightInfo(String flightInfo) {
        this.flightInfo = flightInfo;
    }

    /**
     * @param busTrainInfo the busTrainInfo to set
     */
    public void setBusTrainInfo(String busTrainInfo) {
        this.busTrainInfo = busTrainInfo;
    }

    /**
     * @param transferDetails the transferDetails to set
     */
    public void setTransferDetails(String transferDetails) {
        this.transferDetails = transferDetails;
    }

    /**
     * @param includedActivities the includedActivities to set
     */
    public void setIncludedActivities(String includedActivities) {
        this.includedActivities = includedActivities;
    }

    /**
     * @param optionalActivities the optionalActivities to set
     */
    public void setOptionalActivities(String optionalActivities) {
        this.optionalActivities = optionalActivities;
    }

    /**
     * @param durationDays the durationDays to set
     */
    public void setDurationDays(int durationDays) {
        this.durationDays = durationDays;
    }

    /**
     * @param durationNights the durationNights to set
     */
    public void setDurationNights(int durationNights) {
        this.durationNights = durationNights;
    }

    /**
     * @param basePrice the basePrice to set
     */
    public void setBasePrice(float basePrice) {
        this.basePrice = basePrice;
    }

    /**
     * @param activityFees the activityFees to set
     */
    public void setActivityFees(float activityFees) {
        this.activityFees = activityFees;
    }

    /**
     * @param totalEstimatedCost the totalEstimatedCost to set
     */
    public void setTotalEstimatedCost(float totalEstimatedCost) {
        this.totalEstimatedCost = totalEstimatedCost;
    }

    /**
     * @param destinationStatus the destinationStatus to set
     */
    public void setDestinationStatus(String destinationStatus) {
        this.destinationStatus = destinationStatus;
    }
    
    /**
     * @param fullAddress the fullAddress to set
     */
    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }
}
