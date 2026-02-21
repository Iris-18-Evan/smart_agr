/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;

public class BuyerDemand implements Serializable {
    private int id;
    private int buyerId;
    private int cropId;
    private double requestedQtyKg;
    private QualityGrade qualityPref; // or allow null for Any
    private LocalDate neededByDate;
    private DemandStatus status;
    private String notes;

    public BuyerDemand() {}

    public BuyerDemand(int id, int buyerId, int cropId, double requestedQtyKg,
                       QualityGrade qualityPref, LocalDate neededByDate,
                       DemandStatus status, String notes) {
        this.id = id;
        this.buyerId = buyerId;
        this.cropId = cropId;
        this.requestedQtyKg = requestedQtyKg;
        this.qualityPref = qualityPref;
        this.neededByDate = neededByDate;
        this.status = status;
        this.notes = notes;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public int getCropId() {
        return cropId;
    }

    public void setCropId(int cropId) {
        this.cropId = cropId;
    }

    public double getRequestedQtyKg() {
        return requestedQtyKg;
    }

    public void setRequestedQtyKg(double requestedQtyKg) {
        this.requestedQtyKg = requestedQtyKg;
    }

    public QualityGrade getQualityPref() {
        return qualityPref;
    }

    public void setQualityPref(QualityGrade qualityPref) {
        this.qualityPref = qualityPref;
    }

    public LocalDate getNeededByDate() {
        return neededByDate;
    }

    public void setNeededByDate(LocalDate neededByDate) {
        this.neededByDate = neededByDate;
    }

    public DemandStatus getStatus() {
        return status;
    }

    public void setStatus(DemandStatus status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
