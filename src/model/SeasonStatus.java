/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

public enum SeasonStatus {
    PLANNED, ACTIVE, COMPLETED, PAUSED, CANCELLED;

   
    public static SeasonStatus fromDb(String dbValue) {
        if (dbValue == null || dbValue.isBlank()) return null;
        switch (dbValue.trim().toLowerCase()) {
            case "planned":   return PLANNED;
            case "active":    return ACTIVE;
            case "completed": return COMPLETED;
            case "paused":    return PAUSED;
            case "cancelled": return CANCELLED;
            default:          return null; // or PLANNED as a default
        }
    }

    
    public String toDb() {
        switch (this) {
            case PLANNED:   return "Planned";
            case ACTIVE:    return "Active";
            case COMPLETED: return "Completed";
            case PAUSED:    return "Paused";
            case CANCELLED: return "Cancelled";
            default:        return null;
        }
    }
}
