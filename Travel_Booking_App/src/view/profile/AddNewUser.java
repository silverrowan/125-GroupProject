/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.profile;

import java.awt.event.ActionListener;
import javax.accessibility.AccessibleContext;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;

/**
 *
 * @author c0350261
 */
public class AddNewUser extends JFrame {

    /**
     * Creates new form ViewCustomerGUI
     */
    public AddNewUser() {
        initComponents();
    }

    
    public JButton getBtnCancel() {
        return btnCancel;
    }

    public void setBtnCancel(JButton btnCancel) {
        this.btnCancel = btnCancel;
    }

    
    public JButton getBtnSave() {
        return btnSave;
    }

    public void setBtnSave(JButton btnSave) {
        this.btnSave = btnSave;
    }

    
    public JTextField getInputCity() {
        return inputCity;
    }

    public void setInputCity(JTextField inputCity) {
        this.inputCity = inputCity;
    }

    
    public JTextField getInputCountry() {
        return inputCountry;
    }

    public void setInputCountry(JTextField inputCountry) {
        this.inputCountry = inputCountry;
    }

    
    public JTextField getInputEmail() {
        return inputEmail;
    }

    public void setInputEmail(JTextField inputEmail) {
        this.inputEmail = inputEmail;
    }

    
    public JTextField getInputFirstName() {
        return inputFirstName;
    }

    public void setInputFirstName(JTextField inputFirstName) {
        this.inputFirstName = inputFirstName;
    }

    
    public JTextField getInputLastName() {
        return inputLastName;
    }

    public void setInputLastName(JTextField inputLastName) {
        this.inputLastName = inputLastName;
    }

    
    public JPasswordField getInputPassword() {
        return inputPassword;
    }

    public void setInputPassword(JPasswordField inputPassword) {
        this.inputPassword = inputPassword;
    }

    
    public JTextField getInputPhone() {
        return inputPhone;
    }

    public void setInputPhone(JTextField inputPhone) {
        this.inputPhone = inputPhone;
    }

    
    public JTextField getInputPost() {
        return inputPost;
    }

    public void setInputPost(JTextField inputPost) {
        this.inputPost = inputPost;
    }

    
    public JTextField getInputProvince() {
        return inputProvince;
    }

    public void setInputProvince(JTextField inputProvince) {
        this.inputProvince = inputProvince;
    }

    
    public JTextField getInputStreet() {
        return inputStreet;
    }

    public void setInputStreet(JTextField inputStreet) {
        this.inputStreet = inputStreet;
    }

    
    public JTextField getInputStreetNumber() {
        return inputStreetNumber;
    }

    public void setInputStreetNumber(JTextField inputStreetNumber) {
        this.inputStreetNumber = inputStreetNumber;
    }

    
    public JTextField getInputUsername() {
        return inputUsername;
    }

    public void setInputUsername(JTextField inputUsername) {
        this.inputUsername = inputUsername;
    }

    public JPanel getPnlAccount() {
        return pnlAccount;
    }

    public void setPnlAccount(JPanel pnlAccount) {
        this.pnlAccount = pnlAccount;
    }

    public JPanel getPnlAddress() {
        return pnlAddress;
    }

    public void setPnlAddress(JPanel pnlAddress) {
        this.pnlAddress = pnlAddress;
    }

    public JPanel getPnlCountry() {
        return pnlCountry;
    }

    public void setPnlCountry(JPanel pnlCountry) {
        this.pnlCountry = pnlCountry;
    }

    public JPanel getPnlPersInfo() {
        return pnlPersInfo;
    }

    public void setPnlPersInfo(JPanel pnlPersInfo) {
        this.pnlPersInfo = pnlPersInfo;
    }

    public JPanel getPnlRegion() {
        return pnlRegion;
    }

    public void setPnlRegion(JPanel pnlRegion) {
        this.pnlRegion = pnlRegion;
    }

    public JPanel getPnlStreet() {
        return pnlStreet;
    }

    public void setPnlStreet(JPanel pnlStreet) {
        this.pnlStreet = pnlStreet;
    }

    
    public JCheckBox getRadioStatus() {
        return radioStatus;
    }

