/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

/**
 *
 * @author ucwaz
 */
import dao.FieldDao;
import db.DBConnection;
import model.ComboItem;
import model.Field;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FieldDaoImpl implements FieldDao {

    private Connection getConnection() throws SQLException {
        return DBConnection.createConnection();
    }

    // ✅ For combo box (id + label)
    @Override
    public List<ComboItem> getFieldComboItems() throws SQLException {
        String sql =
            "SELECT id, field_code " +
            "FROM fields " +
            "ORDER BY field_code";

        List<ComboItem> items = new ArrayList<>();
        items.add(new ComboItem(0, "Select Field"));

        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String code = rs.getString("field_code");
                items.add(new ComboItem(id, code));
            }
        }

        return items;
    }

    // ✅ For loading full field data (optional but required by interface)
    @Override
    public Field getById(int id) throws SQLException {
        String sql =
            "SELECT id, field_code, location, size_ha " +
            "FROM fields WHERE id = ?";

        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Field f = new Field();
                    f.setId(rs.getInt("id"));
                    f.setFieldCode(rs.getString("field_code"));
                    f.setLocation(rs.getString("location"));
                    return f;
                }
            }
        }

        return null;
    }
}
