/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.BuyerDao;
import model.Buyer;
import model.ComboItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BuyerDaoImpl implements BuyerDao {

    private Connection getConnection() throws SQLException {
        return db.DBConnection.createConnection();
    }

    @Override
    public List<ComboItem> getBuyerComboItems() {

        final String sql =
            "SELECT u.id, u.full_name, u.email, u.phone " +
            "FROM users u " +
            "JOIN roles r ON r.id = u.role_id " +
            "WHERE UPPER(r.role_name) = 'BUYER' " +
            "AND u.is_active = 1 " +
            "ORDER BY u.full_name";

        List<ComboItem> items = new ArrayList<>();

        // Optional: first item (no filter)
        items.add(new ComboItem(0, "All Buyers"));

        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("full_name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");

                String label = buildLabel(name, email, phone);
                items.add(new ComboItem(id, label));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load buyers for combo box", e);
        }

        return items;
    }

    @Override
    public Buyer getById(int id) {

        final String sql =
            "SELECT u.id, u.full_name, u.email, u.phone, u.address, u.district, u.is_active " +
            "FROM users u WHERE u.id = ?";

        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Buyer b = new Buyer();
                    b.setId(rs.getInt("id"));
                    b.setName(rs.getString("full_name"));
                    b.setEmail(rs.getString("email"));
                    b.setPhone(rs.getString("phone"));
                    b.setAddress(rs.getString("address"));
                    b.setDistrict(rs.getString("district"));
                    b.setActive(rs.getInt("is_active") == 1);
                    return b;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int insert(Buyer b) {

        final String sql =
            "INSERT INTO users(full_name, email, phone, address, district, role_id, is_active) " +
            "VALUES(?,?,?,?,?, (SELECT id FROM roles WHERE UPPER(role_name)='BUYER'), 1)";

        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, b.getName());
            ps.setString(2, b.getEmail());
            ps.setString(3, b.getPhone());
            ps.setString(4, b.getAddress());
            ps.setString(5, b.getDistrict());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        return keys.getInt(1);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    private String buildLabel(String name, String email, String phone) {

        String label = (name == null || name.isBlank()) ? "-" : name;

        if (email != null && !email.isBlank()) {
            label += " — " + email;
        }

        if (phone != null && !phone.isBlank()) {
            label += " — " + phone;
        }

        return label;
    }
}
