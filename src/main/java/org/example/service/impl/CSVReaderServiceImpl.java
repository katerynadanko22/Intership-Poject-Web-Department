package org.example.service.impl;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.example.dto.UserCSV;
import org.example.exception.CsvValidationException;
import org.example.service.CSVReaderService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Service
public class CSVReaderServiceImpl implements CSVReaderService {

    @Override
    public List<UserCSV> readFromCsv(MultipartFile file) {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CsvToBean<UserCSV> csvToBean =
                    new CsvToBeanBuilder(reader)
                            .withType(UserCSV.class)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();
            List<UserCSV> usersCSV = csvToBean.parse();
            return usersCSV;
        } catch (IOException e) {
            throw new CsvValidationException("An error occurred while processing the CSV file.");
        }
    }

}