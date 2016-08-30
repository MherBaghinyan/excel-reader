package com.university.main;

import com.university.entity.Person;
import com.university.repository.BeanConfiguration;
import com.university.repository.PersonRepository;
import com.university.util.ExcelReader;
import com.university.util.WordReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.io.*;
import java.util.List;

/**
 * http://www.pretechsol.com/2013/11/springdata-jpa-with-mysql-example.html#.VnlFwvl96Uk
 */
public class CSVRunner {

    private static final String userHome = System.getProperty("user.home");
    private static final String userDir = System.getProperty("user.dir");


    public CSVRunner() {
    }

    public static void main(String[] args) {

//        AbstractApplicationContext context = new AnnotationConfigApplicationContext(
//                BeanConfiguration.class);
//        PersonRepository personRepository = context
//                .getBean(PersonRepository.class);
//
//
//
//        ExcelReader.readProfessorDataFromExcel("C:\\Users\\Gebruiker\\Desktop\\word-list\\board_.xlsx", personRepository);
//
//        ((AbstractApplicationContext) context).close();

//        WordReader.readWordDocument("C:\\Users\\Gebruiker\\Desktop\\word-list\\BoardEx Report - Dec 22- 2015- 16 12 21.docx");
        String path = "D:\\excel\\acne-questionnaire.xlsx";

        List<String> duplicates = ExcelReader.readDuplicateEmailsFromExcel(path);

//        ExcelReader.removeDuplicatesAndValidateExcel(path, duplicates);
    }

    private static String selectDirectory(String path) {

        // try current folder first
        String fullPath = userDir + path;
        File file = new File(fullPath);
        if (!file.exists()) {
            System.out.println("File: " + fullPath + " does not exist, switching to User Home folder.");
            // now try user home folder
            fullPath = userHome + path;
            file = new File(fullPath);
            if (!file.exists()) {
                System.out.println("File: " + fullPath + " does not exist");
                return null;
            }
        }

        System.out.println("The selected folder is : " + fullPath);
        return fullPath;
    }




}
