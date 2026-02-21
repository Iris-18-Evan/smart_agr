package dao.impl;

import dao.UserDao;
import db.DBConnection;
import model.Role;
import model.User;

import java.sql.*;

public class UserDaoImpl implements UserDao {

    @Override
    public User findByEmail(String email) throws SQLException {
        final String sql =
                "SELECT u.id, u.full_name, u.email, u.phone, u.password_hash, " +
                "       u.role_id, u.is_active, r.role_name " +
                "FROM users u " +
                "LEFT JOIN roles r ON r.id = u.role_id " +
                "WHERE u.email = ? LIMIT 1";

        try (Connection conn = DBConnection.createConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapRow(rs) : null;
            }
        }
    }

    @Override
    public User authenticate(String email, String password) throws SQLException {
        final String sql =
                "SELECT u.id, u.full_name, u.email, u.phone, u.password_hash, " +
                "       u.role_id, u.is_active, r.role_name " +
                "FROM users u " +
                "LEFT JOIN roles r ON r.id = u.role_id " +
                "WHERE u.email = ? LIMIT 1";

        try (Connection conn = DBConnection.createConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }

                // must be active
                if (rs.getInt("is_active") != 1) return null;

                // NOTE: Plain-text compare for now (replace with hash-to-hash when ready)
                String stored = rs.getString("password_hash");
                if (stored == null || !stored.trim().equals(password.trim())) return null;

                return mapRow(rs);
            }
        }
    }

    /**
     * Maps a ResultSet row to a User.
     * We also override the role LABEL for the Admin account (by email),
     * without changing role_id or the database.
     */
    private User mapRow(ResultSet rs) throws SQLException {
        int id          = rs.getInt("id");
        String fullName = rs.getString("full_name");
        String email    = rs.getString("email");
        String phone    = rs.getString("phone");
        int roleId      = rs.getInt("role_id");
        String roleName = rs.getString("role_name");
        boolean active  = rs.getInt("is_active") == 1;

        if (roleName == null || roleName.isBlank()) {
            roleName = "UNKNOWN";
        }

        // ---- DEBUG (remove after you verify) ----
        System.out.printf("[DEBUG] mapRow BEFORE override: id=%d, fullName=%s, email=%s, roleId=%d, roleName=%s%n",
                id, fullName, email, roleId, roleName);

        // ✅ Override ONLY for your Admin account (use email for uniqueness)
        // TODO: put your real Admin email here:
        if ("admin@test.com".equalsIgnoreCase(email)) {
            roleName = "ADMIN";
        }

        // ---- DEBUG (remove after you verify) ----
        System.out.printf("[DEBUG] mapRow AFTER override : id=%d, roleName=%s%n", id, roleName);

        Role role = new Role(roleId, roleName);
        return new User(id, fullName, email, phone, role, active);
    }
}