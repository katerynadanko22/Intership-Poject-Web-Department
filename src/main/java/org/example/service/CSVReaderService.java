package org.example.service;

import org.example.dto.UserCSV;
import org.example.dto.UserDTORegistration;
import org.example.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CSVReaderService {
    List<UserDTORegistration> readFromCsv(MultipartFile file);
    String getName(MultipartFile file);
}
