// This class is responsible for generating a Bill of Lading (BOL) report based on the customer details of a selected site.



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class BOLReport {
    private ViewSites viewSites;
    private Scanner scanner;

    public BOLReport() {
       this.viewSites = new ViewSites(null); // Assuming null parameter is placeholder
       this.scanner = new Scanner(System.in);
    }

    public void export(int siteId) {
        try {
            // Retrieve customer details based on the selected site ID
            viewSites.DisplaySites();

            // Load Excel template workbook
            FileInputStream inputStream = new FileInputStream(new File("resources/BOL Template.xlsx"));
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            // Populate Excel template with customer details
            Row row = sheet.getRow(2); // Assuming customer details start from row 3
            row.getCell(1).setCellValue(customer.getName());
            row = sheet.getRow(3); // Assuming address details are in row 4
            row.getCell(1).setCellValue(customer.getAddress());
            row = sheet.getRow(4); // Assuming phone number details are in row 5
            row.getCell(1).setCellValue(customer.getPhoneNumber());

            // Save the modified workbook as the final BOL report
            FileOutputStream outputStream = new FileOutputStream(new File("bol_report_" + siteId + ".xlsx"));
            workbook.write(outputStream);
            outputStream.close();

            System.out.println("BOL report generated successfully for site ID: " + siteId);
        } catch (IOException e) {
            System.out.println("Error exporting BOL report: " + e.getMessage());
        }
    }
}