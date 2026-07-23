/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;
import controller.BookingsReworkedController;
import controller.TripsController;

import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import model.Bookings;
import model.Trips;

/**
 *
 * @author kalei
 */
public class AddBookingGUI{
    
    private final int destinationID;
    private final int customerID;
    private final int createdByUserID;

    private final String customerName;
    private final String country;

    private final TripsController tripsController;
    private final BookingsReworkedController bookingsController;

    /**
     * Keep this constructor so the NetBeans GUI designer can open the form.
     * Not used during the real booking workflow.
     */
    public AddBookingGUI() {
        this(0, 0, 0, "", "");
    }

    /**
     * Opens the Add Booking screen with the required booking information.
     *
     * @param destinationID selected destination ID
     * @param customerID customer table ID
     * @param createdByUserID logged-in user ID
     * @param customerName customer display name
     * @param country destination country
     */
    public AddBookingGUI(
            int destinationID,
            int customerID,
            int createdByUserID,
            String customerName,
            String country) {
        
        initComponents();

        this.destinationID = destinationID;
        this.customerID = customerID;
        this.createdByUserID = createdByUserID;
        this.customerName = customerName;
        this.country = country;

        this.tripsController = new TripsController();
        this.bookingsController =
                new BookingsReworkedController();

        configureForm();
        loadBookingInformation();
        loadTrips();

        setLocationRelativeTo(null);
    }
    
