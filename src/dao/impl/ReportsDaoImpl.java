/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao.impl;

import dao.ReportsDao;
import db.DBConnection;
import model.Harvest;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportsDaoImpl implements ReportsDao {

    @Override
    public List<Harvest> getHarvestReport(Date dateFrom, Date dateTo, String cropName) {
        List<Harvest> list = new ArrayList<>();

        // 👇 Adjust SQL to your actual schema (table/column names)
        final String sql =
                "SELECT h.harvest_id, h.field_id, h.date, h.quantity, c.crop_name " +
                "FROM harvest h " +
                "JOIN crop c ON h.crop_id = c.crop_id " +
                "WHERE h.date BETWEEN ? AND ? AND c.crop_name = ? " +
                "ORDER BY h.date ASC, h.harvest_id ASC";

        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, new java.sql.Date(dateFrom.getTime()));
            ps.setDate(2, new java.sql.Date(dateTo.getTime()));
            ps.setString(3, cropName);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Harvest h = new Harvest();

                    // 🔧 Map to your Harvest model's setters.
                    // If your model has different names, change these lines accordingly.
                    // Example assumptions:
                    //   setHarvestId(int), setFieldId(int), setDate(java.util.Date), setQuantity(double)
                    try {
                        h.getClass().getMethod("setHarvestId", int.class)
                                .invoke(h, rs.getInt("harvest_id"));
                    } catch (NoSuchMethodException e) {
                        // fallback if your setter is named setId()
                        try { h.getClass().getMethod("setId", int.class).invoke(h, rs.getInt("harvest_id")); }
                        catch (Exception ignore) {}
                    }
                    try {
                        h.getClass().getMethod("setFieldId", int.class)
                                .invoke(h, rs.getInt("field_id"));
                    } catch (Exception ignore) {}

                    Date utilDate = new Date(rs.getDate("date").getTime());
                    try {
                        h.getClass().getMethod("setDate", Date.class).invoke(h, utilDate);
                    } catch (Exception ignore) {}

                    try {
                        h.getClass().getMethod("setQuantity", double.class)
                                .invoke(h, rs.getDouble("quantity"));
                    } catch (Exception ignore) {}

                    // If your Harvest has cropName, map it; otherwise ignore
                    try {
                        h.getClass().getMethod("setCropName", String.class)
                                .invoke(h, rs.getString("crop_name"));
                    } catch (Exception ignore) {}

                    list.add(h);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}

