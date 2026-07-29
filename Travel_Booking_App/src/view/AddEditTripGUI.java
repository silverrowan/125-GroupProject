/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;
import controller.TripsController;
import model.Trips;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
/**
 *
 * @author kalei
 */
public class AddEditTripGUI extends javax.swing.JFrame {
    
    private TripsController tripsController;
    private Integer editingTripID;

    private final SimpleDateFormat dateFormat =
            new SimpleDateFormat("yyyy-MM-dd");
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AddEditTripGUI.class.getName());

    /**
     * Creates new form AddTravelPackageGUI
     */
    public AddEditTripGUI() {
        initComponents();
        tripsController = new TripsController();
        editingTripID = null;

        configureForm();
        configureAddMode();
    }
    
    /**
    * Opens the form in Edit mode.
     * @param tripID
    */
   public AddEditTripGUI(int tripID) {
       initComponents();

       tripsController = new TripsController();
       editingTripID = tripID;

       configureForm();
       configureEditMode();
       loadTrip();
   }

   //My Methods
   private void configureForm() {
        dateFormat.setLenient(false);

        departureTxt.setToolTipText("Enter date as yyyy-MM-dd");
        returnTxt.setToolTipText("Enter date as yyyy-MM-dd");

        /*
         * Temporary combo-box values.
         *
         * The number at the beginning must be the actual database ID.
         * Replace these examples with IDs that exist in your database.
         */
        destinationCb.removeAllItems();
        destinationCb.addItem("1 - Destination 1");
        destinationCb.addItem("2 - Destination 2");

        tourguideCB.removeAllItems();
        tourguideCB.addItem("0 - No guide assigned");
        tourguideCB.addItem("1 - Tour Guide 1");
        tourguideCB.addItem("2 - Tour Guide 2");
    }
    
    private void configureAddMode() {
        setTitle("Add Trip");
        addTripLbl.setText("Add Trip");

        statusTxt.setText("Upcoming");
    }


    private void configureEditMode() {
        setTitle("Edit Trip");
        addTripLbl.setText("Edit Trip");
    }
    
    private int getSelectedID(
        javax.swing.JComboBox<String> comboBox,
        String fieldName) {

        Object selectedItem = comboBox.getSelectedItem();

        if (selectedItem == null) {
            throw new IllegalArgumentException(
                    "Please select a " + fieldName + "."
            );
        }

        String selectedText = selectedItem.toString().trim();

        int separatorPosition = selectedText.indexOf(" - ");

        String idText;

        if (separatorPosition >= 0) {
            idText = selectedText.substring(
                    0,
                    separatorPosition
            ).trim();
        } else {
            idText = selectedText;
        }

        try {
            return Integer.parseInt(idText);

        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(
                    "The selected " + fieldName
                            + " does not contain a valid ID."
            );
        }
    }
    
    private Date parseDate(
        String dateText,
        String fieldName) {

        if (dateText == null || dateText.isBlank()) {
            throw new IllegalArgumentException(
                    fieldName + " is required."
            );
        }

        try {
            return dateFormat.parse(dateText.trim());

        } catch (ParseException exception) {
            throw new IllegalArgumentException(
                    fieldName
                            + " must use the format yyyy-MM-dd."
            );
        }
    }
    
    private Trips createTripFromForm() {

        String tripTitle = tripnameTxt.getText().trim();

        if (tripTitle.isBlank()) {
            throw new IllegalArgumentException(
                    "Trip name is required."
            );
        }

        int destinationID =
                getSelectedID(
                        destinationCb,
                        "destination"
                );

        int guideID =
                getSelectedID(
                        tourguideCB,
                        "tour guide"
                );

        Date departureDate =
                parseDate(
                        departureTxt.getText(),
                        "Departure date"
                );

        Date returnDate =
                parseDate(
                        returnTxt.getText(),
                        "Return date"
                );

        int maxTravelers;

        try {
            maxTravelers = Integer.parseInt(
                    maxTravelerTxt.getText().trim()
            );

        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(
                    "Maximum travelers must be a whole number."
            );
        }

        if (maxTravelers <= 0) {
            throw new IllegalArgumentException(
                    "Maximum travelers must be greater than zero."
            );
        }

        String status = statusTxt.getText().trim();

        if (status.isBlank()) {
            status = "Upcoming";
        }

        Trips trip = new Trips(
                destinationID,
                guideID,
                tripTitle,
                departureDate,
                returnDate,
                maxTravelers,
                status
        );

        if (editingTripID != null) {
            trip.setTripID(editingTripID);
        }

        return trip;
    }
    
    private void loadTrip() {
        try {
            Trips trip =
                    tripsController.getTripByID(editingTripID);

            if (trip == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "The selected trip could not be found.",
                        "Trip Not Found",
                        JOptionPane.ERROR_MESSAGE
                );

                openViewAllTrips();
                return;
            }

            tripnameTxt.setText(
                    trip.getTripTitle()
            );

            departureTxt.setText(
                    dateFormat.format(
                            trip.getDepartureDate()
                    )
            );

            returnTxt.setText(
                    dateFormat.format(
                            trip.getReturnDate()
                    )
            );

            maxTravelerTxt.setText(
                    String.valueOf(
                            trip.getMaxTravelers()
                    )
            );

            statusTxt.setText(
                    trip.getTripStatus()
            );

            selectComboItemByID(
                    destinationCb,
                    trip.getDestinationID()
            );

            selectComboItemByID(
                    tourguideCB,
                    trip.getAssignedGuideEmployeeID()
            );

        } catch (RuntimeException exception) {
            JOptionPane.showMessageDialog(
                    this,
                    "The trip could not be loaded.\n"
                            + exception.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

            openViewAllTrips();
        }
    }
    
    private void selectComboItemByID(
        javax.swing.JComboBox<String> comboBox,
        int wantedID) {

        for (int index = 0;
                index < comboBox.getItemCount();
                index++) {

            String item = comboBox.getItemAt(index);

            int separatorPosition = item.indexOf(" - ");

            String idText;

            if (separatorPosition >= 0) {
                idText = item.substring(
                        0,
                        separatorPosition
                ).trim();
            } else {
                idText = item.trim();
            }

            try {
                int itemID = Integer.parseInt(idText);

                if (itemID == wantedID) {
                    comboBox.setSelectedIndex(index);
                    return;
                }

            } catch (NumberFormatException exception) {
                // Ignore invalid combo-box items.
            }
        }
    }
    
    private void openViewAllTrips() {
        ViewAllTripsGUI tripsGUI = new ViewAllTripsGUI();

        tripsGUI.setVisible(true);
        this.dispose();
    }
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tripnameTxt = new javax.swing.JTextField();
        destinationCb = new javax.swing.JComboBox<>();
        tripnameLbl = new javax.swing.JLabel();
        destinationLbl = new javax.swing.JLabel();
        tourguideCB = new javax.swing.JComboBox<>();
        tourguideLbl = new javax.swing.JLabel();
        departureTxt = new javax.swing.JTextField();
        maxTravelerTxt = new javax.swing.JTextField();
        returnTxt = new javax.swing.JTextField();
        statusTxt = new javax.swing.JTextField();
        backBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();
        departureLbl = new javax.swing.JLabel();
        maxTravelerLbl = new javax.swing.JLabel();
        returnLbl = new javax.swing.JLabel();
        statusLbl = new javax.swing.JLabel();
        addTripLbl = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Add Trip");

        tripnameTxt.addActionListener(this::tripnameTxtActionPerformed);

        destinationCb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tripnameLbl.setText("Trip Name:");

        destinationLbl.setText("Destination/Package:");

        tourguideCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tourguideLbl.setText("Tour Guide:");

        departureTxt.addActionListener(this::departureTxtActionPerformed);

        backBtn.setText("Back");
        backBtn.addActionListener(this::backBtnActionPerformed);

        saveBtn.setText("Save");
        saveBtn.addActionListener(this::saveBtnActionPerformed);

        departureLbl.setText("Departure:");

        maxTravelerLbl.setText("Max Travelers:");

        returnLbl.setText("Return:");

        statusLbl.setText("Status:");

        addTripLbl.setText("Add / Update Trip");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addComponent(addTripLbl)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(39, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(departureLbl)
                                            .addComponent(tourguideLbl)
                                            .addComponent(destinationLbl)
                                            .addComponent(tripnameLbl)
                                            .addComponent(maxTravelerLbl)
                                            .addComponent(statusLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(returnLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(70, 70, 70)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tourguideCB, 0, 167, Short.MAX_VALUE)
                                    .addComponent(destinationCb, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tripnameTxt, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(departureTxt)
                                    .addComponent(maxTravelerTxt)
                                    .addComponent(statusTxt)
                                    .addComponent(returnTxt)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(backBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(saveBtn)))
                .addGap(52, 52, 52))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(addTripLbl)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tripnameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tripnameLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(destinationCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(destinationLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tourguideCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tourguideLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(departureTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(departureLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(returnTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(returnLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maxTravelerTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maxTravelerLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statusLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backBtn)
                    .addComponent(saveBtn))
                .addGap(36, 36, 36))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    //Netbeans Actions Code
    private void tripnameTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tripnameTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tripnameTxtActionPerformed

    private void departureTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_departureTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_departureTxtActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        // TODO add your handling code here:
        
        try {
            Trips trip = createTripFromForm();

            boolean saved;

            if (editingTripID == null) {
                saved = tripsController.addTrip(trip);
            } else {
                saved = tripsController.updateTrip(trip);
            }

            if (!saved) {
                JOptionPane.showMessageDialog(
                        this,
                        "The trip could not be saved.",
                        "Save Failed",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            String successMessage;

            if (editingTripID == null) {
                successMessage = "Trip added successfully.";
            } else {
                successMessage = "Trip updated successfully.";
            }

            JOptionPane.showMessageDialog(
                    this,
                    successMessage
            );

            openViewAllTrips();

        } catch (IllegalArgumentException exception) {
            JOptionPane.showMessageDialog(
                    this,
                    exception.getMessage(),
                    "Invalid Trip",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (RuntimeException exception) {

            JOptionPane.showMessageDialog(
                    this,
                    "The trip could not be saved.\n"
                            + exception.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_saveBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // TODO add your handling code here:
        openViewAllTrips();
    }//GEN-LAST:event_backBtnActionPerformed
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new AddEditTripGUI().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addTripLbl;
    private javax.swing.JButton backBtn;
    private javax.swing.JLabel departureLbl;
    private javax.swing.JTextField departureTxt;
    private javax.swing.JComboBox<String> destinationCb;
    private javax.swing.JLabel destinationLbl;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel maxTravelerLbl;
    private javax.swing.JTextField maxTravelerTxt;
    private javax.swing.JLabel returnLbl;
    private javax.swing.JTextField returnTxt;
    private javax.swing.JButton saveBtn;
    private javax.swing.JLabel statusLbl;
    private javax.swing.JTextField statusTxt;
    private javax.swing.JComboBox<String> tourguideCB;
    private javax.swing.JLabel tourguideLbl;
    private javax.swing.JLabel tripnameLbl;
    private javax.swing.JTextField tripnameTxt;
    // End of variables declaration//GEN-END:variables

}
