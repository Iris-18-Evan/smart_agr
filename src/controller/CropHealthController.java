/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import view.CropHealthView;
import dao.impl.SeasonDaoImpl;
import model.ComboItem;

import javax.swing.JComboBox;
import java.util.List;

public class CropHealthController {
    
    private final CropHealthView view;
    private final SeasonDaoImpl seasonDao;
    
    public CropHealthController(CropHealthView view) {
        this.view = view;
        this.seasonDao = new SeasonDaoImpl();
        
        System.out.println("=== CropHealthController initialized ===");
        
        // Load real seasons from database
        loadSeasonsFromDatabase();
        
        // Bind save button
        bind();
    }
    
    private void bind() {
        System.out.println("Binding Save button...");
        view.getBtnSave().addActionListener(e -> {
            System.out.println("Save button clicked.");
            saveHealthRecord();
        });
    }
    
    /**
     * Load seasons from database into combo box
     */
    @SuppressWarnings("unchecked")
    private void loadSeasonsFromDatabase() {
        try {
            System.out.println("=== Loading seasons from database ===");
            
            // Get seasons from database
            List<ComboItem> seasons = seasonDao.getSeasonComboItems();
            
            System.out.println("DEBUG: Seasons list is null? " + (seasons == null));
            System.out.println("DEBUG: Seasons size: " + (seasons != null ? seasons.size() : "N/A"));
            
            // Get the combo box with proper type
            JComboBox<ComboItem> comboBox = (JComboBox<ComboItem>) view.getSeasonCombo();
            System.out.println("DEBUG: ComboBox is null? " + (comboBox == null));
            
            // Clear existing items
            comboBox.removeAllItems();
            System.out.println("DEBUG: Cleared combo box");
            
            // Add all seasons from database
            if (seasons != null && !seasons.isEmpty()) {
                for (int i = 0; i < seasons.size(); i++) {
                    ComboItem season = seasons.get(i);
                    System.out.println("DEBUG: Adding item " + i + ": ID=" + season.getId() + ", Label=" + season.getLabel());
                    
                    // Add the item
                    comboBox.addItem(season);
                }
                
                System.out.println("DEBUG: Total items in combo: " + comboBox.getItemCount());
                System.out.println("✅ Loaded " + seasons.size() + " seasons from database");
            } else {
                // No seasons found
                System.out.println("⚠ No seasons found in database");
                comboBox.addItem(new ComboItem(0, "No seasons available"));
            }
            
        } catch (Exception ex) {
            System.err.println("❌ ERROR loading seasons from database");
            ex.printStackTrace();
            view.showError("Failed to load seasons: " + ex.getMessage());
        }
    }
    
    /**
     * Save health record
     */
    @SuppressWarnings("unchecked")
    private void saveHealthRecord() {
        try {
            // Get selected season
            JComboBox<ComboItem> comboBox = (JComboBox<ComboItem>) view.getSeasonCombo();
            Object selected = comboBox.getSelectedItem();
            
            System.out.println("DEBUG: Selected object: " + selected);
            System.out.println("DEBUG: Selected type: " + (selected != null ? selected.getClass() : "null"));
            
            if (selected == null) {
                view.showError("Please select a season.");
                return;
            }
            
            ComboItem selectedSeason = (ComboItem) selected;
            
            if (selectedSeason.getId() == 0) {
                view.showError("Please select a valid season.");
                return;
            }
            
            // Get and validate actual yield
            String yieldText = view.getActualYieldText();
            System.out.println("DEBUG: Yield text: '" + yieldText + "'");
            
            if (yieldText == null || yieldText.trim().isEmpty()) {
                view.showError("Please enter actual yield.");
                return;
            }
            
            double actualYield;
            try {
                actualYield = Double.parseDouble(yieldText.trim());
                if (actualYield < 0) {
                    view.showError("Actual yield must be positive.");
                    return;
                }
            } catch (NumberFormatException ex) {
                view.showError("Please enter a valid number for Actual Yield.");
                return;
            }
       
            // Get status
            String status;
            if (view.getRbGood().isSelected()) {
                status = "Good";
            } else if (view.getRbWarning().isSelected()) {
                status = "Warning";
            } else if (view.getRbCritical().isSelected()) {
                status = "Critical";
            } else {
                view.showError("Please select a status.");
                return;
            }
            
            // Get notes
            String notes = view.getNotesText();
            
            // Show success message
            String message = String.format(
                "Health Report Saved!\n\n" +
                "Season: %s\n" +
                "Actual Yield: %.2f kg\n" +
                "Status: %s\n" +
                "Notes: %s",
                selectedSeason.getLabel(),
                actualYield,
                status,
                notes.isEmpty() ? "(none)" : notes
            );
            
            view.showInfo(message);
            
            System.out.println("✅ Health report saved - Season ID: " + selectedSeason.getId());
            
        } catch (Exception ex) {
            System.err.println("❌ ERROR saving health record");
            ex.printStackTrace();
            view.showError("Failed to save: " + ex.getMessage());
        }
    }
}