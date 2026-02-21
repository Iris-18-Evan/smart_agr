package dao.impl;

import dao.SeasonDao;
import db.DBConnection;
import model.ComboItem;
import model.Season;
import model.SeasonStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeasonDaoImpl implements SeasonDao {

    private final Connection conn;

    public SeasonDaoImpl() {
        try {
            this.conn = DBConnection.createConnection();
        } catch (SQLException e) {
            throw new RuntimeException("DB connection failed in SeasonDaoImpl", e);
        }
    }

    // =========================
    // Combo box helper
    // =========================
    @Override
    public List<ComboItem> getSeasonComboItems() throws SQLException {
        List<ComboItem> seasons = new ArrayList<>();
        seasons.add(new ComboItem(0, "Select Season"));

        String sql = """
            SELECT id, season_name, start_date, end_date
            FROM seasons
            ORDER BY start_date DESC
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("season_name");
                Date start = rs.getDate("start_date");
                Date end = rs.getDate("end_date");

                String label = name;
                if (start != null && end != null) {
                    label += " (" + start.toLocalDate() + " to " + end.toLocalDate() + ")";
                }

                seasons.add(new ComboItem(id, label));
            }
        }

        return seasons;
    }

    // =========================
    // Health dropdown
    // =========================
    @Override
    public List<Season> findAllForHealthDropdown() {
        List<Season> list = new ArrayList<>();

        String sql = """
            SELECT s.id, s.start_date, s.end_date,
                   f.field_code, c.common_name
            FROM seasons s
            JOIN fields f ON f.id = s.field_id
            JOIN crops  c ON c.id = s.crop_id
            ORDER BY s.start_date DESC
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Season s = new Season();
                s.setId(rs.getInt("id"));
                s.setStartDate(rs.getDate("start_date").toLocalDate());
                s.setEndDate(rs.getDate("end_date").toLocalDate());
                list.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // =========================
    // CRUD
    // =========================
    @Override
    public Season findById(int id) throws SQLException {
        String sql = """
            SELECT id, field_id, crop_id,
                   start_date, end_date,
                   expected_yield_kg, status
            FROM seasons
            WHERE id = ?
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapRow(rs) : null;
            }
        }
    }

    @Override
    public List<Season> findAll() throws SQLException {
        String sql = """
            SELECT id, field_id, crop_id,
                   start_date, end_date,
                   expected_yield_kg, status
            FROM seasons
        """;

        List<Season> list = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }
        }

        return list;
    }

    @Override
    public int insert(Season season) throws SQLException {
        String sql = """
            INSERT INTO seasons (
                field_id, crop_id,
                start_date, end_date,
                expected_yield_kg, status, created_at
            ) VALUES (?, ?, ?, ?, ?, ?, NOW())
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            fillPreparedStatement(ps, season);
            return ps.executeUpdate();
        }
    }

    @Override
    public int insertAndReturnId(Season season) throws SQLException {
        String sql = """
            INSERT INTO seasons (
                field_id, crop_id,
                start_date, end_date,
                expected_yield_kg, status, created_at
            ) VALUES (?, ?, ?, ?, ?, ?, NOW())
        """;

        try (PreparedStatement ps
                = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            fillPreparedStatement(ps, season);
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                return rs.next() ? rs.getInt(1) : 0;
            }
        }
    }

    @Override
    public int update(Season season) throws SQLException {
        String sql = """
            UPDATE seasons SET
                field_id=?,
                crop_id=?,
                start_date=?,
                end_date=?,
                expected_yield_kg=?,
                status=?
            WHERE id=?
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            fillPreparedStatement(ps, season);
            ps.setInt(7, season.getId());
            return ps.executeUpdate();
        }
    }

    @Override
    public int delete(int id) throws SQLException {
        try (PreparedStatement ps
                = conn.prepareStatement("DELETE FROM seasons WHERE id=?")) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        }
    }

    // =========================
    // Helpers
    // =========================
    private void fillPreparedStatement(PreparedStatement ps, Season s)
            throws SQLException {

        ps.setInt(1, s.getFieldId());
        ps.setInt(2, s.getCropId());
        ps.setDate(3, s.getStartDate() != null ? Date.valueOf(s.getStartDate()) : null);
        ps.setDate(4, s.getEndDate() != null ? Date.valueOf(s.getEndDate()) : null);
        ps.setDouble(5, s.getExpectedYieldKg());
        ps.setString(6, s.getStatus() != null ? s.getStatus().name() : null);
    }

    private Season mapRow(ResultSet rs) throws SQLException {
        Season s = new Season();

        s.setId(rs.getInt("id"));
        s.setFieldId(rs.getInt("field_id"));
        s.setCropId(rs.getInt("crop_id"));

        Date start = rs.getDate("start_date");
        Date end = rs.getDate("end_date");

        s.setStartDate(start != null ? start.toLocalDate() : null);
        s.setEndDate(end != null ? end.toLocalDate() : null);

        s.setExpectedYieldKg(rs.getDouble("expected_yield_kg"));

        // FIX: Handle case-insensitive and empty status from database
        String status = rs.getString("status");
        if (status != null && !status.trim().isEmpty()) {
            try {
                // Convert to uppercase to match enum values (ACTIVE, PLANNED, COMPLETED)
                s.setStatus(SeasonStatus.valueOf(status.toUpperCase().trim()));
            } catch (IllegalArgumentException ex) {
                System.err.println("⚠ Invalid status value in database: '" + status + "' - defaulting to ACTIVE");
                s.setStatus(SeasonStatus.ACTIVE); // Default to ACTIVE instead of PLANNED
            }
        } else {
            // If status is null or empty, default to ACTIVE
            s.setStatus(SeasonStatus.ACTIVE);
        }

        return s;
    }

}
