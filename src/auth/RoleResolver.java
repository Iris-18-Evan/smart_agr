/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auth;

/**
 *
 * @author ucwaz
 */
import model.Role;
import model.User;

import java.util.Map;

public final class RoleResolver {
    private RoleResolver() {}

    // Normalize keys to lower-case
    private static final Map<String, String> OVERRIDES = Map.of(
        "admin@test.com",   "ADMIN",
        "farmer@test.com",  "FARMER",
        "officer@test.com", "OFFICER",
        "buyer@test.com",   "BUYER"
    );

    public static Role effectiveRole(User u) {
        if (u == null) return new Role(0, "UNKNOWN");
        String emailKey = u.getEmail() == null ? "" : u.getEmail().trim().toLowerCase();
        String forced = OVERRIDES.get(emailKey);
        if (forced != null) {
            // keep roleId to avoid breaking code that reads it, but label becomes forced
            int id = (u.getRole() == null) ? 0 : u.getRole().getId();
            return new Role(id, forced);
        }
        return (u.getRole() == null) ? new Role(0, "UNKNOWN") : u.getRole();
    }
}

