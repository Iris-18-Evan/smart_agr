/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.SeasonDao;
import dao.impl.SeasonDaoImpl;
import model.ComboItem;
import view.HarvestView;
import javax.swing.JOptionPane;
import java.util.List;

/**
 *
 * @author ucwaz
 */
public class HarvestController {
    
    private final HarvestView view;
    private final SeasonDao seasonDao;
    
    public HarvestController(HarvestView view) {
        this.view = view;
        this.seasonDao = new SeasonDaoImpl();
        init();
    }
    
    private void init() {
        loadSeasons();
        // Add other initialization here if needed
    }
    
    // Load seasons into the combo box
    private void loadSeasons() {
        try {
            System.out.println("=== Loading seasons for Harvest form ===");
            
            List<ComboItem> seasons = seasonDao.getSeasonComboItems();
            
            if (seasons == null || seasons.isEmpty()) {
                System.out.println("⚠ No seasons found in database");
            } else {
                System.out.println("Seasons loaded: " + seasons.size());
                for (ComboItem item : seasons) {
                    System.out.println("  - Season: " + item.getLabel());
                }
            }
            
            // Update UI on Swing thread
            javax.swing.SwingUtilities.invokeLater(() -> {
                view.setSeasonItems(seasons);
            });
            
            System.out.println("=== Seasons loaded successfully ===");
            
        } catch (Exception ex) {
            System.err.println("❌ ERROR loading seasons");
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                view,
                "Failed to load seasons: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}

