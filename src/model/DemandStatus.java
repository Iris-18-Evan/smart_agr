package model;

public enum DemandStatus {
    OPEN, PARTIAL, CLOSED, CANCELLED;

    public static DemandStatus fromDb(String s) {
        if (s == null || s.isBlank()) return null;
        s = s.trim().toUpperCase();
        if ("CANCELED".equals(s)) s = "CANCELLED";
        try {
            return valueOf(s);
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }

    public String toDb() {
        String lower = name().toLowerCase();
        return Character.toUpperCase(lower.charAt(0)) + lower.substring(1);
    }
}