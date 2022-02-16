package org.example.facade;

import lombok.RequiredArgsConstructor;
import org.example.dto.UserCSV;
import org.example.modelmapper.UserMapper;
import org.example.repository.UserRepository;
import org.example.service.CSVReaderService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CSVReaderFacade {
    private final CSVReaderService csvReaderService;
    private final PasswordEncoder passwordEncoder;

    public List<UserCSV> readFromCsv(MultipartFile file) {
        List<UserCSV> usersCSV = csvReaderService.readFromCsv(file);
        for (UserCSV user : usersCSV) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return usersCSV;
    }
}
