/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.Date;
import java.util.List;
import model.Harvest;

public interface ReportsDao {

    List<Harvest> getHarvestReport(Date dateFrom, Date dateTo, String cropName);
}

