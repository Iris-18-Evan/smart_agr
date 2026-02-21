/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

/**
 *
 * @author ucwaz
 */
import model.ComboItem;
import model.QualityGrade;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class BuyerDashboardView extends javax.swing.JFrame {

    public BuyerDashboardView() {
        initComponents();
        // set up quality dropdown with enum values
        ComboBoxQuality.removeAllItems();
        for (QualityGrade grade : QualityGrade.values()) {
            ComboBoxQuality.addItem(grade);
        }
    }

    // add submit button listener
    public void addSubmitListener(java.awt.event.ActionListener l) {
        btnSubmit.addActionListener(l);
    }

    // get selected crop id
    public Integer getSelectedCropId() {
        Object sel = ComboBoxFilter.getSelectedItem();
        if (sel instanceof ComboItem) {
            int id = ((ComboItem) sel).getId();
            return id == 0 ? null : id;
        }
        return null;
    }

    // get quantity value
    public double getQuantityKg() {
        String txt = txtQauntity.getText().trim();
        if (txt.isEmpty()) {
            throw new IllegalArgumentException("Quantity (kg) is required.");
        }
        double kg = Double.parseDouble(txt);
        if (kg <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0.");
        }
        return kg;
    }

    // get selected quality grade
    public QualityGrade getSelectedQuality() {
        return (QualityGrade) ComboBoxQuality.getSelectedItem();
    }

    // get needed by date
    public LocalDate getNeededBy() {
        return LocalDate.parse(txtDate.getText().trim());
    }

    // get notes text
    public String getNotes() {
        return txtNotes.getText().trim();
    }

    // load crops into dropdown
    public void setCropItems(List<ComboItem> crops) {
        DefaultComboBoxModel<ComboItem> model = new DefaultComboBoxModel<>();
        model.addElement(new ComboItem(0, "Select Crop"));

        for (ComboItem c : crops) {
            model.addElement(c);
        }

        ComboBoxFilter.setModel(model);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ComboBoxFilter = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        ComboBoxQuality = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtNotes = new javax.swing.JTextArea();
        txtQauntity = new javax.swing.JTextPane();
        txtDate = new javax.swing.JTextPane();
        btnSubmit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollPane4 = new javax.swing.JScrollPane();

        jScrollPane2.setViewportView(jTextPane2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Buyer Dashboard");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 17, -1, -1));

        jLabel2.setText("Quantity (KG)");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 90, 20));

        jPanel1.add(ComboBoxFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 160, -1));

        jLabel3.setText("Filter");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 63, 20));

        jLabel6.setText("Needed By");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 70, -1));

        jLabel5.setText("Note");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 70, -1));

        jPanel1.add(ComboBoxQuality, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, -1, -1));

        jLabel4.setText("Quality");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 50, -1));

        txtNotes.setColumns(20);
        txtNotes.setRows(5);
        jPanel1.add(txtNotes, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, -1, -1));
        jPanel1.add(txtQauntity, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 140, -1));
        jPanel1.add(txtDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 120, -1));

        btnSubmit.setText("Submit");
        jPanel1.add(btnSubmit, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 330, -1, -1));
        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 130, -1));
        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 120, -1));
        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 230, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 380));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(BuyerDashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BuyerDashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BuyerDashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BuyerDashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BuyerDashboardView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<ComboItem> ComboBoxFilter;
    private javax.swing.JComboBox<QualityGrade> ComboBoxQuality;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JTextPane txtDate;
    private javax.swing.JTextArea txtNotes;
    private javax.swing.JTextPane txtQauntity;
    // End of variables declaration//GEN-END:variables
}