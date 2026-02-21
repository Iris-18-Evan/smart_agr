/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.BuyerDemandDao;
import dao.BuyerDao;
import dao.CropDao;
import dao.impl.BuyerDemandDaoImpl;
import dao.impl.BuyerDaoImpl;
import dao.impl.CropDaoImpl;
import model.BuyerDemand;
import model.ComboItem;
import view.BuyerDemandView;
import reports.BuyerDemandReportGenerator;
import reports.BuyerDemandReportGenerator.BuyerDemandData;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuyerDemandController {

    private final BuyerDemandView view;
    private final BuyerDemandDao demandDao;

    // data access objects for buyers and crops
    private final BuyerDao buyerDao = new BuyerDaoImpl();
    private final CropDao cropDao = new CropDaoImpl();

    // store names for display in table
    private final Map<Integer, String> buyerNames = new HashMap<>();
    private final Map<Integer, String> cropNames = new HashMap<>();

    public BuyerDemandController(BuyerDemandView view) {
        this.view = view;
        this.demandDao = new BuyerDemandDaoImpl();
        init();
    }

    private void init() {
        loadFilters();
        wireEvents();
        reloadTable();
    }

    // load buyer and crop data into combo boxes
    private void loadFilters() {
        try {
            // get buyers from database
            List<ComboItem> buyers = buyerDao.getBuyerComboItems();
            view.setBuyerItems(buyers);

            buyerNames.clear();
            if (buyers != null) {
                for (ComboItem c : buyers) {
                    if (c.getId() != 0) {
                        buyerNames.put(c.getId(), c.toString());
                    }
                }
            }

            // get crops from database
            List<ComboItem> crops = cropDao.getCropComboItems();
            view.setCropItems(crops);

            cropNames.clear();
            if (crops != null) {
                for (ComboItem c : crops) {
                    if (c.getId() != 0) {
                        cropNames.put(c.getId(), c.toString());
                    }
                }
            }

            // set up status dropdown
            view.setStatusDefaults();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    view,
                    "Failed to load filter values.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // connect button clicks to actions
    private void wireEvents() {
        view.addSubmitListener(e -> onSubmit());
    }

    private void onSubmit() {
        String validationMsg = validateFilters();
        if (validationMsg != null) {
            JOptionPane.showMessageDialog(
                    view, validationMsg, "Warning", JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        // reload table with filtered data
        reloadTable();
        
        // ask if user wants to generate report
        askToGenerateReport();
    }

    // ask user if they want to generate a report
    private void askToGenerateReport() {
        int response = JOptionPane.showConfirmDialog(
                view,
                "Would you like to generate a report from this data?",
                "Generate Report",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
        
        if (response == JOptionPane.YES_OPTION) {
            // Generate the actual report
            generateReport();
        }
        // if NO, dialog just closes
    }

    /**
     * Generate and display the Buyer Demands report
     */
    private void generateReport() {
        try {
            System.out.println("=== Generating Buyer Demands Report ===");
            
            // Get filtered buyer demands from database (based on current filters)
            Integer buyerId = view.getSelectedBuyerId();
            Integer cropId = view.getSelectedCropId();
            String status = view.getSelectedStatus();
            String quality = view.getSelectedQuality();
            LocalDate from = view.getDateFrom();
            LocalDate to = view.getDateTo();
            Double minQty = view.getMinQuantity();

            List<BuyerDemand> demands = demandDao.findDemands(
                buyerId, cropId, status, quality, from, to, minQty
            );
            
            if (demands == null || demands.isEmpty()) {
                JOptionPane.showMessageDialog(
                    view,
                    "No buyer demands found to generate report.",
                    "No Data",
                    JOptionPane.INFORMATION_MESSAGE
                );
                return;
            }
            
            // Convert to report data format
            List<BuyerDemandData> reportData = new ArrayList<>();
            
            for (BuyerDemand demand : demands) {
                // Get buyer name from our cached map
                String buyerName = buyerNames.getOrDefault(
                    demand.getBuyerId(), 
                    "Buyer-" + demand.getBuyerId()
                );
                
                // Get crop name from our cached map
                String cropName = cropNames.getOrDefault(
                    demand.getCropId(), 
                    "Crop-" + demand.getCropId()
                );
                
                // Convert LocalDate to SQL Date
                java.sql.Date neededBy = demand.getNeededByDate() != null 
                    ? java.sql.Date.valueOf(demand.getNeededByDate()) 
                    : null;
                
                // Create report data bean
                BuyerDemandData data = new BuyerDemandData(
                    demand.getId(),
                    buyerName,
                    cropName,
                    demand.getRequestedQtyKg(),
                    demand.getQualityPref() != null ? demand.getQualityPref().toString() : "",
                    neededBy,
                    demand.getStatus() != null ? demand.getStatus().toString() : "UNKNOWN",
                    demand.getNotes()
                );
                
                reportData.add(data);
            }
            
            // Generate the report
            BuyerDemandReportGenerator.generateReport(reportData);
            
            System.out.println("✅ Report generated with " + reportData.size() + " records");
            
        } catch (Exception ex) {
            System.err.println("❌ ERROR generating report");
            ex.printStackTrace();
            
            JOptionPane.showMessageDialog(
                view,
                "Failed to generate report: " + ex.getMessage(),
                "Report Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // check if filters are valid
    private String validateFilters() {
        LocalDate from = view.getDateFrom();
        LocalDate to = view.getDateTo();
        Double minQty = view.getMinQuantity();

        if (from != null && to != null && to.isBefore(from)) {
            return "Date To cannot be earlier than Date From.";
        }

        if (minQty != null) {
            if (minQty.isNaN() || minQty.isInfinite()) {
                return "Min Qty is not a valid number.";
            }
            if (minQty <= 0) {
                return "Min Qty must be greater than 0.";
            }
        }
        return null;
    }

    // fetch data and update table
    private void reloadTable() {
        try {
            Integer buyerId = view.getSelectedBuyerId();
            Integer cropId = view.getSelectedCropId();
            String status = view.getSelectedStatus();
            String quality = view.getSelectedQuality();
            LocalDate from = view.getDateFrom();
            LocalDate to = view.getDateTo();
            Double minQty = view.getMinQuantity();

            List<BuyerDemand> rows
                    = demandDao.findDemands(buyerId, cropId, status, quality, from, to, minQty);

            view.clearTable();

            if (rows == null || rows.isEmpty()) {
                return;
            }

            int no = 1;
            for (BuyerDemand d : rows) {
                String buyer = buyerNames.getOrDefault(
                        d.getBuyerId(), String.valueOf(d.getBuyerId())
                );
                String crop = cropNames.getOrDefault(
                        d.getCropId(), String.valueOf(d.getCropId())
                );
                String date = (d.getNeededByDate() != null) ? d.getNeededByDate().toString() : "-";
                String qty = String.format("%.0f", d.getRequestedQtyKg());
                String st = (d.getStatus() != null) ? d.getStatus().name() : "-";

                view.addRow(no++, buyer, crop, qty, date, st);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    view,
                    "Failed to load data: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}