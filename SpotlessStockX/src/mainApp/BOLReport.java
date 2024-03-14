// This class is responsible for generating a Bill of Lading (BOL) report based on the customer details of a selected site.



package mainApp;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class BOLReport {
    private ViewSites viewSites;
    private Scanner scanner;

    public BOLReport() {
       this.viewSites = new ViewSites(null);
       this.scanner = new Scanner(System.in);
    }

    public void export() {
        try {
        	viewSites.DisplaySites();
        	
        	
            // Retrieve customer details based on the selected site ID
            Customer customer = viewSites.getCustomerDetails();

            // Load BOL template document
            File templateFile = new File("bol_template.docx");
            FileInputStream templateStream = new FileInputStream(templateFile);
            XWPFDocument document = new XWPFDocument(templateStream);

            // Replace placeholders with customer details
            replacePlaceholder(document, "CustomerName", customer.getName());
            replacePlaceholder(document, "Address", customer.getAddress());
            replacePlaceholder(document, "PhoneNumber", customer.getPhoneNumber());

            // Save the modified document as the final BOL report
            File outputFile = new File("bol_report_" + siteId + ".docx");
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            document.write(outputStream);
            outputStream.close();

            System.out.println("BOL report generated successfully for site ID: " + siteId);
        } catch (IOException e) {
            System.out.println("Error exporting BOL report: " + e.getMessage());
        }
    }

    private void replacePlaceholder(XWPFDocument document, String placeholder, String value) {
        for (XWPFParagraph paragraph : document.getParagraphs()) {
            List<XWPFRun> runs = paragraph.getRuns();
            for (XWPFRun run : runs) {
                String text = run.getText(0);
                if (text != null && text.contains(placeholder)) {
                    run.setText(text.replace(placeholder, value), 0);
                }
            }
        }
    }
}