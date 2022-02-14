package org.example.service;

import org.example.dto.UserCSV;
import org.example.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CSVReaderService {
    List<UserCSV> readFromCsv(MultipartFile file);
    String getName(MultipartFile file);
}
