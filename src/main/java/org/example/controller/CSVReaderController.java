package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.UserCSV;
import org.example.dto.UserDTORegistration;
//import org.example.facade.CSVReaderFacade;
import org.example.service.CSVReaderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api(value = "Read users from CSV file", description = "REST Apis related to User Entity")
@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("api/users")
public class CSVReaderController {

    private final CSVReaderService csvReaderService;
//    private final CSVReaderFacade csvReaderFacade;

    @ApiOperation(value = "Read users from CSV file", response = UserCSV.class, tags = "users List From CSV")
    @PreAuthorize("hasAnyAuthority('read')")
    @PostMapping(value = "/upload/csv")
    private List<UserDTORegistration> readUsersFromCSV(@RequestParam("file") MultipartFile file) {
        return csvReaderService.readFromCsv(file);
    }
    @GetMapping(value = "/name")
    private String getName(@RequestParam("file") MultipartFile file) {
        return csvReaderService.getName(file);
    }
}