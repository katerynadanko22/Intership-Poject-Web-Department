package org.example.facade;

import lombok.RequiredArgsConstructor;
import org.example.dto.UserCSV;
import org.example.entity.User;
import org.example.modelmapper.UserMapper;
import org.example.repository.UserRepository;
import org.example.service.CSVReaderService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CSVReaderFacade {
    private final CSVReaderService csvReaderService;
    private final UserRepository userRepository;
    private final UserMapper mapper;

    public  List<UserCSV> readFromCsv(MultipartFile file){
            List<UserCSV> usersCSV =csvReaderService.readFromCsv(file);
            List<User> users = usersCSV
                    .stream()
                    .map(mapper::csvToEntity)
                    .collect(Collectors.toList());
             userRepository.saveAll(users);
        return usersCSV;
    }
}
