/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Payments;
import model.Payments.methodType;
import model.Payments.paymentStatusType;
import utility.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author kalei
 */
public class PaymentsDAO {
        public List<Payments> getAllPayments() {
        List<Payments> payments = new ArrayList<>();

        String sql = """
                SELECT payment_id,
                       booking_id,
                       invoice_date,
                       base_price,
                       activity_fees,
                       tax_amount,
                       total_amount,
                       payment_date,
                       amount_paid,
                       payment_method,
                       payment_status
                FROM payments
                ORDER BY payment_id DESC
                """;

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {
                payments.add(mapPayment(resultSet));
            }

        } catch (SQLException exception) {
            throw new RuntimeException(
                    "Error retrieving payments: "
                    + exception.getMessage(),
                    exception
            );
        }

        return payments;
    }

    public Payments getPaymentByID(int paymentID) {
        String sql = """
                SELECT payment_id,
                       booking_id,
                       invoice_date,
                       base_price,
                       activity_fees,
                       tax_amount,
                       total_amount,
                       payment_date,
                       amount_paid,
                       payment_method,
                       payment_status
                FROM payments
                WHERE payment_id = ?
                """;

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, paymentID);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapPayment(resultSet);
                }
            }

        } catch (SQLException exception) {
            throw new RuntimeException(
                    "Error retrieving payment: "
                    + exception.getMessage(),
                    exception
            );
        }

        return null;
    }

    public Payments getPaymentByBookingID(int bookingID) {
        String sql = """
                SELECT payment_id,
                       booking_id,
                       invoice_date,
                       base_price,
                       activity_fees,
                       tax_amount,
                       total_amount,
                       payment_date,
                       amount_paid,
                       payment_method,
                       payment_status
                FROM payments
                WHERE booking_id = ?
                """;

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, bookingID);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapPayment(resultSet);
                }
            }

        } catch (SQLException exception) {
            throw new RuntimeException(
                    "Error retrieving payment by booking: "
                    + exception.getMessage(),
                    exception
            );
        }

        return null;
    }

    public boolean addPayment(Payments payment) {
        String sql = """
                INSERT INTO payments (
                    booking_id,
                    invoice_date,
                    base_price,
                    activity_fees,
                    tax_amount,
                    total_amount,
                    payment_date,
                    amount_paid,
                    payment_method,
                    payment_status
                )
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
            )
        ) {

            statement.setInt(
                    1,
                    payment.getBookingID()
            );

            setNullableDate(
                    statement,
                    2,
                    payment.getInvoiceDate()
            );

            statement.setFloat(
                    3,
                    payment.getBasePrice()
            );

            statement.setFloat(
                    4,
                    payment.getActivityFees()
            );

            statement.setFloat(
                    5,
                    payment.getTaxAmount()
            );

            statement.setFloat(
                    6,
                    payment.getTotalAmount()
            );

            setNullableDate(
                    statement,
                    7,
                    payment.getPaymentDate()
            );

            statement.setFloat(
                    8,
                    payment.getAmountPaid()
            );

            setNullablePaymentMethod(
                    statement,
                    9,
                    payment.getPaymentMethod()
            );

            paymentStatusType status =
                    payment.getPaymentStatus();

            if (status == null) {
                status = paymentStatusType.Pending;
            }

            statement.setString(
                    10,
                    status.name()
            );

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys =
                             statement.getGeneratedKeys()) {

                    if (generatedKeys.next()) {
                        payment.setPaymentID(
                                generatedKeys.getInt(1)
                        );
                    }
                }

                return true;
            }

        } catch (SQLException exception) {
            throw new RuntimeException(
                    "Error adding payment: "
                    + exception.getMessage(),
                    exception
            );
        }

        return false;
    }

    public boolean updatePayment(Payments payment) {
        String sql = """
                UPDATE payments
                SET booking_id = ?,
                    invoice_date = ?,
                    base_price = ?,
                    activity_fees = ?,
                    tax_amount = ?,
                    total_amount = ?,
                    payment_date = ?,
                    amount_paid = ?,
                    payment_method = ?,
                    payment_status = ?
                WHERE payment_id = ?
                """;

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(
                    1,
                    payment.getBookingID()
            );

            setNullableDate(
                    statement,
                    2,
                    payment.getInvoiceDate()
            );

            statement.setFloat(
                    3,
                    payment.getBasePrice()
            );

            statement.setFloat(
                    4,
                    payment.getActivityFees()
            );

            statement.setFloat(
                    5,
                    payment.getTaxAmount()
            );

            statement.setFloat(
                    6,
                    payment.getTotalAmount()
            );

            setNullableDate(
                    statement,
                    7,
                    payment.getPaymentDate()
            );

            statement.setFloat(
                    8,
                    payment.getAmountPaid()
            );

            setNullablePaymentMethod(
                    statement,
                    9,
                    payment.getPaymentMethod()
            );

            paymentStatusType status =
                    payment.getPaymentStatus();

            if (status == null) {
                status = paymentStatusType.Pending;
            }

            statement.setString(
                    10,
                    status.name()
            );

            statement.setInt(
                    11,
                    payment.getPaymentID()
            );

            return statement.executeUpdate() > 0;

        } catch (SQLException exception) {
            throw new RuntimeException(
                    "Error updating payment: "
                    + exception.getMessage(),
                    exception
            );
        }
    }

    public boolean updatePaymentStatus(
            int paymentID,
            paymentStatusType status) {

        String sql = """
                UPDATE payments
                SET payment_status = ?
                WHERE payment_id = ?
                """;

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    status.name()
            );

            statement.setInt(
                    2,
                    paymentID
            );

            return statement.executeUpdate() > 0;

        } catch (SQLException exception) {
            throw new RuntimeException(
                    "Error updating payment status: "
                    + exception.getMessage(),
                    exception
            );
        }
    }

    public boolean deletePayment(int paymentID) {
        String sql = """
                DELETE FROM payments
                WHERE payment_id = ?
                """;

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(
                    1,
                    paymentID
            );

            return statement.executeUpdate() > 0;

        } catch (SQLException exception) {
            throw new RuntimeException(
                    "Error deleting payment: "
                    + exception.getMessage(),
                    exception
            );
        }
    }

    private Payments mapPayment(ResultSet resultSet)
            throws SQLException {

        String paymentMethodValue =
                resultSet.getString("payment_method");

        methodType paymentMethod = null;

        if (paymentMethodValue != null) {
            paymentMethod =
                    convertDatabaseMethod(paymentMethodValue);
        }

        String paymentStatusValue =
                resultSet.getString("payment_status");

        paymentStatusType paymentStatus =
                paymentStatusType.Pending;

        if (paymentStatusValue != null) {
            paymentStatus =
                    paymentStatusType.valueOf(
                            paymentStatusValue
                    );
        }

        Payments payment = new Payments(
                resultSet.getInt("booking_id"),
                resultSet.getDate("invoice_date"),
                resultSet.getFloat("base_price"),
                resultSet.getFloat("activity_fees"),
                resultSet.getFloat("tax_amount"),
                resultSet.getFloat("total_amount"),
                resultSet.getDate("payment_date"),
                resultSet.getFloat("amount_paid"),
                paymentMethod,
                paymentStatus
        );

        payment.setPaymentID(
                resultSet.getInt("payment_id")
        );

        return payment;
    }

    private void setNullableDate(
            PreparedStatement statement,
            int parameterIndex,
            java.util.Date date)
            throws SQLException {

        if (date == null) {
            statement.setNull(
                    parameterIndex,
                    Types.DATE
            );
        } else {
            statement.setDate(
                    parameterIndex,
                    new java.sql.Date(
                            date.getTime()
                    )
            );
        }
    }

    private void setNullablePaymentMethod(
            PreparedStatement statement,
            int parameterIndex,
            methodType paymentMethod)
            throws SQLException {

        if (paymentMethod == null) {
            statement.setNull(
                    parameterIndex,
                    Types.VARCHAR
            );
        } else {
            statement.setString(
                    parameterIndex,
                    convertMethodToDatabase(paymentMethod)
            );
        }
    }

    private String convertMethodToDatabase(
            methodType paymentMethod) {

        return switch (paymentMethod) {
            case CREDIT -> "Credit Card";
            case DEBIT -> "Debit Card";
            case PAYPAL -> "PayPal";
        };
    }

    private methodType convertDatabaseMethod(
            String databaseValue) {

        return switch (databaseValue) {
            case "Credit Card" -> methodType.CREDIT;
            case "Debit Card" -> methodType.DEBIT;
            case "PayPal" -> methodType.PAYPAL;

            default -> throw new IllegalArgumentException(
                    "Unknown payment method: "
                    + databaseValue
            );
        };
    }
}
