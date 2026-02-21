package controller;

import dao.ReportsDao;
import dao.impl.ReportsDaoImpl;
import model.Harvest;

import java.util.Date;
import java.util.List;

public class ReportsController {

    private final ReportsDao reportsDao;

    public ReportsController() {
        this.reportsDao = new ReportsDaoImpl();
    }

    public List<Harvest> getReportData(Date dateFrom, Date dateTo, String cropName) {

        if (dateFrom == null || dateTo == null) {
            System.out.println("Date range is required.");
            return null;
        }

        if (dateFrom.after(dateTo)) {
            System.out.println("Date From must be before Date To.");
            return null;
        }

        if (cropName == null || cropName.equals("Select Crop")) {
            System.out.println("Please select a crop.");
            return null;
        }

        // Fetch and return data
        return reportsDao.getHarvestReport(dateFrom, dateTo, cropName);
    }
}
