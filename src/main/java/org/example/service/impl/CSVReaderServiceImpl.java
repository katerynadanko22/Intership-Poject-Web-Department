package org.example.service.impl;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.UserCSV;
import org.example.dto.UserDTORegistration;
import org.example.entity.ProjectPosition;
import org.example.entity.User;
import org.example.exception.CsvValidationException;
import org.example.modelmapper.UserMapper;
import org.example.repository.UserRepository;
import org.example.service.CSVReaderService;
import org.example.service.ProjectPositionService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CSVReaderServiceImpl implements CSVReaderService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Override
    public List<UserCSV> readFromCsv(@NonNull MultipartFile file) {
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
//    @Override
//    public List<UserCSV> readFromCsv(@NonNull MultipartFile file) {
//        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
//            CsvToBean<UserCSV> csvToBean =
//                    new CsvToBeanBuilder(reader)
//                            .withType(UserCSV.class)
//                            .withIgnoreLeadingWhiteSpace(true)
//                            .build();
//            List<UserCSV> usersCSV = csvToBean.parse();
//            List<User> users = usersCSV
//                    .stream()
//                    .map(mapper::csvToEntity)
//                    .collect(Collectors.toList());
//            userRepository.saveAll(users);
//            return usersCSV;
//        } catch (IOException e) {
//            throw new CsvValidationException("An error occurred while processing the CSV file.");
//        }
//    }
    public String getName(MultipartFile file){
        return file.getOriginalFilename();
    }

}