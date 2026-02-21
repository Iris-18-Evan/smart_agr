package controller;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import model.User;
import view.LoginView;
import view.DashboardView;        // Admin/Farmer/Officer dashboard
import view.BuyerDashboardView;  // Buyer dashboard

import javax.swing.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class LoginController {

    private final LoginView view;
    private final UserDao userDao;

    // ========= Add this override map (email -> role name to FORCE) =========
    // Put your REAL emails here (lower-case). You can add/remove as needed.
    private static final Map<String, String> ROLE_OVERRIDES = new HashMap<>();
    static {
        ROLE_OVERRIDES.put("admin@test.com",   "ADMIN");
        ROLE_OVERRIDES.put("farmer@test.com",  "FARMER");
        ROLE_OVERRIDES.put("officer@test.com", "OFFICER");
        ROLE_OVERRIDES.put("buyer@test.com",   "BUYER");
    }

    public LoginController(LoginView view) {
        this.view = view;
        this.userDao = new UserDaoImpl();
        bind();
    }

    private void bind() {
        // Attach ONE clean listener (trim inputs)
        view.addSignInListener(e -> handleLogin(view.getEmail().trim(), view.getPassword().trim()));
        System.out.println("[DEBUG] SignIn listener count = " + view.getSignInListenerCount());
    }

    private void handleLogin(String email, String password) {
        System.out.println("[DEBUG] handleLogin entered: email='" + email + "', pwdLen=" + (password == null ? 0 : password.length()));

        // Basic validation
        if (email == null || email.isBlank() || !email.contains("@")) {
            view.setErrorMessage("Enter a valid email address.");
            return;
        }
        if (password == null || password.isBlank()) {
            view.setErrorMessage("Password cannot be empty.");
            return;
        }

        // Background thread for DB work
        new Thread(() -> {
            try {
                User user = userDao.authenticate(email, password);

                SwingUtilities.invokeLater(() -> {
                    if (user == null) {
                        view.setErrorMessage("Invalid credentials or inactive account.");
                        return;
                    }

                    // ===== Compute EFFECTIVE role name (override by email if configured) =====
                    String effectiveRole = computeEffectiveRoleName(user);
                    Integer roleId = (user.getRole() != null) ? user.getRole().getId() : null;

                    System.out.println("[DEBUG] dbRole=" +
                            ((user.getRole() == null) ? "null" : user.getRole().getName()) +
                            ", roleId=" + roleId +
                            ", effectiveRole=" + effectiveRole);

                    // Welcome dialog shows the EFFECTIVE role
                    JOptionPane.showMessageDialog(
                            view,
                            "Welcome " + user.getFullName() + (effectiveRole != null ? " (" + effectiveRole + ")" : ""),
                            "Login Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    // ===== Route by EFFECTIVE role name (not role_id) =====
                    if (effectiveRole == null || effectiveRole.isBlank() || "UNKNOWN".equalsIgnoreCase(effectiveRole)) {
                        JOptionPane.showMessageDialog(
                                view,
                                "Your account role could not be determined. Contact administrator.",
                                "Access Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }

                    switch (effectiveRole) {
                        case "BUYER" -> {
                            BuyerDashboardView bdv = new BuyerDashboardView();
                            new BuyerDashboardController(bdv, user, this::showLoginAgain);
                            bdv.setLocationRelativeTo(null);
                            bdv.setVisible(true);
                            view.dispose();
                        }
                        // Admin, Farmer, Officer -> Admin-style dashboard (your original behavior)
                        case "ADMIN", "FARMER", "OFFICER" -> {
                            DashboardView adv = new DashboardView();
                            new DashboardController(adv, user);
                            adv.setLocationRelativeTo(null);
                            adv.setVisible(true);
                            view.dispose();
                        }
                        default -> {
                            JOptionPane.showMessageDialog(
                                    view,
                                    "Unknown role: " + effectiveRole + ". Contact administrator.",
                                    "Access Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    }
                });

            } catch (SQLException ex) {
                ex.printStackTrace();
                SwingUtilities.invokeLater(() ->
                        view.setErrorMessage("Database error. Please try again.")
                );
            }
        }).start();
    }

    // Re-open Login screen (used by Buyer after submit)
    private void showLoginAgain() {
        SwingUtilities.invokeLater(() -> {
            LoginView lv = new LoginView();
            new LoginController(lv);  // re-bind controller
            lv.setLocationRelativeTo(null);
            lv.setVisible(true);
        });
    }

    // ================== Helpers ==================

    /**
     * Returns the effective role name for the user:
     * 1) If the user's email is in the override map, use that role name.
     * 2) Otherwise, use the DB-provided role name (upper-cased).
     * 3) If nothing found, returns "UNKNOWN".
     */
    private String computeEffectiveRoleName(User user) {
        if (user == null) return "UNKNOWN";

        String email = user.getEmail();
        String dbRole = (user.getRole() != null && user.getRole().getName() != null)
                ? user.getRole().getName().trim().toUpperCase()
                : "UNKNOWN";

        String key = (email == null) ? "" : email.trim().toLowerCase();
        String forced = ROLE_OVERRIDES.get(key);
        String effective = (forced != null) ? forced : dbRole;

        // Optional: keep behavior consistent elsewhere by storing back into the model
        // (just the label, NOT the id)
        if (user.getRole() != null) {
            user.getRole().setName(effective); // requires a setter on Role; omit if Role is immutable/enum
        }

        return effective;
    }
}