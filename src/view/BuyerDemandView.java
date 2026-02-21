/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.time.LocalDate;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;          
import javax.swing.table.DefaultTableModel;

import model.ComboItem;
import controller.BuyerDemandController;

public class BuyerDemandView extends javax.swing.JFrame {

    public BuyerDemandView() {
        initComponents();
        // set up dropdown options
        setQualityDefaults();
        setStatusDefaults();
        // initialize controller to load data
        new BuyerDemandController(this);
    }

    // clear all rows from table
    public void clearTable() {
        DefaultTableModel m = (DefaultTableModel) tblBuyerDemands.getModel();
        m.setRowCount(0);
    }

    // add new row to table
    public void addRow(Object... cells) {
        DefaultTableModel m = (DefaultTableModel) tblBuyerDemands.getModel();
        m.addRow(cells);
    }

    // get selected buyer id from dropdown
    public Integer getSelectedBuyerId() {
        Object o = BuyerComboBox.getSelectedItem();
        return (o instanceof ComboItem ci) ? ci.getId() : null;
    }

    // get selected crop id from dropdown
    public Integer getSelectedCropId() {
        Object o = CropComboBox.getSelectedItem();
        return (o instanceof ComboItem ci) ? ci.getId() : null;
    }

    // get selected status value
    public String getSelectedStatus() {
        Object sel = StatusComboBox.getSelectedItem();
        if (sel == null) {
            return null;
        }
        String status = sel.toString().trim();
        return (status.isEmpty() || "all".equalsIgnoreCase(status)) ? null : status;
    }

    // get selected quality value
    public String getSelectedQuality() {
        Object sel = QualityComboBox.getSelectedItem();
        String label = (sel == null) ? null : sel.toString();
        return (label == null || "all".equalsIgnoreCase(label)) ? null : label;
    }

    // parse date from text field
    public LocalDate getDateFrom() {
        String s = txtDateFrom.getText().trim();
        if (s.isEmpty()) {
            return null;
        }
        try {
            return LocalDate.parse(s);
        } catch (Exception ex) {
            return null;
        }
    }

    // parse date to text field
    public LocalDate getDateTo() {
        String s = txtDateTo.getText().trim();
        if (s.isEmpty()) {
            return null;
        }
        try {
            return LocalDate.parse(s);
        } catch (Exception ex) {
            return null;
        }
    }

