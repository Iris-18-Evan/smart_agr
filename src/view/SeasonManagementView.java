/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import model.ComboItem;
import controller.SeasonManagementController;
import java.time.LocalDate;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JOptionPane;

public class SeasonManagementView extends javax.swing.JFrame {

    private ButtonGroup expectedYieldGroup;

    public SeasonManagementView() {
        initComponents();

        // Controller
        new SeasonManagementController(this);

        // ButtonGroup for Expected Yield
        expectedYieldGroup = new ButtonGroup();
        expectedYieldGroup.add(Planned);
        expectedYieldGroup.add(Critical);

        // Default selection
        Planned.setSelected(true);
    }

    /* =========================
       COMBO BOX LOADERS
       ========================= */

    public void setSeasonItems(List<ComboItem> items) {
        ComboItem[] data = (items != null)
                ? items.toArray(new ComboItem[0])
                : new ComboItem[0];

        SeasonCombobox.setModel(new DefaultComboBoxModel<>(data));

        if (SeasonCombobox.getItemCount() > 0) {
            SeasonCombobox.setSelectedIndex(0);
        }
    }

    public void setFieldItems(List<ComboItem> items) {
        ComboItem[] data = (items != null)
                ? items.toArray(new ComboItem[0])
                : new ComboItem[0];

        FieldComboBox.setModel(new DefaultComboBoxModel<>(data));

        if (FieldComboBox.getItemCount() > 0) {
            FieldComboBox.setSelectedIndex(0);
        }
    }

    public void setCropItems(List<ComboItem> items) {
        ComboItem[] data = (items != null)
                ? items.toArray(new ComboItem[0])
                : new ComboItem[0];

        CropComboBox.setModel(new DefaultComboBoxModel<>(data));

        if (CropComboBox.getItemCount() > 0) {
            CropComboBox.setSelectedIndex(0);
        }
    }

    /* =========================
       GETTERS FOR CONTROLLER
       ========================= */

    public Integer getSelectedSeasonId() {
        Object o = SeasonCombobox.getSelectedItem();
        return (o instanceof ComboItem ci) ? ci.getId() : null;
    }

    public Integer getSelectedFieldId() {
        Object o = FieldComboBox.getSelectedItem();
        return (o instanceof ComboItem ci) ? ci.getId() : null;
    }

    public Integer getSelectedCropId() {
        Object o = CropComboBox.getSelectedItem();
        return (o instanceof ComboItem ci) ? ci.getId() : null;
    }

    public LocalDate getStartDate() {
        try {
            String text = txtStartDate.getText().trim();
            if (text.isEmpty()) return null;
            return LocalDate.parse(text); // yyyy-MM-dd
        } catch (Exception e) {
            return null;
        }
    }

    // Expected Yield (Planned / Critical)
    public String getExpectedYieldType() {
        if (Planned.isSelected()) return "PLANNED";
        if (Critical.isSelected()) return "CRITICAL";
        return null;
    }

    public String getNotes() {
        return txtNotes.getText().trim();
    }

    /* =========================
       ACTIONS
       ========================= */

    // FIXED: This should add listener to the Save button (jButton2), not Exit button
    public void addSaveListener(java.awt.event.ActionListener listener) {
        jButton2.addActionListener(listener);
    }
    
    // Add listener for Exit button
    public void addExitListener(ActionListener listener) {
        btnExit.addActionListener(listener);
    }

    public void showSuccess(String message) {
        javax.swing.JOptionPane.showMessageDialog(
                this,
                message,
                "Success",
                javax.swing.JOptionPane.INFORMATION_MESSAGE
        );
    }

    public void showError(String message) {
        javax.swing.JOptionPane.showMessageDialog(
                this,
                message,
                "Error",
                javax.swing.JOptionPane.ERROR_MESSAGE
        );
    }

    public void clearForm() {
        if (SeasonCombobox.getItemCount() > 0) SeasonCombobox.setSelectedIndex(0);
        if (FieldComboBox.getItemCount() > 0) FieldComboBox.setSelectedIndex(0);
        if (CropComboBox.getItemCount() > 0) CropComboBox.setSelectedIndex(0);

        txtStartDate.setText("");
        Planned.setSelected(true);
        txtNotes.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        CropComboBox = new javax.swing.JComboBox<>();
        SeasonCombobox = new javax.swing.JComboBox<>();
        FieldComboBox = new javax.swing.JComboBox<>();
        Planned = new javax.swing.JCheckBox();
        Critical = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtNotes = new javax.swing.JTextArea();
        btnExit = new javax.swing.JButton();
        txtStartDate = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Create Crop Season");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Field");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Expected Yield");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Season");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Crop");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Notes");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Start Date");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, -1));

        jPanel1.add(CropComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 130, -1));

        jPanel1.add(SeasonCombobox, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 130, -1));

        jPanel1.add(FieldComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 130, -1));

        Planned.setText("Planned");
        jPanel1.add(Planned, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, -1, -1));

        Critical.setText("Critical");
        jPanel1.add(Critical, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 250, -1, -1));

        txtNotes.setColumns(20);
        txtNotes.setRows(5);
        jScrollPane1.setViewportView(txtNotes);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, -1, -1));

        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        jPanel1.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 430, 90, -1));
        
        jPanel1.add(txtStartDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, 120, -1));

        jButton2.setText("Save Season");
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 430, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, 490));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(SeasonManagementView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SeasonManagementView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SeasonManagementView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SeasonManagementView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new SeasonManagementView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox Critical;
    private javax.swing.JComboBox<ComboItem> CropComboBox;
    private javax.swing.JComboBox<ComboItem> FieldComboBox;
    private javax.swing.JCheckBox Planned;
    private javax.swing.JComboBox<ComboItem> SeasonCombobox;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea txtNotes;
    private javax.swing.JTextField txtStartDate;
    // End of variables declaration//GEN-END:variables
}
