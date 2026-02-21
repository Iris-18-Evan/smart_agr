/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.BuyerDemandDao;
import dao.CropDao;
import dao.impl.BuyerDemandDaoImpl;
import dao.impl.CropDaoImpl;
import model.BuyerDemand;
import model.ComboItem;
import model.DemandStatus;
import model.QualityGrade;
import model.User;
import view.BuyerDashboardView;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class BuyerDashboardController {

    private final BuyerDashboardView view;
    private final User currentUser;
    private final BuyerDemandDao demandDao;
    private final CropDao cropDao;
    private final Runnable onExit;

    public BuyerDashboardController(
            BuyerDashboardView view,
            User currentUser,
            Runnable onExit
    ) {
        this.view = view;
        this.currentUser = currentUser;
        this.onExit = onExit;

        this.demandDao = new BuyerDemandDaoImpl();
        this.cropDao = new CropDaoImpl();

        bind();
        loadCrops();   // ✅ NOW CORRECT
    }

    private void bind() {
        view.addSubmitListener(e -> submitDemand());
    }

    // 🔄 Load crops into Filter combo
    private void loadCrops() {
        try {
            List<ComboItem> crops = cropDao.getCropComboItems();
            view.setCropItems(crops);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    view,
                    "Failed to load crops.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void submitDemand() {
        try {
            Integer cropId = view.getSelectedCropId();
            double qty = view.getQuantityKg();
            QualityGrade quality = view.getSelectedQuality();
            LocalDate neededBy = view.getNeededBy();
            String notes = view.getNotes();

            // 🔒 Validation
            if (cropId == null || cropId == 0) {
                showValidation("Please select a crop.");
                return;
            }

            if (qty <= 0) {
                showValidation("Quantity must be greater than 0.");
                return;
            }

            if (neededBy == null) {
                showValidation("Please select Needed By date.");
                return;
            }

            if (quality == null) {
                quality = QualityGrade.A;
            }

            // 🧾 Summary confirmation
            String summary =
                    "Please confirm your demand:\n\n" +
                    "Crop ID: " + cropId + "\n" +
                    "Quantity (KG): " + qty + "\n" +
                    "Quality: " + quality + "\n" +
                    "Needed By: " + neededBy + "\n" +
                    "Notes: " + (notes.isBlank() ? "-" : notes);

            int choice = JOptionPane.showConfirmDialog(
                    view,
                    summary,
                    "Confirm Submission",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (choice != JOptionPane.YES_OPTION) {
                return;
            }

            BuyerDemand d = new BuyerDemand();
            d.setBuyerId(currentUser.getId());
            d.setCropId(cropId);
            d.setRequestedQtyKg(qty);
            d.setQualityPref(quality);
            d.setNeededByDate(neededBy);
            d.setStatus(DemandStatus.OPEN);
            d.setNotes(notes);

            int inserted = demandDao.insert(d);

            if (inserted > 0) {
                JOptionPane.showMessageDialog(
                        view,
                        "Demand submitted successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                view.dispose();
                if (onExit != null) onExit.run();

            } else {
                JOptionPane.showMessageDialog(
                        view,
                        "Failed to submit demand.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    view,
                    "Unexpected error: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void showValidation(String msg) {
        JOptionPane.showMessageDialog(
                view,
                msg,
                "Validation Error",
                JOptionPane.WARNING_MESSAGE
        );
    }
}
