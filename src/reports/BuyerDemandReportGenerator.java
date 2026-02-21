package reports;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.WindowConstants;
import java.io.File;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuyerDemandReportGenerator {

    // =========================
    // DATA BEAN
    // =========================
    public static class BuyerDemandData {
        private Integer id;
        private String buyerName;
        private String cropName;
        private Double quantityKg;
        private String qualityGrade;
        private Date neededBy;
        private String status;
        private String notes;

        public BuyerDemandData(Integer id, String buyerName, String cropName,
                              Double quantityKg, String qualityGrade,
                              Date neededBy, String status, String notes) {
            this.id = id;
            this.buyerName = buyerName;
            this.cropName = cropName;
            this.quantityKg = quantityKg;
            this.qualityGrade = qualityGrade;
            this.neededBy = neededBy;
            this.status = status;
            this.notes = notes;
        }

        public Integer getId() { return id; }
        public String getBuyerName() { return buyerName; }
        public String getCropName() { return cropName; }
        public Double getQuantityKg() { return quantityKg; }
        public String getQualityGrade() { return qualityGrade; }
        public Date getNeededBy() { return neededBy; }
        public String getStatus() { return status; }
        public String getNotes() { return notes; }
    }

    // =========================
    // HELPER: FIND JRXML FILE
    // =========================
    private static String findJRXMLPath() throws Exception {
        // Try multiple possible locations
        String[] paths = {
            "src/reports/BuyerDemandsReport.jrxml",
            "C:/smart_agr/src/reports/BuyerDemandsReport.jrxml",
            "build/classes/reports/BuyerDemandsReport.jrxml",
            "C:/smart_agr/build/classes/reports/BuyerDemandsReport.jrxml"
        };

        for (String path : paths) {
            File file = new File(path);
            if (file.exists()) {
                System.out.println("✅ Found JRXML at: " + file.getAbsolutePath());
                return file.getAbsolutePath();
            }
        }

        // Not found in any location
        throw new Exception(
            "❌ BuyerDemandsReport.jrxml NOT found! Tried locations:\n" +
            "  - src/reports/BuyerDemandsReport.jrxml\n" +
            "  - C:/smart_agr/src/reports/BuyerDemandsReport.jrxml\n" +
            "  - build/classes/reports/BuyerDemandsReport.jrxml\n" +
            "Please ensure the file exists in one of these locations."
        );
    }

    // =========================
    // VIEW REPORT
    // =========================
    public static void generateReport(List<BuyerDemandData> demandDataList) throws Exception {

        System.out.println("=== Generating Buyer Demands Report ===");

        // Find JRXML file
        String jrxmlPath = findJRXMLPath();

        // Compile the report
        JasperReport jasperReport =
                JasperCompileManager.compileReport(jrxmlPath);

        JRBeanCollectionDataSource dataSource =
                new JRBeanCollectionDataSource(demandDataList);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("REPORT_TITLE", "Buyer Demands Report");
        parameters.put("GENERATED_BY", "Smart Agriculture System");

        JasperPrint jasperPrint =
                JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        JasperViewer viewer = new JasperViewer(jasperPrint, false);
        viewer.setTitle("Buyer Demands Report");

        // ✅ CRITICAL FIX (prevents Ant build failure + file lock)
        viewer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        viewer.setVisible(true);

        System.out.println("=== Report Generated Successfully ===");
        System.out.println("Total demands: " + demandDataList.size());
    }

    // =========================
    // EXPORT PDF
    // =========================
    public static void generatePDF(List<BuyerDemandData> demandDataList, String outputPath)
            throws Exception {

        System.out.println("=== Generating Buyer Demands PDF ===");

        // Find JRXML file
        String jrxmlPath = findJRXMLPath();

        // Compile the report
        JasperReport jasperReport =
                JasperCompileManager.compileReport(jrxmlPath);

        JRBeanCollectionDataSource dataSource =
                new JRBeanCollectionDataSource(demandDataList);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("REPORT_TITLE", "Buyer Demands Report");
        parameters.put("GENERATED_BY", "Smart Agriculture System");

        JasperPrint jasperPrint =
                JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        JasperExportManager.exportReportToPdfFile(jasperPrint, outputPath);

        System.out.println("=== PDF Generated Successfully ===");
        System.out.println("Saved to: " + outputPath);
    }
}