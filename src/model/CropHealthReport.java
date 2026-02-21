/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;

public class CropHealthReport implements Serializable {

    private int id;
    private int seasonId;
    private LocalDate reportDate;
    private String diseaseType;
    private String pestIssues;
    private String fertilizerUsed;
    private HealthStatus status;
    private int reportedBy; 
    private String notes;

    public CropHealthReport() {
    }

    public CropHealthReport(int id, int seasonId, LocalDate reportDate,
            String diseaseType, String pestIssues, String fertilizerUsed,
            HealthStatus status, int reportedBy, String notes) {
        this.id = id;
        this.seasonId = seasonId;
        this.reportDate = reportDate;
        this.diseaseType = diseaseType;
        this.pestIssues = pestIssues;
        this.fertilizerUsed = fertilizerUsed;
        this.status = status;
        this.reportedBy = reportedBy;
        this.notes = notes;
    }

    public int getId() { return id;}
    
    public void setId(int id) {this.id = id;}
    
    public int getSeasonId() {return seasonId;}
    
    public void setSeasonId(int seasonId) {this.seasonId = seasonId;}
    
    public LocalDate getReportDate() {return reportDate;}
    
    public void setReportDate(LocalDate reportDate) {this.reportDate = reportDate;}
    
    public String getDiseaseType() {return diseaseType;}
    
    public void setDiseaseType(String diseaseType) {this.diseaseType = diseaseType;}
    
    public String getPestIssues() {return pestIssues;}
    
    public void setPestIssues(String pestIssues) {this.pestIssues = pestIssues;}
    
    public String getFertilizerUsed() {return fertilizerUsed;}
    
    public void setFertilizerUsed(String fertilizerUsed) {this.fertilizerUsed = fertilizerUsed;}
    
    public HealthStatus getStatus() {return status;}
    
    public void setStatus(HealthStatus status) {this.status = status;}
    
    public int getReportedBy() {return reportedBy;}
    
    public void setReportedBy(int reportedBy) {this.reportedBy = reportedBy;}
    
    public String getNotes() {return notes;}
    
    public void setNotes(String notes) {this.notes = notes;}

}
