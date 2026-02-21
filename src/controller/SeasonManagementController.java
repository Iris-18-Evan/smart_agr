/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
/**
 *
 * @author ucwaz
 */
import dao.SeasonDao;
import dao.FieldDao;
import dao.CropDao;
import dao.impl.SeasonDaoImpl;
import dao.impl.FieldDaoImpl;
import dao.impl.CropDaoImpl;
import model.ComboItem;
import view.SeasonManagementView;

import javax.swing.JOptionPane;
import java.util.List;

public class SeasonManagementController {

    private final SeasonManagementView view;
    private final SeasonDao seasonDao;
    private final FieldDao fieldDao;
    private final CropDao cropDao;

    public SeasonManagementController(SeasonManagementView view) {
        this.view = view;
        this.seasonDao = new SeasonDaoImpl();
        this.fieldDao = new FieldDaoImpl();
        this.cropDao = new CropDaoImpl();
        init();
    }

    private void init() {
        loadComboBoxes();
        wireEvents();
    }

    private void loadComboBoxes() {
        try {
            // Seasons
            List<ComboItem> seasons = seasonDao.getSeasonComboItems();
            view.setSeasonItems(seasons);

            // Fields
            List<ComboItem> fields = fieldDao.getFieldComboItems();
            view.setFieldItems(fields);

            // Crops
            List<ComboItem> crops = cropDao.getCropComboItems();
            view.setCropItems(crops);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    view,
                    "Failed to load data.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void wireEvents() {
        view.addSaveListener(e -> onSave());
        view.addExitListener(e -> onExit());
    }


    private void onSave() {
        String validation = validateInputs();
        if (validation != null) {
            view.showError(validation);
            return;
        }

        // TODO: save to DB later
        view.showSuccess("Crop season saved successfully!");
        view.clearForm();
    }

    private void onExit() {
        // ✅ clean exit, no validation, no popup
        view.dispose();
    }

    private String validateInputs() {
        // Only validate if user is actually trying to save, not on form load
        /*
    if (view.getSelectedSeasonId() == null || view.getSelectedSeasonId() == 0) {
        return "Please select a season.";
    }
         */
        if (view.getSelectedFieldId() == null || view.getSelectedFieldId() == 0) {
            return "Please select a field.";
        }
        if (view.getSelectedCropId() == null || view.getSelectedCropId() == 0) {
            return "Please select a crop.";
        }
        if (view.getStartDate() == null) {
            return "Please enter a valid start date (yyyy-MM-dd).";
        }
        if (view.getExpectedYieldType() == null) {
            return "Please select expected yield type (Planned or Critical).";
        }
        return null;
    }
}

