/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;
/**
 *
 * @author ucwaz
 */
import dao.CropDao;
import model.ComboItem;
import model.Crop;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CropDaoImpl implements CropDao {
    
    private Connection getConnection() throws SQLException {
        return db.DBConnection.createConnection();
    }
    
    // get all crops from database
    @Override
    public List<Crop> findAll() {
        String sql = "SELECT id, common_name, variety, category FROM crops ORDER BY common_name";
        List<Crop> crops = new ArrayList<>();
        
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Crop crop = new Crop();
                crop.setId(rs.getInt("id"));
                crop.setCommonName(rs.getString("common_name"));
                crop.setVariety(rs.getString("variety"));
                crop.setCategory(rs.getString("category"));
                crops.add(crop);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return crops;
    }
    
    // populate combo box with crop options
    @Override
    public List<ComboItem> getCropComboItems() {
        String sql = "SELECT id, common_name, variety, category FROM crops ORDER BY common_name";
        List<ComboItem> items = new ArrayList<>();
        items.add(new ComboItem(0, "All")); // default option
        
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                int id        = rs.getInt("id");
                String name   = rs.getString("common_name");
                String variety= rs.getString("variety");
                String cat    = rs.getString("category");
                String label  = buildLabel(name, variety, cat);
                items.add(new ComboItem(id, label));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return items;
    }
    
    // fetch single crop by id
    @Override
    public Crop getById(int id) {
        String sql = "SELECT id, common_name, variety, category FROM crops WHERE id = ?";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Crop crop = new Crop();
                    crop.setId(rs.getInt("id"));
                    crop.setCommonName(rs.getString("common_name"));
                    crop.setVariety(rs.getString("variety"));
                    crop.setCategory(rs.getString("category"));
                    return crop;
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }
    
    // format display text for combo box
    private String buildLabel(String name, String variety, String category) {
        String v = (variety == null || variety.isBlank()) ? "" : " — " + variety;
        String c = (category == null || category.isBlank()) ? "" : " (" + category + ")";
        return (name == null ? "-" : name) + v + c;
    }
}