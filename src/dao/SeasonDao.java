/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Season;
import model.ComboItem;
import java.sql.SQLException;
import java.util.List;

public interface SeasonDao {
    List<Season> findAllForHealthDropdown();
    
    // CRUD operations
    Season findById(int id) throws SQLException;
    List<Season> findAll() throws SQLException;
    int insert(Season season) throws SQLException;
    int update(Season season) throws SQLException;
    int delete(int id) throws SQLException;
    int insertAndReturnId(Season season) throws SQLException;
    
    // combo box helper
    List<ComboItem> getSeasonComboItems() throws SQLException;
}



