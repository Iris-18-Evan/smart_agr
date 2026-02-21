/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class Season implements Serializable {
    private int id;
    private int fieldId;
    private int cropId;
    private int farmerId;
    private LocalDate startDate;
    private LocalDate endDate;
    private double expectedYieldKg;
    private SeasonStatus status;  // stored as VARCHAR in DB
    private String notes;

    public Season() {}

    public Season(int id, int fieldId, int cropId, int farmerId,
                  LocalDate startDate, LocalDate endDate,
                  double expectedYieldKg, SeasonStatus status, String notes) {
        this.id = id;
        this.fieldId = fieldId;
        this.cropId = cropId;
        this.farmerId = farmerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.expectedYieldKg = expectedYieldKg;
        this.status = status;
        this.notes = notes;
    }

    public Season(int id, int fieldId, int cropId, int farmerId,
                  Date startDateSql, Date endDateSql,
                  double expectedYieldKg, SeasonStatus status, String notes) {
        this(id, fieldId, cropId, farmerId,
             toLocalDate(startDateSql), toLocalDate(endDateSql),
             expectedYieldKg, status, notes);
    }

    // Getters/setters…

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getFieldId() { return fieldId; }
    public void setFieldId(int fieldId) { this.fieldId = fieldId; }
    public int getCropId() { return cropId; }
    public void setCropId(int cropId) { this.cropId = cropId; }
    public int getFarmerId() { return farmerId; }
    public void setFarmerId(int farmerId) { this.farmerId = farmerId; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public double getExpectedYieldKg() { return expectedYieldKg; }
    public void setExpectedYieldKg(double expectedYieldKg) { this.expectedYieldKg = expectedYieldKg; }
    public SeasonStatus getStatus() { return status; }
    public void setStatus(SeasonStatus status) { this.status = status; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

   
    public static Date toSqlDate(LocalDate d) { return (d != null) ? Date.valueOf(d) : null; }
    public static LocalDate toLocalDate(Date d) { return (d != null) ? d.toLocalDate() : null; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Season)) return false;
        Season season = (Season) o;
        return id == season.id &&
               fieldId == season.fieldId &&
               cropId == season.cropId &&
               farmerId == season.farmerId &&
               Double.compare(season.expectedYieldKg, expectedYieldKg) == 0 &&
               Objects.equals(startDate, season.startDate) &&
               Objects.equals(endDate, season.endDate) &&
               status == season.status &&
               Objects.equals(notes, season.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fieldId, cropId, farmerId, startDate, endDate, expectedYieldKg, status, notes);
    }

    @Override
    public String toString() {
        return "Season{" +
                "id=" + id +
                ", fieldId=" + fieldId +
                ", cropId=" + cropId +
                ", farmerId=" + farmerId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", expectedYieldKg=" + expectedYieldKg +
                ", status=" + status +
                ", notes='" + notes + '\'' +
                '}';
    }
}

