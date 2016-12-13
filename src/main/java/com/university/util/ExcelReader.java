package com.university.util;

import com.university.entity.Person;
import com.university.repository.PersonRepository;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * http://mxtoolbox.com/SuperToolX.aspx
 */
public class ExcelReader {

    public static List<String> readDuplicateEmailsFromExcel(String path){

        List<String> cleanedEmails = new ArrayList<String>();
        Map<String, String> clients = new HashMap<String, String>();
        Map<String, Integer> remains = new HashMap<String, Integer>();

        try {
            FileInputStream fis = new FileInputStream(path);
            XSSFWorkbook workBook = new XSSFWorkbook(fis);
            Sheet sheet = workBook.getSheetAt(0);

            int first = sheet.getFirstRowNum() + 3;
            int last = sheet.getLastRowNum();

            // B = 995
            // A = 3373
            // C = 2024
            DataFormatter df = new DataFormatter();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            try {

                for (int j = first; j <= 1873 - 1; j++) {
                    XSSFRow row_j = (XSSFRow) sheet.getRow(j);
                    XSSFCell cell_client = row_j.getCell(3);
                    if(cell_client != null && df.formatCellValue(cell_client) != "") {
                        XSSFCell cell_date = row_j.getCell(0);
                        String clientName = df.formatCellValue(cell_client);
                        String purchaseDate = df.formatCellValue(cell_date);

                        System.out.println(" - " + j + " - " + clientName);
                        if (clients.get(clientName) == null) {
                            clients.put(clientName, purchaseDate);
                            remains.put(clientName, j);
                        } else {
                            LocalDate lastDate = LocalDate.parse(clients.get(clientName), format);
                            LocalDate current = LocalDate.parse(purchaseDate, format);
                            if(current.isAfter(lastDate)) {
                                clients.put(clientName, purchaseDate);
                                remains.put(clientName, j);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
//
//            try {
//
//                ArrayList<Integer> remained = new ArrayList<Integer>(remains.values());
//                for (int j = first; j <= 2145 - 1; j++) {
//                    XSSFRow row_j = (XSSFRow) sheet.getRow(j);
//                    XSSFCell cell_client = row_j.getCell(3);
//                    XSSFCell colored_client = row_j.createCell(9);
//                    if (cell_client != null && df.formatCellValue(cell_client) != "") {
//                        String clientName = df.formatCellValue(cell_client);
//                        if (remained.contains(j)) {
//                            colored_client.setCellValue("keep");
//                        }
//                    }
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

            try {

                for (int j = first; j <= 1873 - 1; j++) {
                    XSSFRow row_j = (XSSFRow) sheet.getRow(j);
                    XSSFCell colored_client = row_j.createCell(9);
                    if (df.formatCellValue(colored_client) != "keep") {
                        sheet.removeRow(row_j);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            fis.close();

            // update excel file
            FileOutputStream outFile = new FileOutputStream(new File(path));
            workBook.write(outFile);
            outFile.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return cleanedEmails;
    }
    public static List<String> readProfessorDataFromExcel(String path, PersonRepository personRepository){

        List<String> duplicateEmails = new ArrayList<String>();

        try {
            XSSFWorkbook workBook = new XSSFWorkbook(new FileInputStream(path));
            Sheet sheet = workBook.getSheetAt(0);

            int first = sheet.getFirstRowNum() + 18;
            int last = sheet.getLastRowNum();

            DataFormatter df = new DataFormatter();
            for(int i = first; i < last; i++){
                XSSFRow row = (XSSFRow) sheet.getRow(i);
                XSSFCell cell_0 = row.getCell(0);
                String organization = df.formatCellValue(cell_0);
                System.out.println(organization);
                XSSFCell cell_1 = row.getCell(1);
                String org_type = df.formatCellValue(cell_1);
                System.out.println(org_type);
                XSSFCell cell_2 = row.getCell(2);
                String individual = df.formatCellValue(cell_2);
                System.out.println(individual);
                XSSFCell cell_3 = row.getCell(3);
                String org_current = df.formatCellValue(cell_3);
                System.out.println(org_current);
                XSSFCell cell_4 = row.getCell(4);
                String org_duration = df.formatCellValue(cell_4);
                System.out.println(org_duration);
                XSSFCell cell_5 = row.getCell(5);
                String org_role = df.formatCellValue(cell_5);
                System.out.println(org_role);
                XSSFCell cell_6 = row.getCell(6);
                String org_role_start = df.formatCellValue(cell_6);
                System.out.println(org_role_start);
                XSSFCell cell_7 = row.getCell(7);
                String org_role_end = df.formatCellValue(cell_7);
                System.out.println(org_role_end);
                Person person = new Person();
                person.setName(individual);
                person.setNationality("");
                Person saved = personRepository.save(person);
                System.out.println(person);
            }

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return duplicateEmails;
    }

    public static List<String> removeDuplicatesAndValidateExcel(String path, List<String> getDuplicateEmails){

        List<String> removedEmails = new ArrayList<String>();

        try {
            FileInputStream fis = new FileInputStream(path);
            XSSFWorkbook workBook = new XSSFWorkbook(fis);

            OwnEmailValidator validator = new OwnEmailValidator();

            for (int i = 0; i < workBook.getNumberOfSheets(); i++) {
                Sheet sheet = workBook.getSheetAt(i);

                int first = sheet.getFirstRowNum();
                int last = sheet.getLastRowNum();

                DataFormatter df = new DataFormatter();
                for(int j = first + 1; j < last; j++){

                    XSSFRow row = (XSSFRow) sheet.getRow(j);
                    XSSFCell cell_10 = row.getCell(10);
                    String value10 = df.formatCellValue(cell_10);

                    removeCellData(value10, cell_10, getDuplicateEmails, validator, removedEmails);

                    XSSFCell cell_11 = row.getCell(11);
                    String value11 = df.formatCellValue(cell_11);

                    removeCellData(value11, cell_11, getDuplicateEmails, validator, removedEmails);

                }
            }

            fis.close();

            // update excel file
            FileOutputStream outFile =new FileOutputStream(new File(path));
            workBook.write(outFile);
            outFile.close();

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return removedEmails;
    }

    private static void removeCellData(String value, XSSFCell cell, List<String> getDuplicateEmails, OwnEmailValidator validator, List<String> removedEmails) {
        if(getDuplicateEmails.contains(value) && !value.equals("")) {
            //System.out.println(value);
            cell.setCellValue("");
            removedEmails.add(value);
        } else if((!validator.validate(value) || !validator.isValidEmailAddress(value)) && !value.equals("")) {
            cell.setCellValue("");
            System.out.println(value);
        }
    }

}
