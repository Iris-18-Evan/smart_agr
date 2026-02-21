/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
/**
 *
 * @author ucwaz
 */
import model.ComboItem;
import model.Crop;
import java.sql.SQLException;
import java.util.List;

public interface CropDao {
    List<ComboItem> getCropComboItems();
    Crop getById(int id);
    List<Crop> findAll() throws SQLException;  
}


