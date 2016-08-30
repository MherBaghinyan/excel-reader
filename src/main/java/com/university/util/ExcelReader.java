package com.university.util;

import com.university.entity.Person;
import com.university.repository.PersonRepository;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * http://mxtoolbox.com/SuperToolX.aspx
 */
public class ExcelReader {

    public static List<String> readDuplicateEmailsFromExcel(String path){

        List<String> cleanedEmails = new ArrayList<String>();
        List<String> b = new ArrayList<String>();

        try {
            FileInputStream fis = new FileInputStream(path);
            XSSFWorkbook workBook = new XSSFWorkbook(fis);
            Sheet sheet = workBook.getSheetAt(0);

            int first = sheet.getFirstRowNum() + 1;
            int last = sheet.getLastRowNum();

            // B = 995
            // A = 3373
            // C = 2024
            DataFormatter df = new DataFormatter();

            try {
                for (int j = first; j <= 2023; j++) {
                    XSSFRow row_j = (XSSFRow) sheet.getRow(j);
                    XSSFCell cell_1 = row_j.getCell(3);
                    System.out.println(" - " + j + " - " + df.formatCellValue(cell_1));
                    if (!b.contains(df.formatCellValue(cell_1))) {
                        b.add(df.formatCellValue(cell_1).toLowerCase());
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            if(b.contains("nancylynnealbertson@gmail.com") || b.contains("marne@pixerati.com")) {
                System.out.println("");
            }

            try {
            for(int i = first; i < 223; i++){
                XSSFRow row_i = (XSSFRow) sheet.getRow(i);
                XSSFCell cell_0 = row_i.getCell(0);
                String value_0 = df.formatCellValue(cell_0);
                if (b.contains(value_0.toLowerCase())) {
                    cell_0.setCellValue("");//879
                } else if(!cleanedEmails.contains(value_0.toLowerCase())) {
                    cleanedEmails.add(value_0.toLowerCase()); //3373 - 879
                }

            }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            for(int i = first; i < cleanedEmails.size(); i++) {
                XSSFRow row = (XSSFRow) sheet.getRow(i);
                row.createCell(4).setCellValue(cleanedEmails.get(i));
            }

            fis.close();

            // update excel file
            FileOutputStream outFile =new FileOutputStream(new File(path));
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
