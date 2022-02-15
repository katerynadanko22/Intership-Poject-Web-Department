package org.example.service.impl;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.example.dto.UserCSV;
import org.example.dto.UserDTORegistration;
import org.example.exception.CsvValidationException;
import org.example.modelmapper.UserMapper;
import org.example.repository.UserRepository;
import org.example.service.CSVReaderService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CSVReaderServiceImpl implements CSVReaderService {

    @Override
    public List<UserDTORegistration> readFromCsv(@NonNull MultipartFile file) {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CsvToBean<UserDTORegistration> csvToBean =
                    new CsvToBeanBuilder(reader)
                            .withType(UserDTORegistration.class)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();
            List<UserDTORegistration> usersCSV = csvToBean.parse();
            return usersCSV;
        } catch (IOException e) {
            throw new CsvValidationException("An error occurred while processing the CSV file.");
        }
    }
    public String getName(MultipartFile file){
        return file.getOriginalFilename();
    }

}