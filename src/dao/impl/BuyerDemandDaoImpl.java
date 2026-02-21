/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.BuyerDemandDao;
import model.BuyerDemand;
import model.ComboItem;
import model.QualityGrade;
import model.DemandStatus;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BuyerDemandDaoImpl implements BuyerDemandDao {

    private Connection getConnection() throws SQLException {
        return db.DBConnection.createConnection();
    }

    @Override
    public List<BuyerDemand> findDemands(
            Integer buyerId,
            Integer cropId,
            String status,
            String quality,
            LocalDate fromDate,
            LocalDate toDate,
            Double minQty
    ) throws SQLException {

        List<BuyerDemand> demands = new ArrayList<>();

        StringBuilder sql = new StringBuilder(
            "SELECT id, buyer_id, crop_id, requested_qty_kg, quality_pref, " +
            "needed_by_date, status, notes, created_at " +
            "FROM buyer_demands WHERE 1=1"
        );

        if (buyerId != null && buyerId != 0) sql.append(" AND buyer_id = ?");
        if (cropId != null && cropId != 0) sql.append(" AND crop_id = ?");
        if (status != null && !status.equalsIgnoreCase("All")) sql.append(" AND status = ?");
        if (quality != null && !quality.equalsIgnoreCase("All")) sql.append(" AND quality_pref = ?");
        if (fromDate != null) sql.append(" AND needed_by_date >= ?");
        if (toDate != null) sql.append(" AND needed_by_date <= ?");
        if (minQty != null) sql.append(" AND requested_qty_kg >= ?");

        sql.append(" ORDER BY needed_by_date");

        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql.toString())) {

            int i = 1;

            if (buyerId != null && buyerId != 0) ps.setInt(i++, buyerId);
            if (cropId != null && cropId != 0) ps.setInt(i++, cropId);
            if (status != null && !status.equalsIgnoreCase("All")) ps.setString(i++, status);
            if (quality != null && !quality.equalsIgnoreCase("All")) ps.setString(i++, quality);
            if (fromDate != null) ps.setDate(i++, Date.valueOf(fromDate));
            if (toDate != null) ps.setDate(i++, Date.valueOf(toDate));
            if (minQty != null) ps.setDouble(i++, minQty);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    BuyerDemand d = new BuyerDemand();
                    d.setId(rs.getInt("id"));
                    d.setBuyerId(rs.getInt("buyer_id"));
                    d.setCropId(rs.getInt("crop_id"));
                    d.setRequestedQtyKg(rs.getDouble("requested_qty_kg"));

                    // ✅ SAFE enum parsing
                    d.setQualityPref(QualityGrade.fromDb(rs.getString("quality_pref")));
                    d.setStatus(DemandStatus.fromDb(rs.getString("status")));

                    Date date = rs.getDate("needed_by_date");
                    if (date != null) {
                        d.setNeededByDate(date.toLocalDate());
                    }

                    d.setNotes(rs.getString("notes"));
                    demands.add(d);
                }
            }
        }

        return demands;
    }

    @Override
    public List<ComboItem> getBuyers() throws SQLException {
        List<ComboItem> buyers = new ArrayList<>();
        buyers.add(new ComboItem(0, "All"));

        String sql = "SELECT id, name, email, phone FROM buyers ORDER BY name";

        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String label = rs.getString("name")
                        + " — " + rs.getString("email")
                        + " — " + rs.getString("phone");

                buyers.add(new ComboItem(rs.getInt("id"), label));
            }
        }

        return buyers;
    }

    @Override
    public List<ComboItem> getCrops() throws SQLException {
        List<ComboItem> crops = new ArrayList<>();
        crops.add(new ComboItem(0, "All"));

        String sql = "SELECT id, common_name, variety, category FROM crops ORDER BY common_name";

        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String name = rs.getString("common_name");
                String variety = rs.getString("variety");
                String category = rs.getString("category");

                String v = (variety == null || variety.isBlank()) ? "" : " — " + variety;
                String cat = (category == null || category.isBlank()) ? "" : " (" + category + ")";
                crops.add(new ComboItem(rs.getInt("id"), name + v + cat));
            }
        }

        return crops;
    }

    @Override
    public int insert(BuyerDemand demand) throws SQLException {
        String sql =
            "INSERT INTO buyer_demands " +
            "(buyer_id, crop_id, requested_qty_kg, quality_pref, needed_by_date, status, notes) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, demand.getBuyerId());
            ps.setInt(2, demand.getCropId());
            ps.setDouble(3, demand.getRequestedQtyKg());
            ps.setString(4,
                demand.getQualityPref() == null ? null : demand.getQualityPref().toDb()
            );
            ps.setDate(5, Date.valueOf(demand.getNeededByDate()));
            ps.setString(6,
                demand.getStatus() == null ? null : demand.getStatus().toDb()
            );
            ps.setString(7, demand.getNotes());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                return rs.next() ? rs.getInt(1) : -1;
            }
        }
    }
}
