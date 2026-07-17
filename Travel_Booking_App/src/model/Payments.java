
package model;
import java.util.Date;

/**
 *
 * @author Mariah Malczewska
 */
public class Payments {
    private int paymentID;
    private int bookingID;
    private Date invoiceDate;
    private float basePrice;
    private float activityFees;
    private float taxAmount;
    private float totalAmount;
    private Date paymentDate;
    private float amountPaid;
    private methodType paymentMethod;
    private paymentStatusType paymentStatus;

    //CONSTRUCTORS

    public Payments() {
        this.bookingID = bookingID;
        this.basePrice = 0;
        this.activityFees = 0;
        this.taxAmount = 0;
        this.totalAmount = 0;
        this.amountPaid = 0;
    }

    public Payments(int bookingID, Date invoiceDate, float basePrice, float activityFees, float taxAmount, float totalAmount, Date paymentDate, float amountPaid, methodType paymentMethod, paymentStatusType paymentStatus) {
        this.bookingID = bookingID;
        this.invoiceDate = invoiceDate;
        this.basePrice = basePrice;
        this.activityFees = activityFees;
        this.taxAmount = taxAmount;
        this.totalAmount = totalAmount;
        this.paymentDate = paymentDate;
        this.amountPaid = amountPaid;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }
    
    //emum field options
    public static enum methodType { CREDIT, DEBIT, PAYPAL } //doesnt match database perfectly
    public static enum paymentStatusType { Pending, Paid, Refunded, Failed }
    
    //GETTERS
    /**
     * @return the paymentID
     */
    public int getPaymentID() {
        return paymentID;
    }

    /**
     * @return the bookingID
     */
    public int getBookingID() {
        return bookingID;
    }

    /**
     * @return the invoiceDate
     */
    public Date getInvoiceDate() {
        return invoiceDate;
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
     * @return the taxAmount
     */
    public float getTaxAmount() {
        return taxAmount;
    }

    /**
     * @return the totalAmount
     */
    public float getTotalAmount() {
        return totalAmount;
    }

    /**
     * @return the paymentDate
     */
    public Date getPaymentDate() {
        return paymentDate;
    }

    /**
     * @return the amountPaid
     */
    public float getAmountPaid() {
        return amountPaid;
    }

    /**
     * @return the paymentMethod
     */
    public methodType getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * @return the paymentStatus
     */
    public paymentStatusType getPaymentStatus() {
        return paymentStatus;
    }

    //SETTERS
    /**
     * @param bookingID the bookingID to set
     */
    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    /**
     * @param invoiceDate the invoiceDate to set
     */
    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
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
     * @param taxAmount the taxAmount to set
     */
    public void setTaxAmount(float taxAmount) {
        this.taxAmount = taxAmount;
    }

    /**
     * @param totalAmount the totalAmount to set
     */
    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * @param paymentDate the paymentDate to set
     */
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * @param amountPaid the amountPaid to set
     */
    public void setAmountPaid(float amountPaid) {
        this.amountPaid = amountPaid;
    }

    /**
     * @param paymentMethod the paymentMethod to set
     */
    public void setPaymentMethod(methodType paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * @param paymentStatus the paymentStatus to set
     */
    public void setPaymentStatus(paymentStatusType paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
