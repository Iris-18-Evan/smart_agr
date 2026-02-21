/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.impl.SeasonDaoImpl;
import model.Season;
import model.SeasonStatus;
import model.User;
import view.*;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ucwaz
 */
public class DashboardController {

    private final DashboardView view;
    private final SeasonDaoImpl seasonDao;
    private User currentUser;

    // Constructor for existing code (with User parameter)
    public DashboardController(DashboardView view, User user) {
        this.view = view;
        this.currentUser = user;
        this.seasonDao = new SeasonDaoImpl();

        // Wire up button events
        wireButtons();

        // Load dashboard data
        loadDashboardData();
    }

    // Constructor for new code (without User parameter)
    public DashboardController(DashboardView view) {
        this(view, null);
    }

    /**
     * Wire up all button click events
     */
    private void wireButtons() {
        // Add Seasons button
        view.getBtnAddSeasons().addActionListener(e -> openAddSeasons());

        // Record Harvest button
        view.getBtnRecordHarvest().addActionListener(e -> openRecordHarvest());

        // Health Report button
        view.getBtnHealthReport().addActionListener(e -> openHealthReport());

        // Buyer Demands button
        view.getBtnBuyerDemands().addActionListener(e -> openBuyerDemands());

        System.out.println("✅ Dashboard buttons wired successfully");
    }

    /**
     * Open Add Seasons form
     */
    private void openAddSeasons() {
        System.out.println("Opening Season Management form...");

        // Create and show Season Management window
        java.awt.EventQueue.invokeLater(() -> {
            SeasonManagementView seasonView = new SeasonManagementView();
            seasonView.setVisible(true);
        });
    }

    /**
     * Open Record Harvest form
     */
    private void openRecordHarvest() {
        System.out.println("Opening Harvest Recording form...");

        // Create and show Harvest window
        java.awt.EventQueue.invokeLater(() -> {
            HarvestView harvestView = new HarvestView();
            harvestView.setVisible(true);
        });
    }

    /**
     * Open Health Report form
     */
    private void openHealthReport() {
        System.out.println("Opening Crop Health Report form...");

        // Create and show Crop Health window
        java.awt.EventQueue.invokeLater(() -> {
            CropHealthView healthView = new CropHealthView();
            healthView.setVisible(true);
        });
    }

    /**
     * Open Buyer Demands form
     */
    private void openBuyerDemands() {
        System.out.println("Opening Buyer Demands form...");

        // Create and show Buyer Demands window
        java.awt.EventQueue.invokeLater(() -> {
            BuyerDemandView demandView = new BuyerDemandView();
            demandView.setVisible(true);
        });
    }

    /**
     * Load all dashboard data (KPIs only for now)
     */
    public void loadDashboardData() {
        try {
            System.out.println("=== Loading Dashboard Data ===");

            // Load KPI metrics
            loadKPIs();

            // TODO: Load active seasons into table once DashboardView has the methods
            System.out.println("=== Dashboard Data Loaded Successfully ===");

        } catch (Exception ex) {
            System.err.println("❌ ERROR loading dashboard data");
            ex.printStackTrace();
        }
    }

    /**
     * Load KPI metrics (Expected Yield, Actual Yield, Available Harvest)
     */
    private void loadKPIs() {
        try {
            // Get all active seasons
            List<Season> allSeasons = seasonDao.findAll();

            double totalExpectedYield = 0.0;
            double totalActualYield = 0.0;
            double availableHarvest = 0.0;

            // Calculate totals
            for (Season season : allSeasons) {
                // Only count ACTIVE and PLANNED seasons for expected yield
                if (season.getStatus() == SeasonStatus.ACTIVE
                        || season.getStatus() == SeasonStatus.PLANNED) {
                    totalExpectedYield += season.getExpectedYieldKg();
                }

                // For actual yield and available harvest, you would typically
                // query from a Harvest table. For now, we'll use placeholder logic.
                // TODO: Implement actual harvest data retrieval
            }

            // Update view with KPI values
            view.setExpectedYield(totalExpectedYield);
            view.setActualYield(totalActualYield);
            view.setAvailableHarvest(availableHarvest);

            System.out.println("KPIs loaded - Expected: " + totalExpectedYield + " kg");

        } catch (SQLException ex) {
            System.err.println("❌ ERROR loading KPIs");
            ex.printStackTrace();
        }
    }

    /**
     * Public method to refresh dashboard (call this when returning from other
     * forms)
     */
    public void refreshDashboard() {
        loadDashboardData();
    }
}
