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
import model.Field;
import java.sql.SQLException;
import java.util.List;

public interface FieldDao {
    List<ComboItem> getFieldComboItems() throws SQLException;
    Field getById(int id) throws SQLException;
}