    public void setRadioStatus(JCheckBox radioStatus) {
        this.radioStatus = radioStatus;
    }

    
    public JComboBox<String> getSelectionRole() {
        return selectionRole;
    }

    public void setSelectionRole(JComboBox<String> selectionRole) {
        this.selectionRole = selectionRole;
    }

//    public JRootPane getRootPane() {
//        return rootPane;
//    }
//
//    public void setRootPane(JRootPane rootPane) {
//        this.rootPane = rootPane;
//    }
//
//    public boolean isRootPaneCheckingEnabled() {
//        return rootPaneCheckingEnabled;
//    }
//
//    public void setRootPaneCheckingEnabled(boolean rootPaneCheckingEnabled) {
//        this.rootPaneCheckingEnabled = rootPaneCheckingEnabled;
//    }
//
//    public AccessibleContext getAccessibleContext() {
//        return accessibleContext;
//    }

//    public void setAccessibleContext(AccessibleContext accessibleContext) {
//        this.accessibleContext = accessibleContext;
//    }
    
    
    
    // listeners
    
    public void addCancelBtnListener(ActionListener cancelListener) {
        btnCancel.addActionListener(cancelListener);
    }
    
    
    public void addSaveBtnListener(ActionListener saveListener) {
        btnSave.addActionListener(saveListener);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblDestinationCountry = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        pnlAccount = new javax.swing.JPanel();
        lblDestinationName = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        inputUsername = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        selectionRole = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        radioStatus = new javax.swing.JCheckBox();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        inputPassword = new javax.swing.JPasswordField();
        pnlPersInfo = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        inputFirstName = new javax.swing.JTextField();
        inputEmail = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        inputLastName = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        inputPhone = new javax.swing.JTextField();
        lblDestinationName4 = new javax.swing.JLabel();
        pnlAddress = new javax.swing.JPanel();
        pnlStreet = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        inputStreetNumber = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        inputStreet = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        pnlRegion = new javax.swing.JPanel();
        inputCity = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        inputProvince = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        inputPost = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        pnlCountry = new javax.swing.JPanel();
        inputCountry = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        lblDestinationName2 = new javax.swing.JLabel();
        lblDestinationName1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();

        lblDestinationCountry.setFont(new java.awt.Font("Noto Sans", 2, 12)); // NOI18N
        lblDestinationCountry.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDestinationCountry.setText("country/region");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblDestinationName.setFont(new java.awt.Font("Noto Sans", 3, 14)); // NOI18N
        lblDestinationName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDestinationName.setText("Account");
        lblDestinationName.setToolTipText("");

        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("*");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("lowercase only");

        inputUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputUsernameActionPerformed(evt);
            }
        });

        jLabel3.setText("Username");

        selectionRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Customer", "Travel Agent", "Tour guide", "Admin" }));

        jLabel5.setText("Role");

        radioStatus.setSelected(true);
        radioStatus.setText("Active");
        radioStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioStatusActionPerformed(evt);
            }
        });

        jLabel30.setText("Password");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(102, 102, 102));
        jLabel31.setText("must be at least 8 characters");

        javax.swing.GroupLayout pnlAccountLayout = new javax.swing.GroupLayout(pnlAccount);
        pnlAccount.setLayout(pnlAccountLayout);
        pnlAccountLayout.setHorizontalGroup(
            pnlAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccountLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inputPassword)
                    .addComponent(inputUsername)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAccountLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(selectionRole, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(radioStatus))
                    .addGroup(pnlAccountLayout.createSequentialGroup()
                        .addGroup(pnlAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlAccountLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1))
                            .addComponent(jLabel4)
                            .addComponent(lblDestinationName, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31)
                            .addComponent(jLabel30))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlAccountLayout.setVerticalGroup(
            pnlAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccountLayout.createSequentialGroup()
                .addComponent(lblDestinationName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(pnlAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(selectionRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(radioStatus))
                .addContainerGap())
        );

        jLabel7.setText("First name");

        jLabel12.setText("Email");

        inputFirstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputFirstNameActionPerformed(evt);
            }
        });

        inputEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputEmailActionPerformed(evt);
            }
        });

        jLabel16.setForeground(new java.awt.Color(255, 0, 0));
        jLabel16.setText("*");

        jLabel14.setText("Phone");

        inputLastName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputLastNameActionPerformed(evt);
            }
        });

        jLabel15.setForeground(new java.awt.Color(255, 0, 0));
        jLabel15.setText("*");

        jLabel10.setText("Last name");

        jLabel17.setForeground(new java.awt.Color(255, 0, 0));
        jLabel17.setText("*");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("(999) 888-7777");

        inputPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputPhoneActionPerformed(evt);
            }
        });

        lblDestinationName4.setFont(new java.awt.Font("Noto Sans", 3, 14)); // NOI18N
        lblDestinationName4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDestinationName4.setText("Personal Information");
        lblDestinationName4.setToolTipText("");

        javax.swing.GroupLayout pnlPersInfoLayout = new javax.swing.GroupLayout(pnlPersInfo);
        pnlPersInfo.setLayout(pnlPersInfoLayout);
        pnlPersInfoLayout.setHorizontalGroup(
            pnlPersInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPersInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPersInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPersInfoLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15))
                    .addComponent(inputLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(inputFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlPersInfoLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlPersInfoLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(inputPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDestinationName4, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnlPersInfoLayout.setVerticalGroup(
            pnlPersInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPersInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDestinationName4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPersInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlPersInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlPersInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(102, 102, 102));
        jLabel20.setText("street name");

        inputStreetNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputStreetNumberActionPerformed(evt);
            }
        });

        jLabel8.setText("Street number");

        jLabel11.setText("Street");

        inputStreet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputStreetActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("suite, number");

        javax.swing.GroupLayout pnlStreetLayout = new javax.swing.GroupLayout(pnlStreet);
        pnlStreet.setLayout(pnlStreetLayout);
        pnlStreetLayout.setHorizontalGroup(
            pnlStreetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStreetLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(pnlStreetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlStreetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(inputStreetNumber, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(pnlStreetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jLabel11)
                    .addComponent(inputStreet, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        pnlStreetLayout.setVerticalGroup(
            pnlStreetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStreetLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(pnlStreetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlStreetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputStreetNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputStreet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlStreetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel20))
                .addGap(0, 0, 0))
        );

        inputCity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputCityActionPerformed(evt);
            }
        });

        jLabel25.setText("Postal code");

        inputProvince.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputProvinceActionPerformed(evt);
            }
        });

        jLabel21.setText("City");

        inputPost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputPostActionPerformed(evt);
            }
        });

        jLabel23.setText("Province");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(102, 102, 102));
        jLabel24.setText("e.g. V8V 2W2");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(102, 102, 102));
        jLabel22.setText("abbr.");

        javax.swing.GroupLayout pnlRegionLayout = new javax.swing.GroupLayout(pnlRegion);
        pnlRegion.setLayout(pnlRegionLayout);
        pnlRegionLayout.setHorizontalGroup(
            pnlRegionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegionLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(pnlRegionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlRegionLayout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(117, 117, 117)
                        .addComponent(jLabel23)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel25))
                    .addGroup(pnlRegionLayout.createSequentialGroup()
                        .addComponent(inputCity, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlRegionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inputProvince, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlRegionLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel22)))
                        .addGroup(pnlRegionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlRegionLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jLabel24))
                            .addGroup(pnlRegionLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(inputPost)))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        pnlRegionLayout.setVerticalGroup(
            pnlRegionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegionLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(pnlRegionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel23)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlRegionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputProvince, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputPost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlRegionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel24))
                .addGap(0, 0, 0))
        );

        inputCountry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputCountryActionPerformed(evt);
            }
        });

        jLabel26.setText("Country");

        javax.swing.GroupLayout pnlCountryLayout = new javax.swing.GroupLayout(pnlCountry);
        pnlCountry.setLayout(pnlCountryLayout);
        pnlCountryLayout.setHorizontalGroup(
            pnlCountryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCountryLayout.createSequentialGroup()
                .addGroup(pnlCountryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel26)
                    .addComponent(inputCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );
        pnlCountryLayout.setVerticalGroup(
            pnlCountryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCountryLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        lblDestinationName2.setFont(new java.awt.Font("Noto Sans", 3, 14)); // NOI18N
        lblDestinationName2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDestinationName2.setText("Address");
        lblDestinationName2.setToolTipText("");

        javax.swing.GroupLayout pnlAddressLayout = new javax.swing.GroupLayout(pnlAddress);
        pnlAddress.setLayout(pnlAddressLayout);
        pnlAddressLayout.setHorizontalGroup(
            pnlAddressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAddressLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAddressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAddressLayout.createSequentialGroup()
                        .addGroup(pnlAddressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlRegion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlCountry, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlStreet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6))
                    .addGroup(pnlAddressLayout.createSequentialGroup()
                        .addComponent(lblDestinationName2, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pnlAddressLayout.setVerticalGroup(
            pnlAddressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAddressLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDestinationName2)
                .addGap(8, 8, 8)
                .addComponent(pnlStreet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlRegion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblDestinationName1.setFont(new java.awt.Font("Noto Sans", 2, 18)); // NOI18N
        lblDestinationName1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDestinationName1.setText("User Profile");
        lblDestinationName1.setToolTipText("");

        btnSave.setBackground(new java.awt.Color(204, 255, 204));
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnCancel.setBackground(new java.awt.Color(243, 243, 243));
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(btnCancel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSave)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnSave))
                .addContainerGap())
        );

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel19.setText("required");

        jLabel18.setForeground(new java.awt.Color(255, 0, 0));
        jLabel18.setText("*");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pnlAccount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlPersInfo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel19)
                                .addGap(47, 47, 47))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(212, 212, 212)
                        .addComponent(lblDestinationName1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblDestinationName1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(pnlPersInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(15, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void radioStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioStatusActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSaveActionPerformed

    private void inputCountryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputCountryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputCountryActionPerformed

    private void inputPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputPostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputPostActionPerformed

    private void inputProvinceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputProvinceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputProvinceActionPerformed

    private void inputCityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputCityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputCityActionPerformed

    private void inputStreetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputStreetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputStreetActionPerformed

    private void inputStreetNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputStreetNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputStreetNumberActionPerformed

    private void inputPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputPhoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputPhoneActionPerformed

    private void inputEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputEmailActionPerformed

    private void inputLastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputLastNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputLastNameActionPerformed

    private void inputFirstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputFirstNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputFirstNameActionPerformed

    private void inputUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputUsernameActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSave;
    private javax.swing.JTextField inputCity;
    private javax.swing.JTextField inputCountry;
    private javax.swing.JTextField inputEmail;
    private javax.swing.JTextField inputFirstName;
    private javax.swing.JTextField inputLastName;
    private javax.swing.JPasswordField inputPassword;
    private javax.swing.JTextField inputPhone;
    private javax.swing.JTextField inputPost;
    private javax.swing.JTextField inputProvince;
    private javax.swing.JTextField inputStreet;
    private javax.swing.JTextField inputStreetNumber;
    private javax.swing.JTextField inputUsername;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblDestinationCountry;
    private javax.swing.JLabel lblDestinationName;
    private javax.swing.JLabel lblDestinationName1;
    private javax.swing.JLabel lblDestinationName2;
    private javax.swing.JLabel lblDestinationName4;
    private javax.swing.JPanel pnlAccount;
    private javax.swing.JPanel pnlAddress;
    private javax.swing.JPanel pnlCountry;
    private javax.swing.JPanel pnlPersInfo;
    private javax.swing.JPanel pnlRegion;
    private javax.swing.JPanel pnlStreet;
    private javax.swing.JCheckBox radioStatus;
    private javax.swing.JComboBox<String> selectionRole;
    // End of variables declaration//GEN-END:variables
}
