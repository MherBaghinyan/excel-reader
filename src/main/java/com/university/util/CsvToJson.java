package com.university.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.json.JSONObject;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Gebruiker on 12/13/2016.
 */
public class CsvToJson {

    public static void convert() throws IOException {

        String destFileName = "D:\\epam\\mismatches_input.csv";
        String outputFileName = "D:\\epam\\mismatches_output.json";

        File sourceFolderFile = new File(destFileName);
        //StringUtils.endsWithIgnoreCase(name, ".csv");


        FileWriter out = new FileWriter(outputFileName);
        CSVPrinter csvPrinter = null;

        String[] headers = null;

        try (CSVParser csvParser = new CSVParser(new FileReader(sourceFolderFile), CSVFormat.RFC4180.withHeader())) {
            if (csvPrinter == null) {
                headers = csvParser.getHeaderMap().keySet().toArray(new String[0]);
                csvPrinter = CSVFormat.DEFAULT.withHeader(headers)
                        .print(out);
            }

            String[] values = new String[headers.length];

            for (CSVRecord record : csvParser) {

                JSONObject jsonOutput = new JSONObject();

                for (int i = 0; i < headers.length; i++) {
                    String header = headers[i];
                    if (record.isMapped(header)) {
                        values[i] = record.get(header);
                        jsonOutput.append(header, values[i]);
                    } else {
                        values[i] = "";
                    }
                }

                csvPrinter.printRecord(jsonOutput);
            }

        }

        if (csvPrinter != null) {
            csvPrinter.close();
        }

    }
}
