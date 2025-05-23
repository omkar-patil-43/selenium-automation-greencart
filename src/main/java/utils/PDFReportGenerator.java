package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

public class PDFReportGenerator {

    private static List<String[]> steps = new ArrayList<String[]>();

    public static void logStep(String stepDesc, String status, String screenshotPath) {
        steps.add(new String[]{stepDesc, status, screenshotPath});
    }

    public static void generatePDFReport(String pdfPath) throws Exception {
        // Create folders if they don't exist
        File pdfFile = new File(pdfPath);
        File parentDir = pdfFile.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
        document.open();

        // Title
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
        Paragraph title = new Paragraph("Automation Test Report", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        document.add(new Paragraph("Generated: " + new java.util.Date()));
        document.add(new Paragraph("\n"));

        for (String[] step : steps) {
            document.add(new Paragraph("Step: " + step[0]));
            document.add(new Paragraph("Status: " + step[1]));

            if (step[2] != null && new File(step[2]).exists()) {
                try {
                    Image img = Image.getInstance(step[2]);
                    img.scaleToFit(500, 300);
                    document.add(img);
                } catch (Exception e) {
                    document.add(new Paragraph("Unable to load screenshot."));
                }
            } else {
                document.add(new Paragraph("Screenshot not available."));
            }

            document.add(new Paragraph("\n"));
        }

        document.close();
        System.out.println("PDF report generated at: " + pdfPath);
    }
}