    //My Methods
    private void configureForm() {
        customerTxt.setEditable(false);
        countryTxt.setEditable(false);
        bookingDateTxt.setEditable(false);

        jTable1.setModel(
                new DefaultTableModel(
                        new Object[][] {},
                        new String[] {
                            "Trip ID",
                            "Trip Name",
                            "Departure",
                            "Return"
                        }
                ) {
                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {
                        return false;
                    }
                }
        );

        jTable1.setRowSelectionAllowed(true);
        jTable1.setColumnSelectionAllowed(false);

        jTable1.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );
    }
    
    private void loadBookingInformation() {
        customerTxt.setText(customerName);
        countryTxt.setText(country);

        bookingDateTxt.setText(
                new java.sql.Date(
                        System.currentTimeMillis()
                ).toString()
        );
    }
    
    private void loadTrips() {
        DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();

        tableModel.setRowCount(0);

        if (destinationID <= 0) {
            return;
        }

        try {
            List<Trips> trips =
                    tripsController.getTripsByDestination(
                            destinationID
                    );

            for (Trips trip : trips) {

                tableModel.addRow(new Object[] {
                    trip.getTripID(),
                    trip.getTripTitle(),
                    trip.getDepartureDate(),
                    trip.getReturnDate()
                });
            }

            if (trips.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "There are no upcoming trips available "
                                + "for this destination.",
                        "No Trips Available",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

        } catch (RuntimeException exception) {

            JOptionPane.showMessageDialog(
                    this,
                    exception.getMessage(),
                    "Unable to Load Trips",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    private int getSelectedTripID() {
        int selectedRow = jTable1.getSelectedRow();

        if (selectedRow == -1) {
            throw new IllegalArgumentException(
                    "Please select a trip from the table."
            );
        }

        Object tripIDValue =
                jTable1.getValueAt(selectedRow, 0);

        return Integer.parseInt(
                tripIDValue.toString()
        );
    }
    
    private int getNumberOfTravelers() {
        String travelersText = numTravelersTxt.getText().trim();

        if (travelersText.isEmpty()) {
            throw new IllegalArgumentException(
                    "Please enter the number of travelers."
            );
        }

        int numberOfTravelers;

        try {
            numberOfTravelers =
                    Integer.parseInt(travelersText);

        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(
                    "The number of travelers must be "
                            + "a whole number."
            );
        }

        if (numberOfTravelers <= 0) {
            throw new IllegalArgumentException(
                    "The number of travelers must be "
                            + "at least 1."
            );
        }

        return numberOfTravelers;
    }
    
    private Bookings createBookingFromForm() {

        int selectedTripID = getSelectedTripID();

        int numberOfTravelers = getNumberOfTravelers();

        Bookings booking = new Bookings(
                customerID,
                selectedTripID,
                new Date(),
                numberOfTravelers
        );

        booking.setCreatedByUserID(createdByUserID);

        booking.setBookingStatus("Upcoming");

        booking.setSpecialRequests(specialReqTxt.getText().trim());

        booking.setBookingNotes(bookingNotesTxt.getText().trim());

        return booking;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        customerLbl = new javax.swing.JLabel();
        countrylbl = new javax.swing.JLabel();
        bookingDateLbl = new javax.swing.JLabel();
        bookingDateTxt = new javax.swing.JTextField();
        addBookingTitle = new javax.swing.JLabel();
        customerTxt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        numTravelersTxt = new javax.swing.JTextField();
        specialReqLbl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        specialReqTxt = new javax.swing.JTextArea();
        bookingNotesLbl = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        bookingNotesTxt = new javax.swing.JTextArea();
        backBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        countryTxt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        customerLbl.setText("Customer:");

        countrylbl.setText("Country:");

        bookingDateLbl.setText("Booking Date: ");

        bookingDateTxt.addActionListener(this::bookingDateTxtActionPerformed);

        addBookingTitle.setText("Add Booking");

        customerTxt.addActionListener(this::customerTxtActionPerformed);

        jLabel6.setText("Number of Travelers: ");

        numTravelersTxt.addActionListener(this::numTravelersTxtActionPerformed);

        specialReqLbl.setText("Special Requests:");

        specialReqTxt.setColumns(20);
        specialReqTxt.setRows(5);
        jScrollPane1.setViewportView(specialReqTxt);

        bookingNotesLbl.setText("Booking Notes:");

        bookingNotesTxt.setColumns(20);
        bookingNotesTxt.setRows(5);
        jScrollPane2.setViewportView(bookingNotesTxt);

        backBtn.setText("Back");
        backBtn.addActionListener(this::backBtnActionPerformed);

        saveBtn.setText("Save");
        saveBtn.addActionListener(this::saveBtnActionPerformed);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Trip ID", "Country", "Departure", "Return"
            }
        ));
        jScrollPane3.setViewportView(jTable1);

        jLabel1.setText("Please Select a Trip with your Preferred Dates");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(specialReqLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(105, 105, 105)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(bookingNotesLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                            .addComponent(jLabel6)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(numTravelersTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                            .addComponent(bookingDateLbl)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(bookingDateTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(backBtn)
                                                    .addGap(451, 451, 451)
                                                    .addComponent(saveBtn))
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(countryTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addGap(35, 35, 35)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(countrylbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(customerLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(customerTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(21, 21, 21))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(295, 295, 295)
                        .addComponent(addBookingTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(addBookingTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bookingDateLbl, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(customerTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(customerLbl)
                        .addComponent(bookingDateTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(countrylbl)
                    .addComponent(jLabel6)
                    .addComponent(numTravelersTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(countryTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(specialReqLbl)
                    .addComponent(bookingNotesLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(backBtn)
                        .addContainerGap(42, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(saveBtn)
                        .addGap(46, 46, 46))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void customerTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_customerTxtActionPerformed

    private void numTravelersTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numTravelersTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numTravelersTxtActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // TODO add your handling code here:
        ProductDetailsGUI productDetailsGUI =
            new ProductDetailsGUI(destinationID);
        productDetailsGUI.setVisible(true);

        dispose();
    }//GEN-LAST:event_backBtnActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveBtnActionPerformed

    private void bookingDateTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookingDateTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bookingDateTxtActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addBookingTitle;
    private javax.swing.JButton backBtn;
    private javax.swing.JLabel bookingDateLbl;
    private javax.swing.JTextField bookingDateTxt;
    private javax.swing.JLabel bookingNotesLbl;
    private javax.swing.JTextArea bookingNotesTxt;
    private javax.swing.JTextField countryTxt;
    private javax.swing.JLabel countrylbl;
    private javax.swing.JLabel customerLbl;
    private javax.swing.JTextField customerTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField numTravelersTxt;
    private javax.swing.JButton saveBtn;
    private javax.swing.JLabel specialReqLbl;
    private javax.swing.JTextArea specialReqTxt;
    // End of variables declaration//GEN-END:variables
}
