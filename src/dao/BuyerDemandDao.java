/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao;

import model.BuyerDemand;
import model.ComboItem;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface BuyerDemandDao {

    List<BuyerDemand> findDemands(
            Integer buyerId,
            Integer cropId,
            String status,
            String quality,
            LocalDate fromDate,
            LocalDate toDate,
            Double minQty
    ) throws SQLException;

    List<ComboItem> getBuyers() throws SQLException;

    List<ComboItem> getCrops() throws SQLException;

    int insert(BuyerDemand demand) throws SQLException;  // <-- add this
}


