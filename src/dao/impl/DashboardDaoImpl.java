/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao.impl;

import dao.DashboardDao;
import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 

public class DashboardDaoImpl implements DashboardDao {

    private final Connection conn;

    public DashboardDaoImpl() {
        try {            
            this.conn = DBConnection.createConnection();
        } catch (SQLException e) {
            throw new RuntimeException("DB connection failed in DashboardDaoImpl", e);
        }
    }

    @Override
    public double getTotalExpectedYield() {
        final String sql = "SELECT COALESCE(SUM(expected_yield_kg),0) FROM seasons";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getDouble(1) : 0.0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    @Override
    public double getTotalActualYield() {
        final String sql = "SELECT COALESCE(SUM(actual_yield_kg),0) FROM harvests";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getDouble(1) : 0.0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    @Override
    public double getTotalAvailableHarvest() {
        final String sql = "SELECT COALESCE(SUM(available_qty_kg),0) FROM harvests";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getDouble(1) : 0.0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0.0;
        }
    }
}