    // parse minimum quantity value
    public Double getMinQuantity() {
        String s = txtMinQty.getText().trim();
        if (s.isEmpty()) {
            return null;
        }
        try {
            return Double.valueOf(s);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    // get raw text for validation
    public String getDateFromText() {
        return txtDateFrom.getText();
    }

    public String getDateToText() {
        return txtDateTo.getText();
    }

    public String getMinQuantityText() {
        return txtMinQty.getText();
    }

    // connect submit button to controller
    public void addSubmitListener(java.awt.event.ActionListener l) {
        btnSubmit.addActionListener(l);
    }

    // connect buyer dropdown to controller
    public void addBuyerChangeListener(java.awt.event.ActionListener l) {
        BuyerComboBox.addActionListener(l);
    }

    // connect crop dropdown to controller
    public void addCropChangeListener(java.awt.event.ActionListener l) {
        CropComboBox.addActionListener(l);
    }

    // load buyers into dropdown
    public void setBuyerItems(List<ComboItem> items) {
        ComboItem[] data = (items != null) ? items.toArray(new ComboItem[0]) : new ComboItem[0];
        BuyerComboBox.setModel(new DefaultComboBoxModel<>(data));
        if (BuyerComboBox.getItemCount() > 0) {
            BuyerComboBox.setSelectedIndex(0);
        }
    }

    // load crops into dropdown
    public void setCropItems(List<ComboItem> items) {
        ComboItem[] data = (items != null) ? items.toArray(new ComboItem[0]) : new ComboItem[0];
        CropComboBox.setModel(new DefaultComboBoxModel<>(data));
        if (CropComboBox.getItemCount() > 0) {
            CropComboBox.setSelectedIndex(0);
        }
    }

    // set status dropdown options
    public void setStatusDefaults() {
        StatusComboBox.setModel(new DefaultComboBoxModel<>(
                new String[]{"All", "OPEN", "PARTIAL", "CLOSED", "CANCELLED"}
        ));
        StatusComboBox.setSelectedIndex(0);
    }

    // set quality dropdown options
    public void setQualityDefaults() {
        DefaultComboBoxModel<ComboItem> qm = new DefaultComboBoxModel<>();
        qm.addElement(new ComboItem(0, "All"));
        qm.addElement(new ComboItem(1, "A"));
        qm.addElement(new ComboItem(2, "B"));
        qm.addElement(new ComboItem(3, "C"));
        QualityComboBox.setModel(qm);
        QualityComboBox.setSelectedIndex(0);
    }

    // show warning dialog
    public void showWarning(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Warning", JOptionPane.WARNING_MESSAGE);
    }

    // show info dialog
    public void showInfo(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    // show error dialog
    public void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMinQty = new javax.swing.JTextPane();
        txtDateFrom = new javax.swing.JTextPane();
        txtDateTo = new javax.swing.JTextPane();
        StatusComboBox = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBuyerDemands = new javax.swing.JTable();
        btnSubmit = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        BuyerComboBox = new javax.swing.JComboBox<>();
        QualityComboBox = new javax.swing.JComboBox<>();
        CropComboBox = new javax.swing.JComboBox<>();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Buyer Demands");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 170, -1));

        jLabel2.setText("Filter");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jLabel4.setText("Date From");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, 20));

        jLabel3.setText("Min Qty");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 120, 50, 20));
        jPanel1.add(txtMinQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 120, 120, -1));
        jPanel1.add(txtDateFrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, 110, -1));
        jPanel1.add(txtDateTo, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 120, 110, -1));

        jPanel1.add(StatusComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, 110, -1));

        jLabel5.setText("Date To");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, 50, 20));

        tblBuyerDemands.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Buyer", "Crop", "Qty (kg)", "Needed By", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblBuyerDemands);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 580, 170));

        btnSubmit.setText("Submit");
        jPanel1.add(btnSubmit, new org.netbeans.lib.awtextra.AbsoluteConstraints(482, 350, 120, -1));

        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        jPanel1.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 350, 80, -1));

        jPanel1.add(BuyerComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 120, -1));

        QualityComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QualityComboBoxActionPerformed(evt);
            }
        });
        jPanel1.add(QualityComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, 110, -1));

        jPanel1.add(CropComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 130, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 390));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void QualityComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QualityComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_QualityComboBoxActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // Show confirmation dialog
        int choice = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to return to the Dashboard?\nAny unsaved changes will be lost.",
            "Confirm Exit",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );
        
        // If user clicks Yes, close this window and return to dashboard
        if (choice == JOptionPane.YES_OPTION) {
            this.dispose();
            // The original dashboard should still be open in the background
        }
        // If user clicks No, do nothing - stay on this page
    }//GEN-LAST:event_btnExitActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BuyerDemandView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BuyerDemandView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BuyerDemandView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BuyerDemandView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BuyerDemandView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new BuyerDemandView().setVisible(true));


    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<model.ComboItem> BuyerComboBox;
    private javax.swing.JComboBox<model.ComboItem> CropComboBox;
    private javax.swing.JComboBox<model.ComboItem> QualityComboBox;
    private javax.swing.JComboBox<String> StatusComboBox;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblBuyerDemands;
    private javax.swing.JTextPane txtDateFrom;
    private javax.swing.JTextPane txtDateTo;
    private javax.swing.JTextPane txtMinQty;
    // End of variables declaration//GEN-END:variables
}