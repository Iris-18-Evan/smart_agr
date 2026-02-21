/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public enum QualityGrade {
    A, B, C, ANY;

    public static QualityGrade fromDb(String s) {
        if (s == null || s.isBlank()) return null;
        return valueOf(s.trim().toUpperCase());
    }

    public String toDb() {
        return this == ANY ? "ANY" : name();
    }
}

