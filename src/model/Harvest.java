/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Harvest implements Serializable {
    private int id;
    private int seasonId;
    private LocalDate harvestDate;
    private double actualYieldKg;
    private QualityGrade qualityGrade;
    private double availableQtyKg;
    private String notes;

    public Harvest() {}

    public Harvest(int id, int seasonId, LocalDate harvestDate,
                   double actualYieldKg, QualityGrade qualityGrade,
                   double availableQtyKg, String notes) {
        this.id = id;
        this.seasonId = seasonId;
        this.harvestDate = harvestDate;
        this.actualYieldKg = actualYieldKg;
        this.qualityGrade = qualityGrade;
        this.availableQtyKg = availableQtyKg;
        this.notes = notes;
    }

        public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public LocalDate getHarvestDate() {
        return harvestDate;
    }

    public void setHarvestDate(LocalDate harvestDate) {
        this.harvestDate = harvestDate;
    }

    public double getActualYieldKg() {
        return actualYieldKg;
    }

    public void setActualYieldKg(double actualYieldKg) {
        this.actualYieldKg = actualYieldKg;
    }

    public QualityGrade getQualityGrade() {
        return qualityGrade;
    }

    public void setQualityGrade(QualityGrade qualityGrade) {
        this.qualityGrade = qualityGrade;
    }

    public double getAvailableQtyKg() {
        return availableQtyKg;
    }

    public void setAvailableQtyKg(double availableQtyKg) {
        this.availableQtyKg = availableQtyKg;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}

