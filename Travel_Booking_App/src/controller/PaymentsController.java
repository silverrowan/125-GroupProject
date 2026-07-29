/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.PaymentsDAO;
import model.Payments;
import model.Payments.paymentStatusType;

import java.util.List;
/**
 *
 * @author kalei
 */
public class PaymentsController {
    private final PaymentsDAO paymentsDAO;

    public PaymentsController() {
        paymentsDAO = new PaymentsDAO();
    }

    public List<Payments> getAllPayments() {
        return paymentsDAO.getAllPayments();
    }

    public Payments getPaymentByID(int paymentID) {
        if (paymentID <= 0) {
            throw new IllegalArgumentException(
                    "Payment ID must be greater than zero."
            );
        }

        return paymentsDAO.getPaymentByID(paymentID);
    }

    public Payments getPaymentByBookingID(int bookingID) {
        if (bookingID <= 0) {
            throw new IllegalArgumentException(
                    "Booking ID must be greater than zero."
            );
        }

        return paymentsDAO.getPaymentByBookingID(
                bookingID
        );
    }

    public boolean addPayment(Payments payment) {
        validatePayment(payment);

        Payments existingPayment =
                paymentsDAO.getPaymentByBookingID(
                        payment.getBookingID()
                );

        if (existingPayment != null) {
            throw new IllegalArgumentException(
                    "This booking already has a payment record."
            );
        }

        return paymentsDAO.addPayment(payment);
    }

    public boolean updatePayment(Payments payment) {
        validatePayment(payment);

        if (payment.getPaymentID() <= 0) {
            throw new IllegalArgumentException(
                    "A valid payment ID is required."
            );
        }

        return paymentsDAO.updatePayment(payment);
    }

    public boolean updatePaymentStatus(
            int paymentID,
            paymentStatusType status) {

        if (paymentID <= 0) {
            throw new IllegalArgumentException(
                    "Payment ID must be greater than zero."
            );
        }

        if (status == null) {
            throw new IllegalArgumentException(
                    "Payment status is required."
            );
        }

        return paymentsDAO.updatePaymentStatus(
                paymentID,
                status
        );
    }

    public boolean deletePayment(int paymentID) {
        if (paymentID <= 0) {
            throw new IllegalArgumentException(
                    "Payment ID must be greater than zero."
            );
        }

        return paymentsDAO.deletePayment(paymentID);
    }

    private void validatePayment(Payments payment) {
        if (payment == null) {
            throw new IllegalArgumentException(
                    "Payment information is required."
            );
        }

        if (payment.getBookingID() <= 0) {
            throw new IllegalArgumentException(
                    "A valid booking is required."
            );
        }

        if (payment.getBasePrice() < 0) {
            throw new IllegalArgumentException(
                    "Base price cannot be negative."
            );
        }

        if (payment.getActivityFees() < 0) {
            throw new IllegalArgumentException(
                    "Activity fees cannot be negative."
            );
        }

        if (payment.getTaxAmount() < 0) {
            throw new IllegalArgumentException(
                    "Tax amount cannot be negative."
            );
        }

        if (payment.getTotalAmount() < 0) {
            throw new IllegalArgumentException(
                    "Total amount cannot be negative."
            );
        }

        if (payment.getAmountPaid() < 0) {
            throw new IllegalArgumentException(
                    "Amount paid cannot be negative."
            );
        }

        float expectedTotal =
                payment.getBasePrice()
                + payment.getActivityFees()
                + payment.getTaxAmount();

        if (Math.abs(
                payment.getTotalAmount() - expectedTotal
        ) > 0.01f) {

            throw new IllegalArgumentException(
                    "Total amount must equal the base price, "
                    + "activity fees, and tax amount."
            );
        }

        if (payment.getAmountPaid()
                > payment.getTotalAmount()) {

            throw new IllegalArgumentException(
                    "Amount paid cannot be greater "
                    + "than the total amount."
            );
        }
    }
}
